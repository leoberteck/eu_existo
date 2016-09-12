/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto;

import br.edu.ifsp.bri.euexisto.composite.CidadeComposite;
import br.edu.ifsp.bri.euexisto.facade.CidadeFacade;
import br.edu.ifsp.bri.euexisto.domain.Cidade;
import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.service.CidadeService;
import br.edu.ifsp.bri.euexisto.service.EstadoService;
import java.util.ArrayList;
import java.util.List;
import oracle.net.aso.i;

/**
 *
 * @author gahsabio
 */
public class CidadeTeste {
    
    public static void main (String args[]) {        
        CidadeComposite cidadeComposite = new CidadeComposite();
        cidadeComposite.setNomeCidade("Araçatuba");
        cidadeComposite.setNomeEstado("Sao Paulo");
        cidadeComposite.setUf("SP");
        CidadeFacade.add(cidadeComposite);
        
        cidadeComposite = new CidadeComposite("Birigui","Sao Paulo","SP");
        CidadeFacade.add(cidadeComposite);

        cidadeComposite = new CidadeComposite("Rio de Janeiro","Rio de Janeiro","RJ");
        CidadeFacade.add(cidadeComposite);
        
        EstadoService estadoService = new EstadoService();
        List<Estado>  listaEstado   = estadoService.list(cidadeComposite.getUf(), "S");
        Estado        estado        = (Estado) listaEstado.get(0);
              
        Cidade        cidade        = new Cidade();
        CidadeService cidadeService = new CidadeService();

        List<Cidade> listaCidade = cidadeService.list(estado.getId());
        for (int i=0; i<listaCidade.size();i++) {
            System.out.println(listaCidade.get(i).toString());
        }
        
        
        listaCidade = cidadeService.list();
        for (int i=0; i<listaCidade.size();i++) {
            System.out.println(listaCidade.get(i).toString());
        }
    }// fim do método msind
    
}// fim da classe CidadeTeste
