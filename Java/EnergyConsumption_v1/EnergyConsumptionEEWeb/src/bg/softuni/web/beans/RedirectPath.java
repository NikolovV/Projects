package bg.softuni.web.beans;

public class RedirectPath {

	/**
	 * RedirectPath constants
	 */
	public static final String LOGIN_REDIRECT = "/pages/login?faces-redirect=true";
	public static final String CREATE_PAID_BILL_REDIRECT = "/pages/createPaidBill?faces-redirect=true";
	public static final String CREATE_UNPAID_BILL_REDIRECT = "/pages/createUnpaidBill?faces-redirect=true";
	public static final String EDIT_PAID_BILL_REDIRECT = "/pages/editPaidBill?faces-redirect=true";
	public static final String EDIT_UNPAID_BILL_REDIRECT = "/pages/editUnPaidBill?faces-redirect=true";
	public static final String PAID_BILL_REDIRECT = "/pages/paidBills?faces-redirect=true";
	public static final String UNPAID_BILL_REDIRECT = "/pages/unpaidBills?faces-redirect=true";
	public static final String WELCOCME_BILL_REDIRECT = "/pages/welcome?faces-redirect=true";
	public static final String HOME_REDIRECT = "/pages/allUsersUnpaidBills?faces-redirect=true";
	public static final String CREATE_USER_REDIRECT = "/pages/createUser?faces-redirect=true";
}