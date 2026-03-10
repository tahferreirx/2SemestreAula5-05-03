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

import com.exemplo.atividade2.Service.TipoService;
import com.exemplo.atividade2.entity.Tipo;

@RestController
@RequestMapping("/tipo")

public class TipoController {

    private final TipoService tipoService;

    public TipoController(TipoService tipoService) {
        this.tipoService = tipoService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Tipo>> getAllTipo() {
        List<Tipo> tipos = tipoService.getAllTipo();
        return ResponseEntity.ok(tipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tipo> getTipoById(@PathVariable Long id) {
        Tipo tipo = tipoService.getTipoById(id);
        if (tipo != null) {
            return ResponseEntity.ok(tipo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Tipo> criaTipo(@RequestBody Tipo tipo) {
        Tipo criarTipo = tipoService.saveTipo(tipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarTipo);
    }

    @PutMapping("/")
    public ResponseEntity<Tipo> alterarTipo(@PathVariable Long id,
            @RequestBody Tipo tipo) {
        Tipo alterarTipo = tipoService.alteraTipo(id, tipo);
        if (alterarTipo != null) {
            return ResponseEntity.ok(alterarTipo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteTipo(@PathVariable Long id) {
        boolean deleted = tipoService.deleteTipo(id);
        if (deleted) {
            return ResponseEntity.ok().body("Tipo apagado com Sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}