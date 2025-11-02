package com.esg.horta.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ESPECIE")
public class Especie {

  @Id
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "especie_seq_generator" // Nome interno para referência
  )
  // 2. Mapeia o gerador para a sequência REAL no Oracle
  @SequenceGenerator(
          name = "especie_seq_generator",
          sequenceName = "SEQ_ESPECIE", // <<--- Nome EXATO da sua sequência no SQL
          allocationSize = 1 // Recomendado para Oracle
  )
  @Column(name = "ID")

  private Long id;

  @NotBlank
  @Column(name = "NOME", nullable = false, length = 60)
  private String nome;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getNome() { return nome; }
  public void setNome(String nome) { this.nome = nome; }
}

