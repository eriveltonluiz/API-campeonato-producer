package com.erivelton.campeonato.infraestrutura.servico;

import com.erivelton.campeonato.dto.requisicao.ConfrontoRequisicao;
import com.erivelton.campeonato.dto.requisicao.DadosEquipeRequisicao;

import java.util.List;

public interface OrganizacaoCampeonato<T> {

    void mapear(T objeto);
}