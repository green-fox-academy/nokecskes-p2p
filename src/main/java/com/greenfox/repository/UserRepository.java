package com.greenfox.repository;

import com.greenfox.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Connor on 2017.05.17..
 */
public interface UserRepository extends CrudRepository<User, Long> {

  User findById(Long id);

}
