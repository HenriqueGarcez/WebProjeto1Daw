/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/ 
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CatalogoDAO;
import br.edu.ifsul.dao.LivroDAO;
import br.edu.ifsul.dao.IdiomaDAO;
import br.edu.ifsul.dao.FormatoDAO;
import br.edu.ifsul.modelo.Livro;
import br.edu.ifsul.util.Util;
import static java.lang.Integer.parseInt;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author henri
*/
@ManagedBean(name = "controleLivro")
@SessionScoped
public class ControleLivro {
    
    private LivroDAO dao;
    private Livro objeto;
    private FormatoDAO daoFormato;
    private IdiomaDAO daoIdioma;
    private CatalogoDAO daoCatalogo;
        
        
    public ControleLivro(){
        
        dao = new LivroDAO();
        daoFormato = new FormatoDAO();
        daoIdioma = new IdiomaDAO();
        daoCatalogo = new CatalogoDAO();
    }
    
    public String listar(){
    
        return "/privado/livro/listar?faces-redirect=true";
    }
    
    public String novo(){
    
        objeto = new Livro();
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
    
    public String editar(String isbn){
        objeto = dao.localizar(isbn);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(String isbn){
        objeto = dao.localizar(isbn);
        if(dao.remover(objeto))
            Util.mensagemInformacao(dao.getMensagem());
        else
            Util.mensagemErro(dao.getMensagem());
    }
    
    public LivroDAO getDao() {
        return dao;
    }

    public void setDao(LivroDAO dao) {
        this.dao = dao;
    }

    public Livro getObjeto() {
        return objeto;
    }

    public void setObjeto(Livro objeto) {
        this.objeto = objeto;
}

    public FormatoDAO getDaoFormato() {
        return daoFormato;
    }

    public void setDaoFormato(FormatoDAO daoFormato) {
        this.daoFormato = daoFormato;
    }

    public IdiomaDAO getDaoIdioma() {
        return daoIdioma;
    }

    public void setDaoIdioma(IdiomaDAO daoIdioma) {
        this.daoIdioma = daoIdioma;
    }

    public CatalogoDAO getDaoCatalogo() {
        return daoCatalogo;
    }

    public void setDaoCatalogo(CatalogoDAO daoCatalogo) {
        this.daoCatalogo = daoCatalogo;
    }
    
}
