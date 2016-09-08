/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.service;

import br.edu.ifsp.bri.euexisto.domain.Bairro;
import br.edu.ifsp.bri.euexisto.repository.BairroRepository;
import java.io.Serializable;
import java.util.List;
             
/**
 *
 * @author gahsabio
 */
public class BairroService implements Serializable {

    private BairroRepository bairroRepository;

    public BairroService() {
        bairroRepository = new BairroRepository();
    }

    // Esse método deve fazer as seguintes verificações
    // Inclusão:  não pode incluir um bairro com um nome que já exista
    // Alteração: não pode alterar o nome para um nome que já exista
    public void add(Bairro bairro) {
        if  (check(bairro, 0))
            this.getBairroRepository().add(bairro);
    }// fim do método add
    
    public void update(Bairro bairro){
        if  (check(bairro, bairro.getId()))
            this.getBairroRepository().update(bairro);
    }// fim do método update 
    
    public void remove(Bairro bairro){
        this.getBairroRepository().remove(bairro.getId());
    }// fim do metodo remove
    
    public boolean check (Bairro bairro, int idOld) {
        boolean bairroOk = true;
        
        if  (bairro.getNome().trim().equals(""))
             bairroOk = false;
        else if (this.getBairroRepository().check(bairro, idOld) > 0 )
                 bairroOk = false;
        
        return bairroOk;
    }// fim do método heck
    
    public List<Bairro> list(){
        return this.getBairroRepository().list();
    }// fim do método list
    
    public List<Bairro> list(String valor){
        return this.getBairroRepository().list(valor);
    }// fim do método list
    
    public BairroRepository getBairroRepository() {
        return bairroRepository;
    }// fim do método getBairroRepository

    public void setBairroRepository(BairroRepository bairroRepository) {
        this.bairroRepository = bairroRepository;
    }// fim do método setBairroRepository
    
}// fim da classe BairroService