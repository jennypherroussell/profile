/**
 * 
 */
package com.demo.perfilador.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * @author jroussell
 *
 */
@Data
@Document(collection = "banco")
public class Banco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2053540013116047255L;
	@Id
	private String id;
	private String nombreBanco;
	private String telefono;
	private Direccion direccion;

}
