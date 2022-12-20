package com.erivelton.campeonato.controle;

import com.erivelton.campeonato.dto.requisicao.ConfrontoRequisicao;
import com.erivelton.campeonato.infraestrutura.servico.OrganizacaoCampeonato;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Put;
import io.micronaut.validation.Validated;
import jakarta.inject.Named;

import javax.validation.Valid;

@Validated
@Controller("/api/confronto")
public class ConfrontoController {

    private final OrganizacaoCampeonato organizacaoCampeonato;

    public ConfrontoController(@Named("confronto") OrganizacaoCampeonato organizacaoCampeonato) {
        this.organizacaoCampeonato = organizacaoCampeonato;
    }

    @Put
    public void salvarConfronto(@Body @Valid ConfrontoRequisicao confrontoRequisicao){
        organizacaoCampeonato.mapear(confrontoRequisicao);
    }

}
