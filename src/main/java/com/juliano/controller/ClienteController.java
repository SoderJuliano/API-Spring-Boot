package com.juliano.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juliano.domain.model.Cliente;
import com.juliano.domain.model.service.CatalagoClienteService;
import com.juliano.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private CatalagoClienteService catalagoClienteService;

	@GetMapping
	public List<Cliente> Listar() {
		return clienteRepository.findAll();
	}

	@GetMapping("/{clinentId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clinentId) {
	
		return clienteRepository.findById(clinentId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		
			/* codigo não otimizado x codigo otimizado, ambos funcionam
			 * Optional<Cliente> cliente = clienteRepository.findById(clinentId);
		
			if(cliente.isPresent()) {
				return ResponseEntity.ok(cliente.get());
			}
			
			return ResponseEntity.notFound().build();*/
			
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		//return clienteRepository.save(cliente);
		return catalagoClienteService.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente){
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		//cliente = clienteRepository.save(cliente);
		cliente = catalagoClienteService.salvar(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		//clienteRepository.deleteById(clienteId);
		catalagoClienteService.excluir(clienteId);
		return ResponseEntity.noContent().build();
	}
	
}
