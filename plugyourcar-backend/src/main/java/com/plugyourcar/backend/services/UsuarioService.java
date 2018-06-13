package com.plugyourcar.backend.services;

import com.plugyourcar.backend.dto.UsuarioDTO;
import com.plugyourcar.backend.exceptions.UserNameOrEmailExistsException;
import com.plugyourcar.backend.model.Usuario;

public interface UsuarioService {
	
	Usuario registrarUsuario(UsuarioDTO usuario) throws UserNameOrEmailExistsException;
	
}
