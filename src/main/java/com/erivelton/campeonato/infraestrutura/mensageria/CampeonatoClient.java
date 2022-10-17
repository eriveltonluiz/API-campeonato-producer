package com.erivelton.campeonato.infraestrutura.mensageria;

import com.erivelton.campeonato.aplicacao.dto.requisicao.ConfrontoRequisicao;
import com.erivelton.campeonato.aplicacao.dto.requisicao.DadosEquipeRequisicao;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

import java.util.List;

@KafkaClient
public interface CampeonatoClient {

    @Topic("campeonato.equipes")
    void enviarEquipes(@KafkaKey String chave, List<DadosEquipeRequisicao> equipes);

    @Topic("campeonato.acrescimo.confronto")
    void enviarConfronto(@KafkaKey String chave, ConfrontoRequisicao confrontoRequisicao);
}
