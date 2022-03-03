package com.projecto.java.controller;

import java.util.*;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projecto.java.model.Departamento;
import com.projecto.java.service.DepartamentoService;

@RestController
public class DepartamentoController {

	@Autowired
	private DepartamentoService service;

//	Methods Get

	@GetMapping("/departamentos")
	public List<Departamento> index() {
		return service.findAllDepartamentos();
	}

	@GetMapping("departamentos/{id}")
	public ResponseEntity<?> findDepartamentetoById(@PathVariable int id) {

		Departamento departamento = null;

		Map<String, Object> response = new HashMap<>();

		try {

			departamento = service.findById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (departamento == null) {
			response.put("mensaje", "El departamento ID: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Departamento>(departamento, HttpStatus.OK);

	}

//	Method Post

	@PostMapping("/departamento")
	public ResponseEntity<?> saveDepartamento(@RequestBody Departamento departamento) {

		Departamento departamentoNew = null;

		Map<String, Object> response = new HashMap<>();

		try {

			departamentoNew = service.saveDepartamento(departamento);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", departamentoNew);
		response.put("cliente", "El departamento ha sido creado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

//	Method Put

	@PutMapping("/departamento/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateDepartamento(@RequestBody Departamento departamento, @PathVariable int id) {

		Departamento departamentoUpdate = service.findById(id);

		Map<String, Object> response = new HashMap<>();

		if (departamentoUpdate == null) {
			response.put("mensaje", "Error: no se puedo editar el departamento con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			departamentoUpdate.setIdDepartamento(id);
			departamentoUpdate.setNombre(departamento.getNombre());
			departamentoUpdate.setUbicacion(departamento.getUbicacion());

			service.saveDepartamento(departamentoUpdate);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("departamento", departamentoUpdate);
		response.put("mensaje", "El departamento ha sido actualizado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

//	Method Delete

	@DeleteMapping("/departamento/{id}")
	public ResponseEntity<?> deleteDepartamento(@PathVariable int id) {

		Departamento departamentoDelete = service.findById(id);

		Map<String, Object> response = new HashMap<>();

		if (departamentoDelete == null) {
			response.put("mensaje", "Error: no se pudo eliminar el departamento con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			service.deleteDepartamento(id);
//			Controlar error claves foraneas
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar la información en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (ConstraintViolationException e) {
			response.put("mensaje", "Error con las claves foráneas al eliminar");
			response.put("error", e.getMessage());

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("departamento", departamentoDelete);
		response.put("mensaje", "El departamento ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
