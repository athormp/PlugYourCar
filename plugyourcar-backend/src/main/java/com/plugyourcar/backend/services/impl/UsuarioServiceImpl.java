package com.plugyourcar.backend.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.plugyourcar.backend.model.Usuario;
import com.plugyourcar.backend.repositories.UsuarioRepository;
import com.plugyourcar.backend.services.UsuarioService;

@Service(value = "usuarioService")
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public UserDetails loadUsuarioByDniNie(String usuarioId) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByDni_nie(usuarioId);
		if(usuario == null){
			throw new UsernameNotFoundException("Nombre de usuario o password incorrectos.");
		}
		return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getPassword().getPasswordEncriptada(), getAuthority());
	}

	private List getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	/*public List findAll() {
		List list = new ArrayList<>();
		usuarioRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}*/
}
