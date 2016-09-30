/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.facade;

import br.edu.ifsp.bri.euexisto.composite.CidadeComposite;
import br.edu.ifsp.bri.euexisto.composite.FaixaEtariaComposite;
import br.edu.ifsp.bri.euexisto.domain.Cidade;
import br.edu.ifsp.bri.euexisto.domain.FaixaEtaria;
import br.edu.ifsp.bri.euexisto.service.CidadeService;
import br.edu.ifsp.bri.euexisto.service.FaixaEtariaService;
import br.edu.ifsp.bri.euexisto.facade.CidadeFacade;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class FaixaEtariaFacade {

    private static FaixaEtariaService faixaEtariaService = new FaixaEtariaService();
    
    public static FaixaEtaria get(FaixaEtariaComposite faixaEtariaComposite){
        // Obter a Cidade
        CidadeComposite cidadeComposite = new CidadeComposite();
        cidadeComposite.setNomeCidade(faixaEtariaComposite.getNomeCidade());
        cidadeComposite.setNomeEstado(faixaEtariaComposite.getNomeEstado());
        cidadeComposite.setUf(faixaEtariaComposite.getUf());
        Cidade          cidade          = CidadeFacade.get(cidadeComposite);

        FaixaEtaria       faixaEtaria      = new FaixaEtaria();
        List<FaixaEtaria> listaFaixaEtaria = new ArrayList<FaixaEtaria>();
        
        // Localizar a faixa Etária para a cidade
        // Se não existir, cadastrar
        listaFaixaEtaria = faixaEtariaService.list(faixaEtariaComposite.getDescricao(), cidade.getId());
        if   (listaFaixaEtaria.size()==0) {
             add(faixaEtariaComposite, cidade);
             listaFaixaEtaria = faixaEtariaService.list(faixaEtariaComposite.getDescricao(), cidade.getId());
        }
        
        faixaEtaria = (FaixaEtaria) listaFaixaEtaria.get(0);
        
        return faixaEtaria;
    }// fim do método get
        
    public static void add(FaixaEtariaComposite faixaEtariaComposite, Cidade cidade) {
        FaixaEtaria        faixaEtaria        = new FaixaEtaria();
        
        faixaEtaria.setDescricao(faixaEtariaComposite.getDescricao());
        faixaEtaria.setIdadeIni(faixaEtariaComposite.getIdadeIni());
        faixaEtaria.setIdadeFim(faixaEtariaComposite.getIdadeFim());
        faixaEtaria.setCidade(cidade);
        faixaEtariaService.add(faixaEtaria);    
    }// fim do método add
}// fim da classe FaixaEtaria Conector