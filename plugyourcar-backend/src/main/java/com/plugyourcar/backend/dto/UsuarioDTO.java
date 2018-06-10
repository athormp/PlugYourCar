package com.plugyourcar.backend.dto;

public class UsuarioDTO {

	private String password;
	private String nombre;
	private String apellidos;
	private String email;
	private String dni;
	private String telefonoContacto;
	private String marcaVehiculo;
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
	
	public String getDni() {
		return dni;
	}
	
	public void setUserName(String dni) {
		this.dni = dni;
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
}
