package webtech.gr14.controller.admin.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webtech.gr14.service.admin.statistic.ReceiptS;

@Controller
@RequestMapping("/admin/manage/receipt")
public class ReceiptC {

	@Autowired
	private ReceiptS rS;
	
	@GetMapping
	public String showReceipt() {
		return "";
	}
	
}
