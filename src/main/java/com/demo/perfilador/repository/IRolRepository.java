/**
 * 
 */
package com.demo.perfilador.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.perfilador.model.Rol;

/**
 * @author jroussell
 *
 */
public interface IRolRepository extends MongoRepository<Rol, String> {

	Rol findByRol(String role);
}
