/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.LivrariaDAO;
import br.edu.ifsul.modelo.Catalogo;
import br.edu.ifsul.modelo.Livraria;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author henri
 */
@ManagedBean(name = "controleLivraria")
@ViewScoped
public class ControleLivraria implements Serializable {

    private LivrariaDAO<Livraria> dao;
    private Livraria objeto;
    private Catalogo catalogo;
    private Boolean novoCatalogo;

    public ControleLivraria() {
        dao = new LivrariaDAO<>();

    }
    
   /* public void imprimeProdutos(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioProdutos", parametros, daoProduto.getListaTodos());
    }*/
        

    public void novoCatalogo() {
        catalogo = new Catalogo();
        setNovoCatalogo((Boolean) true);
    }
    
    public void alterarCatalogo(int index){
        catalogo = objeto.getCatalogos().get(index);
        novoCatalogo = false;
    }
    
    public void salvarCatalogo(){
        if (novoCatalogo){
            objeto.adicionarCatalogo(catalogo);
        }
        Util.mensagemInformacao("Catalogo persistido com sucesso!");
    }
    
    public void removerCatalogo(int index){
        objeto.removerCatalogo(index);
        Util.mensagemInformacao("Catalogo removido com sucesso!");
    }

    public String listar() {
        return "/privado/livraria/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Livraria();
    }

    public void salvar() {
        boolean persistiu;
        if (objeto.getId() == null) {
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

    public void editar(Integer id) {
        objeto = dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public LivrariaDAO getDao() {
        return dao;
    }

    public void setDao(LivrariaDAO dao) {
        this.dao = dao;
    }

    public Livraria getObjeto() {
        return objeto;
    }

    public void setObjeto(Livraria objeto) {
        this.objeto = objeto;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public Boolean getNovoCatalogo() {
        return novoCatalogo;
    }

    public void setNovoCatalogo(Boolean novoCatalogo) {
        this.novoCatalogo = novoCatalogo;
    }

}