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

import com.exemplo.atividade2.Service.PetService;
import com.exemplo.atividade2.entity.Pet;

@RestController
@RequestMapping("/pet")

public class PetController {
	
private final PetService petService;
	
	public PetController (PetService petService) {
		this.petService = petService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Pet>> getAllPet() {
		List<Pet> pets = petService.getAllPets();
		return ResponseEntity.ok(pets);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
		Pet pet = petService.getPetById(id);
		if (pet !=null) {
			return ResponseEntity.ok(pet);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Pet> criarPet(@RequestBody Pet pet) {
		Pet criarPet = petService.savePet(pet);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarPet);
	}
	
	@PutMapping("/")
	public ResponseEntity<Pet> alterePet(@PathVariable Long id,
			@RequestBody Pet pet){
		Pet alterePet = petService.alterePet(id, pet);
		if(alterePet !=null) {
			return ResponseEntity.ok(alterePet); 
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/")
	public ResponseEntity<String> deletePet (@PathVariable Long id) {
		boolean deleted = petService.deletePet(id);
		if(deleted) {
			return ResponseEntity.ok().body("Usuário apagado com Sucesso!");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}


