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
public class CidadeComposite {
    
    private String nomeCidade;
    private String nomeEstado;
    private String uf;
    
    public CidadeComposite (){
        nomeCidade = "";
        nomeEstado = "";
        uf         = "";
    }
    
    public CidadeComposite (String nomeCidade, String nomeEstado, String uf){
        this.nomeCidade = nomeCidade;
        this.nomeEstado = nomeEstado;
        this.uf         = uf;
    }

    /**
     * @return the nomeCidade
     */
    public String getNomeCidade() {
        return nomeCidade;
    }

    /**
     * @param nomeCidade the nomeCidade to set
     */
    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    /**
     * @return the nomeEstado
     */
    public String getNomeEstado() {
        return nomeEstado;
    }

    /**
     * @param nomeEstado the nomeEstado to set
     */
    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
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
    
    
    
}
