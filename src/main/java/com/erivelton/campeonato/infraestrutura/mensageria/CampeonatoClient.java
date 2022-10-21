package com.erivelton.campeonato.infraestrutura.mensageria;

import com.erivelton.campeonato.dto.requisicao.ConfrontoRequisicao;
import com.erivelton.campeonato.dto.requisicao.DadosEquipeRequisicao;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;

import java.util.List;

@KafkaClient
public interface CampeonatoClient {

    @Topic("CAMPEONATO.EQUIPES")
    void enviarEquipes(List<DadosEquipeRequisicao> equipes);

    @Topic("CAMPEONATO.ACRESCIMO.CONFRONTO")
    void enviarConfronto(ConfrontoRequisicao confrontoRequisicao);
}
