package com.esg.horta.service;

import com.esg.horta.entity.Canteiro;
import com.esg.horta.repository.CanteiroRepository;
import com.esg.horta.repository.RelatorioRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HortaService {

  private final CanteiroRepository canteiroRepo;
  private final RelatorioRepository relatorioRepo;

  public HortaService(CanteiroRepository canteiroRepo, RelatorioRepository relatorioRepo) {
    this.canteiroRepo = canteiroRepo;
    this.relatorioRepo = relatorioRepo;
  }

  public List<Canteiro> listarCanteiros() { return canteiroRepo.findAll(); }
  public Canteiro criar(Canteiro c) { return canteiroRepo.save(c); }
  public Canteiro atualizar(Long id, Canteiro novo) {
    Canteiro atual = canteiroRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Canteiro não encontrado"));
    atual.setNome(novo.getNome());
    atual.setEspecieId(novo.getEspecieId());
    atual.setAreaM2(novo.getAreaM2());
    atual.setMetaDoacaoKg(novo.getMetaDoacaoKg());
    return canteiroRepo.save(atual);
  }
  public void deletar(Long id) { canteiroRepo.deleteById(id); }

  public List<Map<String,Object>> doacoesMensais(int ano, int mes) {
    return relatorioRepo.doacoesMensais(ano, mes);
  }

  public Map<String,Object> indicadores(int ano, int mes) {
    return relatorioRepo.indicadores(ano, mes);
  }
}

