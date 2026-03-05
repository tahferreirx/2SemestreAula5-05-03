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

import com.exemplo.atividade2.Service.ChipService;
import com.exemplo.atividade2.entity.Chip;

@RestController
@RequestMapping("/chip")
public class ChipController {
	
	private final ChipService chipService;
	
	public ChipController (ChipService chipService) {
		this.chipService = chipService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Chip>> getAllChip() {
		List<Chip> chips = chipService.getAllChips();
		return ResponseEntity.ok(chips);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Chip> getChipById(@PathVariable Long id) {
		Chip chip = chipService.getChipById(id);
		if (chip !=null) {
			return ResponseEntity.ok(chip);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Chip> criarChip(@RequestBody Chip chip) {
		Chip criarChip = chipService.saveChip(chip);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarChip);
	}
	
	@PutMapping("/")
	public ResponseEntity<Chip> altereChip(@PathVariable Long id,
			@RequestBody Chip chip){
		Chip altereChip = chipService.altereChip(id, chip);
		if(altereChip !=null) {
			return ResponseEntity.ok(altereChip);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/")
	public ResponseEntity<String> deleteChip (@PathVariable Long id) {
		boolean deleted = chipService.deleteChip(id);
		if(deleted) {
			return ResponseEntity.ok().body("Usuário apagado com Sucesso!");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
