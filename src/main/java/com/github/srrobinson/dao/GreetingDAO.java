package com.github.srrobinson.dao;

import com.github.srrobinson.model.Greeting;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by srrobinson.
 * Code is for learning purposes only and is not guaranteed in any way.
 */
@Transactional
public interface GreetingDAO extends CrudRepository<Greeting, Integer> {


}
