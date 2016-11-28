package bg.softuni.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.softuni.entity.model.PaidBillModel;

/**
 * Session Bean implementation class UserService
 */
@Stateless
public class PaidBillService implements PaidBillServiceLocal {

	@PersistenceContext
	protected EntityManager entityManager;

	public PaidBillService() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaidBillModel> findAllPaidBills(Long id) {
		// String query = "select model from PaidBillModel model where
		// model.user.id = :id order by upper(model.month) asc";
		String query = "select model from PaidBillModel model where model.user.id = :id order by model.year, model.month asc";
		Query q = entityManager.createQuery(query);
		q.setParameter("id", id);
		return q.getResultList();
	}

	@Override
	public PaidBillModel findById(Long id) {
		try {
			PaidBillModel instance = entityManager.find(PaidBillModel.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public PaidBillModel save(PaidBillModel enity) {
		entityManager.persist(enity);
		return enity;
	}

	@Override
	public PaidBillModel update(PaidBillModel enity) {
		entityManager.merge(enity);
		entityManager.flush();
		return enity;
	}

	@Override
	public void delete(PaidBillModel enity) {
		/**
		 * Get reference to billmodel to solve "Removing a detached instance"
		 * error.
		 */
		PaidBillModel enityForDelete = entityManager.getReference(PaidBillModel.class, enity.getId());
		entityManager.remove(enityForDelete);
	}

	@Override
	public PaidBillModel checkPaidBillExist(Long uId, Long month, Long year) {
		StringBuffer query = new StringBuffer(
				"select model from PaidBillModel model where model.user.id = :id and model.month = :m and model.year = :y");
		Query q = entityManager.createQuery(query.toString());
		q.setParameter("id", uId);
		q.setParameter("m", month);
		q.setParameter("y", year);
		try {
			return (PaidBillModel) q.getSingleResult();
		} catch (NoResultException nre) {
			// the bill doesn't exist
			return null;
		}
	}
}