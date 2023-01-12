package com.erivelton.campeonato.infraestrutura.servico;

import com.erivelton.campeonato.dto.requisicao.DadosCampeonatoRequisicao;
import com.erivelton.campeonato.infraestrutura.mensageria.CampeonatoClient;
import com.erivelton.campeonato.infraestrutura.servico.validacao.ValidacaoCustomizada;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class CriacaoCampeonatoServico implements OrganizacaoCampeonato<DadosCampeonatoRequisicao>{

    private static final Logger LOG = LoggerFactory.getLogger(CriacaoCampeonatoServico.class);

    private final CampeonatoClient campeonatoClient;

    private final ValidacaoCustomizada validacaoCustomizada;

    public CriacaoCampeonatoServico(CampeonatoClient campeonatoClient, @Named("criacaoCampeoanto") ValidacaoCustomizada validacaoCustomizada) {
        this.campeonatoClient = campeonatoClient;
        this.validacaoCustomizada = validacaoCustomizada;
    }

    @Override
    public void mapear(DadosCampeonatoRequisicao dadosCampeonato) {
        LOG.info("Validação dos dados do campeonato");
        validacaoCustomizada.limite(dadosCampeonato);

        campeonatoClient.enviarEquipes(dadosCampeonato.getDadosEquipe());
        LOG.debug("Dados do campeonato {} enviado para fila da peça de confrontos de torneio", dadosCampeonato);
    }
}
