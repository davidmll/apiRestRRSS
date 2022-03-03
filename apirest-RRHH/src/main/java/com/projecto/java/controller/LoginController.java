package com.projecto.java.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projecto.java.model.Login;
import com.projecto.java.service.LoginService;

@RestController
public class LoginController {

	
	@Autowired
	private LoginService service;
	
//	Methods Get

	@GetMapping("/logins")
	public List<Login> index() {
		return service.findAllLogin();
	}

	@GetMapping("/logins/{id}")
	public ResponseEntity<?> findLoginById(@PathVariable int id) {

		Login login = null;

		Map<String, Object> response = new HashMap<>();

		try {

			login = service.findById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (login == null) {
			response.put("mensaje", "El empleado ID: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Login>(login, HttpStatus.OK);

	}

//	Method Post

	@PostMapping("/login")
	public ResponseEntity<?> saveLogin(@RequestBody Login login) {

		Login loginSave= null;

		Map<String, Object> response = new HashMap<>();

		try {
			loginSave = service.saveLogin(login);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", loginSave);
		response.put("cliente", "El login se ha sido realizado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

//	Method Put

	@PutMapping("/login/{id}")
	public ResponseEntity<?> updateLogin(@RequestBody Login login, @PathVariable int id) {

		Login loginUpdate = service.findById(id);

		Map<String, Object> response = new HashMap<>();

		if (loginUpdate == null) {
			response.put("mensaje", "Error: no se puedo editar el usuario con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			loginUpdate.setIdlogin(id);
			loginUpdate.setUsuario(login.getUsuario());
			loginUpdate.setPassword(login.getPassword());

			service.saveLogin(loginUpdate);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("empleado", loginUpdate);
		response.put("mensaje", "El usuario ha sido actualizado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

//	Method Delete

	@DeleteMapping("/login/{id}")
	public ResponseEntity<?> deleteLogin(@PathVariable int id) {

		Login loginDelete = service.findById(id);

		Map<String, Object> response = new HashMap<>();

		if (loginDelete == null) {
			response.put("mensaje", "Error: no se pudo eliminar el usuario con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			service.deleteLogin(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar la información en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("login", loginDelete);
		response.put("mensaje", "El usuario ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
