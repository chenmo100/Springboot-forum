package com.lrm.dao;

import com.lrm.po.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {      //使用者仓库

    User findByUsernameAndPassword(String username, String password);
}
