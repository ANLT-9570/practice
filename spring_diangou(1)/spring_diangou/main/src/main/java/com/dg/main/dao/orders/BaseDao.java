package com.dg.main.dao.orders;

import java.io.Serializable;

public interface BaseDao<T> {
    T findBy(Serializable id);

    void deleteBy(Serializable id);
}
