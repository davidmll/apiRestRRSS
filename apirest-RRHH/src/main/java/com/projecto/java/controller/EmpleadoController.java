package com.projecto.java.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projecto.java.model.Empleado;
import com.projecto.java.service.EmpleadoService;

@RestController
public class EmpleadoController {

	@Autowired
	private EmpleadoService service;

//	Methods Get

	@GetMapping("/empleados")
	public List<Empleado> index() {
		return service.findAllEmpleado();
	}

	@GetMapping("/empleados/{id}")
	public ResponseEntity<?> findEmpleadoById(@PathVariable int id) {

		Empleado empleado = null;

		Map<String, Object> response = new HashMap<>();

		try {

			empleado = service.findById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (empleado == null) {
			response.put("mensaje", "El empleado ID: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);

	}

//	Method Post

	@PostMapping("/empleado")
	public ResponseEntity<?> saveEmpleado(@RequestBody Empleado empleado) {

		Empleado empleadoSave = null;

		Map<String, Object> response = new HashMap<>();

		try {
			empleadoSave = service.saveEmpleado(empleado);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", empleadoSave);
		response.put("cliente", "El empleado ha sido creado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

//	Method Put

	@PutMapping("/empleado/{id}")
	public ResponseEntity<?> updateEmpleado(@RequestBody Empleado empleado, @PathVariable int id) {

		Empleado empleadoUpdate = service.findById(id);

		Map<String, Object> response = new HashMap<>();

		if (empleadoUpdate == null) {
			response.put("mensaje", "Error: no se puedo editar el empleado con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			empleadoUpdate.setIdempleado(id);
			empleadoUpdate.setDNI(empleado.getDNI());
			empleadoUpdate.setNombre(empleado.getNombre());
			empleadoUpdate.setSalario(empleado.getSalario());
			empleadoUpdate.setTelefono(empleado.getTelefono());

			service.saveEmpleado(empleadoUpdate);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("empleado", empleadoUpdate);
		response.put("mensaje", "El empleado ha sido actualizado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

//	Method Delete

	@DeleteMapping("/empleado/{id}")
	public ResponseEntity<?> deleteEmpleado(@PathVariable int id) {

		Empleado empleadoDelete = service.findById(id);

		Map<String, Object> response = new HashMap<>();

		if (empleadoDelete == null) {
			response.put("mensaje", "Error: no se pudo eliminar al empleado con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			service.deleteEmpleado(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar la información en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("jefe", empleadoDelete);
		response.put("mensaje", "El empleado ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
