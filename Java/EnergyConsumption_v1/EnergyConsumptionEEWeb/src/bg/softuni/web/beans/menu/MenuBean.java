package bg.softuni.web.beans.menu;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import bg.softuni.web.beans.RedirectPath;

/**
 * Control the navigation menu of logged user
 *
 */
@ManagedBean(name = "menuBean")
@RequestScoped
public class MenuBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
	}

	public String toCreatePaidBills() {
		return RedirectPath.CREATE_PAID_BILL_REDIRECT;
	}

	public String toCreateUnPaidBills() {
		return RedirectPath.CREATE_UNPAID_BILL_REDIRECT;
	}

	public String toPaidBills() {
		return RedirectPath.PAID_BILL_REDIRECT;
	}

	public String toUnpaidBills() {
		return RedirectPath.UNPAID_BILL_REDIRECT;
	}

	public String toCreateUser() {
		return RedirectPath.CREATE_USER_REDIRECT;
	}

	public String toHome() {
		return RedirectPath.HOME_REDIRECT;
	}
}