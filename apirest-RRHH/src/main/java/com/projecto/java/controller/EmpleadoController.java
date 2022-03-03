package com.projecto.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.projecto.java.service.EmpleadoService;

@Controller
public class EmpleadoController {

	@Autowired
	private EmpleadoService service;
}
