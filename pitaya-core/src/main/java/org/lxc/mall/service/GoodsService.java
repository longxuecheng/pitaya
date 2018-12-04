package org.lxc.mall.service;

import java.util.ArrayList;
import java.util.List;

import org.lxc.mall.api.goods.IGoodsService;
import org.lxc.mall.api.supplier.ISupplierService;
import org.lxc.mall.common.utils.time.TimeFormatter;
import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.dao.GoodsMapper;
import org.lxc.mall.dao.GoodsPhotoMapper;
import org.lxc.mall.model.Goods;
import org.lxc.mall.model.GoodsPhoto;
import org.lxc.mall.model.Supplier;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.GoodsQueryCondition;
import org.lxc.mall.model.request.GoodsWriteCondition;
import org.lxc.mall.model.response.GoodsPhoto_DTO;
import org.lxc.mall.model.response.Goods_DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class GoodsService implements IGoodsService {
	
	// HTTP prefix for static file resource
	private String imagePathPrefix = "http://localhost:8080/images/";
	
	@Autowired
	private GoodsMapper goodsDao;
	
	@Autowired
	private GoodsPhotoMapper goodsPhotoDao;
	
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
		Supplier s = supplierService.queryById(id);
		Goods_DTO dto = installGoodsDTO(g);
		dto.setSupplierName(s.getName());
		return dto;
	}

	@Override
	public Long add(GoodsWriteCondition query) throws ProcessException{
		Goods g = query.parseModel();
		try {
			int affected = goodsDao.insertSelective(g);
			if (affected == 0) {
				throw new ProcessException("新增商品失败");
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new ProcessException("新增商品失败");
		}
		return g.getId();
	}

	@Override
	public Long update(GoodsWriteCondition query) throws ProcessException{
		Goods g = query.parseModel();
		try {
			int affected = goodsDao.updateByPrimaryKey(g);
			if (affected == 0) {
				throw new ProcessException("更新商品信息失败");
			}
		}catch (Exception e) {
			throw new ProcessException("更新商品信息失败");
		}
		return g.getId();
	}
	
	@Override
	public List<GoodsPhoto_DTO> getPictures(Long goodsId) {
		List<GoodsPhoto> gps = goodsPhotoDao.selectByGoodsId(goodsId);
		return buildGoodsPhotoDTOs(gps);
	}

	@Override
	public void savePictures(Long goodsId,String name, String path) {
		GoodsPhoto gp = new GoodsPhoto();
		String httpPath = imagePathPrefix+path;
		gp.setName(name);
		gp.setPath(httpPath);
		gp.setGoodsId(goodsId);
		try {
			goodsPhotoDao.insertSelective(gp);
		}catch (Exception e) {
			e.printStackTrace();
			throw new ProcessException("上传图片%s异常",name);
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
		dto.setProducingArea(good.getProducingArea());
		dto.setSupplierId(good.getSupplierId());
		dto.setCategory(good.getCategory());
		dto.setCreateTime(TimeFormatter.formatDefault(good.getCreateTime()));
		dto.setUpdateTime(TimeFormatter.formatDefault(good.getUpdateTime()));
		return dto;
	}
	
	private List<GoodsPhoto_DTO> buildGoodsPhotoDTOs(List<GoodsPhoto> gps) {
		if (gps == null || gps.isEmpty()) {
			return null;
		}
		List<GoodsPhoto_DTO> dtos = new ArrayList<>();
		for (GoodsPhoto gp : gps) {
			GoodsPhoto_DTO dto = new GoodsPhoto_DTO();
			dto.setId(gp.getId());
			dto.setName(gp.getName());
			dto.setPath(gp.getPath());
			dto.setDisplayOrder(gp.getDisplayOrder());
			dtos.add(dto);
		}
		return dtos;
	}

}
