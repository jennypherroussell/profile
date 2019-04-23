/**
 * 
 */
package com.demo.perfilador.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @author jroussell
 *
 */
@Data
public class Direccion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 136617923699384228L;
	private String calle;
	private String numeroExterior;
	private String colonia;
	private String codigoPostal;

}
