package com.projecto.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.projecto.java.service.JefeService;

@Controller
public class JefeController {

	@Autowired
	private JefeService service;
	
}

