package br.com.project.api.service.impl;

import br.com.project.api.domain.User;
import br.com.project.api.domain.dto.UserDTO;
import br.com.project.api.exceptions.DataIntegrityViolationException;
import br.com.project.api.exceptions.ObjectNotFountException;
import br.com.project.api.repository.UserRepository;
import br.com.project.api.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(()-> new ObjectNotFountException("Objeto não encontrado"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO user) {
        findByEmail(null, user);
        return userRepository.save(mapper.map(user, User.class));
    }

    @Override
    public User update(Long id, UserDTO user){
        var userCadastrado = findById(id);
        findByEmail(id, user);
        BeanUtils.copyProperties(user, userCadastrado, "id");
        return userRepository.save(userCadastrado);
    }

    @Override
    public void delete(Long id){
        findById(id);
        userRepository.deleteById(id);
    }


    private void findByEmail(Long id, UserDTO user){
        Optional<User> email = userRepository.findByEmail(user.getEmail());
        if(email.isPresent() && !email.get().getId().equals(id)){
            throw new DataIntegrityViolationException("E-mail já está associado a outro cadastro");
        }
    }


}
