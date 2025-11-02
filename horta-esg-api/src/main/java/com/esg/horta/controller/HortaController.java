package com.esg.horta.controller;

import com.esg.horta.entity.Canteiro;
import com.esg.horta.service.HortaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/horta")
public class HortaController {

  private final HortaService service;

  public HortaController(HortaService service) {
    this.service = service;
  }

  @GetMapping("/public/ping")
  public Map<String,String> ping() {
    return Map.of("status","ok");
  }

  @GetMapping("/canteiros")
  public List<Canteiro> listarCanteiros() {
    return service.listarCanteiros();
  }

  @PostMapping("/canteiros")
  public Canteiro criar(@RequestBody @Valid Canteiro c) {
    return service.criar(c);
  }

  @PutMapping("/canteiros/{id}")
  public Canteiro atualizar(@PathVariable Long id, @RequestBody @Valid Canteiro c) {
    return service.atualizar(id, c);
  }

  @DeleteMapping("/canteiros/{id}")
  public Map<String,String> deletar(@PathVariable Long id) {
    service.deletar(id);
    return Map.of("message","Canteiro deletado");
  }

  @GetMapping("/relatorios/doacoes-mensais")
  public List<Map<String,Object>> doacoesMensais(@RequestParam int ano, @RequestParam int mes) {
    return service.doacoesMensais(ano, mes);
  }

  @GetMapping("/indicadores")
  public Map<String,Object> indicadores(@RequestParam int ano, @RequestParam int mes) {
    return service.indicadores(ano, mes);
  }
}

