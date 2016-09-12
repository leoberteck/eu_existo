/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.facade;

import br.edu.ifsp.bri.euexisto.composite.CidadeComposite;
import br.edu.ifsp.bri.euexisto.domain.Cidade;
import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.service.CidadeService;
import br.edu.ifsp.bri.euexisto.service.EstadoService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class CidadeFacade {
    
    public static void add (CidadeComposite cidadeComposite) {
        Cidade        cidade        = new Cidade();
        CidadeService cidadeService = new CidadeService();
        
        Estado        estado        = new Estado();
        EstadoService estadoService = new EstadoService();
        List<Estado>  listaEstado   = new ArrayList<Estado>();
        
        // Localizar o estado
        // Se não existir, cadastrar
        listaEstado = estadoService.list(cidadeComposite.getUf(), "S");
        if   (listaEstado.size()==0) {
             estado.setNome(cidadeComposite.getNomeEstado());
             estado.setUf(cidadeComposite.getUf());
             estadoService.add(estado);
             listaEstado = estadoService.list(cidadeComposite.getUf(), "S");
             estado      = new Estado();
             estado      = (Estado) listaEstado.get(0);
        }
        else estado      = (Estado) listaEstado.get(0);

        cidade.setNome(cidadeComposite.getNomeCidade());
        cidade.setEstado(estado);
        cidadeService.add(cidade);
    }// fim do método add
}// fim da classe CidadeFacade
