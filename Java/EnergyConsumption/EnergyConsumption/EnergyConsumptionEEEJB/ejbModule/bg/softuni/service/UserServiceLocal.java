package bg.softuni.service;

import java.util.List;

import javax.ejb.Local;

import bg.softuni.entity.model.UserModel;

@Local
public interface UserServiceLocal {

	List<UserModel> allUsersUnpaidBillCounter();

	List<UserModel> findAllUsers();

	UserModel findById(Long id);

	UserModel loginUser(String aUserName, String aPassword);

	UserModel save(UserModel enity);

	UserModel update(UserModel enity);

	void delete(UserModel enity);

}