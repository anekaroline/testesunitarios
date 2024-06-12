package br.com.project.api.service.impl;

import br.com.project.api.domain.User;
import br.com.project.api.domain.dto.UserDTO;
import br.com.project.api.exceptions.ObjectNotFountException;
import br.com.project.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class UserServiceImplTest {
    public static final long ID = 1L;
    public static final String NAME = "User";
    public static final String EMAIL = "XXXXXXXXXXXXXX";
    public static final String SENHA = "123";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final int INDEX = 0;
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;
    @BeforeEach
    void setUp() {
        openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenRuturnAnUserInstance() {
        when(userRepository.findById(anyLong())).thenReturn(optionalUser);
        User response = userService.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(SENHA, response.getSenha());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(userRepository.findById(anyLong())).thenThrow(new ObjectNotFountException(OBJETO_NAO_ENCONTRADO));
        try{
            userService.findById(ID);
        } catch (Exception ex){
            assertEquals(ObjectNotFountException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenRuturnAnUserInstance() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> responseList = userService.findAll();

        assertNotNull(responseList);
        assertEquals(1, responseList.size());
        assertEquals(User.class, responseList.get(INDEX).getClass());
        assertEquals(ID, responseList.get(INDEX).getId());
        assertEquals(NAME, responseList.get(INDEX).getName());
        assertEquals(EMAIL, responseList.get(INDEX).getEmail());
        assertEquals(SENHA, responseList.get(INDEX).getSenha());
        assertEquals(user, responseList.get(INDEX));
    }



    @Test
    void whenCreateThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(user);
        var response = userService.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(SENHA, response.getSenha());
        assertEquals(user, response);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, SENHA);
        userDTO = new UserDTO(ID, NAME, EMAIL, SENHA);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, SENHA));
    }
}