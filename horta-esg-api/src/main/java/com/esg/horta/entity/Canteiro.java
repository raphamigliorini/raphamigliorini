package com.esg.horta.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "CANTEIRO")
public class Canteiro {

  @Id
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "canteiro_seq_generator" // Nome de referência
  )
  // 2. Mapeia o gerador para a sequência REAL no banco de dados Oracle
  @SequenceGenerator(
          name = "canteiro_seq_generator",
          sequenceName = "SEQ_CANTEIRO", // <<--- Nome EXATO da sua sequência SQL
          allocationSize = 1 // Recomendado para evitar saltos de ID
  )
  @Column(name = "ID")
  private Long id;

  @NotBlank
  @Column(name="NOME")
  private String nome;

  @NotNull
  @Column(name="ESPECIE_ID")
  private Long especieId;

  @Positive
  @Column(name="AREA_M2")
  private Double areaM2;

  @Positive
  @Column(name="META_DOACAO_KG")
  private Double metaDoacaoKg;

  // getters/setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getNome() { return nome; }
  public void setNome(String nome) { this.nome = nome; }
  public Long getEspecieId() { return especieId; }
  public void setEspecieId(Long especieId) { this.especieId = especieId; }
  public Double getAreaM2() { return areaM2; }
  public void setAreaM2(Double areaM2) { this.areaM2 = areaM2; }
  public Double getMetaDoacaoKg() { return metaDoacaoKg; }
  public void setMetaDoacaoKg(Double metaDoacaoKg) { this.metaDoacaoKg = metaDoacaoKg; }
}

