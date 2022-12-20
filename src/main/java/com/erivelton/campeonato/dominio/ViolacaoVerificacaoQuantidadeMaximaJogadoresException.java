package com.erivelton.campeonato.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViolacaoVerificacaoQuantidadeMaximaJogadoresException extends RuntimeException{

    private Map<String, String> violacoes = new HashMap<>();

    public ViolacaoVerificacaoQuantidadeMaximaJogadoresException(String mensagem){
        super(mensagem);
    }

    public void adicionarViolacoes(Map<String, String> violacoes){
        this.violacoes.putAll(violacoes);
    }

    public Map<String, String> getViolacoes() {
        return violacoes;
    }

}
