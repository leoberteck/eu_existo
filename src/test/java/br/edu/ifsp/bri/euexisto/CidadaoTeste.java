/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto;

import br.edu.ifsp.bri.euexisto.composite.CidadaoComposite;
import br.edu.ifsp.bri.euexisto.domain.Cidadao;
import br.edu.ifsp.bri.euexisto.facade.CidadaoFacade;
import br.edu.ifsp.bri.euexisto.service.CidadaoService;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 *
 * @author gahsabio
 */
public class CidadaoTeste {
    
    public static void main (String args[]) throws ParseException   {
        
        //
        // nome, cpf, email, telefoneFixo, telefoneCelular, dataNasc, senha, 
        // estado, cidade, cep, nº, complemento
        // se o cep é somente um para toda a cidade --> rua e bairro
        // numero inicial e final do cep
        CidadaoFacade cidadaoFacade = new CidadaoFacade();
        
        SimpleDateFormat sdf      = new SimpleDateFormat("dd/MM/yyyy");
        Date             dataUtil;
        java.sql.Date    dataNasc;
        
        CidadaoComposite cidadaoComposite = new CidadaoComposite();
        cidadaoComposite.setNomeCidadao("Helen de Freitas Santos");
        cidadaoComposite.setCpf("595.416.016-34");
        cidadaoComposite.setEmail("hemilen1964@terra.com.br");
        cidadaoComposite.setTelefoneFixo("(18) 3301-0603");
        cidadaoComposite.setTelefoneCelular("(18) 99125-0603");
        dataUtil = sdf.parse("21/03/1964");
        dataNasc = new java.sql.Date(dataUtil.getTime());
        cidadaoComposite.setDataNasc(dataNasc);
        cidadaoComposite.setSexo("F");
        cidadaoComposite.setSenha("teste");
        cidadaoComposite.setUf("SP");
        cidadaoComposite.setNomeEstado("São Paulo");
        cidadaoComposite.setNomeCidade("Araçatuba");
        cidadaoComposite.setNomeBairro("");
        cidadaoComposite.setNomeRua("");
        cidadaoComposite.setNumeroCep("16.015-303");
        cidadaoComposite.setNumero("995");
        cidadaoComposite.setComplemento("");
        cidadaoComposite.setNumeroIni(717);
        cidadaoComposite.setNumeroFim(9999);
        cidadaoFacade.add(cidadaoComposite);
        
        
        cidadaoComposite = new CidadaoComposite();
        cidadaoComposite.setNomeCidadao("José Miguel Sábio");
        cidadaoComposite.setCpf("059.554.188-73");
        cidadaoComposite.setEmail("josemiguelsabio@gmail.com");
        cidadaoComposite.setTelefoneFixo("(18) 3301-0603");
        cidadaoComposite.setTelefoneCelular("(18) 99125-0679");
        dataUtil = sdf.parse("22/04/1964");
        dataNasc = new java.sql.Date(dataUtil.getTime());
        cidadaoComposite.setDataNasc(dataNasc);
        cidadaoComposite.setSexo("M");
        cidadaoComposite.setSenha("minhasenha");
        cidadaoComposite.setUf("SP");
        cidadaoComposite.setNomeEstado("São Paulo");
        cidadaoComposite.setNomeCidade("Araçatuba");
        cidadaoComposite.setNomeBairro("");
        cidadaoComposite.setNomeRua("");
        cidadaoComposite.setNumeroCep("16.015-303");
        cidadaoComposite.setNumero("995");
        cidadaoComposite.setComplemento("");
        cidadaoComposite.setNumeroIni(717);
        cidadaoComposite.setNumeroFim(9999);
        cidadaoFacade.add(cidadaoComposite);
        
        CidadaoService cidadaoService = new CidadaoService();
        List<Cidadao>  listaCidadao   = cidadaoService.list();
        Cidadao        cidadao        = new Cidadao();
        for (int i=0; i<listaCidadao.size();i++) {
            cidadao = listaCidadao.get(i);
            System.out.println(cidadao.toString());
        }
        
        // Busca por email
        listaCidadao = cidadaoService.list("hemilen1964@terra.com.br", "E");
        if  (listaCidadao.size()>0) {
            cidadao = listaCidadao.get(0);
            System.out.println("Por email " + cidadao.toString());
        }
        
        // Busca por cpf
        listaCidadao = cidadaoService.list("059.554.188-73", "C");
        if  (listaCidadao.size()>0) {
            cidadao = listaCidadao.get(0);
            System.out.println("Por cpf Miguel " + cidadao.toString());
        }
        listaCidadao = cidadaoService.list("595.416.016-34", "C");
        if  (listaCidadao.size()>0) {
            cidadao = listaCidadao.get(0);
            System.out.println("Por cpf Helen " + cidadao.toString());
        }
    }
    
}
