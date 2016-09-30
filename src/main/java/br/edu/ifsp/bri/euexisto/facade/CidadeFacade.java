/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.facade;

import br.edu.ifsp.bri.euexisto.composite.CidadeComposite;
import br.edu.ifsp.bri.euexisto.composite.EstadoComposite;
import br.edu.ifsp.bri.euexisto.domain.Cidade;
import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.service.CidadeService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class CidadeFacade {
    
    private static CidadeService cidadeService = new CidadeService();
    
    public static Cidade get(CidadeComposite cidadeComposite){
        // Obter o estado
        EstadoComposite estadoComposite = new EstadoComposite();
        estadoComposite.setNome(cidadeComposite.getNomeEstado());
        estadoComposite.setUf(cidadeComposite.getUf());
        Estado          estado          = EstadoFacade.get(estadoComposite);
        
        Cidade          cidade          = new Cidade();
        List<Cidade>    listaCidade     = new ArrayList<Cidade>();
        
        // Localizar a cidade
        // Se não existir, cadastrar
        listaCidade = cidadeService.list(cidadeComposite.getNomeCidade(), estado.getId());
        if   (listaCidade.size()==0) {
             add(cidadeComposite, estado);
             listaCidade = cidadeService.list(cidadeComposite.getNomeCidade(), estado.getId());
        }
        
        cidade      = (Cidade) listaCidade.get(0);
        
        return cidade;
    }// fim do método get
    
    
    public static void add (CidadeComposite cidadeComposite, Estado estado) {
        Cidade cidade = new Cidade();
        cidade.setNome(cidadeComposite.getNomeCidade());
        cidade.setEstado(estado);
        cidadeService.add(cidade);
    }// fim do método add
}// fim da classe CidadeFacade
