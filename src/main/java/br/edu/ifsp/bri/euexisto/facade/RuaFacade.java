/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.facade;

import br.edu.ifsp.bri.euexisto.domain.Rua;
import br.edu.ifsp.bri.euexisto.service.RuaService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Helen
 */
public class RuaFacade {
    
    private static RuaService ruaService = new RuaService();
    
    public static Rua get(String nomeRua){
        Rua        rua        = new Rua();
        List<Rua>  listaRua   = new ArrayList<Rua>();
        
        // Localizar a rua
        // Se não existir, cadastrar
        listaRua = ruaService.list(nomeRua);
        if   (listaRua.size()==0) {
             add(nomeRua);
        }
        
        listaRua = ruaService.list(nomeRua);
        rua      = (Rua) listaRua.get(0);
        
        return rua;
    }// fim do método get
    
    public static void add(String nomeRua){
        Rua rua = new Rua();
        rua.setNome(nomeRua);
        ruaService.add(rua);
     }// fim do método add
}
