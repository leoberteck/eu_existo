/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.controller;

import br.edu.ifsp.bri.euexisto.domain.Rua;
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
public class RuaController implements Serializable {
  
    private Rua        rua;            //usar para adição 
    private Rua        ruaSelecionado; //usar alteração e exclusão
    private RuaService ruaService; 
    private List<Rua>  listaRua;


    @PostConstruct
    public void startDados(){
        rua            = new Rua();
        ruaSelecionado = new Rua();
        ruaService     = new RuaService();
        listaRua       = new ArrayList<>();
        this.list();
    }    
    
    public String add(){
        this.getRuaService().add(rua);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
  
    public String update(){
        this.getRuaService().update(ruaSelecionado);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
    
    public String remove(){
        this.getRuaService().remove(ruaSelecionado);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
        
    public void list(){
        listaRua = this.getRuaService().list();
    }
    
    public void clearDados(){
        rua            = new Rua();
        ruaSelecionado = new Rua();
        ruaService     = new RuaService();
        listaRua       = new ArrayList<>();
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
    public RuaService getRuaService() {
        return ruaService;
    }

    public void setRuaService(RuaService ruaService) {
        this.ruaService = ruaService;
    }
    
    public Rua getRua() {
        return rua;
    }

    public void setRua(Rua rua) {
        this.rua = rua;
    }

    public Rua getRuaSelecionado() {
        return ruaSelecionado;
    }

    public void setRuaSelecionado(Rua ruaSelecionado) {
        this.ruaSelecionado = ruaSelecionado;
    }

    public List<Rua> getListaRua() {
        return listaRua;
    }

    public void setListaRua(List<Rua> listaRua) {
        this.listaRua = listaRua;
    }
   
}// fim da classe RuaController

