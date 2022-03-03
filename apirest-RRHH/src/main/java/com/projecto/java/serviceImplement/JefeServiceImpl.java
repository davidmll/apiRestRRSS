package com.projecto.java.serviceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projecto.java.Dao.JefeRepository;
import com.projecto.java.service.JefeService;

@Service
public class JefeServiceImpl implements JefeService {

	@Autowired
	private JefeRepository repo;
}
