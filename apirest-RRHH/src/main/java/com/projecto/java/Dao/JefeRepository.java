package com.projecto.java.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projecto.java.model.Jefe;

@Repository
public interface JefeRepository extends CrudRepository<Jefe, Integer> {

}
