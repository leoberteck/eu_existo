/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.controller;

import br.edu.ifsp.bri.euexisto.domain.Cep;
import br.edu.ifsp.bri.euexisto.domain.Cidade;
import br.edu.ifsp.bri.euexisto.domain.Endereco;
import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.service.CepService;
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
public class EnderecoController implements Serializable {
  
    private Endereco             endereco;            //usar para adição 
    private Endereco             enderecoSelecionado; //usar alteração e exclusão
    private Estado               estado;
    private Cidade               cidade;
    private Cep                  cep;
    
    private EnderecoService      enderecoService; 
    private EstadoService        estadoService;
    private CidadeService        cidadeService;
    private CepService           cepService;
    
    private List<Endereco>       listaEndereco;
    private List<Estado>         listaEstado;
    private List<Cidade>         listaCidade;
    private List<Cep>            listaCep;
    
    @PostConstruct
    public void startDados(){
        endereco            = new Endereco();
        enderecoSelecionado = new Endereco();
        estado              = new Estado();
        cidade              = new Cidade();
        cep                 = new Cep();
        
        enderecoService     = new EnderecoService();
        estadoService       = new EstadoService();
        cidadeService       = new CidadeService();
        cepService          = new CepService();
        
        listaEndereco       = new ArrayList<>();
        listaEstado         = estadoService.list();
        listaCidade         = new ArrayList<>();
        listaCep            = new ArrayList<>();
        this.list();
    }    
    
    public String add(){
        endereco.setCep(cep);
        this.getEnderecoService().add(endereco);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
  
    public String update(){
        endereco.setCep(cep);
        this.getEnderecoService().update(enderecoSelecionado);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
    
    public String remove(){
        this.getEnderecoService().remove(enderecoSelecionado);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
        
    public void list(){
        listaEstado   = this.getEstadoService().list();
        listaCidade   = this.getCidadeService().list(estado.getId());
        listaCep      = this.getCepService().list(cidade.getId());
        listaEndereco = this.getEnderecoService().list(cidade.getId());
    }
 
    public void listEnderedco(){
        listaEndereco = this.getEnderecoService().list(cidade.getId());
    }  
    
    public void listCidade() {
        listaCidade = this.getCidadeService().list(estado.getId());
        this.listCep();
    }
    
    public void listCep() {
        listaCep      = this.getCepService().list(cidade.getId());
        listaEndereco = this.getEnderecoService().list(cidade.getId());
    }
    
    public void clearDados(){
        endereco            = new Endereco();
        enderecoSelecionado = new Endereco();
        enderecoService     = new EnderecoService();
        listaEndereco       = new ArrayList<>();
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

    public CepService getCepService() {
        return cepService;
    }

    public void setCepService(CepService cepService) {
        this.cepService = cepService;
    }
    
    public EnderecoService getEnderecoService() {
        return enderecoService;
    }

    public void setEnderecoService(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
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
    
    public Cep getCep() {
        return cep;
    }

    public void setCep(Cep cep) {
        this.cep = cep;
    }
    
    public Endereco getEndereco() {
        return endereco;
    }
    
    public Endereco setEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Endereco getEnderecoSelecionado() {
        return enderecoSelecionado;
    }

    public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
        this.enderecoSelecionado = enderecoSelecionado;
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

    public List<Cep> getListaCep() {
        return listaCep;
    }

    public void setListaCep(List<Cep> listaCep) {
        this.listaCep = listaCep;
    }

    public List<Endereco> getListaEndereco() {
        return listaEndereco;
    }

    public void setListaEndereco(List<Endereco> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }

}// fim da classe EnderecoController
