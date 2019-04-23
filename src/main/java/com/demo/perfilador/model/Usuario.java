/**
 * 
 */
package com.demo.perfilador.model;

/**
 * @author jroussell
 *
 */
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "usuarios")
@Data
public class Usuario {

	@Id
	private String id;
	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	private String email;

	private String password;
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private String rfc;
	private boolean activo;
	@DBRef
	private Set<Rol> roles;

}