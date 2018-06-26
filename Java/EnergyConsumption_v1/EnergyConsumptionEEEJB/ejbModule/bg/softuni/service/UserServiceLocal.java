package bg.softuni.service;

import java.util.List;

import javax.ejb.Local;

@Local
public interface UserServiceLocal<T> {

    List<T> allUsersUnpaidBillCounter();

    List<T> findAllUsers();

    T loginUser(String aUserName, String aPassword);
}