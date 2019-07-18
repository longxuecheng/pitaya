package org.lxc.mall.service;

import java.math.BigDecimal;

import org.lxc.mall.core.exception.ProcessException;
import org.lxc.mall.dao.PaymentMapper;
import org.lxc.mall.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentService {
	
	@Autowired
	private PaymentMapper paymentDao;
	
	public void payOffline(BigDecimal amount,String saleOrderNo,Long saleOrderId) throws Exception {
		Payment p = new Payment();
		p.setSaleOrderNo(saleOrderNo);
		p.setSaleOrderId(saleOrderId);
		p.setStatus(Payment.PayStatus.Success.code());
		p.setMerchantOrderNo("TODO");
		p.setMethod(Payment.PayMethod.Offline.code());
		p.setDescription(Payment.PayMethod.Offline.desc());
		p.setAmount(amount);
		try {
			paymentDao.insertSelective(p);
		}catch (Exception e) {
			e.printStackTrace();
			ProcessException.throwExeptionByFormat("off-line pay error");
		}
		
	}
	
	public void payByWechat() {
		
	}

}
