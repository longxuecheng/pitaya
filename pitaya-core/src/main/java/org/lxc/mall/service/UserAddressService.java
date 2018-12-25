package org.lxc.mall.service;

import java.util.List;

import org.lxc.mall.api.userAddress.IUserAddressService;
import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.dao.UserAddressMapper;
import org.lxc.mall.model.UserAddress;
import org.lxc.mall.model.request.UserAddressWriteCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAddressService implements IUserAddressService {
	
	@Autowired
	private UserAddressMapper userAddressDao;
	
	@Override
	public Integer add(UserAddressWriteCondition query) throws Exception{
		UserAddress ua = query.parseModel();
		try {
			int affected = userAddressDao.insertSelective(ua);
			if (affected == 0) {
				throw new ProcessException("新增用户地址失败");
			}
		}catch (Exception e) {
			e.printStackTrace();
			ProcessException.throwExeptionByFormat("新增用户地址 %s 失败", ua.getName());
		}
		return ua.getId();
	}

	@Override
	public List<UserAddress> queryListByUserId(Integer userId) {
		return userAddressDao.selectByUserId(userId);
	}

	@Override
	public Integer update(UserAddressWriteCondition query) throws Exception {
		UserAddress ua = query.parseModel();
		try {
			int affected = userAddressDao.updateByPrimaryKeySelective(ua);
			if (affected == 0) {
				throw new ProcessException("更新用户地址失败");
			}
		}catch (Exception e) {
			e.printStackTrace();
			ProcessException.throwExeptionByFormat("更新用户地址 %s 失败", ua.getName());
		}
		return ua.getId();
	}
	
	
}
