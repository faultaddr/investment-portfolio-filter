package com.example.demo.dao;

import java.util.List;

/**
 * Created by panyu on 2017/6/9.
 */
public abstract class Dao<T> {
    public abstract boolean save(T u);

    public abstract boolean delete(T u);

    public abstract boolean update(T u);

    public abstract List cursor(T u, String s, Class aclass)throws Exception;

}
