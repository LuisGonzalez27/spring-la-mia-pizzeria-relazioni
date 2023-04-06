package org.learning.pizzeria.repository;

import org.learning.pizzeria.model.Offerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffertaRepository extends JpaRepository<Offerta, Integer> {
}
