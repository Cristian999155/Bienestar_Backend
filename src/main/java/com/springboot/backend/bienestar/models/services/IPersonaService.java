package com.springboot.backend.bienestar.models.services;

import java.util.List;

import com.springboot.backend.bienestar.models.entity.Persona;

public interface IPersonaService {
	
	public List<Persona> findAll();
	
	public Persona findById(long id);
	
	public Persona save(Persona persona);
	
	public void delete (long id);
	

}
