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

    public static void add(CepComposite cepComposite){
        Cep                cep                = new Cep();
        Estado             estado             = new Estado();
        Cidade             cidade             = new Cidade();
        Bairro             bairro             = new Bairro();
        Rua                rua                = new Rua();
        
        EstadoService      estadoService      = new EstadoService();
        CidadeService      cidadeService      = new CidadeService();
        CepService         cepService         = new CepService();
        BairroService      bairroService      = new BairroService();
        RuaService         ruaService         = new RuaService();

        List<Estado>       listaEstado        = new ArrayList<Estado>();
        List<Cidade>       listaCidade        = new ArrayList<Cidade>();
        List<Bairro>       listaBairro        = new ArrayList<Bairro>();
        List<Rua>          listaRua           = new ArrayList<Rua>();
        
        CidadeComposite    cidadeComposite    = new CidadeComposite(cepComposite.getNomeCidade(), 
                                                                    cepComposite.getNomeEstado(),
                                                                    cepComposite.getUf());
        CidadeFacade.add(cidadeComposite);
        
        listaEstado = estadoService.list(cepComposite.getUf(), "S");
        estado      = (Estado) listaEstado.get(0);
               
        listaCidade = cidadeService.list(cepComposite.getNomeCidade(), estado.getId());
        cidade      = (Cidade) listaCidade.get(0);
        
        // Localizar o bairro
        // Se não existir, cadastrar
        if   (!cepComposite.getNomeBairro().equals("")) {
             listaBairro = bairroService.list(cepComposite.getNomeBairro());
             if   (listaBairro.size()==0) {
                  bairro.setNome(cepComposite.getNomeBairro());
                  bairroService.add(bairro);
                  listaBairro = bairroService.list(cepComposite.getNomeBairro());
                  bairro      = new Bairro();
                  bairro      = (Bairro) listaBairro.get(0);
             }
             else bairro      = (Bairro) listaBairro.get(0);
        }
        
        // Localizar a Rua
        // Se não existir, cadastrar
        if   (!cepComposite.getNomeRua().equals("")) {
             listaRua = ruaService.list(cepComposite.getNomeRua());
             if   (listaRua.size()==0) {
                  rua.setNome(cepComposite.getNomeRua());
                  ruaService.add(rua);
                  listaRua = ruaService.list(cepComposite.getNomeRua());
                  rua      = new Rua();
                  rua      = (Rua) listaRua.get(0);
             }
             else rua      = (Rua) listaRua.get(0);
        }
        
        cep.setNumeroCep(cepComposite.getNumeroCep());
        cep.setNumeroIni(cepComposite.getNumeroIni());
        cep.setNumeroFim(cepComposite.getNumeroFim());
        cep.setCidade(cidade);
        cep.setBairro(bairro);
        cep.setRua(rua);
        cepService.add(cep);
    }// fim do método add
    
}// fim da classe CepFacade
