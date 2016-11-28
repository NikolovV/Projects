package bg.softuni.service;

import java.util.List;

import javax.ejb.Local;

import bg.softuni.entity.model.PaidBillModel;

@Local
public interface PaidBillServiceLocal {
	/**
	 * Return list of paid bills by user id
	 * 
	 * @param id
	 *            user ID
	 * @return list of paid bills
	 */
	List<PaidBillModel> findAllPaidBills(Long id);

	/**
	 * Find paid bill by bill id
	 * 
	 * @param id
	 *            bill id
	 * @return - on success return bill or null
	 */
	PaidBillModel findById(Long id);

	/**
	 * Save enity to database
	 * 
	 * @param enity
	 * @return
	 */
	PaidBillModel save(PaidBillModel enity);

	/**
	 * Update database enity
	 * 
	 * @param enity
	 * @return
	 */
	PaidBillModel update(PaidBillModel enity);

	/**
	 * Delete enity from database
	 * 
	 * @param enity
	 */
	void delete(PaidBillModel enity);

	/**
	 * Check bill existence.
	 * 
	 * @param uId
	 *            user id
	 * @param month
	 *            bill month
	 * @param year
	 *            bill year
	 * @return
	 */
	PaidBillModel checkPaidBillExist(Long uId, Long month, Long year);
}