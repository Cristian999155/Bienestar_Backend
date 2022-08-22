package com.springboot.backend.bienestar.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.backend.bienestar.models.dao.IPersonaDao;
import com.springboot.backend.bienestar.models.entity.Persona;

@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private IPersonaDao personaDao;
	@Override
	@Transactional(readOnly = true)
	public List<Persona> findAll() {
		
		return (List<Persona>) personaDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Persona findById(long id) {
		
		return personaDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Persona save(Persona persona) {
		
		return personaDao.save(persona);
	}
	
	@Override
	@Transactional
	public void delete(long id) {
		personaDao.deleteById(id);
		
	}

}
