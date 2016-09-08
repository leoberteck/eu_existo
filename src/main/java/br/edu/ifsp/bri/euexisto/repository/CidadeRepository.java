/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.repository;

import br.edu.ifsp.bri.euexisto.domain.Cidade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gahsabio
 */
public class CidadeRepository implements Serializable {
    
    public CidadeRepository() {
    }
    
    public void add(Cidade cidade){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(cidade);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add
    
    public void update(Cidade cidade){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(cidade);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update
    
    
    public void remove( int id ){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        Cidade cidadeEncontrado = entityManager.find(Cidade.class, id );
        entityManager.remove(cidadeEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    
    public Cidade getById(int id){
        Cidade cidade = new Cidade();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            cidade = entityManager.find(Cidade.class, id);
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return cidade;
    }// fim do método getById;
    
    
    public List<Cidade> list(){
        List<Cidade> listaCidade = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query = entityManager.createQuery("SELECT tp FROM Cidade tp order by idEstado, nome " );
            listaCidade = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaCidade;
    }// fim do método list
    
    
    public List<Cidade> list(int idEstado){
        List<Cidade> listaCidade = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query = entityManager.createQuery("SELECT tp FROM Cidade tp where idEstado = " + idEstado + " order by nome ");
            listaCidade = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaCidade;
    }// fim do método list
    
    
    // Buscar s cidade pelo nome 
    public List<Cidade> list(String nome, int idEstado){
        String sql = "SELECT tp FROM Cidade tp "
                   + "where  upper(nome) = '" + nome.trim().toUpperCase() + "' "
                   + "and    idEstado    = "  + idEstado;
        
        List<Cidade> listaCidade = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query = entityManager.createQuery(sql);
            listaCidade = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaCidade;
    }// fim do método list
    
    
    // Verificar se a cidade que está sendo incluída ja existe
    // 
    // Fazer essa verificação pelo nome
    // idOld é o id do objeto que está sendo alterado. Zero quando for inclusao
    public int check(Cidade cidade, int idOld){
        // Verificar se já existe uma cidade cadastrada para o nome
        List<Cidade>  listaCidade    = new ArrayList<>();
        String        sql            = generateSQL(cidade.getNome(), cidade.getEstado().getId(), idOld);
        EntityManager entityManager = JPAConnection.getEntityManager();
        Query         query;
        try {       
            query       = entityManager.createQuery(sql);   
            listaCidade = query.getResultList();
        } 
        catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        
        return listaCidade.size();
    }// fim do método check
    
    //
    private String generateSQL (String nome, int idEstado, int idOld) {
        String sql = "";
        
        if   (idOld == 0) {
             sql = "SELECT id FROM Cidade "
                 + "where  upper(nome) = '" + nome.trim().toUpperCase() + "' "
                 + "and    idEstado    = "  + idEstado;
             }
        else {
             sql = "SELECT id FROM Cidade "
                 + "where  upper(nome) = '" + nome.trim().toUpperCase() + "' "
                     
                 + "and    idEstado    = "  + idEstado                  + " "
                 + "and    id          <> " + idOld;
        }

        return sql;
    }// fim do método generateSQL  
}
