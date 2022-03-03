package com.projecto.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.projecto.java.service.DepartamentoService;

@Controller
public class DepartamentoController {

	@Autowired
	private DepartamentoService service;
}
