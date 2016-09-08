/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.service;

import br.edu.ifsp.bri.euexisto.domain.Cep;
import br.edu.ifsp.bri.euexisto.repository.CepRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class CepService {
    
    private CepRepository cepRepository;

    public CepService() {
        cepRepository = new CepRepository();
    }
    
    // Esse método deve fazer as seguintes verificações
    // Inclusão:  não pode incluir um cep que já exista
    // Alteração: não pode alterar um cep para um que já exista
    public void add(Cep cep) {
        //   na inclusão, id = 0
        if   (check(cep, 0))
              this.getCepRepository().add(cep);
    }// fim do metodo add
    
    public void update(Cep cep){
        //   na alteração, passar o id do cep que está sendo alterado
        if   (check(cep, cep.getId()))
              this.getCepRepository().update(cep);
    }// fim do método update
    
    public void remove(Cep cep){
        this.getCepRepository().remove(cep.getId());
    }// fim do métodoremove
    
    // na inclusão,  id = 0
    // na alteração, id do objeto que está sendo alterado
    public boolean check (Cep cep, int idOld) {
        boolean cepOk = true;
        
        if   (cep.getNumeroCep().trim().equals(""))
             cepOk = false;
        else if   (cep.getCidade().getId() == 0)
                  cepOk = false;
        else if   ((cep.getNumeroIni()!=0) && (cep.getNumeroFim() == 0))
                  cepOk = false;
        else if   (!check(cep.getNumeroCep(),cep.getBairro().getId(),cep.getRua().getId()))
                  cepOk = false;
        else if   (this.cepRepository.check(cep.getNumeroCep(), idOld) > 0)
                  cepOk = false;
        
        return  cepOk;
    }// fim do método check
    
    public boolean check(String cep, int idBairro, int idRua) {
        boolean cepOk = true;
        
        String ultima = cep.substring(7, 10);
        
        if     (ultima.equals("000")) {
               if   ((idBairro != 0) || (idRua !=0))
                    cepOk = false;
        }
        else   {
               if   ((idBairro == 0) || (idRua ==0))
                    cepOk = false;
        }
        return  cepOk;
    }
    
    public List<Cep> list(){
        return this.getCepRepository().list();
    }// fim do método list
    
    public List<Cep> list(int idCidade){
        return this.getCepRepository().list(idCidade);
    }// fim do método list
    
    public List<Cep> list(String cep){
        return this.getCepRepository().list(cep);
    }// fim do método list
    
    public CepRepository getCepRepository() {
        return cepRepository;
    }// fim do metodo getCepRepository

    public void setCepRepository(CepRepository cepRepository) {
        this.cepRepository = cepRepository;
    }// fim do método setCepRepository
    
}// fim da classe CepService
