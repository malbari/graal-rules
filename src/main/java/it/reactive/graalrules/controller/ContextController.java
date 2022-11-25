package it.reactive.graalrules.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.reactive.graalrules.model.Context;
import it.reactive.graalrules.repository.ContextRepository;
import it.reactive.graalrules.service.ContextService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ContextController {

	@Autowired
	ContextRepository contextRepository;

	@Autowired
	ContextService contextService;

	@GetMapping("/contexts")
	public ResponseEntity<List<Context>> getAllContexts() {
		try {
			List<Context> contexts = contextRepository.findAll();
			return new ResponseEntity<>(contexts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/contexts/{id}")
	public ResponseEntity<Context> getContextId(@PathVariable("id") long id) {
		Optional<Context> oc = contextRepository.findById(id);
		if (oc.isPresent()) {
			return new ResponseEntity<>(oc.get(), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/contexts/{code}")
	public ResponseEntity<Context> getContextByCode(@PathVariable("code") String code) {
		Context c = contextRepository.findByCode(code);
		if (c != null) {
			return new ResponseEntity<>(c, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/contexts")
	public ResponseEntity<Context> createContext(@RequestBody Context c) {
		try {
			Context context = contextRepository.save(c);
			return new ResponseEntity<>(context, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/contexts")
	public ResponseEntity<Context> updateContext(@RequestBody Context c) {
		try {
			contextService.persistContext(c);
			return new ResponseEntity<>(c, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/contexts/{id}")
	public ResponseEntity<HttpStatus> deleteContext(@PathVariable("id") long id) {
		try {
			contextRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/contexts")
	public ResponseEntity<HttpStatus> deleteAllContext() {
		try {
			contextRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
