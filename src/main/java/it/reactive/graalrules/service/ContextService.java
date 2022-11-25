package it.reactive.graalrules.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.reactive.graalrules.model.Context;

@Service
public class ContextService {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void persistContext(Context c) {
		em.persist(c);
	}

}
