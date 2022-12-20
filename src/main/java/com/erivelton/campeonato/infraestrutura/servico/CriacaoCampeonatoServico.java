package com.erivelton.campeonato.infraestrutura.servico;

import com.erivelton.campeonato.dto.requisicao.DadosCampeonatoRequisicao;
import com.erivelton.campeonato.infraestrutura.mensageria.CampeonatoClient;
import com.erivelton.campeonato.infraestrutura.servico.validacao.ValidacaoCustomizada;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
public class CriacaoCampeonatoServico implements OrganizacaoCampeonato<DadosCampeonatoRequisicao>{

    private final CampeonatoClient campeonatoClient;

    private final ValidacaoCustomizada validacaoCustomizada;

    public CriacaoCampeonatoServico(CampeonatoClient campeonatoClient, @Named("criacaoCampeoanto") ValidacaoCustomizada validacaoCustomizada) {
        this.campeonatoClient = campeonatoClient;
        this.validacaoCustomizada = validacaoCustomizada;
    }

    @Override
    public void mapear(DadosCampeonatoRequisicao dadosCampeonato) {
        validacaoCustomizada.limite(dadosCampeonato);
        campeonatoClient.enviarEquipes(dadosCampeonato.getDadosEquipe());
    }
}
