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

import bg.softuni.entity.model.PaidBillModel;
import bg.softuni.service.PaidBillServiceLocal;
import bg.softuni.service.UnPaidBillServiceLocal;
import bg.softuni.web.beans.Constants;
import bg.softuni.web.beans.LoggedUser;
import bg.softuni.web.beans.RedirectPath;
import bg.softuni.web.utils.MessageUtils;

@ManagedBean(name = "createPaidBill")
@ViewScoped
public class CreatePaidBillBean {

	@EJB
	PaidBillServiceLocal paidBillService;
	@EJB
	UnPaidBillServiceLocal unPaidBillService;

	private PaidBillModel paidBillModel;

	/**
	 * Inject to take user from session
	 */

	private List<Integer> months;
	private Integer currentMonth;

	@PostConstruct
	public void init() {
		/**
		 * Generate months choose list.
		 */
		months = new ArrayList<>();
		for (int i = 1; i < 13; i++) {
			months.add(i);
		}
		paidBillModel = new PaidBillModel();
	}

	/**
	 * Submit form
	 * 
	 * @return if has error throw error massage, otherway - true.
	 */
	public String submitBill() {
		if (!validate()) {
			return null;
		}
		paidBillModel.setUser(LoggedUser.getLoggedUser());

		/**
		 * If paidBill exist, throw error.
		 */
		if (paidBillService.checkPaidBillExist(paidBillModel.getUser().getId(), paidBillModel.getMonth(),
				paidBillModel.getYear()) != null) {
			MessageUtils.addErrorMessage("error.bill.exist");
			return null;
		}

		/**
		 * If unpaidBill is already paid, throw error.
		 */
		if (unPaidBillService.checkUnpaidBillExist(paidBillModel.getUser().getId(), paidBillModel.getMonth(),
				paidBillModel.getYear()) != null) {
			MessageUtils.addErrorMessage("error.bill.not.paid");
			return null;
		} else {
			/**
			 * Create paidBill in database
			 */
			paidBillModel.setSumWithTax((paidBillModel.getSumWithoutTax() * Constants.DDS_TAX));
			paidBillService.save(paidBillModel);
			return RedirectPath.PAID_BILL_REDIRECT;
		}
	}

	/**
	 * Validate create paidBill form
	 * 
	 * @return if has error throw error massage, otherway - true.
	 */
	protected boolean validate() {
		boolean hasErrors = false;
		if (paidBillModel.getMonth() == null) {
			MessageUtils.addErrorMessage("error.required.month");
			hasErrors = true;
		}
		if (paidBillModel.getYear() == null) {
			MessageUtils.addErrorMessage("error.required.year");
			hasErrors = true;
		}
		if (paidBillModel.getSumWithoutTax() == null) {
			MessageUtils.addErrorMessage("error.required.sum.without.tax");
			hasErrors = true;
		}

		if (hasErrors) {
			return false;
		}

		return true;
	}

	/**
	 * Iterate to check is there a error message
	 * 
	 * @return true - no error, false - error
	 */
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

	/**
	 * Getter and setter
	 */

	public List<Integer> getMonths() {
		return months;
	}

	public void setMonths(List<Integer> monts) {
		this.months = monts;
	}

	public Integer getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(Integer currentMonth) {
		this.currentMonth = currentMonth;
	}

	public PaidBillModel getPaidBillModel() {
		return paidBillModel;
	}

	public void setPaidBillModel(PaidBillModel paidBillModel) {
		this.paidBillModel = paidBillModel;
	}
}