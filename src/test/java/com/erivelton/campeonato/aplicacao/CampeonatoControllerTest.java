package com.erivelton.campeonato.aplicacao;

import com.erivelton.campeonato.infraestrutura.mensageria.CampeonatoClient;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class CampeonatoControllerTest {

    @Inject
    private CampeonatoClient campeonatoClient;

}