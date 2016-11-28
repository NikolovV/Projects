package bg.softuni.web.beans.list;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bg.softuni.entity.model.PaidBillModel;
import bg.softuni.service.PaidBillServiceLocal;
import bg.softuni.web.beans.LoggedUser;

@ManagedBean(name = "listPaidBills")
@ViewScoped
public class ListPaidBillsBean {

	@EJB
	PaidBillServiceLocal paidBillService;

	@PostConstruct
	public void init() {
	}

	public String createPaidBill() {
		return "/pages/createPaidBill";
	}

	public String editPaidBill() {
		return "/pages/editPaidBill";
	}

	/**
	 * List of all paid bill for currently logged user
	 * 
	 * @return
	 */
	public List<PaidBillModel> getAllBills() {
		return paidBillService.findAllPaidBills(LoggedUser.getLoggedUser().getId());
	}

	/**
	 * getter and setter
	 */
}