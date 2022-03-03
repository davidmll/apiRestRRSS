package com.projecto.java.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projecto.java.Dao.JefeRepository;
import com.projecto.java.model.Jefe;
import com.projecto.java.service.JefeService;

@Service
public class JefeServiceImpl implements JefeService   {

	@Autowired
	private JefeRepository repo;

	@Override
	public List<Jefe> findAllJefe() {

		return (List<Jefe>) repo.findAll();
	}

	@Override
	public Jefe findById(int id) {

		return repo.findById(id).orElse(null);
	}

	@Override
	public Jefe saveJefe(Jefe jefe) {

		System.out.println("Imple" + jefe.getDepartamentos());
		return repo.save(jefe);
	}

	@Override
	public void deleteJefe(int id) {
		repo.deleteById(id);
	}

}
