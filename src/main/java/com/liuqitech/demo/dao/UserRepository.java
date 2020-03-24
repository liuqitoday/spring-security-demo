package com.liuqitech.demo.dao;

import com.liuqitech.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  /**
   * 根据用户名查找用户
   * @param username 用户名
   * @return 用户
   */
  User findByUsername(String username);
}
