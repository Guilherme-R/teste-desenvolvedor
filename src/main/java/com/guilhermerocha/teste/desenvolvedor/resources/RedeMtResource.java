package com.guilhermerocha.teste.desenvolvedor.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.guilhermerocha.teste.desenvolvedor.entities.RedeMt;
import com.guilhermerocha.teste.desenvolvedor.services.RedeMtService;

@RestController
@RequestMapping(value = "/redes_mt")
public class RedeMtResource {
	
	@Autowired
	private RedeMtService service;
	
	@GetMapping
	public ResponseEntity<List<RedeMt>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<RedeMt> findById(@PathVariable Long id){
		RedeMt obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<RedeMt> insert(@RequestBody RedeMt obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value= "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<RedeMt> update(@PathVariable Long id, @RequestBody RedeMt obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
		
	}
}
