package com.github.pires.example.dal.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pires.example.dal.UserService;
import com.github.pires.example.dal.entities.User;
import com.github.pires.example.dal.impl.daos.UserEntityDao;
import com.github.pires.example.dal.impl.entities.UserEntity;

/**
 * Implementation of {@link UserService} OSGi service.
 */
public class UserServiceImpl implements UserService {

  private static final Logger log = LoggerFactory.getLogger(UserService.class);

  private UserEntityDao userDao;

  public UserServiceImpl() {
  }

  public void create(User user) {
    log.info("Creating new user..");
    if (user != null) {
      UserEntity newEntity = new UserEntity();
      newEntity.setName(user.getName());
      userDao.persist(newEntity);
    }
  }

  public List<User> findAll() {
    log.info("Retrieving all persisted users..");
    final int totalUsers = userDao.count();
    if (totalUsers > 0) {
      List<UserEntity> entities = userDao.findAll();
      List<User> users = new ArrayList<>(entities.size());
      for (UserEntity entity : entities) {
        log.info("Found user with name {}", entity.getName());
        User user = new User();
        user.setName(entity.getName());
        users.add(user);
      }
      return users;
    }
    return Collections.emptyList();
  }

  public int count() {
    log.info("Counting all persisted users..");
    return userDao.count();
  }

  public UserEntityDao getUserDao() {
    return userDao;
  }

  public void setUserDao(UserEntityDao userDao) {
    this.userDao = userDao;
  }

  void activate(Map<String, ?> configuration) {
    log.info("Activating Billing service.");
  }

  void modified(Map<String, ?> configuration) {
    // TODO
  }

  void deactivate() {
    log.info("Deactivating Billing service.");
  }

}