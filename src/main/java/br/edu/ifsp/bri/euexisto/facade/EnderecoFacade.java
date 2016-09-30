/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.facade;

import br.edu.ifsp.bri.euexisto.composite.CepComposite;
import br.edu.ifsp.bri.euexisto.composite.EnderecoComposite;
import br.edu.ifsp.bri.euexisto.domain.Bairro;
import br.edu.ifsp.bri.euexisto.domain.Cep;
import br.edu.ifsp.bri.euexisto.domain.Endereco;
import br.edu.ifsp.bri.euexisto.domain.Rua;
import br.edu.ifsp.bri.euexisto.service.BairroService;
import br.edu.ifsp.bri.euexisto.service.CepService;
import br.edu.ifsp.bri.euexisto.service.EnderecoService;
import br.edu.ifsp.bri.euexisto.service.RuaService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class EnderecoFacade {

    private static EnderecoService enderecoService = new EnderecoService();
    
    public static Endereco get(EnderecoComposite enderecoComposite){
        Endereco       endereco      = new Endereco();
        List<Endereco> listaEndereco = new ArrayList<Endereco>();
        
        // Localizar o endereco
        // Se não existir, cadastrar
        String cepFinal = enderecoComposite.getNumeroCep().substring(7, 10);
        System.out.println("************************************");
        System.out.println("CEP final " + cepFinal + " " + enderecoComposite.getNumeroCep());
        System.out.println("************************************");
        if   (cepFinal.equals("000")) {
             listaEndereco = enderecoService.list(enderecoComposite.getNumeroCep(), enderecoComposite.getNomeRua(), enderecoComposite.getNumero());
        }
        else {
             listaEndereco = enderecoService.list(enderecoComposite.getNumeroCep(), enderecoComposite.getNumero());
        }
        
        if   (listaEndereco.size()==0) {
             add(enderecoComposite);
             listaEndereco = enderecoService.list(enderecoComposite.getNumeroCep(), enderecoComposite.getNumero());
        }
        
        endereco = (Endereco) listaEndereco.get(0);
        
        return endereco;
    }// fim do método get
    
    
    public static void add (EnderecoComposite enderecoComposite) {
        Cep                cep                = new Cep();
        Bairro             bairro             = new Bairro();
        Rua                rua                = new Rua();
        
        CepService         cepService         = new CepService();
        BairroService      bairroService      = new BairroService();
        RuaService         ruaService         = new RuaService();

        List<Cep>          listaCep           = new ArrayList<Cep>();
        List<Bairro>       listaBairro        = new ArrayList<Bairro>();
        List<Rua>          listaRua           = new ArrayList<Rua>();
        
        CepComposite       cepComposite       = new CepComposite(enderecoComposite.getNumeroCep(),  enderecoComposite.getNumeroIni(),
                                                                 enderecoComposite.getNumeroFim(),  enderecoComposite.getNomeEstado(),
                                                                 enderecoComposite.getUf(),         enderecoComposite.getNomeCidade(),
                                                                 enderecoComposite.getNomeBairro(), enderecoComposite.getNomeRua());
        cep = CepFacade.get(cepComposite);
        
        String cepFinal = enderecoComposite.getNumeroCep().substring(7, 10);
        if   (!cepFinal.equals("000")) {
             rua    = RuaFacade.get(enderecoComposite.getNomeRua());
             bairro = BairroFacade.get(enderecoComposite.getNomeBairro());
        }

        Endereco endereco = new Endereco();
        endereco.setNumero(enderecoComposite.getNumero());
        endereco.setComplemento(enderecoComposite.getComplemento());
        endereco.setCep(cep);
        endereco.setBairro(bairro);
        endereco.setRua(rua);
        enderecoService.add(endereco);
    } // fim do metodo add
    
}// fim da classe EnderecoFacade
