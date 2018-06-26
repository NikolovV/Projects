package bg.softuni.web.beans.delete;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import bg.softuni.base.service.BaseBillLocal;
import bg.softuni.entity.model.PaidBillModel;
import bg.softuni.web.beans.RedirectPath;

@ManagedBean(name = "deletePaidBillBean")
@ViewScoped
public class DeletePaidBillBean {

    @Inject
    HttpServletRequest request;

    @EJB
    BaseBillLocal<PaidBillModel> paidBillService;

    private PaidBillModel paidBillModel;

    @PostConstruct
    public void init() {
	/**
	 * Take parameter from request
	 */
	String id = request.getParameter("id");
	if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
	    paidBillModel = paidBillService.findById(Long.valueOf(id));
	}
    }

    /**
     * Delete paidBill
     * 
     * @return redirect page
     */
    public String deleteAction() {
	paidBillService.delete(paidBillModel);
	return RedirectPath.PAID_BILL_REDIRECT;
    }

    /**
     * Getter and setter
     */
    public PaidBillModel getPaidBillModel() {
	return paidBillModel;
    }

    public void setPaidBillModel(PaidBillModel paidBillModel) {
	this.paidBillModel = paidBillModel;
    }
}