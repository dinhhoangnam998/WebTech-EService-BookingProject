package webtech.gr14.controller.admin.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import webtech.gr14.model.Acc;
import webtech.gr14.model.reserve.ReserveOrder;
import webtech.gr14.service.admin.manage.GuestS;

@Controller
@RequestMapping("/admin/manage/guests")
public class GuestC {

	@Autowired
	private GuestS gS;

	@GetMapping("/bad-transactions")
	public String showListBadTransaction(Model model, @RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "10") int pS) {
		List<ReserveOrder> badTrans = gS.getBadTransactionsOfGuests(p, pS);
		model.addAttribute("trans", badTrans);
		List<Integer> pages = gS.getPageList(p, pS);
		model.addAttribute("pages", pages);
		return "/admin/manage/guest/bad-transactions";
	}

	@GetMapping("/{gid}")
	public String showGuestInfo(Model model, @PathVariable int gid) {
		Acc guestAcc = gS.aR.getOne(gid);
		model.addAttribute("guest", guestAcc);
		List<ReserveOrder> recentTrans = gS.getSomeRecentTrans(gid);
		model.addAttribute("reserveOrders", recentTrans);
		return "/admin/manage/guest/guest-info";
	}

	@ResponseBody
	@GetMapping("/{gid}/get-more-transactions")
	public List<ReserveOrder> fetchTransactions(@PathVariable int gid, @RequestParam int ith) {
		return gS.getMoreTransactions(gid, ith);
	}

	@ResponseBody
	@GetMapping("/{gid}/warning")
	public String warning(@PathVariable int gid) {
		return gS.warning(gid);
	}

	@ResponseBody
	@GetMapping("/{gid}/unwarning")
	public String unwarning(@PathVariable int gid) {
		return gS.unwarning(gid);
	}

	@ResponseBody
	@GetMapping("/{gid}/block")
	public String block(@PathVariable int gid) {
		return gS.block(gid);
	}

	@ResponseBody
	@GetMapping("/{gid}/unblock")
	public String unblock(@PathVariable int gid) {
		return gS.unblock(gid);
	}

	@ResponseBody
	@GetMapping("/bad-transactions/{tid}/checked")
	public String checked(Model model, @PathVariable int tid) {
		return gS.markChecked(tid);
	}

}
