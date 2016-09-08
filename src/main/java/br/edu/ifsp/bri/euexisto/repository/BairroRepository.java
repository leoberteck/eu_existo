/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.repository;

import br.edu.ifsp.bri.euexisto.domain.Bairro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gahsabio
 */
public class BairroRepository implements Serializable {
   
    public BairroRepository() {
    }
    
    public void add(Bairro bairro){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(bairro);            //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add
    
    public void update(Bairro bairro){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(bairro);              //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update
    
    
    public void remove( int id ){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        Bairro bairroEncontrado = entityManager.find(Bairro.class, id );
        entityManager.remove(bairroEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    
    public List<Bairro> list(){
        List<Bairro> listaBairro = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query = entityManager.createQuery("SELECT tp FROM Bairro tp order by nome ");
            listaBairro = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaBairro;
    }// fim do método list
   
    
    // Buscar o bairro pelo nome
    public List<Bairro> list(String valor){
        List<Bairro> listaBairro = new ArrayList<>();

        if   (!valor.trim().equals("")) {
             String sql = "SELECT tp FROM Bairro tp "
                        + "where upper(nome) = '" + valor.trim().toUpperCase() + "'";

             EntityManager entityManager = JPAConnection.getEntityManager();
             try {       
                 Query query = entityManager.createQuery(sql);
                 listaBairro = query.getResultList();
             } catch (Exception e){
                 e.printStackTrace();
             }
             entityManager.close();
        }
        
        return listaBairro;
    }// fim do método list
    
    
    // Verificar se o bairro que está sendo incluído ja existe
    // idOld é o id do objeto que está sendo alterado. Zero quando for inclusao
    public int check(Bairro bairro, int idOld){
        // Verificar se já existe um bairro cadastrado para o nome
        List<Bairro>  listaBairro   = new ArrayList<>();
        String        sql           = generateSQL(bairro.getNome(), idOld);
        EntityManager entityManager = JPAConnection.getEntityManager();
        Query         query;
        try {       
            query       = entityManager.createQuery(sql);   
            listaBairro = query.getResultList();
        } 
        catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        
        return listaBairro.size();
    }// fim do método check  
    
        
    private String generateSQL (String valor, int idOld) {
        String sql = "";
        
        if   (idOld == 0) {
             sql = "SELECT id FROM Bairro "
                 + "where  upper(nome) = '" + valor.trim().toUpperCase() + "'";
        }
        else {
             sql = "SELECT id FROM Bairro "
                 + "where  upper(nome) = '" + valor.trim().toUpperCase() + "' "
                 + "and    id          <> " + idOld;
        }

        return sql;
    }// fim do método generateSQL    
}// fim da classe BairroRepository
