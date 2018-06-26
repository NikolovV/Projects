package bg.softuni.base.service;

import javax.ejb.Local;

@Local
public interface BaseServiceID<T> {
    T findById(Long id);
}
