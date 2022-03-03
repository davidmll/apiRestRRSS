package com.projecto.java.service;

import java.util.List;

import com.projecto.java.model.Empleado;

public interface EmpleadoService {

	List<Empleado> findAllEmpleado();

	Empleado findById(int id);

	Empleado saveEmpleado(Empleado empleado);

	void deleteEmpleado(int id);

}