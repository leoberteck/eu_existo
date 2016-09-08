/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.service;

import br.edu.ifsp.bri.euexisto.domain.FaixaEtaria;
import br.edu.ifsp.bri.euexisto.repository.FaixaEtariaRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class FaixaEtariaService {
    
    private FaixaEtariaRepository faixaEtariaRepository;

    public FaixaEtariaService() {
        faixaEtariaRepository = new FaixaEtariaRepository();
    }
    
    // Esse método deve fazer as seguintes verificações
    // Inclusão:  não pode incluir uma faixa etária com uma descricao, para uma cidade, que já exista
    // Alteração: não pode alterar a descrição de uma faixa etária para uma descrição que já exista, para uma mesma cidade
    public void add(FaixaEtaria faixaEtaria) {
        //   na inclusão, id = 0
        if   (check(faixaEtaria, 0))
              this.getFaixaEtariaRepository().add(faixaEtaria);
    }// fim do metodo add
    
    public void update(FaixaEtaria faixaEtaria){
        //   na alteração, passar o id da faixaEtaria que está sendo alterada
        if   (check(faixaEtaria, faixaEtaria.getId()))
              this.getFaixaEtariaRepository().update(faixaEtaria);
    }// fim do método update
    
    public void remove(FaixaEtaria faixaEtaria){
        this.getFaixaEtariaRepository().remove(faixaEtaria.getId());
    }// fim do métodoremove
    
    // na inclusão,  id = 0
    // na alteração, id do objeto que está sendo alterado
    public boolean check (FaixaEtaria faixaEtaria, int idOld) {
        boolean faixaEtariaOk = true;
        
        if   (faixaEtaria.getDescricao().trim().equals(""))
             faixaEtariaOk = false;
        else if   (faixaEtaria.getCidade().getId() == 0)
                  faixaEtariaOk = false;
        else if   (faixaEtaria.getIdadeFim() == 0)
                  faixaEtariaOk = false;
        else if   (faixaEtaria.getIdadeIni() > faixaEtaria.getIdadeFim())
                  faixaEtariaOk = false;
        else if   (this.faixaEtariaRepository.check(faixaEtaria, idOld) > 0)
                  faixaEtariaOk = false;

        return  faixaEtariaOk;
    }// fim do método check
    
    public List<FaixaEtaria> list(){
        return this.getFaixaEtariaRepository().list();
    }// fim do método list
    
    public List<FaixaEtaria> list(int idCidade){
        return this.getFaixaEtariaRepository().list(idCidade);
    }// fim do método list
    
    public List<FaixaEtaria> list(String descricao, int idCidade){
        return this.getFaixaEtariaRepository().list(descricao, idCidade);
    }// fim do método list
    
    public List<FaixaEtaria> list(int idade, int idCidade){
        return this.getFaixaEtariaRepository().list(idade, idCidade);
    }// fim do método list
    
    public FaixaEtariaRepository getFaixaEtariaRepository() {
        return faixaEtariaRepository;
    }// fim do metodo getFaixaEtariaRepository

    public void setFaixaEtariaRepository(FaixaEtariaRepository faixaEtariaRepository) {
        this.faixaEtariaRepository = faixaEtariaRepository;
    }// fim do método setFaixaEtariaRepository
        
}// fim da classe FaixaEtariaService
