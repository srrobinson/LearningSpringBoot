package com.github.srrobinson.dao;

import com.github.srrobinson.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by srrobinson.
 * Code is for learning purposes only and is not guaranteed in any way.
 */

@Transactional
public interface UserDAO extends CrudRepository<User, Integer> {


}
