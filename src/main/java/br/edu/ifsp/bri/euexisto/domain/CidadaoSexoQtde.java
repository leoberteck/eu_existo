/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author gahsabio
 */
@Entity
public class CidadaoSexoQtde implements Serializable {
    
     @Id
     private String valor;
     private long   qtdeFem;
     private long   qtdeMasc;
     
     public CidadaoSexoQtde() {
         
     }
     
     public CidadaoSexoQtde(String valor, long qtdeFem, long qtdeMasc){
         this.valor     = valor;
         this.qtdeFem   = qtdeFem;
         this.qtdeMasc  = qtdeMasc;
     }
     
     public void setValor(String valor){
         this.valor = valor;
     }
     public String getValor(){
         return this.valor;
     }
     
     public void setQtdeFem(long qtdeFem){
         this.qtdeFem = qtdeFem;
     }
     public long getQtdeFem(){
         return this.qtdeFem;
     }
     
     public void setQtdeMasc(long qtdeMasc){
         this.qtdeMasc = qtdeMasc;
     }
     public long getQtdeMasc(){
         return this.qtdeMasc;
     }
     
}// fim da classe CidadaoSexoQtde
