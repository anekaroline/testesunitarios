package br.com.project.api.controller.api;

import br.com.project.api.domain.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
public interface IUserAPI {

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> findById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<UserDTO>> findAll();

    @PostMapping
    ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO);

    @PutMapping("/{id}")
    ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO user);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
