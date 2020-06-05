package com.lrm.service;

import com.lrm.po.User;


public interface UserService {

    User checkUser(String username, String password);

    void addUser(String avatar,String email,String nickname,String username,String password);
}
