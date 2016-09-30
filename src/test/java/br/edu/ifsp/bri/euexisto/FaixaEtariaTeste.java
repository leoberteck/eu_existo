/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto;

import br.edu.ifsp.bri.euexisto.composite.FaixaEtariaComposite;
import br.edu.ifsp.bri.euexisto.facade.FaixaEtariaFacade;
import br.edu.ifsp.bri.euexisto.domain.Cidade;
import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.domain.FaixaEtaria;
import br.edu.ifsp.bri.euexisto.service.CidadeService;
import br.edu.ifsp.bri.euexisto.service.EstadoService;
import br.edu.ifsp.bri.euexisto.service.FaixaEtariaService;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class FaixaEtariaTeste {

    public static void main (String args[]) {
        FaixaEtaria          faixaEtaria        = new FaixaEtaria();
        FaixaEtariaComposite faixaEtariaComposite;

        
        faixaEtariaComposite = new FaixaEtariaComposite("São Paulo", "SP", "Araçatuba", "De 0 a 2 anos",     0,   2);
        faixaEtaria = FaixaEtariaFacade.get(faixaEtariaComposite);

        faixaEtariaComposite = new FaixaEtariaComposite("São Paulo", "SP", "Araçatuba", "De 2 a 6 anos",     2,   6);
        faixaEtaria = FaixaEtariaFacade.get(faixaEtariaComposite);
        
        faixaEtariaComposite = new FaixaEtariaComposite("São Paulo", "SP", "Araçatuba", "De 6 a 10 anos",    6,   10);
        faixaEtaria = FaixaEtariaFacade.get(faixaEtariaComposite);
        
        faixaEtariaComposite = new FaixaEtariaComposite("São Paulo", "SP", "Araçatuba", "De 10 a 14 anos",   10,  14);
        faixaEtaria = FaixaEtariaFacade.get(faixaEtariaComposite);
        
        faixaEtariaComposite = new FaixaEtariaComposite("São Paulo", "SP", "Araçatuba", "De 14 a 17 anos",   14,  17);
        faixaEtaria = FaixaEtariaFacade.get(faixaEtariaComposite);
        
        faixaEtariaComposite = new FaixaEtariaComposite("São Paulo", "SP", "Araçatuba", "De 17 a 22 anos",   17,  22);
        faixaEtaria = FaixaEtariaFacade.get(faixaEtariaComposite);
        
        faixaEtariaComposite = new FaixaEtariaComposite("São Paulo", "SP", "Araçatuba", "De 22 a 30 anos",   22,  30);
        faixaEtaria = FaixaEtariaFacade.get(faixaEtariaComposite);
        
        faixaEtariaComposite = new FaixaEtariaComposite("São Paulo", "SP", "Araçatuba", "De 30 a 40 anos",   30,  40);
        faixaEtaria = FaixaEtariaFacade.get(faixaEtariaComposite);
        
        faixaEtariaComposite = new FaixaEtariaComposite("São Paulo", "SP", "Araçatuba", "De 40 a 50 anos",   40,  50);
        faixaEtaria = FaixaEtariaFacade.get(faixaEtariaComposite);
        
        faixaEtariaComposite = new FaixaEtariaComposite("São Paulo", "SP", "Araçatuba", "De 50 a 60 anos",   50,  60);
        faixaEtaria = FaixaEtariaFacade.get(faixaEtariaComposite);
        
        faixaEtariaComposite = new FaixaEtariaComposite("São Paulo", "SP", "Araçatuba", "De 60 a 70 anos",   60,  70);
        faixaEtaria = FaixaEtariaFacade.get(faixaEtariaComposite);
        
        faixaEtariaComposite = new FaixaEtariaComposite("São Paulo", "SP", "Araçatuba", "De 70 a 80 anos",   70,  80);
        faixaEtaria = FaixaEtariaFacade.get(faixaEtariaComposite);
        
        faixaEtariaComposite = new FaixaEtariaComposite("São Paulo", "SP", "Araçatuba", "De 80 a 90 anos",   80,  90);
        faixaEtaria = FaixaEtariaFacade.get(faixaEtariaComposite);
        
        faixaEtariaComposite = new FaixaEtariaComposite("São Paulo", "SP", "Araçatuba", "De 90 a 100 anos",  90,  100);
        faixaEtaria = FaixaEtariaFacade.get(faixaEtariaComposite);
        
        faixaEtariaComposite = new FaixaEtariaComposite("São Paulo", "SP", "Araçatuba", "De 100 a 150 anos", 100, 150);
        faixaEtaria = FaixaEtariaFacade.get(faixaEtariaComposite);
        
        
        FaixaEtariaService faixaEtariaService = new FaixaEtariaService();
        
        EstadoService estadoService = new EstadoService();
        List<Estado>  listaEstado   = estadoService.list(faixaEtariaComposite.getUf(), "S");
        Estado        estado        = (Estado) listaEstado.get(0);
              
        CidadeService cidadeService = new CidadeService();
        List<Cidade>  listaCidade   = cidadeService.list(faixaEtariaComposite.getNomeCidade(), estado.getId());
        Cidade        cidade        = (Cidade) listaCidade.get(0);

        List<FaixaEtaria> listaFaixaEtaria = faixaEtariaService.list(cidade.getId());
        for (int i=0; i<listaFaixaEtaria.size();i++) {
            System.out.println(listaFaixaEtaria.get(i).toString());
        }
     }// fim do método main
    
}// fim da classe FaixaEtariaTeste
