package com.dev2ever.service;

import com.dev2ever.config.LocalConfigurationTest;
import com.dev2ever.model.Author;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.junit.jupiter.api.Test;

//@SpringJUnitConfig(classes= LocalConfigurationTest.class)
@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = LocalConfigurationTest.class)
@ActiveProfiles({"local","production"})
public class ServiceAuthorTest {

    @Autowired
    private ServiceAuthor serviceAuthor;

    @Test
    public void saveAuthor() {
        Author author = new Author();
        author.setEmail("test@test.com");
        author.setPhone("000-000-0000");
        author.setName("John");
        author.setLastName("Doe");
        serviceAuthor.save(author);
    }
}
