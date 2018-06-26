package bg.softuni.web.beans.create;

import java.util.Iterator;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import bg.softuni.base.service.BaseServiceLocal;
import bg.softuni.entity.model.UserModel;
import bg.softuni.web.beans.RedirectPath;
import bg.softuni.web.utils.GeneralUtils;
import bg.softuni.web.utils.MessageUtils;

@ManagedBean(name = "createUserBean")
@ViewScoped
public class CreateUserBean {

    @Inject
    HttpServletRequest request;

    @EJB
    BaseServiceLocal<UserModel> userService;

    private UserModel user;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
	    + "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" + "(\\.[A-Za-z]{2,})$";
    private Pattern pattern;

    @PostConstruct
    public void init() {
	user = new UserModel();
    }

    public String createAction() {
	if (!validate()) {
	    return null;
	}
	String cryptedPassword = GeneralUtils.encodeSha256Password(user.getPassword());
	user.setPassword(cryptedPassword);
	userService.save(user);
	return RedirectPath.WELCOCME_BILL_REDIRECT;
    }

    public UserModel getUser() {
	return user;
    }

    public void setUser(UserModel user) {
	this.user = user;
    }

    protected boolean validate() {
	boolean hasErrors = false;
	if (StringUtils.isBlank(user.getUsername())) {
	    MessageUtils.addErrorMessage("error.required.username");
	    hasErrors = true;
	}

	if (StringUtils.isBlank(user.getPassword())) {
	    MessageUtils.addErrorMessage("error.required.password");
	    hasErrors = true;
	}

	if (StringUtils.isBlank(user.getFirstName())) {
	    MessageUtils.addErrorMessage("error.required.firstname");
	    hasErrors = true;
	}

	if (StringUtils.isBlank(user.getLastName())) {
	    MessageUtils.addErrorMessage("error.required.lastname");
	    hasErrors = true;
	}

	pattern = Pattern.compile(EMAIL_PATTERN);
	if (StringUtils.isNotBlank(user.getEmail()) && !pattern.matcher(user.getEmail()).matches()) {
	    MessageUtils.addErrorMessage("error.invalid.email");
	    hasErrors = true;
	}

	if (hasErrors) {
	    return false;
	}

	return true;
    }

    /**
     * Verifies if a error messages are present in the context
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
}