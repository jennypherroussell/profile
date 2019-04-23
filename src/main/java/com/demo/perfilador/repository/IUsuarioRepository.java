/**
 * 
 */
package com.demo.perfilador.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.perfilador.model.Usuario;

/**
 * @author jroussell
 *
 */
public interface IUsuarioRepository extends MongoRepository<Usuario, String> {

    Usuario findByEmail(String email);
}
