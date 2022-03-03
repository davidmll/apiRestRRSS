package com.projecto.java.service;

import java.util.List;

import com.projecto.java.model.Login;

public interface LoginService {

	List<Login> findAllLogin();

	Login findById(int id);

	Login saveLogin(Login login);

	void deleteLogin(int id);

}