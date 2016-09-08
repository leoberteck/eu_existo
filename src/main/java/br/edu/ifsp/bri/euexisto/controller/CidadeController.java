/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.controller;

import br.edu.ifsp.bri.euexisto.domain.Cidade;
import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.service.CidadeService;
import br.edu.ifsp.bri.euexisto.service.EstadoService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author gahsabio
 */
@Named
@SessionScoped
public class CidadeController implements Serializable {
  
    private Cidade          cidade;            //usar para adição 
    private Cidade          cidadeSelecionado; //usar alteração e exclusão
    private Estado          estado;

    private CidadeService   cidadeService; 
    private EstadoService   estadoService;

    private List<Cidade>    listaCidade;
    private List<Estado>    listaEstado;
    
    @PostConstruct
    public void startDados(){
        cidade            = new Cidade();
        cidadeSelecionado = new Cidade();
        estado            = new Estado();

        cidadeService     = new CidadeService();
        estadoService     = new EstadoService();

        listaCidade       = new ArrayList<>();
        listaEstado       = estadoService.list();
                
        this.list();
    }    
    
    public String add(){
        cidade.setEstado(estado);
        this.getCidadeService().add(cidade);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
  
    public String update(){
        cidade.setEstado(estado);
        this.getCidadeService().update(cidadeSelecionado);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
    
    public String remove(){
        this.getCidadeService().remove(cidadeSelecionado);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
    
    public Cidade getById (int id){
        return this.getCidadeService().getById(id);
    }
        
    public void list(){
        listaEstado = this.getEstadoService().list();
        listaCidade = this.getCidadeService().list(estado.getId());
    }
        
    public void listCidade(){
        listaCidade = this.getCidadeService().list(estado.getId());
    }
    
    public void clearDados(){
        cidade            = new Cidade();
        cidadeSelecionado = new Cidade();
        cidadeService     = new CidadeService();
        listaCidade       = new ArrayList<>();
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

    public Cidade getCidadeSelecionado() {
        return cidadeSelecionado;
    }

    public void setCidadeSelecionado(Cidade cidadeSelecionado) {
        this.cidadeSelecionado = cidadeSelecionado;
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
   
}// fim da classe CidadeController
