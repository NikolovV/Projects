package bg.softuni.web.beans.list;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bg.softuni.base.service.BaseBillLocal;
import bg.softuni.entity.model.UnPaidBillModel;
import bg.softuni.web.beans.LoggedUser;

@ManagedBean(name = "listUnPaidBills")
@ViewScoped
public class ListUnPaidBillsBean {

    @EJB
    BaseBillLocal<UnPaidBillModel> unPaidBillService;

    @PostConstruct
    public void init() {
    }

    public String createUnpaidBill() {
	return "/pages/createUnpaidBill";
    }

    public String editUnpaidBill() {
	return "/pages/editUnPaidBill";
    }

    public List<UnPaidBillModel> getAllBills() {
	return unPaidBillService.findAllBills(LoggedUser.getLoggedUser().getId());
    }
}