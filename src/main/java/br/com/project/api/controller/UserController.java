package br.com.project.api.controller;

import br.com.project.api.controller.api.IUserAPI;
import br.com.project.api.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements IUserAPI {
    @Override
    public ResponseEntity<User> findById(Integer id) {
        return ResponseEntity.ok().body(new User(1, "Ane", "ane@email.com", "123"));
    }
}
