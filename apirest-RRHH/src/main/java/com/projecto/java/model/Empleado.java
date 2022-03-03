package com.projecto.java.model;

import javax.persistence.*;

@Entity
@Table(name = "empleados")
public class Empleado {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idempleado;

	private String DNI;
	private String nombre;
	private double salario;
	private int telefono;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_departamento")
	private Departamento departamentos;

//	Getters and Setters

	public int getIdempleado() {
		return idempleado;
	}

	public void setIdempleado(int idempleado) {
		this.idempleado = idempleado;
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

	public Departamento getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(Departamento departamentos) {
		this.departamentos = departamentos;
	}

}
