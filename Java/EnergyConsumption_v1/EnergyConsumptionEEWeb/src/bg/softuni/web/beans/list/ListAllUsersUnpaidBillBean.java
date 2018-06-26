package bg.softuni.web.beans.list;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bg.softuni.entity.model.UserModel;
import bg.softuni.service.UserServiceLocal;

@ManagedBean(name = "listAllUsersUnpaidBillBean")
@ViewScoped
public class ListAllUsersUnpaidBillBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    UserServiceLocal<UserModel> userService;

    @PostConstruct
    public void init() {
    }

    /**
     * Get all users with unpaid bills
     * 
     * @return list of users
     */
    public List<UserModel> getAllUsersUnpaidBills() {
	return userService.allUsersUnpaidBillCounter();
    }
}