package bg.softuni.base.service;

import javax.ejb.Local;

@Local
public interface BaseServiceLocal<T> {

    T save(T enity);

    T update(T enity);

    void delete(T enity);
}
