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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class UserControllerTest {

    public static final long ID = 1L;
    public static final String NAME = "User";
    public static final String EMAIL = "XXXXXXXXXXXXXX";
    public static final String SENHA = "123";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final int INDEX = 0;
    public static final String E_MAIL_STRING_EXCEPTION = "E-mail já está associado a outro cadastro";

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
    void whenFindByIdThenRuturnAnUserInstance() {
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
    void findAll() {
    }

    @Test
    void create() {
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