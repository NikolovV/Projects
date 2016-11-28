package bg.softuni.service;

import java.util.List;

import javax.ejb.Local;

import bg.softuni.entity.model.UnPaidBillModel;

@Local
public interface UnPaidBillServiceLocal {

	List<UnPaidBillModel> findAllUnPaidBills(Long id);

	UnPaidBillModel findById(Long id);

	UnPaidBillModel save(UnPaidBillModel enity);

	UnPaidBillModel update(UnPaidBillModel enity);

	UnPaidBillModel checkUnpaidBillExist(Long uId, Long month, Long year);

	void delete(UnPaidBillModel enity);
}