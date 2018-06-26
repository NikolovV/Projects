package bg.softuni.base.service;

import java.util.List;

import javax.ejb.Local;

@Local
public interface BaseBillLocal<T> extends BaseServiceLocal<T>, BaseServiceID<T> {
    List<T> findAllBills(Long id);

    T checkBillExist(Long uId, Long month, Long year);
}
