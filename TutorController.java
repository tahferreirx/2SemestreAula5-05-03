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

import com.exemplo.atividade2.Service.TutorService;
import com.exemplo.atividade2.entity.Tutor;


@RestController
@RequestMapping("/tutor")

public class TutorController {
	
private final TutorService tutorService;
	
	public TutorController (TutorService tutorService) {
		this.tutorService = tutorService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Tutor>> getAllTutor() {
		List<Tutor> tutors = tutorService.getAllTutors();
		return ResponseEntity.ok(tutors);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tutor> getTutorById(@PathVariable Long id) {
		Tutor tutor = tutorService.getTutorById(id);
		if (tutor !=null) {
			return ResponseEntity.ok(tutor);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Tutor> criarTutor(@RequestBody Tutor tutor) {
		Tutor criarTutor = tutorService.saveTutor(tutor);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarTutor);
	}
	
	@PutMapping("/")
	public ResponseEntity<Tutor> altereTutor(@PathVariable Long id,
			@RequestBody Tutor tutor){
		Tutor altereTutor = tutorService.altereTutor(id, tutor);
		if(altereTutor !=null) {
			return ResponseEntity.ok(altereTutor); 
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/")
	public ResponseEntity<String> deletePet (@PathVariable Long id) {
		boolean deleted = tutorService.deleteTutor(id);
		if(deleted) {
			return ResponseEntity.ok().body("Usuário apagado com Sucesso!");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}

