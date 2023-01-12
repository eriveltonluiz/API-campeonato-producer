package com.erivelton.campeonato.controle;

import com.erivelton.campeonato.dto.requisicao.ConfrontoRequisicao;
import com.erivelton.campeonato.infraestrutura.servico.OrganizacaoCampeonato;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Put;
import io.micronaut.validation.Validated;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@Validated
@Controller("/api/confronto")
public class ConfrontoController {

    private static final Logger LOG = LoggerFactory.getLogger(ConfrontoController.class);

    private final OrganizacaoCampeonato organizacaoCampeonato;

    public ConfrontoController(@Named("confronto") OrganizacaoCampeonato organizacaoCampeonato) {
        this.organizacaoCampeonato = organizacaoCampeonato;
    }

    @Put
    public void salvarConfronto(@Body @Valid ConfrontoRequisicao confrontoRequisicao){
        LOG.debug("Recebendo confronto a ser atualizado: {}", confrontoRequisicao);
        organizacaoCampeonato.mapear(confrontoRequisicao);
    }

}
