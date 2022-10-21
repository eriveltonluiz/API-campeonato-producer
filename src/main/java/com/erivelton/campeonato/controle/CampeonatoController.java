package com.erivelton.campeonato.controle;

import com.erivelton.campeonato.dto.requisicao.ConfrontoRequisicao;
import com.erivelton.campeonato.dto.requisicao.DadosEquipeRequisicao;
import com.erivelton.campeonato.infraestrutura.servico.OrganizacaoCampeonato;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Validated
@Controller("/api/campeonato")
@RequiredArgsConstructor
public class CampeonatoController {

    private final OrganizacaoCampeonato organizacaoCampeonato;

    @Post
    public void criar(@Body @Valid List<DadosEquipeRequisicao> dadosEquipeRequisicao){
        organizacaoCampeonato.criar(dadosEquipeRequisicao);
    }

    @Post(value = "/confronto")
    public void salvarConfronto(@Body @Valid ConfrontoRequisicao confrontoRequisicao){
        organizacaoCampeonato.mapearConfronto(confrontoRequisicao);
    }

}
