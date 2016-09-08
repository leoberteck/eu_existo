/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.service;

import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.domain.EstadoQtde;
import br.edu.ifsp.bri.euexisto.repository.EstadoRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
                
/**
 *
 * @author gahsabio
 */
public class EstadoService implements Serializable {

    private EstadoRepository estadoRepository;

    public EstadoService() {
        estadoRepository = new EstadoRepository();
    }
    
    // Esse método deve fazer as seguintes verificações
    // Inclusão:  não pode incluir um estado com um  nome que já xista
    //            não pode incluir um estado com uma uf   que já exista
    // Alteração: não pode alterar o nome para um  nome que já xista
    //            não pode alterar a uf   para uma uf   que já exista
    public void add(Estado estado) {
        //   na inclusão, id = 0
        if   (check(estado, 0))
              this.getEstadoRepository().add(estado);
    }// fim do metodo add
    
    public void update(Estado estado){
        //   na alteração, passar o id dp estado que está sendo alterado
        if   (check(estado, estado.getId()))
              this.getEstadoRepository().update(estado);
    }// fim do método update
    
    public void remove(Estado estado){
        this.getEstadoRepository().remove(estado.getId());
    }// fim do métodoremove
    
    // na inclusão,  id = 0
    // na alteração, id do objeto que está sendo alterado
    public boolean check (Estado estado, int idOld) {
        boolean estadoOk = true;
        
        if   (estado.getNome().trim().equals(""))
             estadoOk = false;
        else if   (estado.getUf().trim().equals(""))
                  estadoOk = false;
        else if   (this.estadoRepository.check(estado, idOld) > 0)
                  estadoOk = false;
        
        return  estadoOk;
    }// fim do método check
    
    public Estado getById (int id) {
        return this.estadoRepository.getById(id);
    }// fim do metodo getById
    
    public int getQtdeCidade(int idEstado){
        return this.estadoRepository.getQtdeCidade(idEstado);
    }// fim do método getQtdeCidade
    
    public List<Estado> list(){
        return this.getEstadoRepository().list();
    }// fim do método list
    
    public List<Estado> list(String valor, String tipo){
        return this.getEstadoRepository().list(valor, tipo);
    }// fim do método list
    
    public List<EstadoQtde> listEstadoQtde(){
        return this.getEstadoRepository().listEstadoQtde();
    }// fim do método ListEstadoQtde
    
    public EstadoRepository getEstadoRepository() {
        return estadoRepository;
    }// fim do metodo getEstadoRepository

    public void setEstadoRepository(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }// fim do método setEstadoRepository
    
}// fim da classe EstadoService
