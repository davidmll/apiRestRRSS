package com.projecto.java.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projecto.java.model.Login;

@Repository
public interface LoginDao extends CrudRepository<Login, Integer> {

}
