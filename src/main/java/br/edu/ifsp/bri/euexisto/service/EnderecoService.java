/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.service;

import br.edu.ifsp.bri.euexisto.domain.Endereco;
import br.edu.ifsp.bri.euexisto.repository.EnderecoRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class EnderecoService {
    
    private EnderecoRepository enderecoRepository;

    public EnderecoService() {
        enderecoRepository = new EnderecoRepository();
    }
    
    // Esse método deve fazer as seguintes verificações
    // Inclusão:  não pode incluir um endereco que já exista
    // Alteração: não pode alterar um endereco para um que já exista
    public void add(Endereco endereco) {
        //   na inclusão, id = 0
        if   (check(endereco, 0))
              this.getEnderecoRepository().add(endereco);
    }// fim do metodo add
    
    public void update(Endereco endereco){
        //   na alteração, passar o id do endereco que está sendo alterado
        if   (check(endereco, endereco.getId()))
              this.getEnderecoRepository().update(endereco);
    }// fim do método update
    
    public void remove(Endereco endereco){
        this.getEnderecoRepository().remove(endereco.getId());
    }// fim do método remove
    
    // na inclusão,  id = 0
    // na alteração, id do objeto que está sendo alterado
    public boolean check (Endereco endereco, int idOld) {
        boolean enderecoOk = true;

        if   (endereco.getNumero().trim().equals("")){
             enderecoOk = false;
        }
        else if   (endereco.getCep().getId() == 0) {
                  enderecoOk = false;
        }
        else if   (((endereco.getBairro().getId()!=0) && (endereco.getRua().getId() == 0)) ||
                   ((endereco.getBairro().getId()==0) && (endereco.getRua().getId() != 0))) {
                  enderecoOk = false;
        }
        else if   (!check(endereco.getCep().getNumeroCep(), endereco.getCep().getBairro().getId(), 
                          endereco.getCep().getRua().getId())) {
                  enderecoOk = false;
        }
        else if   (this.enderecoRepository.check(endereco.getCep().getId(), endereco.getRua().getId(), 
                                                 endereco.getNumero(),      idOld) > 0) {
                  enderecoOk = false;
        }

        return  enderecoOk;
    }// fim do método check
    
    public boolean check(String cep, int idBairro, int idRua) {
        boolean enderecoOk = true;
        
        String ultima = cep.substring(7, 10);
                        
        if     (ultima.equals("000")) {
               if   ((idBairro != 0) || (idRua !=0))
                    enderecoOk = false;
        }
        else   {
               if   ((idBairro == 0) || (idRua ==0))
                    enderecoOk = false;
        }
        
        return  enderecoOk;
    }
    
    public List<Endereco> list(){
        return this.getEnderecoRepository().list();
    }// fim do método list
        
    public List<Endereco> list(int idCidade){
        return this.getEnderecoRepository().list(idCidade);
    }// fim do método list
    
    public List<Endereco> list(int idCep, String numero){
        return this.getEnderecoRepository().list(idCep, numero);
    }// fim do método list
    
    public List<Endereco> list(int idCep, int idRua, String numero){
        return this.getEnderecoRepository().list(idCep, idRua, numero);
    }// fim do método list
    
    public EnderecoRepository getEnderecoRepository() {
        return enderecoRepository;
    }// fim do metodo getEnderecoRepository

    public void setEnderecoRepository(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }// fim do método setEnderecoRepository
    
} // fim da classe EnderecoService
