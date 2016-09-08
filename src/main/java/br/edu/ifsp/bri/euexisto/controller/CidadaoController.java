/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.controller;

import br.edu.ifsp.bri.euexisto.composite.CidadaoComposite;
import br.edu.ifsp.bri.euexisto.domain.Cep;
import br.edu.ifsp.bri.euexisto.domain.Cidadao;
import br.edu.ifsp.bri.euexisto.domain.Cidade;
import br.edu.ifsp.bri.euexisto.domain.Endereco;
import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.facade.CidadaoFacade;
import br.edu.ifsp.bri.euexisto.service.CepService;
import br.edu.ifsp.bri.euexisto.service.CidadaoService;
import br.edu.ifsp.bri.euexisto.service.CidadeService;
import br.edu.ifsp.bri.euexisto.service.EnderecoService;
import br.edu.ifsp.bri.euexisto.service.EstadoService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author gahsabio
 */
@Named
@SessionScoped
public class CidadaoController implements Serializable {
  
    private Cidadao              cidadao;            
    private Estado               estado;
    private Cidade               cidade;
    
    private CidadaoComposite     cidadaoComposite;            //usar para adição 
    private CidadaoComposite     cidadaoCompositeSelecionado; //usar alteração e exclusão
    
    private CidadaoFacade        cidadaoFacade;
    
    private EstadoService        estadoService;
    private CidadeService        cidadeService;
    private CidadaoService       cidadaoService;
    
    private List<Estado>         listaEstado;
    private List<Cidade>         listaCidade;
    private List<Cidadao>        listaCidadao;
    
    @PostConstruct
    public void startDados(){
        cidadaoComposite            = new CidadaoComposite();
        cidadaoCompositeSelecionado = new CidadaoComposite();
        
        cidadao                     = new Cidadao();
        estado                      = new Estado();
        cidade                      = new Cidade();
        
        cidadaoFacade               = new CidadaoFacade();
        
        estadoService               = new EstadoService();
        cidadeService               = new CidadeService();
        cidadaoService              = new CidadaoService();
        
        listaEstado                 = estadoService.list();
        listaCidade                 = new ArrayList<>();
        listaCidadao                = new ArrayList<>();
        
        this.list();
    }    
    
    public String add(){
        this.getCidadaoFacade().add(cidadaoComposite);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
  
    public String update(){
        //this.getEnderecoService().update(enderecoSelecionado);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
    
    public String remove(){
//        this.getCidadaoService().remove(cidadaoCompositeSelecionado);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
        
    public void list(){
        listaEstado   = this.getEstadoService().list();
        listaCidade   = this.getCidadeService().list(estado.getId());
        listaCidadao  = this.getCidadaoService().list(cidade.getId(),"C");
    }// fim do método list
 
    public void listCidade() {
        listaCidade   = this.getCidadeService().list(estado.getId());
        listaCidadao  = this.getCidadaoService().list(cidade.getId(),"C");
    }
 
    public void listCidadao() {
        listaCidadao  = this.getCidadaoService().list(cidade.getId(),"C");
    }
    
    public void clearDados(){
        cidadao                     = new Cidadao();
        cidadaoService              = new CidadaoService();
        listaCidadao                = new ArrayList<>();
        cidadaoComposite            = new CidadaoComposite();
        cidadaoCompositeSelecionado = new CidadaoComposite();
    }

    // Métodos para chamada às páginas    
    public String doAdd(){
        return "incluir.xhtml?faces-redirect=true";
    }
    
    public String doUpdate(){
        return "alterar.xhtml?faces-redirect=true";
    }
    
    public String doRemove(){
        return "remover.xhtml?faces-redirect=true";
    }
    
    public String doList(){
        return "consultar.xhtml?faces-redirect=true";
    }
    
    public String doCancel(){
        //this.clearDados();
        //this.list();
        return "listar.xhtml?faces-redirect=true";
    }
    
    
    // ========================
    // gets e sets 
    // ========================
    public CidadaoFacade getCidadaoFacade() {
        return cidadaoFacade;
    }
    
    public void setCidadaoFacade(CidadaoFacade cidadaoFacade) {
        this.cidadaoFacade = cidadaoFacade;
    }
    
    public EstadoService getEstadoService() {
        return estadoService;
    }

    public void setEstadoService(EstadoService estadoService) {
        this.estadoService = estadoService;
    }
    
    public CidadeService getCidadeService() {
        return cidadeService;
    }

    public void setCidadeService(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }
    
    public CidadaoService getCidadaoService() {
        return cidadaoService;
    }

    public void setCidadaoService(CidadaoService cidadaoService) {
        this.cidadaoService = cidadaoService;
    }
    
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    public Cidadao getCidadao() {
        return cidadao;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
    }
    
    public CidadaoComposite getCidadaoComposite(){
        return cidadaoComposite;
    }
    
    public void setCidadaoComposite (CidadaoComposite cidadaoComposite) {
        this.cidadaoComposite = cidadaoComposite;
    }
    
    public CidadaoComposite getCidadaoCompositeSelecionado() {
        return cidadaoCompositeSelecionado;
    }

    public void setCidadaoCompositeSelecionado(CidadaoComposite cidadaoCompositeSelecionado) {
        this.cidadaoCompositeSelecionado = cidadaoCompositeSelecionado;
    }

    public List<Estado> getListaEstado() {
        return listaEstado;
    }

    public void setListaEstado(List<Estado> listaEstado) {
        this.listaEstado = listaEstado;
    }

    public List<Cidade> getListaCidade() {
        return listaCidade;
    }

    public void setListaCidade(List<Cidade> listaCidade) {
        this.listaCidade = listaCidade;
    }

    public List<Cidadao> getListaCidadao() {
        return listaCidadao;
    }

    public void setListaCidadao(List<Cidadao> listaCidadao) {
        this.listaCidadao = listaCidadao;
    }

}// fim da classe CidadaoController
