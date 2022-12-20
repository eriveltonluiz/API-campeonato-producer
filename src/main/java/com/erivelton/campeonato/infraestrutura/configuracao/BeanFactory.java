package com.erivelton.campeonato.infraestrutura.configuracao;

import com.erivelton.campeonato.infraestrutura.mensageria.CampeonatoClient;
import com.erivelton.campeonato.infraestrutura.servico.CriacaoCampeonatoServico;
import com.erivelton.campeonato.infraestrutura.servico.ElaboracaoConfrontoServico;
import com.erivelton.campeonato.infraestrutura.servico.OrganizacaoCampeonato;
import com.erivelton.campeonato.infraestrutura.servico.validacao.ValidacaoCustomizada;
import com.erivelton.campeonato.infraestrutura.servico.validacao.ValidacaoGolsJogadores;
import com.erivelton.campeonato.infraestrutura.servico.validacao.ValidacaoQuantidadeMaximaJogadores;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Named;

@Factory
public class BeanFactory {

    @Bean
    @Named("quantidadeMaximaJogadores")
    public ValidacaoCustomizada validacaoCustomizadaQuantidadeMaximaJogadores(){
        return new ValidacaoQuantidadeMaximaJogadores();
    }

    @Bean
    @Named("golsJogadores")
    public ValidacaoCustomizada validacaoCustomizadaGolsJogadores(){
        return new ValidacaoGolsJogadores();
    }

    @Bean
    @Named("confronto")
    public OrganizacaoCampeonato getElaboracaoConfrontoServico(CampeonatoClient campeonatoClient, @Named("golsJogadores") ValidacaoCustomizada validacaoCustomizada){
        return new ElaboracaoConfrontoServico(campeonatoClient, validacaoCustomizada);
    }

    @Bean
    @Named("criacaoCampeonato")
    public OrganizacaoCampeonato getCriacaoCampeonatoServico(CampeonatoClient campeonatoClient, @Named("quantidadeMaximaJogadores") ValidacaoCustomizada validacaoCustomizada){
        return new CriacaoCampeonatoServico(campeonatoClient, validacaoCustomizada);
    }

}
