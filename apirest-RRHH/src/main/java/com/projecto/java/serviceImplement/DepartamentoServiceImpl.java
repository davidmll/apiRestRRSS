package com.projecto.java.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projecto.java.Dao.DepartamentoRepository;
import com.projecto.java.model.Departamento;
import com.projecto.java.service.DepartamentoService;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	private DepartamentoRepository repo;

	@Override
	public List<Departamento> findAllDepartamentos() {

		return (List<Departamento>) repo.findAll();
	}

	@Override
	public Departamento findById(int id) {

		return repo.findById(id).orElse(null);
	}

	@Override
	public Departamento saveDepartamento(Departamento departamento) {

		return repo.save(departamento);
	}

	@Override
	public void deleteDepartamento(int id) {
		repo.deleteById(id);
	}

}
