/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.facade;

import br.edu.ifsp.bri.euexisto.domain.Bairro;
import br.edu.ifsp.bri.euexisto.service.BairroService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Helen
 */
public class BairroFacade {
    
    private static BairroService bairroService = new BairroService();
    
    public static Bairro get(String nomeBairro){
        Bairro        bairro        = new Bairro();
        List<Bairro>  listaBairro   = new ArrayList<Bairro>();
        
        // Localizar o bairro
        // Se não existir, cadastrar
        listaBairro = bairroService.list(nomeBairro);
        if   (listaBairro.size()==0) {
             add(nomeBairro);
        }
        
        listaBairro = bairroService.list(nomeBairro);
        bairro      = (Bairro) listaBairro.get(0);
        
        return bairro;
    }// fim do método get
    
    public static void add(String nomeBairro){
        Bairro bairro = new Bairro();
        bairro.setNome(nomeBairro);
        bairroService.add(bairro);
     }// fim do método add    
}
