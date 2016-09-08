/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.service;

import br.edu.ifsp.bri.euexisto.domain.Cidade;
import br.edu.ifsp.bri.euexisto.repository.CidadeRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class CidadeService {

    private CidadeRepository cidadeRepository;

    public CidadeService() {
        cidadeRepository = new CidadeRepository();
    }
    
    // Esse método deve fazer as seguintes verificações
    // Inclusão:  não pode incluir uma cidade com um  nome, para um estado, que já exista
    // Alteração: não pode alterar o nome de uma cidade para um  nome que já exista, para um mesmo estado
    public void add(Cidade cidade) {
        //   na inclusão, id = 0
        if   (check(cidade, 0))
              this.getCidadeRepository().add(cidade);
    }// fim do metodo add
    
    public void update(Cidade cidade){
        //   na alteração, passar o id da cidade que está sendo alterada
        if   (check(cidade, cidade.getId()))
              this.getCidadeRepository().update(cidade);
    }// fim do método update
    
    public void remove(Cidade cidade){
        this.getCidadeRepository().remove(cidade.getId());
    }// fim do métodoremove
    
    // na inclusão,  id = 0
    // na alteração, id do objeto que está sendo alterado
    public boolean check (Cidade cidade, int idOld) {
        boolean cidadeOk = true;
        
        if   (cidade.getNome().trim().equals(""))
             cidadeOk = false;
        else if   (cidade.getEstado().getId() == 0)
                  cidadeOk = false;
        else if   (this.cidadeRepository.check(cidade, idOld) > 0)
                  cidadeOk = false;

        return  cidadeOk;
    }// fim do método check
    
    public Cidade getById (int id) {
        return this.cidadeRepository.getById(id);
    }// fim do método getById
    
    public List<Cidade> list(){
        return this.getCidadeRepository().list();
    }// fim do método list
    
    public List<Cidade> list(int idEstado){
        return this.getCidadeRepository().list(idEstado);
    }// fim do método list
    
    public List<Cidade> list(String nome, int idEstado){
        return this.getCidadeRepository().list(nome, idEstado);
    }// fim do método list
    
    public CidadeRepository getCidadeRepository() {
        return cidadeRepository;
    }// fim do metodo getCidadeRepository

    public void setCidadeRepository(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }// fim do método setCidadeRepository
        
}// fim da classe CidadeService
