/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.controller;

import br.edu.ifsp.bri.euexisto.domain.Bairro;
import br.edu.ifsp.bri.euexisto.service.BairroService;

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
public class BairroController implements Serializable {
  
    private Bairro        bairro;            //usar para adição 
    private Bairro        bairroSelecionado; //usar alteração e exclusão
    private BairroService bairroService; 
    private List<Bairro>  listaBairro;


    @PostConstruct
    public void startDados(){
        bairro            = new Bairro();
        bairroSelecionado = new Bairro();
        bairroService     = new BairroService();
        listaBairro       = new ArrayList<>();
        this.list();
    }    
    
    public String add(){
        this.getBairroService().add(bairro);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
  
    public String update(){
        this.getBairroService().update(bairroSelecionado);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
    
    public String remove(){
        this.getBairroService().remove(bairroSelecionado);
        this.clearDados();
        this.list();
        return "listar.xhtml?faces-redirect=true";
    }
        
    public void list(){
        listaBairro = this.getBairroService().list();
    }
    
    public void clearDados(){
        bairro            = new Bairro();
        bairroSelecionado = new Bairro();
        bairroService     = new BairroService();
        listaBairro       = new ArrayList<>();
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
    public BairroService getBairroService() {
        return bairroService;
    }

    public void setBairroService(BairroService bairroService) {
        this.bairroService = bairroService;
    }
    
    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public Bairro getBairroSelecionado() {
        return bairroSelecionado;
    }

    public void setBairroSelecionado(Bairro bairroSelecionado) {
        this.bairroSelecionado = bairroSelecionado;
    }

    public List<Bairro> getListaBairro() {
        return listaBairro;
    }

    public void setListaBairro(List<Bairro> listaBairro) {
        this.listaBairro = listaBairro;
    }
   
}// fim da classe BairroController
