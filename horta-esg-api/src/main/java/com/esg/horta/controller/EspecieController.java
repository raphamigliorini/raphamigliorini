package com.esg.horta.controller;

import com.esg.horta.entity.Canteiro;
import com.esg.horta.entity.Especie;
import com.esg.horta.repository.CanteiroRepository;
import com.esg.horta.repository.EspecieRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especies")
public class EspecieController {

  private final EspecieRepository repo;

  public EspecieController(EspecieRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public List<Especie> listar() {
    return repo.findAll();
  }

  @PostMapping
  public ResponseEntity<Especie> criar(@RequestBody @Valid Especie especie) {
    return ResponseEntity.ok(repo.save(especie));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Especie> obter(@PathVariable Long id) {
    return repo.findById(id).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    if (!repo.existsById(id)) return ResponseEntity.notFound().build();
    repo.deleteById(id);
    return ResponseEntity.noContent().build();
  }
  @Autowired
  private EspecieRepository especieRepository; // Assumindo EspecieRepository

  @Autowired
  private CanteiroRepository canteiroRepository;

  // ... outros repositórios (Colheita, Doacao)

  // 1. POST /api/especies - Criação
  @PostMapping("/especies")
  public ResponseEntity<Especie> createEspecie(@RequestBody Especie especie) {
    Especie savedEspecie = especieRepository.save(especie);
    return new ResponseEntity<>(savedEspecie, HttpStatus.CREATED); // 201 Created
  }

  // 2. GET /api/canteiros - Listagem
  @GetMapping("/canteiros")
  public ResponseEntity<List<Canteiro>> getAllCanteiros() {
    List<Canteiro> canteiros = canteiroRepository.findAll();
    return ResponseEntity.ok(canteiros); // 200 OK
  }
}

