package br.pedro.rproject;

import br.pedro.rproject.repositories.PersonaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
class RProjectApplicationTests {

    @Autowired
    private PersonaRepository repository;

    @Test
    void contextLoads() {
    }

}
