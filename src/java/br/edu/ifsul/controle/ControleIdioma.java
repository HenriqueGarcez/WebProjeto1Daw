/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.IdiomaDAO;
import br.edu.ifsul.modelo.Idioma;
import br.edu.ifsul.util.Util;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author henri
 */
@ManagedBean(name = "controleIdioma")
@SessionScoped
public class ControleIdioma {
    
    private IdiomaDAO dao;
    private Idioma objeto;
        
        
    public ControleIdioma(){
        
        dao = new IdiomaDAO();
    }
    
    public String listar(){
    
        return "/privado/idioma/listar?faces-redirect=true";
    }
    
    public String novo(){
    
        objeto = new Idioma();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
    
        if( dao.salvar(objeto)){
        
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        }else{
            
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }  
    
    public String editar(Integer id){
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover( Integer id){
        objeto = dao.localizar(id);
        if(dao.remover(objeto))
            Util.mensagemInformacao(dao.getMensagem());
        else
            Util.mensagemErro(dao.getMensagem());
    }
    
    public IdiomaDAO getDao() {
        return dao;
    }

    public void setDao(IdiomaDAO dao) {
        this.dao = dao;
    }

    public Idioma getObjeto() {
        return objeto;
    }

    public void setObjeto(Idioma objeto) {
        this.objeto = objeto;
}
    
}
