package it.reactive.graalrules.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import it.reactive.graalrules.model.Context;

@Transactional(readOnly = true)
public interface ContextRepository extends JpaRepository<Context, Long> {
	Context findByCode(String code);
}
