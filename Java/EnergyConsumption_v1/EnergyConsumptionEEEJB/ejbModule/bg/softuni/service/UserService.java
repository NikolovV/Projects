package bg.softuni.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.softuni.base.service.BaseServiceID;
import bg.softuni.base.service.BaseServiceLocal;
import bg.softuni.entity.model.UserModel;

/**
 * Session Bean implementation class UserService
 */
@Stateless
public class UserService implements UserServiceLocal<UserModel>, BaseServiceLocal<UserModel>, BaseServiceID<UserModel> {

    @PersistenceContext
    protected EntityManager entityManager;

    public UserService() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserModel> findAllUsers() {
	String query = "select model from UserModel model order by upper(model.username) asc";
	Query q = entityManager.createQuery(query);
	return q.getResultList();
    }

    @Override
    public UserModel findById(Long id) {
	try {
	    UserModel instance = entityManager.find(UserModel.class, id);
	    return instance;
	} catch (RuntimeException re) {
	    throw re;
	}
    }

    @Override
    public UserModel loginUser(String aUserName, String aPassword) {
	StringBuffer query = new StringBuffer(
		"select model from UserModel model where model.username = :em and model.password = :p");
	Query q = entityManager.createQuery(query.toString());
	q.setParameter("em", aUserName);
	q.setParameter("p", aPassword);
	try {
	    return (UserModel) q.getSingleResult();
	} catch (NoResultException nre) {
	    // the user doesn't exist
	    return null;
	}
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserModel> allUsersUnpaidBillCounter() {
	String query = "select new UserModel(usermodel.id, usermodel.customerId, usermodel.username, usermodel.password, usermodel.firstName, usermodel.lastName, usermodel.email, COUNT(UnPaidBillModel)) "
		+ "from UserModel usermodel left join usermodel.unPaidBills UnPaidBillModel group by usermodel.id order by upper(usermodel.username) asc";
	Query q = entityManager.createQuery(query);
	return q.getResultList();
    }

    @Override
    public UserModel save(UserModel enity) {
	entityManager.persist(enity);
	return enity;
    }

    @Override
    public UserModel update(UserModel enity) {
	entityManager.merge(enity);
	entityManager.flush();
	return enity;
    }

    @Override
    public void delete(UserModel enity) {
	entityManager.remove(enity);
    }
}