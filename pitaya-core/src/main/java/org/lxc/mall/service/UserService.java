package org.lxc.mall.service;

import java.util.ArrayList;
import java.util.List;

import org.lxc.mall.api.user.IUserService;
import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.dao.UserMapper;
import org.lxc.mall.model.User;
import org.lxc.mall.model.common.PaginationInfo;
import org.lxc.mall.model.request.UserQueryCondition;
import org.lxc.mall.model.request.UserWriteCondition;
import org.lxc.mall.model.response.User_DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class UserService implements IUserService {
	
	@Autowired
	private UserMapper userDao;

	private List<User_DTO> buildUserDTOs(List<User> users) {
		if (users == null || users.isEmpty()) {
			return null;
		}
		List<User_DTO> dtos = new ArrayList<>();
		for (User user : users) {
			dtos.add(installUserDTO(user));
		}
		return dtos;
	}
	
	private User_DTO installUserDTO(User user) {
		if (user == null) {
			return null;
		}
		User_DTO dto = new User_DTO();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setPhoneNo(user.getPhoneNo());
		return dto;
	}
	
	@Override
	public PaginationInfo<User_DTO> queryByCondition(UserQueryCondition query) {
		PaginationInfo<User_DTO> page = new PaginationInfo<>();
		Page<User> p = PageHelper.startPage(query.pageNo, query.pageSize, true);
		List<User> users = userDao.selectAll();
		page.setItems(buildUserDTOs(users));
		page.setTotal(p.getTotal());
		page.setPageNo(query.pageNo);
		page.setPageSize(query.pageSize);
		return page;
	}
	
	@Override
	public User_DTO queryById(Integer id) {
		User user = userDao.selectByPrimaryKey(id);
		return installUserDTO(user);
	}

	@Override
	public Integer add(UserWriteCondition query) throws ProcessException, Exception {
		User u = query.parseModel();
		try {
			int affected = userDao.insertSelective(u);
			if (affected == 0) {
				throw new ProcessException("新增用户失败");
			}
		}catch (Exception e) {
			e.printStackTrace();
			ProcessException.throwExeptionByFormat("新增用户 %s 失败", u.getName());
		}
		return u.getId();
	}

	@Override
	public Integer update(UserWriteCondition query) throws ProcessException, Exception {
		User u = query.parseModel();
		try {
			int affected = userDao.updateByPrimaryKeySelective(u);
			if (affected == 0) {
				throw new ProcessException("更新用户失败");
			}
		}catch (Exception e) {
			e.printStackTrace();
			ProcessException.throwExeptionByFormat("更新用户 %s 失败", u.getName());
		}
		return u.getId();
	}
	
}