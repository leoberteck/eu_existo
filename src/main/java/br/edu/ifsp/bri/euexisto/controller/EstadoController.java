/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.controller;

import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.domain.EstadoQtde;
import br.edu.ifsp.bri.euexisto.service.EstadoService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author gahsabio
 */
@Named
@SessionScoped
public class EstadoController implements Serializable {
  
    private Estado           estado;            //usar para adição 
    private Estado           estadoSelecionado; //usar alteração e exclusão
    private EstadoService    estadoService; 
    private List<Estado>     listaEstado;
    private List<EstadoQtde> listaEstadoQtde;


    @PostConstruct
    public void startDados(){
        estado            = new Estado();
        estadoSelecionado = new Estado();
        estadoService     = new EstadoService();
        listaEstado       = new ArrayList<>();
        listaEstadoQtde   = new ArrayList<>();
        this.list();
    }    
    
    public String add(){
        this.getEstadoService().add(estado);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
  
    public String update(){
        this.getEstadoService().update(estadoSelecionado);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
    
    public String remove(){
        this.getEstadoService().remove(estadoSelecionado);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
        
    public void list(){
        listaEstado     = this.getEstadoService().list();
        listaEstadoQtde = this.getEstadoService().listEstadoQtde();
    }
    
    public int getQtdeCidade (int idEstado){
        return this.getEstadoService().getQtdeCidade(idEstado);
    }
    
    public void listEstadoQtde() {
        listaEstadoQtde = this.getEstadoService().listEstadoQtde();
    }
    
    public void clearDados(){
        estado            = new Estado();
        estadoSelecionado = new Estado();
        estadoService     = new EstadoService();
        listaEstado       = new ArrayList<>();
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
/*
    public void setEstadoService(EstadoService estadoService) {
        this.estadoService = estadoService;
    }
  */  
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Estado getEstadoSelecionado() {
        return estadoSelecionado;
    }

    public void setEstadoSelecionado(Estado estadoSelecionado) {
        this.estadoSelecionado = estadoSelecionado;
    }

    public List<Estado> getListaEstado() {
        return listaEstado;
    }

    public void setListaEstado(List<Estado> listaEstado) {
        this.listaEstado = listaEstado;
    }
    
    public List<EstadoQtde> getListaEstadoQtde() {
        return listaEstadoQtde;
    }

    public void setListaEstadoQtde(List<EstadoQtde> listaEstadoQtde) {
        this.listaEstadoQtde = listaEstadoQtde;
    }
   
}// fim da classe EstadoController
