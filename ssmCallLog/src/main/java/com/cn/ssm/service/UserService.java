package com.cn.ssm.service;


import com.cn.ssm.domain.User;

import java.util.List;

public interface UserService extends BaseService<User>{

    public void longTx();

    public void save(User u);


    public List<User> selectPage(int offset, int len);
}
