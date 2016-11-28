package bg.softuni.web.beans.edit;

import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import bg.softuni.entity.model.PaidBillModel;
import bg.softuni.service.PaidBillServiceLocal;
import bg.softuni.web.beans.Constants;
import bg.softuni.web.beans.RedirectPath;
import bg.softuni.web.utils.MessageUtils;

@ManagedBean(name = "editPaidBillBean")
@ViewScoped
public class EditPaidBillBean {

	@Inject
	HttpServletRequest request;

	@EJB
	PaidBillServiceLocal paidBillService;

	private PaidBillModel paidBillModel;

	@PostConstruct
	public void init() {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			// if true fetch paidBillModel
			paidBillModel = paidBillService.findById(Long.valueOf(id));
		}
	}

	/**
	 * Update database
	 */
	public String updateAction() {

		if (!validate()) {
			return null;
		}
		// perform calculation
		paidBillModel.setSumWithTax(paidBillModel.getSumWithoutTax() * Constants.DDS_TAX);
		paidBillService.update(paidBillModel);
		return RedirectPath.PAID_BILL_REDIRECT;
	}

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
	 * getter and setter
	 */
	public PaidBillModel getPaidBillModel() {
		return paidBillModel;
	}

	public void setPaidBillModel(PaidBillModel paidBillModel) {
		this.paidBillModel = paidBillModel;
	}
}