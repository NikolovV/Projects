package bg.softuni.web.beans.delete;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import bg.softuni.entity.model.PaidBillModel;
import bg.softuni.entity.model.UnPaidBillModel;
import bg.softuni.service.PaidBillServiceLocal;
import bg.softuni.service.UnPaidBillServiceLocal;
import bg.softuni.web.beans.RedirectPath;
import bg.softuni.web.utils.MessageUtils;

@ManagedBean(name = "deleteUnPaidBillBean")
@ViewScoped
public class DeleteUnPaidBillBean {

	@Inject
	HttpServletRequest request;

	@EJB
	UnPaidBillServiceLocal unPaidBillService;

	@EJB
	PaidBillServiceLocal paidBillService;

	private UnPaidBillModel unpaidBillModel;
	private PaidBillModel paidBillModel;

	@PostConstruct
	public void init() {
		/**
		 * Take parameter from request
		 */
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			// if true fetch unpaidBillModel
			unpaidBillModel = unPaidBillService.findById(Long.valueOf(id));
		}
		// create instance of paidBillModel
		paidBillModel = new PaidBillModel();
	}

	/**
	 * When delete unPaidBill it goes to paid bill if doesn't exist
	 * 
	 * @return null otherway
	 */
	public String deleteAction() {
		// if exist paid bill throw error
		if (paidBillService.checkPaidBillExist(unpaidBillModel.getUser().getId(), unpaidBillModel.getMonth(),
				unpaidBillModel.getYear()) != null) {
			MessageUtils.addErrorMessage("error.bill.exist");
			return null;
		} else {
			// set parameter to paidBillModel and save to database
			paidBillModel.setUser(unpaidBillModel.getUser());
			paidBillModel.setMonth(unpaidBillModel.getMonth());
			paidBillModel.setYear(unpaidBillModel.getYear());
			paidBillModel.setSumWithoutTax(unpaidBillModel.getSumWithoutTax());
			paidBillModel.setSumWithTax(unpaidBillModel.getSumWithTax());

			paidBillService.save(paidBillModel);
			unPaidBillService.delete(unpaidBillModel);
			return RedirectPath.UNPAID_BILL_REDIRECT;
		}
	}

	/**
	 * Getter and setter
	 */
	public UnPaidBillModel getUnpaidBillModel() {
		return unpaidBillModel;
	}

	public void setUnpaidBillModel(UnPaidBillModel unpaidBillModel) {
		this.unpaidBillModel = unpaidBillModel;
	}

	public PaidBillModel getPaidBillModel() {
		return paidBillModel;
	}

	public void setPaidBillModel(PaidBillModel paidBillModel) {
		this.paidBillModel = paidBillModel;
	}
}