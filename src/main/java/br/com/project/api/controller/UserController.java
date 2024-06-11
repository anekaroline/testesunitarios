package br.com.project.api.controller;

import br.com.project.api.controller.api.IUserAPI;
import br.com.project.api.domain.dto.UserDTO;
import br.com.project.api.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements IUserAPI {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService service;
    @Override
    public ResponseEntity<UserDTO> findById(Long id) {
        return ResponseEntity.ok().body(mapper.map(service.findById(id),  UserDTO.class));
    }

}
