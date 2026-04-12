/**
 * 
 */
package br.com.rpires.controller;

import java.util.List;

import br.com.rpires.dao.IProdutoDAO;
import br.com.rpires.dao.ProdutoDAOImpl;
import br.com.rpires.domain.Produto;

/**
 * @author rodrigo.pires
 *
 */
public class ProdutoController {

    private final IProdutoDAO produtoDAO;

    public ProdutoController() {
        this.produtoDAO = new ProdutoDAOImpl();
    }

    public Integer create(Produto produto) throws Exception {
        return produtoDAO.create(produto);
    }

    public Integer update(Produto produto) throws Exception {
        return produtoDAO.update(produto);
    }

    public Integer delete(Long id) throws Exception {
        if (id == null) {
            return 0;
        }
        return produtoDAO.delete(id.intValue());
    }

    public List<Produto> buscarTodos() throws Exception {
        return produtoDAO.buscarTodos();
    }
}
