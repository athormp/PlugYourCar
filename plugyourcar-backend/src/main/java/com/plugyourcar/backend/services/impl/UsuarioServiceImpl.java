package com.plugyourcar.backend.services.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
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
import com.plugyourcar.backend.model.Login;
import com.plugyourcar.backend.model.Saldo;
import com.plugyourcar.backend.model.Usuario;
import com.plugyourcar.backend.repositories.LoginRepository;
import com.plugyourcar.backend.repositories.SaldoRepository;
import com.plugyourcar.backend.repositories.UsuarioRepository;
import com.plugyourcar.backend.services.UsuarioService;
import com.plugyourcar.backend.utils.Utils;

@Service(value = "usuarioService")
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SaldoRepository saldoRepository;
	
	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;	
	
	private static final Logger log = LoggerFactory.getLogger(PuntosCargaLoaderServiceImpl.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUserName(username);
		Login login = new Login();
		if(usuario == null){
			log.info("Nombre de usuario o password incorrecto");
			
			// Generamos el registro de login en la base de datos, en este caso un login erróneo
			login.setExito(false);
			try {
				Timestamp horaLogin = Utils.dateFormatterA("");
				login.setHoraLogin(horaLogin);
				login.setDescripcionError("El usuario con el DNI " + username + ", no existe o la password que introdujo es incorrecta");
				loginRepository.save(login);
			} catch (ParseException dfe) {
				log.debug("Login imposible de generar para el usuario: " + username + ", formato incorrecto de fecha");
			}
			
			throw new UsernameNotFoundException("Nombre de usuario o password incorrecto");
		}
		
		// Generamos el registro de login en la base de datos, en este caso un login exitoso
		login.setUsuario(usuario);
		
		try {
			Timestamp horaLogin = Utils.dateFormatterA("");
			login.setHoraLogin(horaLogin);
			loginRepository.save(login);
		} catch (ParseException dfe) {
			log.debug("Login imposible de generar para el usuario: " + usuario.getUsername() + ", formato incorrecto de fecha");
		}
		
		return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), getAuthority());
	}

	@Transactional
	@Override
	public Usuario registrarUsuario(UsuarioDTO usuarioDTO) throws UserNameOrEmailExistsException {
		
		// Se comprueba que no exista un usuario con el mismo DNI o email
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
		
		// Se crea el nuevo usuario
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
        
        // Se genera el monedero que estará asociado al usuario, por defecto el saldo es de 0 euros
        Saldo saldo = new Saldo();
        saldo.setCantidadDisponible(0.0);
        saldoRepository.save(saldo);
        
        usuario.setSaldo(saldo);
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
