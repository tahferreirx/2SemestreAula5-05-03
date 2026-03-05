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

import com.exemplo.atividade2.Service.RacaService;
import com.exemplo.atividade2.entity.Raca;

@RestController
@RequestMapping("/raca")

public class RacaController {
	
	private final RacaService racaService;
	
	public RacaController (RacaService racaService) {
		this.racaService = racaService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Raca>> getAllRaca() {
		List<Raca> racas = racaService.getAllRacas();
		return ResponseEntity.ok(racas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Raca> getRacaById(@PathVariable Long id) {
		Raca raca = racaService.getRacaById(id);
		if (raca !=null) {
			return ResponseEntity.ok(raca);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Raca> criarRaca(@RequestBody Raca raca) {
		Raca criarRaca = racaService.saveRaca(raca);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarRaca);
	}
	
	@PutMapping("/")
	public ResponseEntity<Raca> altereRaca(@PathVariable Long id,
			@RequestBody Raca raca){
		Raca altereRaca = racaService.altereRaca(id, raca);
		if(altereRaca !=null) {
			return ResponseEntity.ok(altereRaca); 
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/")
	public ResponseEntity<String> deletePet (@PathVariable Long id) {
		boolean deleted = racaService.deleteRaca(id);
		if(deleted) {
			return ResponseEntity.ok().body("Usuário apagado com Sucesso!");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
