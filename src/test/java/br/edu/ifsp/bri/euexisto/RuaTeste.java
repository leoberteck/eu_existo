/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto;

import br.edu.ifsp.bri.euexisto.domain.Rua;
import br.edu.ifsp.bri.euexisto.service.RuaService;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class RuaTeste {
    
    public static void main (String[] args) {
        Rua        rua        = new Rua();
        RuaService ruaService = new RuaService();
        
        rua.setNome("Rua Minas gerais");
        ruaService.add(rua);
        
        rua = new Rua();
        rua.setNome("Rua Paraná");
        ruaService.add(rua);
        
        rua = new Rua();
        List<Rua> listaRua = ruaService.list("Rua Minas gerais");
        rua = (Rua) listaRua.get(0);
        rua.setNome("Rua Minas Gerais");
        ruaService.update(rua);
        
        rua = new Rua();
        rua.setNome("Rua Rio de Janeiro");
        ruaService.add(rua);
        
        rua = new Rua();
        rua.setNome("Rua Tiradentes");
        ruaService.add(rua);
                
        rua = new Rua();
        rua.setNome("Rua Floriano Peixoto");
        ruaService.add(rua);
        
        rua = new Rua();
        rua.setNome("Rua Riachuelo");
        ruaService.add(rua);

        listaRua = ruaService.list();
        
        for (int i=0; i<listaRua.size();i++) {
            System.out.println(listaRua.get(i).toString());
        }
    }      
}
