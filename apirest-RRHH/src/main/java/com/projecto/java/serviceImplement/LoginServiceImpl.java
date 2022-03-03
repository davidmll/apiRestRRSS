package com.projecto.java.serviceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projecto.java.Dao.LoginDao;

@Service
public class LoginServiceImpl {

	@Autowired
	private LoginDao repo;
}
