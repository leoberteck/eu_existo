/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.controller;

import br.edu.ifsp.bri.euexisto.domain.Cidade;
import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.domain.FaixaEtaria;
import br.edu.ifsp.bri.euexisto.service.CidadeService;
import br.edu.ifsp.bri.euexisto.service.EstadoService;
import br.edu.ifsp.bri.euexisto.service.FaixaEtariaService;

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
public class FaixaEtariaController implements Serializable {
  
    private FaixaEtaria        faixaEtaria;            //usar para adição 
    private FaixaEtaria        faixaEtariaSelecionado; //usar alteração e exclusão
    private Estado             estado;
    private Cidade             cidade;
    
    private FaixaEtariaService faixaEtariaService; 
    private EstadoService      estadoService;
    private CidadeService      cidadeService;
    
    private List<FaixaEtaria>  listaFaixaEtaria;
    private List<Estado>       listaEstado;
    private List<Cidade>       listaCidade;
    
    
    @PostConstruct
    public void startDados(){
        faixaEtaria            = new FaixaEtaria();
        faixaEtariaSelecionado = new FaixaEtaria();
        estado                 = new Estado();
        cidade                 = new Cidade();
        
        faixaEtariaService     = new FaixaEtariaService();
        estadoService          = new EstadoService();
        cidadeService          = new CidadeService();
        
        listaFaixaEtaria       = new ArrayList<>();
        listaEstado            = new ArrayList<>();
        listaCidade            = new ArrayList<>();
        
        this.list();
    }    
    
    public String add(){
        faixaEtaria.setCidade(cidade);
        this.getFaixaEtariaService().add(faixaEtaria);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
  
    public String update(){
        faixaEtaria.setCidade(cidade);
        this.getFaixaEtariaService().update(faixaEtariaSelecionado);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
    
    public String remove(){
        this.getFaixaEtariaService().remove(faixaEtariaSelecionado);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }

    public void list(){
        listaEstado      = this.getEstadoService().list();
        listaCidade      = this.getCidadeService().list(estado.getId());
        listaFaixaEtaria = this.getFaixaEtariaService().list(cidade.getId());
    }

    public void listFaixaEtaria(){
        listaFaixaEtaria = this.getFaixaEtariaService().list(cidade.getId());
    }

    public void listCidade(){
        listaCidade      = this.getCidadeService().list(estado.getId());
        listaFaixaEtaria = this.getFaixaEtariaService().list(cidade.getId());
    }
    
    public void clearDados(){
        faixaEtaria            = new FaixaEtaria();
        faixaEtariaSelecionado = new FaixaEtaria();
        faixaEtariaService     = new FaixaEtariaService();
        listaFaixaEtaria       = new ArrayList<>();
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

    public FaixaEtariaService getFaixaEtariaService() {
        return faixaEtariaService;
    }

    public void setFaixaEtariaService(FaixaEtariaService faixaEtariaService) {
        this.faixaEtariaService = faixaEtariaService;
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
    
    public FaixaEtaria getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(FaixaEtaria faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public FaixaEtaria getFaixaEtariaSelecionado() {
        return faixaEtariaSelecionado;
    }

    public void setFaixaEtariaSelecionado(FaixaEtaria faixaEtariaSelecionado) {
        this.faixaEtariaSelecionado = faixaEtariaSelecionado;
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
    
    public List<FaixaEtaria> getListaFaixaEtaria() {
        return listaFaixaEtaria;
    }

    public void setListaFaixaEtaria(List<FaixaEtaria> listaFaixaetaria) {
        this.listaFaixaEtaria = listaFaixaEtaria;
    }

} // fim da classe FaixaEtariaController
