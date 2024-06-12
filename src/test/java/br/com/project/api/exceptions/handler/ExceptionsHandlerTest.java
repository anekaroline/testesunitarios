package br.com.project.api.exceptions.handler;

import br.com.project.api.exceptions.DataIntegrityViolationException;
import br.com.project.api.exceptions.ObjectNotFountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class ExceptionsHandlerTest {
    @InjectMocks
    private ExceptionsHandler handler;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntity() {
        var response = handler.objectNotFound(
                new ObjectNotFountException("Objeto não encontrado"), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("Objeto não encontrado",response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void whenDataIntegrityViolationExceptionThenReturnAResponseEntity() {

        var response = handler.dataIntegrityViolation(
                new DataIntegrityViolationException("E-mail já está associado a outro cadastro"),
                new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("E-mail já está associado a outro cadastro",response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }
}