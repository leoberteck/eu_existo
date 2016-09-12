/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto;

import br.edu.ifsp.bri.euexisto.composite.CepComposite;
import br.edu.ifsp.bri.euexisto.facade.CepFacade;
import br.edu.ifsp.bri.euexisto.domain.Bairro;
import br.edu.ifsp.bri.euexisto.domain.Cep;
import br.edu.ifsp.bri.euexisto.domain.Cidade;
import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.domain.Rua;
import br.edu.ifsp.bri.euexisto.service.BairroService;
import br.edu.ifsp.bri.euexisto.service.CepService;
import br.edu.ifsp.bri.euexisto.service.CidadeService;
import br.edu.ifsp.bri.euexisto.service.EstadoService;
import br.edu.ifsp.bri.euexisto.service.RuaService;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class CepTeste {

    public static void main (String args[]) {
        CepService    cepService    = new CepService();
        EstadoService estadoService = new EstadoService();
        CidadeService cidadeService = new CidadeService();
        
        CepComposite  cepComposite  = new CepComposite("16.015-303", 717,             9999, 
                                                       "São Paulo",  "SP", 
                                                       "Araçatuba",  "Jardim Sumaré", "Rua Minas Gerais");
        CepFacade.add(cepComposite);
        
        cepComposite  = new CepComposite("16.201-407", 0,             9999, 
                                         "São Paulo",  "SP", 
                                         "Birigui",    "Residencial Portal da Pérola II", "Rua Pedro Cavalo");
        CepFacade.add(cepComposite);
        
        cepComposite  = new CepComposite("16.011-035", 0,             9999, 
                                         "São Paulo",  "SP", 
                                         "Araçatuba",  "Jardim Paulista", "Rua Paraná");
        CepFacade.add(cepComposite);
        
        cepComposite  = new CepComposite("11.740-000", 0,             0, 
                                         "São Paulo",  "SP", 
                                         "Itanhaém",   "", "");
        // Arrumar - não está deixando inserir quando bairo e rua são nulos
        //CepFacade.add(cepComposite);
        
        List<Estado> listaEstado = estadoService.list(cepComposite.getUf(), "S");
        Estado       estado      = listaEstado.get(0);
        List<Cidade> listaCidade = cidadeService.list(cepComposite.getNomeCidade(), estado.getId());
        Cidade       cidade      = listaCidade.get(0);
        
        List<Cep> listaCep = cepService.list(cidade.getId());
        for (int i=0; i<listaCep.size();i++) {
            System.out.println(listaCep.get(i).toString());
        }
    }        
    
}// fim da classe CepTeste
