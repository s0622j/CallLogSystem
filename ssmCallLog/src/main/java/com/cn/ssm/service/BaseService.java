package com.cn.ssm.service;

import java.util.List;

public interface BaseService<T> {
    public void insert(T t);
    public void update(T t);
    public void delete(Integer id);
    public T selectOne(Integer id);
    public List<T> selectAll();

    /**
     * 分页查询
     */
    public List<T> selectPage(int offset, int len) ;

    public int selectCount();
}
