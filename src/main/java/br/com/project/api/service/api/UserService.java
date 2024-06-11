package br.com.project.api.service.api;

import br.com.project.api.domain.User;

public interface UserService {

    User findById(Long id);
}
