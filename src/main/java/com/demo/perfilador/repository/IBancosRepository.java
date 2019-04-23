/**
 * 
 */
package com.demo.perfilador.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.perfilador.model.Banco;

/**
 * @author jroussell
 *
 */
public interface IBancosRepository extends MongoRepository<Banco, String> {

	Banco findByNombreBanco(String banco);

	Optional<Banco> findById(String id);
}
