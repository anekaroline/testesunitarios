package br.com.project.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApiApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void testMain() {
        ApiApplication.main(new String[]{});
        assertNotNull(applicationContext, "O contexto da aplicação deve ser carregado");
    }

}
