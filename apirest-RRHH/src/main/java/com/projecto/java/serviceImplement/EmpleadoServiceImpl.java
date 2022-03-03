package com.projecto.java.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projecto.java.Dao.EmpleadoRepository;
import com.projecto.java.model.Empleado;
import com.projecto.java.service.EmpleadoService;


@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoRepository repo;
	

	@Override
	public List<Empleado> findAllEmpleado() {
		
		return (List<Empleado>) repo.findAll();
	}
	
	
	@Override
	public Empleado findById(int id) {
		
		return repo.findById(id).orElse(null);
	}
	

	@Override
	public Empleado saveEmpleado(Empleado empleado) {
		
		return repo.save(empleado);
	}
	


	@Override
	public void deleteEmpleado(int id) {
		repo.deleteById(id);
	}


	

}
