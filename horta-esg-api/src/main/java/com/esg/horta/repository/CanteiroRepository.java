package com.esg.horta.repository;

import com.esg.horta.entity.Canteiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CanteiroRepository extends JpaRepository<Canteiro, Long> {
}

