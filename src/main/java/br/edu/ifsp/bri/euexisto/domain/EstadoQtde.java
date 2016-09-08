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
public class EstadoQtde implements Serializable {
    
     @Id
     private String sigla;
     private String nome;
     private long   qtde;
     
     public EstadoQtde() {
         
     }
     
     public EstadoQtde(String sigla, String nome, long qtde){
         this.sigla = sigla;
         this.nome  = nome;
         this.qtde  = qtde;
     }
     
     public void setSigla(String sigla){
         this.sigla = sigla;
     }
     public String getSigla(){
         return this.sigla;
     }
     
     public void setNome(String nomr){
         this.nome = nome;
     }
     public String getNome(){
         return this.nome;
     }
     
     public void setQtde(long qtde){
         this.qtde = qtde;
     }
     public long getQtde(){
         return this.qtde;
     }
    
}
