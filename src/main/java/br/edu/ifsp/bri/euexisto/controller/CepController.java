/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.controller;

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
public class CepController implements Serializable {
  
    private Cep             cep;            //usar para adição 
    private Cep             cepSelecionado; //usar alteração e exclusão
    private Estado          estado;
    private Cidade          cidade;
    private Rua             rua;
    private Bairro          bairro;
    
    private CepService      cepService;
    private EstadoService   estadoService;
    private CidadeService   cidadeService;
    private RuaService      ruaService;
    private BairroService   bairroService;
    
    private List<Cep>       listaCep;
    private List<Estado>    listaEstado;
    private List<Cidade>    listaCidade;
    private List<Rua>       listaRua;
    private List<Bairro>    listaBairro;
    
    @PostConstruct
    public void startDados(){
        cep            = new Cep();
        cepSelecionado = new Cep();
        estado         = new Estado();
        cidade         = new Cidade();
        rua            = new Rua();
        bairro         = new Bairro();
        
        cepService     = new CepService();
        estadoService  = new EstadoService();
        cidadeService  = new CidadeService();
        ruaService     = new RuaService();
        bairroService  = new BairroService(); 
        
        listaCep       = new ArrayList<>();
        listaEstado    = new ArrayList<>();
        listaCidade    = new ArrayList<>();
        listaRua       = new ArrayList<>();
        listaBairro    = new ArrayList<>();
        this.list();
    }    
    
    public String add(){
        cep.setCidade(cidade);
        this.getCepService().add(cep);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
  
    public String update(){
        cep.setCidade(cidade);
        this.getCepService().update(cepSelecionado);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
    
    public String remove(){
        this.getCepService().remove(cepSelecionado);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
        
    public void list(){
        listaEstado  = this.getEstadoService().list();
        listaCidade  = this.getCidadeService().list(estado.getId());
        listaCep     = this.getCepService().list(cidade.getId());
        listaRua     = this.getRuaService().list();
        listaBairro  = this.getBairroService().list();
    }
 
    public void listCep(){
        listaCep = this.getCepService().list(cidade.getId());
    }  
    
    public void listCidade(){
        listaCidade    = this.getCidadeService().list(estado.getId());
        listaCep       = this.getCepService().list(cidade.getId());
    }
    
    public void clearDados(){
        cep            = new Cep();
        cepSelecionado = new Cep();
        cepService     = new CepService();
        listaCep       = new ArrayList<>();
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
        this.clearDados();
        this.list();
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
    
    public RuaService getRuaService() {
        return ruaService;
    }

    public void setRuaService(RuaService ruaService) {
        this.ruaService = ruaService;
    }
    
    public BairroService getBairroService() {
        return bairroService;
    }

    public void setBairroService(BairroService bairroService) {
        this.bairroService = bairroService;
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
    
    public Rua getRua() {
        return rua;
    }

    public void setRua(Rua rua) {
        this.rua = rua;
    }
    
    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public Cep getCepSelecionado() {
        return cepSelecionado;
    }

    public void setCepSelecionado(Cep cepSelecionado) {
        this.cepSelecionado = cepSelecionado;
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
    
    public List<Rua> getListaRua() {
        return listaRua;
    }

    public void setListaRua(List<Rua> listaRua) {
        this.listaRua = listaRua;
    }
    
    public List<Bairro> getListaBairro() {
        return listaBairro;
    }

    public void setListaBairro(List<Bairro> listaBairro) {
        this.listaBairro = listaBairro;
    }
       
}// fim da classe CepController
