package com.erivelton.campeonato.dominio;

import java.util.ArrayList;
import java.util.List;

public class ViolacaoVerificacaoQuantidadeMaximaJogadoresException extends RuntimeException{

    private List<String> violacoes = new ArrayList<>();

    public ViolacaoVerificacaoQuantidadeMaximaJogadoresException(String mensagem){
        super(mensagem);
    }

    public void adicionarViolacoes(List<String> violacoes){
        this.violacoes.addAll(violacoes);
    }

    public List<String> getViolacoes() {
        return violacoes;
    }

}
