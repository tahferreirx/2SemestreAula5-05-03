package com.exemplo.atividade2.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.atividade2.Service.EnderecoService;
import com.exemplo.atividade2.entity.Endereco;

@RestController
@RequestMapping("/endereco")

public class EnderecoController {
	
	private final EnderecoService enderecoService;
	
	public EnderecoController (EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Endereco>> getAllEndereco() {
		List<Endereco> enderecos = enderecoService.getAllEnderecos();
		return ResponseEntity.ok(enderecos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> getChipById(@PathVariable Long id) {
		Endereco endereco = enderecoService.getEnderecoById(id);
		if (endereco !=null) {
			return ResponseEntity.ok(endereco);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Endereco> criarEndereco(@RequestBody Endereco endereco) {
		Endereco criarEndereco = enderecoService.saveEndereco(endereco);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarEndereco);
	}
	
	@PutMapping("/")
	public ResponseEntity<Endereco> altereEndereco(@PathVariable Long id,
			@RequestBody Endereco endereco){
		Endereco altereEndereco = enderecoService.altereEndereco(id, endereco);
		if(altereEndereco !=null) {
			return ResponseEntity.ok(altereEndereco); 
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/")
	public ResponseEntity<String> deleteEndereco (@PathVariable Long id) {
		boolean deleted = enderecoService.deleteEndereco(id);
		if(deleted) {
			return ResponseEntity.ok().body("Usuário apagado com Sucesso!");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}

