package com.projecto.java.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projecto.java.Dao.LoginDao;
import com.projecto.java.model.Login;
import com.projecto.java.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao repo;

	@Override
	public List<Login> findAllLogin() {

		return (List<Login>) repo.findAll();
	}

	@Override
	public Login findById(int id) {

		return repo.findById(id).orElse(null);
	}

	@Override
	public Login saveLogin(Login login) {

		return repo.save(login);
	}

	@Override
	public void deleteLogin(int id) {
		repo.deleteById(id);
	}
}
