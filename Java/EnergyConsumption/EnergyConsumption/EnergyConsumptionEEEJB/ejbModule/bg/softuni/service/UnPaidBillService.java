package bg.softuni.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.softuni.entity.model.UnPaidBillModel;

/**
 * Session Bean implementation class UserService
 */
@Stateless
public class UnPaidBillService implements UnPaidBillServiceLocal {

	@PersistenceContext
	protected EntityManager entityManager;

	public UnPaidBillService() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UnPaidBillModel> findAllUnPaidBills(Long id) {
		// String query = "select model from UnPaidBillModel model where
		// model.user.id = :id order by upper(model.month) asc";
		String query = "select model from UnPaidBillModel model where model.user.id = :id order by model.year, model.month asc";
		Query q = entityManager.createQuery(query);
		q.setParameter("id", id);
		return q.getResultList();
	}

	@Override
	public UnPaidBillModel findById(Long id) {
		try {
			UnPaidBillModel instance = entityManager.find(UnPaidBillModel.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public UnPaidBillModel save(UnPaidBillModel enity) {
		entityManager.persist(enity);
		return enity;
	}

	@Override
	public UnPaidBillModel update(UnPaidBillModel enity) {
		entityManager.merge(enity);
		entityManager.flush();
		return enity;
	}

	@Override
	public UnPaidBillModel checkUnpaidBillExist(Long uId, Long month, Long year) {
		StringBuffer query = new StringBuffer(
				"select model from UnPaidBillModel model where model.user.id = :id and model.month = :m and model.year = :y");
		Query q = entityManager.createQuery(query.toString());
		q.setParameter("id", uId);
		q.setParameter("m", month);
		q.setParameter("y", year);
		try {
			return (UnPaidBillModel) q.getSingleResult();
		} catch (NoResultException nre) {
			// the bill doesn't exist
			return null;
		}
	}

	@Override
	public void delete(UnPaidBillModel enity) {
		UnPaidBillModel enityForDelete = entityManager.getReference(UnPaidBillModel.class, enity.getId());
		entityManager.remove(enityForDelete);
	}
}