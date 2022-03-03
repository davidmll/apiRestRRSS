package com.projecto.java.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "jefes")
public class Jefe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idjefe;

	private String DNI;
	private String nombre;

	private double salario;
	private int telefono;

	@OneToMany(mappedBy = "articulo")
	private Set<Departamento> departamentos;
	
//	Getters and Setters

	public int getIdjefe() {
		return idjefe;
	}

	public void setIdjefe(int idjefe) {
		this.idjefe = idjefe;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public Set<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(Set<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

}