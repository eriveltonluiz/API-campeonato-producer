package com.erivelton.campeonato.infraestrutura.servico;

import com.erivelton.campeonato.dto.requisicao.ConfrontoRequisicao;
import com.erivelton.campeonato.infraestrutura.mensageria.CampeonatoClient;
import com.erivelton.campeonato.infraestrutura.servico.validacao.ValidacaoCustomizada;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
public class ElaboracaoConfrontoServico implements OrganizacaoCampeonato<ConfrontoRequisicao>{

    private final CampeonatoClient campeonatoClient;

    private final ValidacaoCustomizada validacaoCustomizada;

    public ElaboracaoConfrontoServico(CampeonatoClient campeonatoClient, @Named("golsJogadores") ValidacaoCustomizada validacaoCustomizada) {
        this.campeonatoClient = campeonatoClient;
        this.validacaoCustomizada = validacaoCustomizada;
    }

    @Override
    public void mapear(ConfrontoRequisicao confrontoRequisicao) {
        validacaoCustomizada.limite(confrontoRequisicao);
        campeonatoClient.enviarConfronto(confrontoRequisicao);
    }
}
