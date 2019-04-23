/**
 * 
 */
package com.demo.perfilador.model;

/**
 * @author jroussell
 *
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "roles")
@Data
public class Rol {
	@Id
	private String id;
	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)

	private String rol;

}
