/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.repository;

import br.edu.ifsp.bri.euexisto.domain.Rua;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gahsabio
 */
public class RuaRepository implements Serializable {
   
    public RuaRepository() {
    }
    
    public void add(Rua rua){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(rua);               //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add
    
    public void update(Rua rua){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(rua);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update
    
    
    public void remove( int id ){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        Rua ruaEncontrado = entityManager.find(Rua.class, id );
        entityManager.remove(ruaEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    
    public List<Rua> list(){
        List<Rua> listaRua = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query = entityManager.createQuery("SELECT tp FROM Rua tp order by nome ");
            listaRua = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaRua;
    }// fim do metodo list
    
    
    // Buscar a rua pelo nome
    public List<Rua> list(String valor){
        List<Rua> listaRua = new ArrayList<>();
        
        if   (!valor.trim().equals("")) {
             String sql = "SELECT tp FROM Rua tp "
                   + "where upper(nome) = '" + valor.trim().toUpperCase() + "'";

             EntityManager entityManager = JPAConnection.getEntityManager();
             try {       
                 Query query = entityManager.createQuery(sql);
                 listaRua = query.getResultList();
             } 
             catch (Exception e){
                 e.printStackTrace();
             }
             entityManager.close();
        }
        
        return listaRua;
    }// fim do método list 
    
    
    // Verificar se a rua que está sendo incluída ja existe
    // idOld é o id do objeto que está sendo alterado. Zero quando for inclusao
    public int check(Rua rua, int idOld){
        // Verificar se já existe uma rua cadastrado para o nome
        List<Rua>     listaRua      = new ArrayList<>();
        String        sql           = generateSQL(rua.getNome(), idOld);
        EntityManager entityManager = JPAConnection.getEntityManager();
        Query         query;
        try {       
            query    = entityManager.createQuery(sql);   
            listaRua = query.getResultList();
        } 
        catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        
        return listaRua.size();
    }// fim do método check  
    
        
    private String generateSQL (String valor, int idOld) {
        String sql = "";
        
        if   (idOld == 0) {
             sql = "SELECT id FROM Rua "
                 + "where  upper(nome) = '" + valor.trim().toUpperCase() + "'";
        }
        else {
             sql = "SELECT id FROM Rua "
                 + "where  upper(nome) = '" + valor.trim().toUpperCase() + "' "
                 + "and    id          <> " + idOld;
        }

        return sql;
    }// fim do método generateSQL
}// fim da classe RuaRepository
