package org.lxc.mall.service;

import java.util.ArrayList;
import java.util.List;

import org.lxc.mall.api.goods.IGoodsService;
import org.lxc.mall.api.supplier.ISupplierService;
import org.lxc.mall.common.utils.time.TimeFormatter;
import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.dao.GoodsMapper;
import org.lxc.mall.dao.GoodsPhotoMapper;
import org.lxc.mall.dao.GoodsSpecificationMapper;
import org.lxc.mall.model.Goods;
import org.lxc.mall.model.GoodsPhoto;
import org.lxc.mall.model.GoodsSpecification;
import org.lxc.mall.model.Supplier;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.GoodsQueryCondition;
import org.lxc.mall.model.request.GoodsWriteCondition;
import org.lxc.mall.model.response.GoodsPhoto_DTO;
import org.lxc.mall.model.response.Goods_DTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Service
@Transactional
public class GoodsService implements IGoodsService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GoodsMapper goodsDao;
	
	@Autowired
	private GoodsPhotoMapper goodsPhotoDao;
	
	@Autowired
	private GoodsSpecificationMapper goodsSpecificationDao;
	
	@Autowired
	private ISupplierService supplierService;
	
	@Override
	public PaginationInfo<Goods_DTO> queryByCondition(GoodsQueryCondition query) {
		PaginationInfo<Goods_DTO> page = new PaginationInfo<>();
		Page<Goods> p = PageHelper.startPage(query.pageNo, query.pageSize, true);
		List<Goods> goods = goodsDao.selectAll();
		page.setItems(buildGoodsDTOs(goods));
		page.setTotal(p.getTotal());
		page.setPageNo(query.pageNo);
		page.setPageSize(query.pageSize);
		return page;
	}

	@Override
	public Goods_DTO queryById(Long id) {
		Goods g = goodsDao.selectByPrimaryKey(id);
		if ( null == g ) {
			return null;
		}
		
		List<GoodsSpecification> goodsSpecifications = goodsSpecificationDao.selectByGoodsId(id);
		Supplier s = supplierService.queryById(g.getSupplierId());
		Goods_DTO dto = installGoodsDTO(g);
		dto.setSpecifications(goodsSpecifications);
		if (s != null) {
			dto.setSupplierName(s.getName());
		}
		return dto;
	}

	@Override
	public Long add(GoodsWriteCondition query) throws Exception{
		Goods g = query.parseModel();
		try {
			int affected = goodsDao.insertSelective(g);
			if (affected == 0) {
				throw new ProcessException("新增商品失败");
			}
		}catch (Exception e) {
			e.printStackTrace();
			ProcessException.throwExeptionByFormat("新增商品 %s 失败", g.getName());
		}
		bindPictures2Goods(g.getId(), query.getPictureIds());
		try {
			for (GoodsSpecification spec : query.parseGoodsSpecifications(g.getId())) {
				goodsSpecificationDao.insert(spec);
			}
		}catch (Exception e) {
			e.printStackTrace();
			ProcessException.throwExeptionByFormat("新增商品 %s 添加规格失败:", g.getName());
		}
		
		return g.getId();
	}

	@Override
	public Long update(GoodsWriteCondition query) throws Exception{
		Goods g = query.parseModel();
		try {
			int affected = goodsDao.updateByPrimaryKeySelective(g);
			if (affected == 0) {
				throw new ProcessException("更新商品信息失败");
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new ProcessException("更新商品信息失败");
		}
		bindPictures2Goods(query.getId(), query.getPictureIds());
		try {
			List<GoodsSpecification> gsfs = goodsSpecificationDao.selectByGoodsId(query.getId());
			for (GoodsSpecification gsf : gsfs) {
				boolean delFlag = true;
				for (GoodsSpecification spec : query.parseGoodsSpecifications(g.getId())) {
					if (spec.getId() == null || spec.getId() == 0  ) {
						goodsSpecificationDao.insert(spec);
					}else {
						goodsSpecificationDao.updateByPrimaryKeySelective(spec);
					}
					
					if (gsf.getId() == spec.getId()) {
						delFlag = false;
					}
				}
				// 如果找不到提交列表中包含有已经存在的规格定义，则删除
				if (delFlag) {
				    goodsSpecificationDao.deleteByPrimaryKey(gsf.getId());
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			ProcessException.throwExeptionByFormat("新增商品 %s 添加规格失败:", g.getName());
		}
		return g.getId();
	}
	
	@Override
	public List<GoodsPhoto_DTO> getPictures(Long goodsId) {
		List<GoodsPhoto> gps = goodsPhotoDao.selectByGoodsId(goodsId);
		return buildGoodsPhotoDTOs(gps);
	}

	@Override
	public GoodsPhoto_DTO savePictures(String name, String httpPath) {
		GoodsPhoto gp = new GoodsPhoto();
		gp.setName(name);
		gp.setPath(httpPath);
		try {
			goodsPhotoDao.insertSelective(gp);
			return installGoodsPhotoDTO(gp);
		}catch (Exception e) {
			e.printStackTrace();
			throw new ProcessException("上传图片%s异常",name);
		}
	}
	
	@Override
	public int deletePicture(Long pictureId) throws Exception {
		try {
			return goodsPhotoDao.deleteByPrimaryKey(pictureId);
		}catch (Exception e) {
			e.printStackTrace();
			ProcessException.throwExeptionByFormat("删除商品图片%d异常",pictureId);
		}
		return 0;
	}
	
	@Override
	public void bindPictures2Goods(Long goodsId, List<Long> pictureIds) throws Exception {
		try {
			if (pictureIds == null || pictureIds.isEmpty()) {
				logger.info("-- No pictures to be bound to goods %d --",goodsId);
				return;
			}
			List<GoodsPhoto> gps = goodsPhotoDao.selectByGoodsId(goodsId);
			pictureIds.stream().filter((id) -> {
				if (gps == null || gps.isEmpty()) {
					return true;
				}
				for (GoodsPhoto gp : gps) {
					if (gp.getId().compareTo(id) != 0) {
						return true;
					}
				}
				return false;
			}).forEach((id) -> {
				GoodsPhoto model = new GoodsPhoto();
				model.setGoodsId(goodsId);
				model.setId(id);
				goodsPhotoDao.updateGoodsIdByPrimaryKey(model);
			});
			
		}catch (Exception e) {
			e.printStackTrace();
			ProcessException.throwExeptionByFormat("图片绑定商品%s 异常",goodsId);
		}
		
	}
	
	private List<Goods_DTO> buildGoodsDTOs(List<Goods> goods) {
		if (goods == null || goods.isEmpty()) {
			return null;
		}
		List<Goods_DTO> dtos = new ArrayList<>();
		for (Goods good : goods) {
			dtos.add(installGoodsDTO(good));
		}
		return dtos;
	}
	
	private Goods_DTO installGoodsDTO(Goods good) {
		if (good == null) {
			return null;
		}
		Goods_DTO dto = new Goods_DTO();
		dto.setId(good.getId());
		dto.setName(good.getName());
		dto.setRetailPrice(good.getRetailPrice());
		dto.setStatus(good.getStatus());
		dto.setProducingArea(good.getProducingArea());
		dto.setSupplierId(good.getSupplierId());
		dto.setDescription(good.getDescription());
		dto.setBriefDescription(good.getBriefDescription());
		dto.setCategory(good.getCategoryId());
		dto.setCreateTime(TimeFormatter.formatDefault(good.getCreateTime()));
		dto.setUpdateTime(TimeFormatter.formatDefault(good.getUpdateTime()));
		dto.setListPicUrl(good.getListPicUrl());
		return dto;
	}
	
	private List<GoodsPhoto_DTO> buildGoodsPhotoDTOs(List<GoodsPhoto> gps) {
		if (gps == null || gps.isEmpty()) {
			return null;
		}
		List<GoodsPhoto_DTO> dtos = new ArrayList<>();
		for (GoodsPhoto gp : gps) {
			dtos.add(installGoodsPhotoDTO(gp));
		}
		return dtos;
	}
	
	private GoodsPhoto_DTO installGoodsPhotoDTO(GoodsPhoto gp) {
		GoodsPhoto_DTO dto = new GoodsPhoto_DTO();
		dto.setId(gp.getId());
		dto.setName(gp.getName());
		dto.setPath(gp.getPath());
		dto.setDisplayOrder(gp.getDisplayOrder());
		return dto;
	}

}
