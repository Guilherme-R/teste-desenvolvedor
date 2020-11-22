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

import com.guilhermerocha.teste.desenvolvedor.entities.Subestacao;
import com.guilhermerocha.teste.desenvolvedor.services.SubestacaoService;

@RestController
@RequestMapping(value = "/subestacoes")
public class SubestacaoResource {
	
	@Autowired
	private SubestacaoService service;
	
	@GetMapping
	public ResponseEntity<List<Subestacao>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<Subestacao> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Subestacao> insert(@RequestBody Subestacao obj) {
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
	public ResponseEntity<Subestacao> update(@PathVariable Long id, @RequestBody Subestacao obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
