package bg.softuni.web.beans.create;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import bg.softuni.base.service.BaseBillLocal;
import bg.softuni.entity.model.PaidBillModel;
import bg.softuni.entity.model.UnPaidBillModel;
import bg.softuni.web.beans.Constants;
import bg.softuni.web.beans.LoggedUser;
import bg.softuni.web.beans.RedirectPath;
import bg.softuni.web.utils.MessageUtils;

@ManagedBean(name = "createUnPaidBill")
@ViewScoped
public class CreateUnPaidBillBean {

    @EJB
    BaseBillLocal<UnPaidBillModel> unPaidBillService;

    @EJB
    BaseBillLocal<PaidBillModel> paidBillService;

    private UnPaidBillModel unPaidBillModel;

    private List<Integer> months;
    private Integer currentMonth;

    @PostConstruct
    public void init() {
	months = new ArrayList<>();

	for (int i = 1; i < 13; i++) {
	    months.add(i);
	}
	unPaidBillModel = new UnPaidBillModel();
    }

    public String submitBill() {
	if (!validate()) {
	    return null;
	}
	unPaidBillModel.setUser(LoggedUser.getLoggedUser());

	/**
	 * If bill exist, throw error.
	 */
	if (unPaidBillService.checkBillExist(unPaidBillModel.getUser().getId(), unPaidBillModel.getMonth(),
		unPaidBillModel.getYear()) != null) {
	    MessageUtils.addErrorMessage("error.bill.exist");
	    return null;
	}
	/**
	 * If exist unpaid bill, throw error.
	 */
	if (paidBillService.checkBillExist(unPaidBillModel.getUser().getId(), unPaidBillModel.getMonth(),
		unPaidBillModel.getYear()) != null) {
	    MessageUtils.addErrorMessage("error.bill.paid");
	    return null;
	} else {
	    unPaidBillModel.setSumWithTax((unPaidBillModel.getSumWithoutTax() * Constants.DDS_TAX));
	    unPaidBillService.save(unPaidBillModel);
	    return RedirectPath.UNPAID_BILL_REDIRECT;
	}
    }

    protected boolean validate() {
	boolean hasErrors = false;
	if (unPaidBillModel.getMonth() == null) {
	    MessageUtils.addErrorMessage("error.required.month");
	    hasErrors = true;
	}
	if (unPaidBillModel.getYear() == null) {
	    MessageUtils.addErrorMessage("error.required.year");
	    hasErrors = true;
	}
	if (unPaidBillModel.getSumWithoutTax() == null) {
	    MessageUtils.addErrorMessage("error.required.sum.without.tax");
	    hasErrors = true;
	}

	if (hasErrors) {
	    return false;
	}

	return true;
    }

    public boolean hasErrors() {
	Iterator<FacesMessage> messages = FacesContext.getCurrentInstance().getMessages();
	for (; messages.hasNext();) {
	    FacesMessage message = messages.next();
	    if (message.getSeverity().compareTo(FacesMessage.SEVERITY_ERROR) == 0) {
		return true;
	    }
	}
	return false;
    }

    public UnPaidBillModel getUnPaidBillModel() {
	return unPaidBillModel;
    }

    public void setUnPaidBillModel(UnPaidBillModel unPaidBillModel) {
	this.unPaidBillModel = unPaidBillModel;
    }

    public List<Integer> getMonths() {
	return months;
    }

    public void setMonths(List<Integer> months) {
	this.months = months;
    }

    public Integer getCurrentMonth() {
	return currentMonth;
    }

    public void setCurrentMonth(Integer currentMonth) {
	this.currentMonth = currentMonth;
    }
}
