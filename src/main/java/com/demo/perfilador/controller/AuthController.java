/**
 * 
 */
package com.demo.perfilador.controller;

/**
 * @author jroussell
 *
 */
import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.perfilador.config.JwtTokenProvider;
import com.demo.perfilador.model.LoginBody;
import com.demo.perfilador.model.Usuario;
import com.demo.perfilador.repository.IUsuarioRepository;
import com.demo.perfilador.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	IUsuarioRepository userRepository;

	@Autowired
	private UsuarioService usuarioService;

	@SuppressWarnings("rawtypes")
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginBody data) {
		try {
			String username = data.getEmail();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			String token = jwtTokenProvider.createToken(username, userRepository.findByEmail(username).getRoles());
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("El email y/o password son incorrectos");
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody Usuario usuario) {
		Usuario usuarioExiste = usuarioService.findUserByEmail(usuario.getEmail());
		if (usuarioExiste != null) {
			throw new BadCredentialsException("El email : " + usuario.getEmail() + " ya esta registrado");
		}
		usuarioService.guardarUsuario(usuario);
		Map<Object, Object> model = new HashMap<>();
		model.put("message", "Usuario registrado correctamente");
		return ok(model);
	}
}