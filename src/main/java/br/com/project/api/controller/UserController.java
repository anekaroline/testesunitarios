package br.com.project.api.controller;

import br.com.project.api.controller.api.IUserAPI;
import br.com.project.api.domain.dto.UserDTO;
import br.com.project.api.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll().stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<UserDTO> create(UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(service.create(userDTO), UserDTO.class));
    }

    @Override
    public ResponseEntity<UserDTO> update(Long id, UserDTO user) {
        return ResponseEntity.ok(mapper.map(service.update(id, user), UserDTO.class));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
