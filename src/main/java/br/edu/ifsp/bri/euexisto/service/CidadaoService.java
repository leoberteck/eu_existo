/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.service;

import br.edu.ifsp.bri.euexisto.domain.Cidadao;
import br.edu.ifsp.bri.euexisto.domain.CidadaoSexoQtde;
import br.edu.ifsp.bri.euexisto.repository.CidadaoRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class CidadaoService {
    
    private CidadaoRepository cidadaoRepository;

    public CidadaoService() {
        cidadaoRepository = new CidadaoRepository();
    }
    
    // Esse método deve fazer as seguintes verificações
    // Inclusão:  não pode incluir um cidadao que já exista
    // Alteração: não pode alterar um cidadao para um que já exista
    public void add(Cidadao cidadao) {
        //   na inclusão, id = 0
        if   (check(cidadao, 0))
              this.getCidadaoRepository().add(cidadao);
    }// fim do metodo add
    
    public void update(Cidadao cidadao){
        //   na alteração, passar o id do cidadao que está sendo alterado
        if   (check(cidadao, cidadao.getId()))
              this.getCidadaoRepository().update(cidadao);
    }// fim do método update
    
    public void remove(Cidadao cidadao){
        this.getCidadaoRepository().remove(cidadao.getId());
    }// fim do método remove
    
    // na inclusão,  id = 0
    // na alteração, id do objeto que está sendo alterado
    public boolean check (Cidadao cidadao, int idOld) {
        boolean cidadaoOk = true;

        if   (cidadao.getNome().trim().equals("")){
             cidadaoOk = false;
             System.out.println("Um " + cidadaoOk);
        }
        else if   (cidadao.getCpf().trim().equals("")){
                  cidadaoOk = false;
             System.out.println("Dois " + cidadaoOk);
        }
        else if   (cidadao.getDataNasc().equals("")){
                  cidadaoOk = false;
             System.out.println("Três " + cidadaoOk);
        }
        else if   (cidadao.getSenha().trim().equals("")){
                  cidadaoOk = false;
             System.out.println("Quatro " + cidadaoOk);
        }
        else if   (cidadao.getEndereco().getId() == 0) {
                  cidadaoOk = false;
             System.out.println("Cinco " + cidadaoOk);
        }
        else if   ((cidadao.getSexo().trim().equals("")) || 
                   (!(cidadao.getSexo().trim().equals("F"))) &&
                   (!(cidadao.getSexo().trim().equals("M")))) {
                  cidadaoOk = false;
             System.out.println("Seis " + cidadaoOk);
        }
        else if   (this.cidadaoRepository.check(cidadao.getEmail(), cidadao.getCpf(), idOld) > 0) {
                  cidadaoOk = false;
             System.out.println("Sete " + cidadaoOk);
        }

        return  cidadaoOk;
    }// fim do método check
    
    public List<Cidadao> list(){
        return this.getCidadaoRepository().list();
    }// fim do método list
        
    public List<Cidadao> list(int valor, String tipoBusca){
        return this.getCidadaoRepository().list(valor, tipoBusca);
    }// fim do método list
    
    public List<Cidadao> list(String valor, String tipoBusca){
        return this.getCidadaoRepository().list(valor, tipoBusca);
    }// fim do método list
      
    public List<CidadaoSexoQtde> listCidadaoEstadoSexoQtde(){
        return this.getCidadaoRepository().listCidadaoEstadoSexoQtde();
    }// fim do método ListCidadaoEstadoSexoQtde
      
    public List<CidadaoSexoQtde> listCidadaoCidadeSexoQtde(int idEstado){
        return this.getCidadaoRepository().listCidadaoCidadeSexoQtde(idEstado);
    }// fim do método ListCidadaoCidadeSexoQtde
    
    public int getCidadaoSexoQtdeMax(){
        return this.getCidadaoRepository().getCidadaoSexoQtdeMax();
    }// fim do método getCidadaoSexoQtdeMax
    
    public CidadaoRepository getCidadaoRepository() {
        return cidadaoRepository;
    }// fim do metodo getCidadaoRepository

    public void setCidadaoRepository(CidadaoRepository cidadaoRepository) {
        this.cidadaoRepository = cidadaoRepository;
    }// fim do método setCidadaoRepository
        
}
