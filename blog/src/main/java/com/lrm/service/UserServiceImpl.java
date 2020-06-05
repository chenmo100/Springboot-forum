package com.lrm.service;

import com.lrm.dao.UserRepository;
import com.lrm.po.User;
import com.lrm.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    @Override
    public void addUser(String avatar, String email, String nickname, String username, String password) {
        User user = new User();

        user.setAvatar(avatar);
        user.setEmail(email);
        user.setCreateTime(new Date());
        user.setNickname(nickname);
        user.setUsername(username);
        user.setUpdateTime(new Date());
        user.setType(1);
        user.setPassword(MD5Utils.code(password));


        userRepository.save(user);
    }
}
