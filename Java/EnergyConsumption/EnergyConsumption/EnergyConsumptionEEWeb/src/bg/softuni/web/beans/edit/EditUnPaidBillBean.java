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

import bg.softuni.entity.model.UnPaidBillModel;
import bg.softuni.service.UnPaidBillServiceLocal;
import bg.softuni.web.beans.Constants;
import bg.softuni.web.beans.RedirectPath;
import bg.softuni.web.utils.MessageUtils;

@ManagedBean(name = "editUnPaidBillBean")
@ViewScoped
public class EditUnPaidBillBean {

	@Inject
	HttpServletRequest request;

	@EJB
	UnPaidBillServiceLocal unPaidBillService;

	private UnPaidBillModel unPaidBillModel;

	@PostConstruct
	public void init() {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			unPaidBillModel = unPaidBillService.findById(Long.valueOf(id));
		}
	}

	public String updateAction() {

		if (!validate()) {
			return null;
		}
		unPaidBillModel.setSumWithTax(unPaidBillModel.getSumWithoutTax() * Constants.DDS_TAX);
		unPaidBillService.update(unPaidBillModel);
		return RedirectPath.UNPAID_BILL_REDIRECT;
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

}
