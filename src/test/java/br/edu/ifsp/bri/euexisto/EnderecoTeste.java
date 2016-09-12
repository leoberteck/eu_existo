/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto;

import br.edu.ifsp.bri.euexisto.composite.EnderecoComposite;
import br.edu.ifsp.bri.euexisto.facade.EnderecoFacade;
import br.edu.ifsp.bri.euexisto.domain.Endereco;
import br.edu.ifsp.bri.euexisto.service.EnderecoService;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class EnderecoTeste {
    public static void main (String args[]) {
        EnderecoService    enderecoService    = new EnderecoService();
        /*
        EnderecoComposite enderecoComposite;
        enderecoComposite = new EnderecoComposite ("16.201-407", 0, 9999, "709", "", "São Paulo", "SP", 
                                                   "Birigui", "Residencial Portal da Pérola II", "Rua Pedro Cavalo");
        EnderecoFacade.add(enderecoComposite);
        
        enderecoComposite = new EnderecoComposite ("16.015-303", 717, 9999, "995", "", "São Paulo", "SP", 
                             "Araçatuba", "Jardim Sumaré", "Rua Minas Gerais");
        EnderecoFacade.add(enderecoComposite);
        */ 
        List<Endereco> listaEndereco = enderecoService.list(3);
        for (int i=0; i<listaEndereco.size();i++) {
            System.out.println(listaEndereco.get(i).toString());
        }

    }// fim do método main
    
}// fim da classe EnderecoTeste
