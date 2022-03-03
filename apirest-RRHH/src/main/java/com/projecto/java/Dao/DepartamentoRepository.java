package com.projecto.java.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projecto.java.model.Departamento;

@Repository
public interface DepartamentoRepository extends CrudRepository<Departamento, Integer> {

}
