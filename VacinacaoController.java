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

import com.exemplo.atividade2.Service.VacinacaoService;
import com.exemplo.atividade2.entity.Vacinacao;


@RestController
@RequestMapping("/tutor")

public class VacinacaoController {
	
private final VacinacaoService vacinacaoService;
	
	public VacinacaoController (VacinacaoService vacinacaoService) {
		this.vacinacaoService = vacinacaoService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Vacinacao>> getAllVacinacao() {
		List<Vacinacao> vacinacoes = vacinacaoService.getAllVacinacoes();
		return ResponseEntity.ok(vacinacoes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vacinacao> getVacinacaoById(@PathVariable Long id) {
		Vacinacao vacinacao = vacinacaoService.getVacinacaoById(id);
		if (vacinacao !=null) {
			return ResponseEntity.ok(vacinacao);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Vacinacao> criarTutor(@RequestBody Vacinacao vacinacao) {
		Vacinacao criarVacinacao = vacinacaoService.saveVacinacao(vacinacao);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarVacinacao);
	}
	
	@PutMapping("/")
	public ResponseEntity<Vacinacao> altereVacinacao(@PathVariable Long id,
			@RequestBody Vacinacao vacinacao){
		Vacinacao altereVacinacao = vacinacaoService.altereVacinacao(id, vacinacao);
		if(altereVacinacao !=null) {
			return ResponseEntity.ok(altereVacinacao); 
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/")
	public ResponseEntity<String> deletePet (@PathVariable Long id) {
		boolean deleted = vacinacaoService.deleteVacinacao(id);
		if(deleted) {
			return ResponseEntity.ok().body("Usuário apagado com Sucesso!");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}


