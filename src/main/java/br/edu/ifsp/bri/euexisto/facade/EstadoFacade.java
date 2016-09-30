/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.facade;

import br.edu.ifsp.bri.euexisto.composite.EstadoComposite;
import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.service.EstadoService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class EstadoFacade {
    
    private static EstadoService estadoService = new EstadoService();
    
    public static Estado get(EstadoComposite estadoComposite){
        Estado        estado        = new Estado();
        List<Estado>  listaEstado   = new ArrayList<Estado>();
        
        // Localizar o estado
        // Se não existir, cadastrar
        listaEstado = estadoService.list(estadoComposite.getUf(), "S");
        if   (listaEstado.size()==0) {
             add(estadoComposite);
             listaEstado = estadoService.list(estadoComposite.getUf(), "S");
        }
        
        estado      = (Estado) listaEstado.get(0);
        
        return estado;
    }// fim do método get
    
    public static void add(EstadoComposite estadoComposite){
        Estado estado = new Estado();
        estado.setNome(estadoComposite.getNome());
        estado.setUf(estadoComposite.getUf());
        estadoService.add(estado);
     }// fim do método add
}// fim da classe EstadoFacade
