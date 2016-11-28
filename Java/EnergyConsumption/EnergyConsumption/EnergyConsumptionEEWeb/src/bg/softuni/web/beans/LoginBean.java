package bg.softuni.web.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import bg.softuni.entity.model.UserModel;
import bg.softuni.service.UserServiceLocal;
import bg.softuni.web.utils.GeneralUtils;
import bg.softuni.web.utils.MessageUtils;

/**
 * Control the login for users
 */
@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// beans.xml need to be added in WEB-INF in order CDI to work
	@Inject
	private HttpServletRequest request;

	@EJB
	UserServiceLocal userService;
	private String username;
	private String password;

	@PostConstruct
	public void init() {
	}

	/**
	 * Implement application login logic
	 * 
	 * @return
	 */
	public String login() {
		// Encrypt the password
		String encryptedPass = GeneralUtils.encodeSha256Password(password);
		// Check fetch user from database if exist
		UserModel user = userService.loginUser(username, encryptedPass);

		if (null == user) {
			// error if wrong parameters
			MessageUtils.addErrorMessage("login.error.invalid.credentials");
			return "";
		} else {
			// if successes put user in session
			request.getSession().setAttribute("LOGGED_USER", user);
			return RedirectPath.WELCOCME_BILL_REDIRECT;
		}
	}

	/**
	 * Implement application logout logic
	 * 
	 * @return
	 */
	public String logout() {
		request.getSession().invalidate();
		return RedirectPath.HOME_REDIRECT;
	}

	public String toLoginPage() {
		return RedirectPath.LOGIN_REDIRECT;
	}

	/**
	 * getter and setter
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}