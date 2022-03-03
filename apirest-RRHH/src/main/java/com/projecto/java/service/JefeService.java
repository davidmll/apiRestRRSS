package com.projecto.java.service;

import java.util.List;

import com.projecto.java.model.Jefe;

public interface JefeService {

	List<Jefe> findAllJefe();

	Jefe findById(int id);

	Jefe saveJefe(Jefe jefe);

	void deleteJefe(int id);

}