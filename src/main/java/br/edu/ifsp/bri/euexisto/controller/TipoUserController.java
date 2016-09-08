/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.controller;

import br.edu.ifsp.bri.euexisto.domain.TipoUser;
import br.edu.ifsp.bri.euexisto.domain.TipoUsuario;
import br.edu.ifsp.bri.euexisto.service.TipoUserService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ifsp
 */

@Named
@SessionScoped
public class TipoUserController implements Serializable {
  
    private TipoUser tipoUser; //usar para adição 
    private TipoUser tipoUserSelecionado; //usar alteração e exclusão
    private TipoUserService tipoUserService; 
    private List<TipoUsuario> tipoUsuario;
    private List<TipoUser> listaTipoUsuario;


    @PostConstruct
    public void startDados(){
        tipoUser = new TipoUser();
        tipoUserSelecionado = new TipoUser();
        tipoUserService = new TipoUserService();
        tipoUsuario = new ArrayList<>();
        listaTipoUsuario = new ArrayList<>();
        //tipoUsuario = this.getTipoUserService().getTipoUsuario();
        listaTipoUser();
    }    
    
    
    public void listaTipoUser(){
        listaTipoUsuario = this.getTipoUserService().listaTipoUser();
        tipoUsuario = this.getTipoUserService().getTipoUsuario();
    }
    
    public String incluirTipoUsuario(){
        this.getTipoUserService().addTipoUser(tipoUser);
        this.clearDados();
        this.listaTipoUser();
        return "lista.xhtml?faces-redirect=true";
    }
  
    public String alterarTipoUsuario(){
        this.getTipoUserService().updateTipoUser(tipoUserSelecionado);
        this.clearDados();
        this.listaTipoUser();
        return "/paginas/tipousuario/lista.xhtml";
    }
    
    public String removerTipoUsuario(){
        this.getTipoUserService().removeTipoUser(tipoUserSelecionado);
        this.clearDados();
        this.listaTipoUser();
        return "lista.xhtml";
    }
    
    
    public String doIncluirTipoUser(){
        return "incluir.xhtml";
    }
    
    public String doAlterarTipoUser(){
        return "alterar.xhtml";
    }
    
    public String doRemoverTipoUser(){
        return "remover.xhtml";
    }
    
    public String doConsultaTipoUser(){
        return "consultar.xhtml";
    }
    
    public String doCancelarCadastro(){
        return "lista.xhtml";
    }
    
    public void clearDados(){
        tipoUser = new TipoUser();
        tipoUserSelecionado = new TipoUser();
        tipoUserService = new TipoUserService();
        tipoUsuario = new ArrayList<>();
        listaTipoUsuario = new ArrayList<>();
    }
    
    public TipoUser getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(TipoUser tipoUser) {
        this.tipoUser = tipoUser;
    }

    public TipoUser getTipoUserSelecionado() {
        return tipoUserSelecionado;
    }

    public void setTipoUserSelecionado(TipoUser tipoUserSelecionado) {
        this.tipoUserSelecionado = tipoUserSelecionado;
    }
 
    public TipoUserService getTipoUserService() {
        return tipoUserService;
    }

    public void setTipoUserService(TipoUserService tipoUserService) {
        this.tipoUserService = tipoUserService;
    }

    public List<TipoUsuario> getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(List<TipoUsuario> tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<TipoUser> getListaTipoUsuario() {
        return listaTipoUsuario;
    }

    public void setListaTipoUsuario(List<TipoUser> listaTipoUsuario) {
        this.listaTipoUsuario = listaTipoUsuario;
    }
   
}//fim da classe TipoUserController
