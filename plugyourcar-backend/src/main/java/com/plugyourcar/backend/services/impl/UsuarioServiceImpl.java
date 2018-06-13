package com.plugyourcar.backend.services.impl;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.plugyourcar.backend.dto.UsuarioDTO;
import com.plugyourcar.backend.exceptions.UserNameOrEmailExistsException;
import com.plugyourcar.backend.model.Usuario;
import com.plugyourcar.backend.repositories.UsuarioRepository;
import com.plugyourcar.backend.services.UsuarioService;

@Service(value = "userService")
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private static final Logger log = LoggerFactory.getLogger(PuntosCargaLoaderServiceImpl.class);
	
	@Override
	public UserDetails loadUserByUsername(String dniNie) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUserName(dniNie);
		if(usuario == null){
			throw new UsernameNotFoundException("Nombre de usuario o password incorrectos.");
		}
		return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), getAuthority());
	}

	@Transactional
	@Override
	public Usuario registrarUsuario(UsuarioDTO usuarioDTO) throws UserNameOrEmailExistsException {
		
		StringBuilder errorMessage = new StringBuilder();
		boolean emailExists = emailExist(usuarioDTO.getEmail());
		boolean userNameExists = userNameExist(usuarioDTO.getDni());
		if (emailExists) {  
			log.info("Existe un usuario con la misma dirección de correo: "  + usuarioDTO.getEmail());
			errorMessage = errorMessage.append("Existe un usuario con la misma dirección de correo: "  + usuarioDTO.getEmail());
		
        }
		
		if (userNameExists) { 
			if(!errorMessage.equals(""))
				errorMessage = errorMessage.append(" / ");
			log.info("Existe un usuario con el mismo DNI/NIE: "  + usuarioDTO.getDni());
			errorMessage = errorMessage.append("Existe un usuario con el mismo DNI/NIE: "  + usuarioDTO.getDni());
        }
		
		
		if (emailExists || userNameExists) {
			throw new UserNameOrEmailExistsException(usuarioDTO.getDni(), errorMessage);
		}
		
        Usuario usuario = new Usuario();    
        usuario.setUserName(usuarioDTO.getDni());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefonoContacto(usuarioDTO.getTelefonoContacto());
        if (usuarioDTO.getMarcaVehiculo() != null && !usuarioDTO.getMarcaVehiculo().equals("")) {
        	usuario.setMarcaVehiculo(usuarioDTO.getMarcaVehiculo());
        }
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setRole(Arrays.asList("ROLE_USER").get(0));
        return usuarioRepository.save(usuario);       
    }
	
    private boolean emailExist(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            return true;
        }
        return false;
    }
    
    private boolean userNameExist(String dniNie) {
        Usuario usuario = usuarioRepository.findByUserName(dniNie);
        if (usuario != null) {
            return true;
        }
        return false;
    }
	
	private List getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("USER"));
	}

	/*public List findAll() {
		List list = new ArrayList<>();
		usuarioRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}*/
}
