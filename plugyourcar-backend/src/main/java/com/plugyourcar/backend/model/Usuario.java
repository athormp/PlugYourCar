package com.plugyourcar.backend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Usuario {

	@Id
	private int id;
	
	private Password password;
	private String nombre;
	private String apellidos;
	private String email;
	private String dni_nie;
	private String telefonoContacto;
	private String marcaVehiculo;
	
	@OneToMany(mappedBy="usuario",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Login> logins;
	
	@OneToMany(mappedBy="usuario",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Reserva> reservas;
	
	@OneToMany(mappedBy="usuario",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Carga> cargas;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDni_nie() {
		return dni_nie;
	}
	
	public void setDni_nie(String dni_nie) {
		this.dni_nie = dni_nie;
	}
	
	public String getTelefonoContacto() {
		return telefonoContacto;
	}
	
	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}
	
	public String getMarcaVehiculo() {
		return marcaVehiculo;
	}
	
	public void setMarcaVehiculo(String marcaVehiculo) {
		this.marcaVehiculo = marcaVehiculo;
	}

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}
	
	
}
