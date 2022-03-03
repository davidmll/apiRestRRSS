package com.projecto.java.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

import com.projecto.java.model.Jefe;
import com.projecto.java.service.JefeService;

@RestController
public class JefeController {

	@Autowired
	private JefeService service;
	
//	Methods Get

	@GetMapping("/jefes")
	public List<Jefe> index() {
		return service.findAllJefe();
	}

	@GetMapping("/jefes/{id}")
	public ResponseEntity<?> findJefestoById(@PathVariable int id) {

		Jefe jefe = null;

		Map<String, Object> response = new HashMap<>();

		try {

			jefe = service.findById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (jefe == null) {
			response.put("mensaje", "El departamento ID: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Jefe>(jefe, HttpStatus.OK);

	}

//	Method Post

	@PostMapping("/jefe")
	public ResponseEntity<?> saveJefe(@RequestBody Jefe jefe) {

		Jefe jefeSave = null;

		Map<String, Object> response = new HashMap<>();

		try {
			System.out.println(jefe.getDepartamentos());
			jefeSave = service.saveJefe(jefe);
			System.out.println(jefeSave.getDepartamentos());

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", jefeSave);
		response.put("cliente", "El jefe ha sido creado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

//	Method Put

	@PutMapping("/jefe/{id}")
	public ResponseEntity<?> updateJefe(@RequestBody Jefe jefe, @PathVariable int id) {

		Jefe jefeUpdate= service.findById(id);

		Map<String, Object> response = new HashMap<>();

		if (jefeUpdate == null) {
			response.put("mensaje", "Error: no se puedo editar el jefe con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			jefeUpdate.setIdjefe(id);
			jefeUpdate.setDNI(jefe.getDNI());
			jefeUpdate.setNombre(jefe.getNombre());
			jefeUpdate.setSalario(jefe.getSalario());
			jefeUpdate.setTelefono(jefe.getTelefono());
			jefeUpdate.setDepartamentos(jefe.getDepartamentos());

			service.saveJefe(jefeUpdate);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("jefe", jefeUpdate);
		response.put("mensaje", "El jefe ha sido actualizado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

//	Method Delete

	@DeleteMapping("/jefe/{id}")
	public ResponseEntity<?> deleteJefe(@PathVariable int id) {

		Jefe jefeDelete = service.findById(id);

		Map<String, Object> response = new HashMap<>();

		if (jefeDelete == null) {
			response.put("mensaje", "Error: no se pudo eliminar al jefe con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			service.deleteJefe(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar la información en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("jefe",jefeDelete);
		response.put("mensaje", "El jefe ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}

