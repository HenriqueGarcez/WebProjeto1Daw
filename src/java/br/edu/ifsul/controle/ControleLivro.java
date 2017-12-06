/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/ 
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AutorDAO;
import br.edu.ifsul.dao.CatalogoDAO;
import br.edu.ifsul.dao.LivroDAO;
import br.edu.ifsul.dao.IdiomaDAO;
import br.edu.ifsul.dao.FormatoDAO;
import br.edu.ifsul.modelo.Autor;
import br.edu.ifsul.modelo.Livro;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import static java.lang.Integer.parseInt;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author henri
*/
@ManagedBean(name = "controleLivro")
@ViewScoped
public class ControleLivro implements Serializable {

    private LivroDAO<Livro> dao;
    private Livro objeto;
    private AutorDAO<Autor> daoAutor;
    private Autor autor;
    private FormatoDAO daoFormato;
    private IdiomaDAO daoIdioma;
    private CatalogoDAO daoCatalogo;

    public ControleLivro() {
        dao = new LivroDAO<>();
        daoAutor = new AutorDAO<>();
        daoFormato = new FormatoDAO();
        daoIdioma = new IdiomaDAO();
        daoCatalogo = new CatalogoDAO();
    }
    
    public void adicionarAutor(){
        if (autor != null){
            if (!objeto.getAutores().contains(autor)){
                objeto.getAutores().add(autor);
                Util.mensagemInformacao("Autor adicionado com sucesso!");
            } else {
                Util.mensagemErro("Autor já existente na lista de desejos!");
            }
        }
    }
    
    public void removerAutor(int index){
        objeto.getAutores().remove(index);
        Util.mensagemInformacao("Autor removido com sucesso!");
    }

    public String listar() {
        return "/privado/livro/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Livro();
    }

    public void salvar() {
        boolean persistiu;
        if (objeto.getISBN()== null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public void editar(String ISBN) {
        objeto = dao.localizar(ISBN);
    }

    public void remover(String ISBN) {
        objeto = dao.localizar(ISBN);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
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

    public AutorDAO<Autor> getDaoAutor() {
        return daoAutor;
    }

    public void setDaoAutor(AutorDAO<Autor> daoAutor) {
        this.daoAutor = daoAutor;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
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