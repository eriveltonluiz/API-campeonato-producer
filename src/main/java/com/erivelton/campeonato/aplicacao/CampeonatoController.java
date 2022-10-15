package com.erivelton.campeonato.aplicacao;

import com.erivelton.campeonato.infraestrutura.mensageria.CampeonatoClient;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Validated
@Controller("/api/campeonato")
@RequiredArgsConstructor
public class CampeonatoController {

    private final CampeonatoClient campeonatoClient;

    @Post
    public void criar(@Body @Valid List<DadosEquipeRequisicao> dadosEquipeRequisicao){
        dadosEquipeRequisicao.forEach(System.out::println);
        campeonatoClient.enviarEquipes("equipes", dadosEquipeRequisicao);
    }

    @Get
    public void buscarTodos(){

    }

    @Post(value = "/confronto")
    public void salvarConfronto(@Body @Valid ConfrontoRequisicao confrontoRequisicao){
        System.out.println(confrontoRequisicao);
    }
}
