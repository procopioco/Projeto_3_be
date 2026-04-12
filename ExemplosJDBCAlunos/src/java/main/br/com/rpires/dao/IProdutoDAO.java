/**
 * 
 */
package br.com.rpires.dao;

import java.util.List;

import br.com.rpires.domain.Produto;

/**
 * @author rodrigo.pires
 *
 */
public interface IProdutoDAO {

    Integer create(Produto produto) throws Exception;

    Integer update(Produto produto) throws Exception;

    Integer delete(int id) throws Exception;

    List<Produto> buscarTodos() throws Exception;
}
