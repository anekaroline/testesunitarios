package br.com.project.api.controller;

import br.com.project.api.controller.api.IUserAPI;
import br.com.project.api.domain.User;
import br.com.project.api.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements IUserAPI {

    @Autowired
    private UserService service;
    @Override
    public ResponseEntity<User> findById(Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

}
