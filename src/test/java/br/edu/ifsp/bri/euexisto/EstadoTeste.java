/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto;

import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.domain.EstadoQtde;
import br.edu.ifsp.bri.euexisto.service.EstadoService;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class EstadoTeste {
    
    public static void main (String[] args) {
        Estado        estado        = new Estado();
        EstadoService estadoService = new EstadoService();
        
        estado.setNome("São Paulo");
        estado.setUf("SP");
        estadoService.add(estado);
        
        estado = new Estado();
        estado.setNome("Rio de Janeiro");
        estado.setUf("RJ");
        estadoService.add(estado);
        
        estado = new Estado();
        estado.setNome("Acre");
        estado.setUf("AC");
        estadoService.add(estado);   
        
        estado = new Estado();
        estado.setNome("Minas Gerais");
        estado.setUf("MG");
        estadoService.add(estado);
        
        estado = new Estado();
        estado.setNome("Mato Grosso");
        estado.setUf("MT");
        estadoService.add(estado);
        
        estado = new Estado();
        estado.setNome("Mato Grosso do Sul");
        estado.setUf("MS");
        estadoService.add(estado);
        
        List<Estado> listaEstado = estadoService.list();
        for (int i=0; i<listaEstado.size();i++) {
            System.out.println(listaEstado.get(i).toString());
        }
        
        /*
        List<Estado> listaEstado = estadoService.listEstado("SC", "S");
        if   (listaEstado.size()==0)
             System.out.println("Estado não encontrado");
        else System.out.println(listaEstado.get(0).toString());
        */
        
        /*
        EstadoQtde estadoQtde = new EstadoQtde();
        List<Object> listaEstadoQtde = estadoService.listEstadoQtde();
        for (int i=0; i<listaEstadoQtde.size();i++){
            estadoQtde = (EstadoQtde) listaEstadoQtde.get(i);
            System.out.println(estadoQtde.getNome() + " " + estadoQtde.getUf() + estadoQtde.getQtde());
        }
        */
        EstadoQtde estadoQtde = new EstadoQtde();
        List<EstadoQtde> listaEstadoQtde = estadoService.listEstadoQtde();
        for (int i=0; i<listaEstadoQtde.size();i++){
            estadoQtde = listaEstadoQtde.get(i);
            System.out.println(estadoQtde.getSigla() + " " + estadoQtde.getNome() + " " + estadoQtde.getQtde());
            
            
            /*
            estadoQtde = (EstadoQtde)(listaEstadoQtde.get(i));
            System.out.println(estadoQtde.getSigla() + " " + estadoQtde.getNome() + " " + estadoQtde.getQtde());
*/
        }
    }
    
}
