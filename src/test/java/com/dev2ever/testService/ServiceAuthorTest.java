package com.dev2ever.testService;

import com.dev2ever.config.LocalConfigurationTest;
import com.dev2ever.model.Author;
import com.dev2ever.service.ServiceAuthor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringJUnitConfig(classes= LocalConfigurationTest.class)
@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = LocalConfigurationTest.class)
public class ServiceAuthorTest {


    @MockBean
    private ServiceAuthor serviceAuthor;

    @Test
    public void saveAuthor() {
        Author author = new Author();
        author.setEmail("test@test.com");
        author.setPhone("000-000-0000");
        author.setName("John");
        author.setLastName("Doe");
        serviceAuthor.save(author);
        assertNotNull(serviceAuthor.getAll());
    }
}
