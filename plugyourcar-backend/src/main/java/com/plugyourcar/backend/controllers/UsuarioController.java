package com.plugyourcar.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.plugyourcar.backend.dto.UsuarioDTO;
import com.plugyourcar.backend.exceptions.UserNameOrEmailExistsException;
import com.plugyourcar.backend.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /*@RequestMapping(value="/usuarios", method = RequestMethod.GET)
    public List listUser(){
        return usuarioService.findAll();
    }*/

    @RequestMapping(value = "/registro", method = RequestMethod.POST)
    public void create(@RequestBody UsuarioDTO usuario) throws UserNameOrEmailExistsException {
        usuarioService.registrarUsuario(usuario);
    }

    /*@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Long id){
        usuarioService.delete(id);
        return "success";
    }*/

}
