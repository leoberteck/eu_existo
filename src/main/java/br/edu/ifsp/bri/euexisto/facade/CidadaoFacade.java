/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.facade;

import br.edu.ifsp.bri.euexisto.composite.CepComposite;
import br.edu.ifsp.bri.euexisto.composite.CidadaoComposite;
import br.edu.ifsp.bri.euexisto.composite.EnderecoComposite;
import br.edu.ifsp.bri.euexisto.domain.Bairro;
import br.edu.ifsp.bri.euexisto.domain.Cep;
import br.edu.ifsp.bri.euexisto.domain.Cidadao;
import br.edu.ifsp.bri.euexisto.domain.Cidade;
import br.edu.ifsp.bri.euexisto.domain.Endereco;
import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.domain.Rua;
import br.edu.ifsp.bri.euexisto.service.BairroService;
import br.edu.ifsp.bri.euexisto.service.CepService;
import br.edu.ifsp.bri.euexisto.service.CidadaoService;
import br.edu.ifsp.bri.euexisto.service.CidadeService;
import br.edu.ifsp.bri.euexisto.service.EnderecoService;
import br.edu.ifsp.bri.euexisto.service.EstadoService;
import br.edu.ifsp.bri.euexisto.service.RuaService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class CidadaoFacade {
    public void add (CidadaoComposite cidadaoComposite) throws ParseException {
        Cep                cep                = new Cep();
        Estado             estado             = new Estado();
        Cidade             cidade             = new Cidade();
        Bairro             bairro             = new Bairro();
        Rua                rua                = new Rua();
        Endereco           endereco           = new Endereco();
        Cidadao            cidadao            = new Cidadao();
        
        EstadoService      estadoService      = new EstadoService();
        CidadeService      cidadeService      = new CidadeService();
        CepService         cepService         = new CepService();
        BairroService      bairroService      = new BairroService();
        RuaService         ruaService         = new RuaService();
        EnderecoService    enderecoService    = new EnderecoService();
        CidadaoService     cidadaoService     = new CidadaoService();

        List<Estado>       listaEstado        = new ArrayList<Estado>();
        List<Cidade>       listaCidade        = new ArrayList<Cidade>();
        List<Bairro>       listaBairro        = new ArrayList<Bairro>();
        List<Rua>          listaRua           = new ArrayList<Rua>();
        List<Cep>          listaCep           = new ArrayList<Cep>();
        List<Endereco>     listaEndereco      = new ArrayList<Endereco>();
        
        EnderecoComposite  enderecoComposite  = new EnderecoComposite(cidadaoComposite.getNumeroCep(),   cidadaoComposite.getNumeroIni(),
                                                                      cidadaoComposite.getNumeroFim(),   cidadaoComposite.getNumero(),
                                                                      cidadaoComposite.getComplemento(), cidadaoComposite.getNomeEstado(),
                                                                      cidadaoComposite.getUf(),          cidadaoComposite.getNomeCidade(),
                                                                      cidadaoComposite.getNomeBairro(),  cidadaoComposite.getNomeRua());
        EnderecoFacade.add(enderecoComposite);
        
        listaEstado = estadoService.list(cidadaoComposite.getUf(), "S");
        estado      = (Estado) listaEstado.get(0);
        
        listaCidade = cidadeService.list(cidadaoComposite.getNomeCidade(), estado.getId());
        cidade      = (Cidade) listaCidade.get(0);
        
        listaCep    = cepService.list(cidadaoComposite.getNumeroCep());
        cep         = (Cep) listaCep.get(0);
        
        // Localizar o bairro
        // Se não existir, cadastrar
        if   (!cidadaoComposite.getNomeBairro().equals("")) {
             listaBairro = bairroService.list(cidadaoComposite.getNomeBairro());
             if   (listaBairro.size()==0) {
                  bairro.setNome(cidadaoComposite.getNomeBairro());
                  bairroService.add(bairro);
                  listaBairro = bairroService.list(cidadaoComposite.getNomeBairro());
                  bairro      = new Bairro();
                  bairro      = (Bairro) listaBairro.get(0);
             }
             else bairro      = (Bairro) listaBairro.get(0);
        }
        
        // Localizar a Rua
        // Se não existir, cadastrar
        if   (!cidadaoComposite.getNomeRua().equals("")) {
             listaRua = ruaService.list(cidadaoComposite.getNomeRua());
             if   (listaRua.size()==0) {
                  rua.setNome(cidadaoComposite.getNomeRua());
                  ruaService.add(rua);
                  listaRua = ruaService.list(cidadaoComposite.getNomeRua());
                  rua      = new Rua();
                  rua      = (Rua) listaRua.get(0);
             }
             else rua      = (Rua) listaRua.get(0);
        }
       
        //Se o CEP é UM para toda a cidade
        if   (!cidadaoComposite.getNomeRua().equals("")) {
             listaEndereco = enderecoService.list(cep.getId(), rua.getId(), cidadaoComposite.getNumero());
        }
        else {
             listaEndereco = enderecoService.list(cep.getId(), cidadaoComposite.getNumero());
        }
        endereco      = (Endereco) listaEndereco.get(0);
        
        //Cidadao responsavel1 = new Cidadao();
        //Cidadao responsavel2 = new Cidadao();
                
               
        SimpleDateFormat sdf      = new SimpleDateFormat("dd/MM/yyyy");
        Date             dataUtil;
        java.sql.Date    dataNasc;
        
        dataUtil = sdf.parse(cidadaoComposite.getDataNascString());
        dataNasc = new java.sql.Date(dataUtil.getTime());
        
        
        
        System.out.println("*****************************************");
        System.out.println("dataNasc " + cidadaoComposite.getDataNascString());
        System.out.println("*****************************************");
        
        System.out.println("*****************************************");
        System.out.println("cep " + endereco.getCep().getNumeroCep());
        System.out.println("*****************************************");
        
        System.out.println("*****************************************");
        System.out.println("sexo " + cidadaoComposite.getSexo());
        System.out.println("*****************************************");
        
        System.out.println("*****************************************");
        System.out.println("dataNasc " + dataNasc);
        System.out.println("*****************************************");
        
        
        cidadao.setNome(cidadaoComposite.getNomeCidadao());
        cidadao.setCpf(cidadaoComposite.getCpf());
        cidadao.setEmail(cidadaoComposite.getEmail());
        cidadao.setTelefoneFixo(cidadaoComposite.getTelefoneFixo());
        cidadao.setTelefoneCelular(cidadaoComposite.getTelefoneCelular());
        cidadao.setDataNasc(dataNasc);
        cidadao.setSenha(cidadaoComposite.getSenha());
        cidadao.setEndereco(endereco);
        cidadao.setSexo(cidadaoComposite.getSexo());
        //cidadao.setResponsavel1(responsavel1);
        //cidadao.setResponsavel2(responsavel2);
        cidadaoService.add(cidadao);

    } // fim do metodo add
        
}
