/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.repository;

import br.edu.ifsp.bri.euexisto.domain.Estado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gahsabio
 */
public class EstadoRepository implements Serializable {
    
    public EstadoRepository() {
    }
    
    public void add(Estado estado){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(estado);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }
    
    public void update(Estado estado){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(estado);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }
    
    
    public void remove( int id ){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        Estado estadoEncontrado = entityManager.find(Estado.class, id );
        entityManager.remove(estadoEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }
    
    
    public List<Estado> list(){
        List<Estado> listaEstado = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query = entityManager.createQuery("SELECT tp FROM Estado tp ");
            listaEstado = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaEstado;
    }
    
    
    // Buscar o estado pelo nome ou pela sigla
    // Se tipo = "N" --> Busca pelo nome
    // Se tipo = "S" --> Busca pela sigla
    public List<Estado> list(String valor, String tipo){
        String sql = "SELECT tp FROM Estado tp ";
        if  (tipo.equals("N")) {
            sql = sql + "where nome = '" + valor + "'";
        }
        else if  (tipo.equals("S")) {
                 sql = sql + "where uf   = '" + valor + "'";
             }
        else sql = "";
        
        List<Estado> listaEstado = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query = entityManager.createQuery(sql);
            listaEstado = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaEstado;
    }    
    
    
    // Verificar se o estado que está sendo incluído ja existe
    // ou
    // Verificar se o estado que está sendo alterado está sendo alterado para
    // um nome ou UF que já existe
    // 
    // Fazer essa verificação pelo nome e pela UF
    // idOld é o id do objeto que está sendo alterado. Zero quando for inclusao
    public int check(Estado estado, int idOld){
        // Verificar se já existe um estado cadastrado para o nome
        List<Estado>  listaEstado    = new ArrayList<>();
        String        sql            = generateSQL(estado.getNome(), "N", idOld);
        EntityManager entityManager = JPAConnection.getEntityManager();
        Query         query;
        try {       
            query       = entityManager.createQuery(sql);   
            listaEstado = query.getResultList();
            
            if  (listaEstado.size() == 0) {
                // Verificar se já existe um estado cadastrado para a sigla 
                // somente se não existe um para o nome            
                sql          = generateSQL(estado.getUf(), "S", idOld);
                query        = entityManager.createQuery(sql);
                listaEstado  = query.getResultList();
            }
        } 
        catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        
        return listaEstado.size();
    }    
    
    //
    // Tipo = N --> verificar se o nome  já existe (fazer select pelo nome)
    // Tipo = S --> verificar se a sigla já existe (fazer select pela sigla)
    private String generateSQL (String valor, String tipo, int idOld) {
        String sql = "";
        
        if   (tipo == "N") {
             if   (idOld == 0) {
                  sql = "SELECT id FROM Estado "
                      + "where  upper(nome) = '" + valor.toUpperCase() + "'";
             }
             else {
                  sql = "SELECT id FROM Estado "
                      + "where  upper(nome) = '" + valor.toUpperCase() + "' "
                      + "and    id          <> " + idOld;
             }
        }
        else {
             if   (idOld == 0) {
                  sql = "SELECT id FROM Estado "
                      + "where  upper(uf) = '" + valor.toUpperCase() + "'";
             }
             else {
                  sql = "SELECT id FROM Estado "
                      + "where  upper(uf) = '" + valor.toUpperCase() + "' "
                      + "and    id        <> " + idOld;
             }
        }// fim do if   (tipo == "N") {

        return sql;
    }// fim do método generateSQL
}// fim da classe EstadoRepository
