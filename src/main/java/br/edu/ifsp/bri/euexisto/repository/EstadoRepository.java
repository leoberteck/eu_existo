/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.repository;

import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.domain.EstadoQtde;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


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
    }// fim do método add
    
    public void update(Estado estado){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(estado);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update
    
    
    public void remove( int id ){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        Estado estadoEncontrado = entityManager.find(Estado.class, id );
        entityManager.remove(estadoEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    
    public Estado getById(int id){
        Estado estado = new Estado();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            estado = entityManager.find(Estado.class, id);
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return estado;
    }// fim do método getById;
    
    
    public int getQtdeCidade(int idEstado){
        int qtde = 0;
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {
            Query query = entityManager.createQuery("select count(c.id) as qtde) " +
                                                    "from   Cidade c "             +
                                                    "where  c.estado.id =  "       + idEstado, 
                                                    Estado.class);
            qtde = Integer.parseInt(query.getResultList().toString());
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return qtde;
    }
    
    public List<EstadoQtde> listEstadoQtde(){
        List<EstadoQtde> listaEstado = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {
//            Query query = entityManager.createQuery("select new EstadoQtde(e.uf, e.nome, count(e.id) as qtde) " +
//                                                    "from   Estado e, Cidade c "     +
//                                                    "where  e = c.estado "     +
//                                                    "group  by e.uf, e.nome "        +
//                                                    "order  by e.uf ", 
//                                                    EstadoQtde.class);

            Query query = entityManager.createQuery("select new EstadoQtde(c.estado.uf, c.estado.nome, count(c.id) as qtde) " +
                                                    "from   Cidade c "     +
                                                    "group  by c.estado.uf, c.estado.nome "        +
                                                    "order  by c.estado.uf ", 
                                                    EstadoQtde.class);
            listaEstado = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaEstado;
    }
    
    public List<Estado> list(){
        List<Estado> listaEstado = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query = entityManager.createQuery("SELECT tp FROM Estado tp order by nome");
            listaEstado = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaEstado;
    }// fim do método list
    
    
    // Buscar o estado pelo nome ou pela sigla
    // Se tipo = "N" --> Busca pelo nome
    // Se tipo = "S" --> Busca pela sigla
    public List<Estado> list(String valor, String tipo){
        String sql = "SELECT tp FROM Estado tp ";
        if  (tipo.equals("N")) {
            sql = sql + "where upper(nome) = '" + valor.trim().toUpperCase() + "'";
        }
        else if  (tipo.equals("S")) {
                 sql = sql + "where upper(uf)   = '" + valor.trim().toUpperCase() + "'";
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
    }// fim do método list
    
    
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
    }// fim do método check
    
    //
    // Tipo = N --> verificar se o nome  já existe (fazer select pelo nome)
    // Tipo = S --> verificar se a sigla já existe (fazer select pela sigla)
    private String generateSQL (String valor, String tipo, int idOld) {
        String sql = "";
        
        if   (tipo == "N") {
             if   (idOld == 0) {
                  sql = "SELECT id FROM Estado "
                      + "where  upper(nome) = '" + valor.trim().toUpperCase() + "'";
             }
             else {
                  sql = "SELECT id FROM Estado "
                      + "where  upper(nome) = '" + valor.trim().toUpperCase() + "' "
                      + "and    id          <> " + idOld;
             }
        }
        else {
             if   (idOld == 0) {
                  sql = "SELECT id FROM Estado "
                      + "where  upper(uf) = '" + valor.trim().toUpperCase() + "'";
             }
             else {
                  sql = "SELECT id FROM Estado "
                      + "where  upper(uf) = '" + valor.trim().toUpperCase() + "' "
                      + "and    id        <> " + idOld;
             }
        }// fim do if   (tipo == "N") {

        return sql;
    }// fim do método generateSQL
}// fim da classe EstadoRepository
