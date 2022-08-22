package com.springboot.backend.bienestar.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="personas")
public class Persona implements Serializable {

	@Id
	private Long documento;
	
	
	
	@Column(nullable=false)
	@NotEmpty(message ="El campo no puede estar vacio")
	private String nombre;
	
	@Column(nullable=false)
	@NotEmpty(message ="no puede estar vacio")
	private String apellido;
	
	@Column(nullable=false, unique = true)
	@NotEmpty(message ="no puede estar vacio")
	@Email
	private String correo;
	
	@Column(name="tipo_usuario",nullable = false)
	private String tipoUsuario;
	@Column(name="fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	public Long getDocumento() {
		return documento;
	}

	public void setDocumento(Long documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	

	private static final long serialVersionUID = 1L;

}
