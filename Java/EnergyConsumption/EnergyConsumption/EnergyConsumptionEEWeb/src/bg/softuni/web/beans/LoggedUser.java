package bg.softuni.web.beans;

import javax.faces.context.FacesContext;

import bg.softuni.entity.model.UserModel;

/**
 * Fetch the logged user from context
 *
 */
public class LoggedUser {

	public static UserModel getLoggedUser() {
		return ((UserModel) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LOGGED_USER"));
	}
}