/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Idioma;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author henri
 */
public class IdiomaDAO implements Serializable {
    
    private String mensagem = "";
    private EntityManager em;
    
    // localizar e substituir com ctrl + h
    public IdiomaDAO(){
    
        em = EntityManagerUtil.getEntityManager();
    }
    
    public List<Idioma> getLista(){
    
        return em.createQuery("from Idioma order by nome").getResultList();
    }
    
    public boolean salvar(Idioma obj){
    
        try{
           em.getTransaction().begin();
           if(obj.getId() == null)
               em.persist(obj);
           else
               em.merge(obj);
           em.getTransaction().commit();
           mensagem = "Objeto persistido com sucesso!";
           return true;
        }catch (Exception e){
           if( em.getTransaction().isActive() == false)
               em.getTransaction().begin();
           em.getTransaction().rollback();
           mensagem = "Erro ao presistir objeto" +
                   Util.getMensagemErro(e);
           return false;
        }
        
    }
    
    public boolean remover(Idioma obj){
    
        try{
           em.getTransaction().begin();
           em.remove(obj);
           em.getTransaction().commit();
           mensagem = "Objeto removido com sucesso!";
           return true;
        }catch (Exception e){
           if( em.getTransaction().isActive() == false)
               em.getTransaction().begin();
           em.getTransaction().rollback();
           mensagem = "Erro ao remover objeto" +
                   Util.getMensagemErro(e);
           return false;
        }
        
    }
    
    public Idioma localizar(Integer id){
    
        return em.find(Idioma.class, id);
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
}
