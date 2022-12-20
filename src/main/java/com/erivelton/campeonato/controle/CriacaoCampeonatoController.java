package com.erivelton.campeonato.controle;

import com.erivelton.campeonato.dto.requisicao.DadosCampeonatoRequisicao;
import com.erivelton.campeonato.infraestrutura.servico.OrganizacaoCampeonato;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import jakarta.inject.Named;

import javax.validation.Valid;

@Validated
@Controller("/api/campeonato")
public class CriacaoCampeonatoController {

    private final OrganizacaoCampeonato organizacaoCampeonato;

    public CriacaoCampeonatoController(@Named("criacaoCampeonato") OrganizacaoCampeonato organizacaoCampeonato) {
        this.organizacaoCampeonato = organizacaoCampeonato;
    }

    @Post
    public void criar(@Body @Valid DadosCampeonatoRequisicao dadosCampeonato) {
        organizacaoCampeonato.mapear(dadosCampeonato);
    }

}
