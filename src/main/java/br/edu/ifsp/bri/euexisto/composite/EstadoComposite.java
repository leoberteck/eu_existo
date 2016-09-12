/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.composite;

/**
 *
 * @author gahsabio
 */
public class EstadoComposite {
    
    private String uf;
    private String nome;
    private int    qtdeCidade;
    
    public EstadoComposite(){
        
    } 
    
    public EstadoComposite (String uf, String nome, int qtdeCidade) {
        this.uf         = uf;
        this.nome       = nome;
        this.qtdeCidade = qtdeCidade;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the qtdeCidade
     */
    public int getQtdeCidade() {
        return qtdeCidade;
    }

    /**
     * @param qtdeCidade the qtdeCidade to set
     */
    public void setQtdeCidade(int qtdeCidade) {
        this.qtdeCidade = qtdeCidade;
    }
    
}// fim da classe EstadoComposite
