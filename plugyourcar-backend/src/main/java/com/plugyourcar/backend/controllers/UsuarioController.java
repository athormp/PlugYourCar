package com.plugyourcar.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.plugyourcar.backend.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value="/usuarios", method = RequestMethod.GET)
    public List listUser(){
        return usuarioService.findAll();
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    public User create(@RequestBody User user){
        return usuarioService.save(user);
    }

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Long id){
        usuarioService.delete(id);
        return "success";
    }

}
