/**
 * 
 */
package com.demo.perfilador.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.perfilador.model.Rol;
import com.demo.perfilador.model.Usuario;
import com.demo.perfilador.repository.IRolRepository;
import com.demo.perfilador.repository.IUsuarioRepository;

/**
 * @author jroussell
 *
 */
@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Autowired
	private IRolRepository roleRepository;

	@Autowired
	private PasswordEncoder encodePassword;

	/**
	 * Busqueda de usuario por email
	 * 
	 * @param email del Usuario
	 * @return Usuario
	 */
	public Usuario findUserByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	public void guardarUsuario(Usuario usuario) {
		usuario.setPassword(encodePassword.encode(usuario.getPassword()));
		usuario.setActivo(true);
		Rol rolUsuario = roleRepository.findByRol("ADMIN");
		usuario.setRoles(new HashSet<>(Arrays.asList(rolUsuario)));
		usuarioRepository.save(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Usuario user = usuarioRepository.findByEmail(email);
		if (user != null) {
			List<GrantedAuthority> authorities = rolesUsuario(user.getRoles());
			return buildUserForAuthentication(user, authorities);
		} else {
			throw new UsernameNotFoundException("no se encontro el usuario");
		}
	}

	private List<GrantedAuthority> rolesUsuario(Set<Rol> userRoles) {
		Set<GrantedAuthority> roles = new HashSet<>();
		userRoles.forEach((role) -> {
			roles.add(new SimpleGrantedAuthority(role.getRol()));
		});

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;
	}

	private UserDetails buildUserForAuthentication(Usuario user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}

}
