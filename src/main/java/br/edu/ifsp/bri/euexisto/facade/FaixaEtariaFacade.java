/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.facade;

import br.edu.ifsp.bri.euexisto.composite.CidadeComposite;
import br.edu.ifsp.bri.euexisto.composite.FaixaEtariaComposite;
import br.edu.ifsp.bri.euexisto.domain.Cidade;
import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.domain.FaixaEtaria;
import br.edu.ifsp.bri.euexisto.service.CidadeService;
import br.edu.ifsp.bri.euexisto.service.EstadoService;
import br.edu.ifsp.bri.euexisto.service.FaixaEtariaService;
import br.edu.ifsp.bri.euexisto.facade.CidadeFacade;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class FaixaEtariaFacade {
    
    public static void add(FaixaEtariaComposite faixaEtariaComposite) {
        FaixaEtaria        faixaEtaria        = new FaixaEtaria();
        FaixaEtariaService faixaEtariaService = new FaixaEtariaService();
        
        EstadoService      estadoService      = new EstadoService();
        Estado             estado             = new Estado();
        List<Estado>       listaEstado        = new ArrayList<Estado>();

        CidadeService      cidadeService      = new CidadeService();
        Cidade             cidade             = new Cidade();
        List<Cidade>       listaCidade        = new ArrayList<Cidade>();

        CidadeComposite    cidadeComposite    = new CidadeComposite();
        CidadeFacade.add(cidadeComposite);
        
        listaEstado = estadoService.list(faixaEtariaComposite.getUf(), "S");
        estado      = (Estado) listaEstado.get(0);
        listaCidade = cidadeService.list(faixaEtariaComposite.getNomeCidade(), estado.getId());
        cidade      = (Cidade) listaCidade.get(0);
        
        faixaEtaria.setDescricao(faixaEtariaComposite.getDescricao());
        faixaEtaria.setIdadeIni(faixaEtariaComposite.getIdadeIni());
        faixaEtaria.setIdadeFim(faixaEtariaComposite.getIdadeFim());
        faixaEtaria.setCidade(cidade);
        faixaEtariaService.add(faixaEtaria);    
    }// fim do método add
}// fim da classe FaixaEtaria Conector