package com.greenfox.repository;

import com.greenfox.models.UserMessage;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Connor on 2017.05.18..
 */
public interface UserMessageRepository extends CrudRepository<UserMessage, Long> {

}
