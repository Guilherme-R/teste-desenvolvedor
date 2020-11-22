package com.guilhermerocha.teste.desenvolvedor.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.guilhermerocha.teste.desenvolvedor.entities.RedeMt;
import com.guilhermerocha.teste.desenvolvedor.repositories.RedeMtRepository;
import com.guilhermerocha.teste.desenvolvedor.services.exceptions.DatabaseException;
import com.guilhermerocha.teste.desenvolvedor.services.exceptions.ResourceNotFoundException;

@Service
public class RedeMtService {

	@Autowired
	private RedeMtRepository repository;
	
	public List<RedeMt> findAll(){
		return repository.findAll();
	}
	
	public RedeMt findById(Long id) {
		Optional<RedeMt> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public RedeMt insert(RedeMt obj) {
		try {
			return repository.save(obj);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public RedeMt update(Long id, RedeMt obj) {
		try {
			RedeMt entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(RedeMt entity, RedeMt obj) {
		entity.setNome(obj.getNome());
		entity.setCodigo(obj.getCodigo());
		entity.setTensaoNominal(obj.getTensaoNominal());
	}
}
