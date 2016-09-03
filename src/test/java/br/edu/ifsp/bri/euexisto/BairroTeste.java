/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto;

import br.edu.ifsp.bri.euexisto.domain.Bairro;
import br.edu.ifsp.bri.euexisto.service.BairroService;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class BairroTeste {
    
    public static void main (String[] args) {
        Bairro        bairro        = new Bairro();
        BairroService bairroService = new BairroService();
        bairro.setNome("Jardim Sumaré");
        System.out.println("aqui antes");
        bairroService.add(bairro);
        List<Bairro> listaBairro = bairroService.list();
        
        for (int i=0; i<listaBairro.size();i++) {
            System.out.println(listaBairro.get(i).toString());
        }
    }    
}
