/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.repository;

import br.edu.ifsp.bri.euexisto.domain.Cep;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gahsabio
 */
public class CepRepository implements Serializable {
    
    public CepRepository() {
    }
    
    public void add(Cep cep){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(cep);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add
    
    public void update(Cep cep){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(cep);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update
    
    
    public void remove( int id ){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        Cep cepEncontrado = entityManager.find(Cep.class, id );
        entityManager.remove(cepEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    
    public List<Cep> list(){
        List<Cep> listaCep = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query = entityManager.createQuery("SELECT tp FROM Cep tp order by idCidade, numeroCep ");
            listaCep = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaCep;
    }// fim do método list
        
    
    public List<Cep> list(int idCidade){
        List<Cep> listaCep = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query = entityManager.createQuery("SELECT tp FROM Rua r, Cep tp " +
                                                    "where r.id         = tp.rua.id "      +
                                                    "and   tp.cidade.id = " + idCidade  + " " +
                                                    "order by r.nome, tp.numeroIni ");
            listaCep = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaCep;
    }// fim do método list
    
    
    // Buscar o cep pelo cep
    public List<Cep> list(String cep){
        String sql = "SELECT tp FROM Cep tp "
                   + "where  numeroCep = '" + cep.trim().toUpperCase() + "' ";
        
        List<Cep> listaCep = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query = entityManager.createQuery(sql);
            listaCep    = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaCep;
    }// fim do método list
    
    
    // Verificar se o cep que está sendo incluído ja existe
    // 
    // Fazer essa verificação pelo cep
    // idOld é o id do objeto que está sendo alterado. Zero quando for inclusao
    public int check(String cep, int idOld){
        // Verificar se já existe um cep cadastrado para a descrição
        List<Cep> listaCep = new ArrayList<>();
        String            sql              = generateSQL(cep, idOld);
        EntityManager     entityManager    = JPAConnection.getEntityManager();
        Query             query;
        try {       
            query    = entityManager.createQuery(sql);   
            listaCep = query.getResultList();
        } 
        catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        
        return listaCep.size();
    }// fim do método check
    

    private String generateSQL (String cep, int idOld) {
        String sql = "";
        
        if   (idOld == 0) {
             sql = "SELECT id FROM Cep tp "
                 + "where  tp.numeroCep = '" + cep.trim().toUpperCase() + "' ";
             }
        else {
             sql = "SELECT id FROM Cep tp "
                 + "where  tp.numeroCep = '" + cep.trim().toUpperCase() + "' "
                 + "and    id  <> " + idOld;
        }

        return sql;
    }// fim do método generateSQL  
  
}// fim da classe CepRepository