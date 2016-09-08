/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.service;

import br.edu.ifsp.bri.euexisto.domain.Rua;
import br.edu.ifsp.bri.euexisto.repository.RuaRepository;
import java.io.Serializable;
import java.util.List;
             
/**
 *
 * @author gahsabio
 */
public class RuaService implements Serializable {

    private RuaRepository ruaRepository;

    public RuaService() {
        ruaRepository = new RuaRepository();
    }

   
    // Esse método deve fazer as seguintes verificações
    // Inclusão:  não pode incluir uma rua com um nome que já exista
    // Alteração: não pode alterar o nome para um nome que já exista
    public void add(Rua rua) {
        if  (check(rua, 0))
            this.getRuaRepository().add(rua);
    }// fim do método add
    
    public void update(Rua rua){
        if  (check(rua, rua.getId()))
            this.getRuaRepository().update(rua);
    }// fim do método update 
    
    public void remove(Rua rua){
        this.getRuaRepository().remove(rua.getId());
    }// fim do método remove
   
    public boolean check (Rua rua, int idOld) {
        boolean ruaOk = true;
        
        if  (rua.getNome().trim().equals(""))
             ruaOk = false;
        else if (this.getRuaRepository().check(rua, idOld) > 0 )
                 ruaOk = false;
        
        return ruaOk;
    }// fim do método check
    
    public List<Rua> list(){
        return this.getRuaRepository().list();
    }// fim do método list
   
    public List<Rua> list(String valor){
        return this.getRuaRepository().list(valor);
    }// fim do método list
    
    public RuaRepository getRuaRepository() {
        return ruaRepository;
    }// fim do método getRuaRepository

    public void setRuaRepository(RuaRepository ruaRepository) {
        this.ruaRepository = ruaRepository;
    }// fim do método setRuaRepository
    
}// fim da classe RuaService