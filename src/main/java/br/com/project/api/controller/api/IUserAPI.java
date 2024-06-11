package br.com.project.api.controller.api;

import br.com.project.api.domain.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public interface IUserAPI {

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> findById(@PathVariable Long id);
}
