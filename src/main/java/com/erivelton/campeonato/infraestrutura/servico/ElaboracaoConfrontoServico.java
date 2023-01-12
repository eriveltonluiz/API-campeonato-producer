package com.erivelton.campeonato.infraestrutura.servico;

import com.erivelton.campeonato.dto.requisicao.ConfrontoRequisicao;
import com.erivelton.campeonato.infraestrutura.mensageria.CampeonatoClient;
import com.erivelton.campeonato.infraestrutura.servico.validacao.ValidacaoCustomizada;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class ElaboracaoConfrontoServico implements OrganizacaoCampeonato<ConfrontoRequisicao>{

    private static final Logger LOG = LoggerFactory.getLogger(ElaboracaoConfrontoServico.class);

    private final CampeonatoClient campeonatoClient;

    private final ValidacaoCustomizada validacaoCustomizada;

    public ElaboracaoConfrontoServico(CampeonatoClient campeonatoClient, @Named("golsJogadores") ValidacaoCustomizada validacaoCustomizada) {
        this.campeonatoClient = campeonatoClient;
        this.validacaoCustomizada = validacaoCustomizada;
    }

    @Override
    public void mapear(ConfrontoRequisicao confrontoRequisicao) {
        LOG.info("Validação do confronto das equipes");
        validacaoCustomizada.limite(confrontoRequisicao);

        campeonatoClient.enviarConfronto(confrontoRequisicao);
        LOG.info("Confronto {} enviado para fila da peça de confrontos de torneio", confrontoRequisicao);
    }
}
