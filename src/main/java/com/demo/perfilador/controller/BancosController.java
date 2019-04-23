/**
 * 
 */
package com.demo.perfilador.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.perfilador.model.Banco;
import com.demo.perfilador.repository.IBancosRepository;

/**
 * @author jroussell
 *
 */
@RestController
@RequestMapping("api/demo")
public class BancosController {
	@Autowired
	IBancosRepository bancosRepository;

	@GetMapping("/bancos")
	public Iterable<Banco> banco() {
		return bancosRepository.findAll();
	}

	@PostMapping("/bancos")
	public String save(@RequestBody Banco banco) {
		bancosRepository.save(banco);

		return banco.getId();
	}

	@GetMapping("/bancos/{id}")
	public Optional<Banco> show(@PathVariable String id) {
		return bancosRepository.findById(id);
	}

	@PutMapping("/bancos/{id}")
	public Banco update(@PathVariable String id, @RequestBody Banco banco) {
		Optional<Banco> bancSave = bancosRepository.findById(id);
		if (banco.getNombreBanco() != null) {
			bancSave.get().setNombreBanco(banco.getNombreBanco());
		}

		bancosRepository.save(bancSave.get());
		return bancSave.get();
	}

	@DeleteMapping("/bancos/{id}")
	public String delete(@PathVariable String id) {
		Optional<Banco> product = bancosRepository.findById(id);
		bancosRepository.delete(product.get());

		return "banco eliminado";
	}
}