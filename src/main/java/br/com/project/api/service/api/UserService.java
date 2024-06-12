package br.com.project.api.service.api;

import br.com.project.api.domain.User;
import br.com.project.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Long id);

    List<User> findAll();

    User create(UserDTO user);

    User update(Long id, UserDTO user);

    void delete(Long id);
}
