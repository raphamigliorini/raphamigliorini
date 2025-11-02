package com.esg.horta.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ESPECIE")
public class Especie {

  @Id
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

