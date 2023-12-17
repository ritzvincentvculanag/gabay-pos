package me.jhayzonalbay.rmmcgabay.repositories;

import java.util.List;

public interface CrudRepository<T> {

    T insert (T t);
    T update (T t);
    T delete (T t);

    List<T> getAll();

}
