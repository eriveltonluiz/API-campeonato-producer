package com.erivelton.campeonato.controle;

import com.erivelton.campeonato.dto.requisicao.ConfrontoRequisicao;
import com.erivelton.campeonato.dto.requisicao.DadosCampeonatoRequisicao;
import com.erivelton.campeonato.infraestrutura.servico.OrganizacaoCampeonato;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

//1 passo adicionar validação da qtdMaximaDe jogadores
//2 passo melhorar as mensagens de validação
//3 passo corrigir tópicos da validação(falta do atributo mensagem)


@RequiredArgsConstructor
@Validated
@Controller("/api/campeonato")
public class CampeonatoController {

    private final OrganizacaoCampeonato organizacaoCampeonato;

    @Post
    public void criar(@Body @Valid DadosCampeonatoRequisicao dadosCampeonato) throws Exception{
        organizacaoCampeonato.criar(dadosCampeonato.getDadosEquipe());
    }

    @Post(value = "/confronto")
    public void salvarConfronto(@Body @Valid ConfrontoRequisicao confrontoRequisicao){
        organizacaoCampeonato.mapearConfronto(confrontoRequisicao);
    }

}
