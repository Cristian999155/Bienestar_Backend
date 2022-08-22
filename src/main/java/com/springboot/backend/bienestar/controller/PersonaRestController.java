package com.springboot.backend.bienestar.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.bienestar.models.entity.Persona;
import com.springboot.backend.bienestar.models.services.IPersonaService;

@CrossOrigin(origins= {"htpp://localhost:4200"})
@RestController
@RequestMapping("/api")
public class PersonaRestController {
	
	@Autowired
	private IPersonaService personaService;
	
	@GetMapping("/personas")
	public List<Persona> index(){
		return personaService.findAll();
	}
	
	
	
	@GetMapping("/personas/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Persona persona = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			persona = personaService.findById(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Erros en la base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(persona == null) 
		{
			response.put("Mensaje", "La persona no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Persona>(persona,HttpStatus.OK);
	}
	
	
	
	
	
	
	@PostMapping("/personas")
	public ResponseEntity<?> create (@Valid @RequestBody Persona persona, BindingResult result) {
		
		Persona personaNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors())
			
		{
			List<String> listaErrores = new ArrayList<>();
			
			for(FieldError error: result.getFieldErrors())
			{
				listaErrores.add("El campo '" + error.getField() + "´ " + error.getDefaultMessage());
			}
			
			response.put("error", listaErrores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
			
		
		try {
			personaNew = personaService.save(persona);
			
		} catch (DataAccessException e) {
			
			response.put("Mensaje", "Error al ingresar el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje", "El usuario ha sido agregado exitosamente!!	"); 
		response.put("Persona", personaNew);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	
	
	
	
	@PutMapping("/personas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Persona persona, BindingResult result, @PathVariable Long id) {
		Persona personaActual = personaService.findById(id);
			
		Persona personaUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
        if(result.hasErrors())
			
		{
			List<String> listaErrores = new ArrayList<>();
			
			for(FieldError error: result.getFieldErrors())
			{
				listaErrores.add("El campo '" + error.getField() + "´ " + error.getDefaultMessage());
			}
			
			response.put("error", listaErrores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
			
		
		if(personaActual == null) 
		{
			response.put("Mensaje", "La persona no se puede actualizar debido a que no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		personaActual.setNombre(persona.getNombre());
		personaActual.setApellido(persona.getApellido());
		personaActual.setCorreo(persona.getCorreo());
		personaUpdated = personaService.save(personaActual);
		}catch (DataAccessException e) {
			
			response.put("Mensaje", "Error al actualizar el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje", "El usuario ha sido modificado exitosamente!!	");
		response.put("Persona", personaUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	
	
	
	
	
	@DeleteMapping("/personas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		
		try {
			personaService.delete(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al eliminar el registro de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje", "El usuario ha sido eliminado exitosamente!!	");
		
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}
