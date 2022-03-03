package com.projecto.java.model;

import javax.persistence.*;

@Entity
@Table(name = "logins")
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idlogin;

	private String usuario;
	private String password;
	
//	Getters and Setters

	public int getIdlogin() {
		return idlogin;
	}

	public void setIdlogin(int idlogin) {
		this.idlogin = idlogin;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
