package com.fortune.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.fortune.base.Base;

@NoRepositoryBean
public interface IBaseRepository extends CrudRepository<Base, String> {

}
