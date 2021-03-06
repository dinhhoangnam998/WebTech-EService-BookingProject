package webtech.gr14.service.acc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webtech.gr14.config.security.SpringSecurityUtil;
import webtech.gr14.model.Acc;
import webtech.gr14.repository.AccR;

@Service
public class AccS {

	@Autowired
	public AccR aR;

	public Acc getAcc() {
		return aR.findByUsername(SpringSecurityUtil.getUsername());
	}

}
