/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.facade;

import br.edu.ifsp.bri.euexisto.composite.CidadaoComposite;
import br.edu.ifsp.bri.euexisto.composite.EnderecoComposite;
import br.edu.ifsp.bri.euexisto.domain.Cidadao;
import br.edu.ifsp.bri.euexisto.domain.Endereco;
import br.edu.ifsp.bri.euexisto.service.CidadaoService;
import br.edu.ifsp.bri.euexisto.service.EnderecoService;
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

    private static CidadaoService cidadaoService = new CidadaoService();
    
    public Cidadao get(CidadaoComposite cidadaoComposite) throws ParseException {
        Cidadao       cidadao      = new Cidadao();
        List<Cidadao> listaCidadao = new ArrayList<Cidadao>();
        
        // Localizar o cidadão
        // Se não existir, cadastrar
        listaCidadao = cidadaoService.list(cidadaoComposite.getCpf(), "C");
        if   (listaCidadao.size()==0) {
             add(cidadaoComposite);
             listaCidadao = cidadaoService.list(cidadaoComposite.getCpf(), "C");
        }
        
        cidadao = (Cidadao) listaCidadao.get(0);
        
        return cidadao;
    }// fim do método get


    public void add (CidadaoComposite cidadaoComposite) throws ParseException {
        EnderecoComposite  enderecoComposite  = new EnderecoComposite(cidadaoComposite.getNumeroCep(),   cidadaoComposite.getNumeroIni(),
                                                                      cidadaoComposite.getNumeroFim(),   cidadaoComposite.getNumero(),
                                                                      cidadaoComposite.getComplemento(), cidadaoComposite.getNomeEstado(),
                                                                      cidadaoComposite.getUf(),          cidadaoComposite.getNomeCidade(),
                                                                      cidadaoComposite.getNomeBairro(),  cidadaoComposite.getNomeRua());
        Endereco           endereco           = EnderecoFacade.get(enderecoComposite);

        
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
        
        Cidadao cidadao = new Cidadao();
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
        
}// fim da classe CidadaoFacade
