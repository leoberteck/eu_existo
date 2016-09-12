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
import br.edu.ifsp.bri.euexisto.domain.Cidade;
import br.edu.ifsp.bri.euexisto.domain.Endereco;
import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.domain.Rua;
import br.edu.ifsp.bri.euexisto.service.BairroService;
import br.edu.ifsp.bri.euexisto.service.CepService;
import br.edu.ifsp.bri.euexisto.service.CidadeService;
import br.edu.ifsp.bri.euexisto.service.EnderecoService;
import br.edu.ifsp.bri.euexisto.service.EstadoService;
import br.edu.ifsp.bri.euexisto.service.RuaService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class EnderecoFacade {
    public static void add (EnderecoComposite enderecoComposite) {
        Cep                cep                = new Cep();
        Estado             estado             = new Estado();
        Cidade             cidade             = new Cidade();
        Bairro             bairro             = new Bairro();
        Rua                rua                = new Rua();
        Endereco           endereco           = new Endereco();
        
        EstadoService      estadoService      = new EstadoService();
        CidadeService      cidadeService      = new CidadeService();
        CepService         cepService         = new CepService();
        BairroService      bairroService      = new BairroService();
        RuaService         ruaService         = new RuaService();
        EnderecoService    enderecoService    = new EnderecoService();

        List<Estado>       listaEstado        = new ArrayList<Estado>();
        List<Cidade>       listaCidade        = new ArrayList<Cidade>();
        List<Bairro>       listaBairro        = new ArrayList<Bairro>();
        List<Rua>          listaRua           = new ArrayList<Rua>();
        List<Cep>          listaCep           = new ArrayList<Cep>();
        
        CepComposite       cepComposite       = new CepComposite(enderecoComposite.getNumeroCep(),  enderecoComposite.getNumeroIni(),
                                                                 enderecoComposite.getNumeroFim(),  enderecoComposite.getNomeEstado(),
                                                                 enderecoComposite.getUf(),         enderecoComposite.getNomeCidade(),
                                                                 enderecoComposite.getNomeBairro(), enderecoComposite.getNomeRua());
        CepFacade.add(cepComposite);
        
        listaEstado = estadoService.list(enderecoComposite.getUf(), "S");
        estado      = (Estado) listaEstado.get(0);
        
        listaCidade = cidadeService.list(enderecoComposite.getNomeCidade(), estado.getId());
        cidade      = (Cidade) listaCidade.get(0);
        
        listaCep    = cepService.list(enderecoComposite.getNumeroCep());
        cep         = (Cep) listaCep.get(0);
        
        // Localizar o bairro
        // Se não existir, cadastrar
        if   (!enderecoComposite.getNomeBairro().equals("")) {
             listaBairro = bairroService.list(enderecoComposite.getNomeBairro());
             if   (listaBairro.size()==0) {
                  bairro.setNome(enderecoComposite.getNomeBairro());
                  bairroService.add(bairro);
                  listaBairro = bairroService.list(enderecoComposite.getNomeBairro());
                  bairro      = new Bairro();
                  bairro      = (Bairro) listaBairro.get(0);
             }
             else bairro      = (Bairro) listaBairro.get(0);
        }
        
        // Localizar a Rua
        // Se não existir, cadastrar
        if   (!enderecoComposite.getNomeRua().equals("")) {
             listaRua = ruaService.list(enderecoComposite.getNomeRua());
             if   (listaRua.size()==0) {
                  rua.setNome(enderecoComposite.getNomeRua());
                  ruaService.add(rua);
                  listaRua = ruaService.list(enderecoComposite.getNomeRua());
                  rua      = new Rua();
                  rua      = (Rua) listaRua.get(0);
             }
             else rua      = (Rua) listaRua.get(0);
        }

        endereco.setNumero(enderecoComposite.getNumero());
        endereco.setComplemento(enderecoComposite.getComplemento());
        endereco.setCep(cep);
        endereco.setBairro(bairro);
        endereco.setRua(rua);
        enderecoService.add(endereco);
    } // fim do metodo add
    
}// fim da classe EnderecoFacade
