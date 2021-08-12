package com.juliano.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juliano.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> Listar() {
		
		var cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Juliano");
		cliente1.setEmail("teste@teste.com");
		cliente1.setTelefone("9999999");
		
		var cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Juliano2");
		cliente2.setEmail("teste@teste.com");
		cliente2.setTelefone("555555555");
		
		return Arrays.asList(cliente1, cliente2);
	}

}
