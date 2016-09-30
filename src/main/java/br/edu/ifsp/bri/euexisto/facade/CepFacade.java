/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.facade;

import br.edu.ifsp.bri.euexisto.composite.CepComposite;
import br.edu.ifsp.bri.euexisto.composite.CidadeComposite;
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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class CepFacade {

    private static CepService cepService = new CepService();
    
    public static Cep get(CepComposite cepComposite){
        Cep       cep      = new Cep();
        List<Cep> listaCep = new ArrayList<Cep>();
        
        // Localizar o cep
        // Se não existir, cadastrar
        listaCep = cepService.list(cepComposite.getNumeroCep());
        if   (listaCep.size()==0) {
             add(cepComposite);
            listaCep = cepService.list(cepComposite.getNumeroCep());
        }
        
        cep      = (Cep) listaCep.get(0);
        
        return cep;
    }// fim do método get
    
    
    public static void add (CepComposite cepComposite) {
        // Obter a cidade
        CidadeComposite cidadeComposite = new CidadeComposite();
        cidadeComposite.setNomeCidade(cepComposite.getNomeCidade());
        cidadeComposite.setNomeEstado(cepComposite.getNomeEstado());
        cidadeComposite.setUf(cepComposite.getUf());
        Cidade cidade = CidadeFacade.get(cidadeComposite);
        
        // Obter a rua
        Rua    rua    = RuaFacade.get(cepComposite.getNomeRua());
        
        // Obter o bairro
        Bairro bairro = BairroFacade.get(cepComposite.getNomeBairro());

        Cep cep = new Cep();
        cep.setRua(rua);
        cep.setBairro(bairro);
        cep.setCidade(cidade);
        cep.setNumeroCep(cepComposite.getNumeroCep());
        cep.setNumeroIni(cepComposite.getNumeroIni());
        cep.setNumeroFim(cepComposite.getNumeroFim());
        cepService.add(cep);
    }// fim do método add

}// fim da classe CepFacade
