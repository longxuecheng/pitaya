package org.lxc.mall.service;

import org.lxc.mall.api.admin.IAdminService;
import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.dao.AdminMapper;
import org.lxc.mall.model.Admin;
import org.lxc.mall.model.request.AdminWriteCondition;
import org.lxc.mall.model.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService{

	@Autowired
	private AdminMapper adminDao;
	
	@Override
	public Long addAdmin(AdminWriteCondition query) throws Exception{
		Admin a = query.parseModel();
		try {
			adminDao.insertSelective(a);
		}catch (Exception e) {
			e.printStackTrace();
			throw new ProcessException("新增管理员异常");
		}
		return a.getId();
	}

	@Override
	public Long updateAdmin(AdminWriteCondition query) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin queryForLogin(LoginRequest query) {
		Admin a = adminDao.selectForLogin(query.parseModel());
		return a;
	}

	@Override
	public Admin queryById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
