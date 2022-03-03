package com.projecto.java.service;

import java.util.List;

import com.projecto.java.model.Departamento;

public interface DepartamentoService {

	List<Departamento> findAllDepartamentos();

	Departamento findById(int id);

	Departamento saveDepartamento(Departamento departamento);

	void deleteDepartamento(int id);

}