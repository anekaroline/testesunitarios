package br.com.project.api.controller;

import br.com.project.api.domain.User;
import br.com.project.api.domain.dto.UserDTO;
import br.com.project.api.service.api.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class UserControllerTest {

    public static final long ID = 1L;
    public static final String NAME = "User";
    public static final String EMAIL = "XXXXXXXXXXXXXX";
    public static final String SENHA = "123";
    public static final int INDEX = 0;

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService service;

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
    void whenFindByIdThenReturnAnUserInstance() {
        when(service.findById(anyLong())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        var result = controller.findById(ID);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(200, result.getStatusCode().value());
        assertEquals(UserDTO.class, result.getBody().getClass());
        assertEquals(ID, result.getBody().getId());
        assertEquals(NAME, result.getBody().getName());
        assertEquals(EMAIL, result.getBody().getEmail());
        assertEquals(SENHA, result.getBody().getSenha());

    }

    @Test
    void whenFindByAllThenReturnAnListUserInstance() {
        when(service.findAll()).thenReturn(List.of(user));
        when(mapper.map(any(), any())).thenReturn(userDTO);

        var result = controller.findAll();

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(200, result.getStatusCode().value());
        assertEquals(ArrayList.class, result.getBody().getClass());
        assertEquals(UserDTO.class, result.getBody().get(INDEX).getClass());
        assertEquals(ID, result.getBody().get(INDEX).getId());
        assertEquals(NAME, result.getBody().get(INDEX).getName());
        assertEquals(EMAIL, result.getBody().get(INDEX).getEmail());
        assertEquals(SENHA, result.getBody().get(INDEX).getSenha());
    }

    @Test
    void whenCreateThenReturnAnListUserInstance() {
        when(service.create(any())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        var result = controller.create(userDTO);
        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(201, result.getStatusCode().value());
        assertEquals(UserDTO.class, result.getBody().getClass());
        assertEquals(ID, result.getBody().getId());
        assertEquals(NAME, result.getBody().getName());
        assertEquals(EMAIL, result.getBody().getEmail());
        assertEquals(SENHA, result.getBody().getSenha());
    }

    @Test
    void whenUpdateThenReturnAnListUserInstance() {
        when(service.update(anyLong(), any())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        var result = controller.update(ID, userDTO);
        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(200, result.getStatusCode().value());
        assertEquals(UserDTO.class, result.getBody().getClass());
        assertEquals(ID, result.getBody().getId());
        assertEquals(NAME, result.getBody().getName());
        assertEquals(EMAIL, result.getBody().getEmail());
        assertEquals(SENHA, result.getBody().getSenha());
    }

    @Test
    void whenDeleteThenReturnAnListUserInstance() {
        doNothing().when(service).delete(anyLong());
        var result = controller.delete(ID);

        assertNotNull(result);
        assertNull(result.getBody());
        assertEquals(ResponseEntity.class, result.getClass());
        assertEquals(204, result.getStatusCode().value());
        verify(service, times(1)).delete(anyLong());
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, SENHA);
        userDTO = new UserDTO(ID, NAME, EMAIL, SENHA);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, SENHA));
    }
}