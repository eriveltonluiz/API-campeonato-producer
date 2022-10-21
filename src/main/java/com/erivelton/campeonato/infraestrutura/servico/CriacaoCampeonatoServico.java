package com.erivelton.campeonato.infraestrutura.servico;

import com.erivelton.campeonato.dominio.ViolacaoVerificacaoQuantidadeTimesException;
import com.erivelton.campeonato.dto.requisicao.ConfrontoRequisicao;
import com.erivelton.campeonato.dto.requisicao.DadosEquipeRequisicao;
import com.erivelton.campeonato.infraestrutura.mensageria.CampeonatoClient;
import com.erivelton.campeonato.infraestrutura.validacao.VerificarQuantidadeTimes;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Singleton
public class CriacaoCampeonatoServico implements OrganizacaoCampeonato{

    private final CampeonatoClient campeonatoClient;

    private final VerificarQuantidadeTimes verificarQuantidadeTimes;

    @Override
    public void criar(List<DadosEquipeRequisicao> dadosEquipeRequisicao) {
        try {
            verificarQuantidadeTimes.validar(dadosEquipeRequisicao.size());
        } catch (ViolacaoVerificacaoQuantidadeTimesException e) {
            throw new RuntimeException(e);
        }
        campeonatoClient.enviarEquipes(dadosEquipeRequisicao);
    }

    @Override
    public void mapearConfronto(ConfrontoRequisicao confrontoRequisicao) {
        campeonatoClient.enviarConfronto(confrontoRequisicao);
    }
}
