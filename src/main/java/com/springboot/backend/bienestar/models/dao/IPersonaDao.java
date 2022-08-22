package com.springboot.backend.bienestar.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.backend.bienestar.models.entity.Persona;

public interface IPersonaDao extends CrudRepository<Persona, Long> {

}
