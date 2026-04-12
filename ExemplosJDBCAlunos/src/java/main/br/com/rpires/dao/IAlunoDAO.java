/**
 * 
 */
package br.com.rpires.dao;

import java.util.List;

import br.com.rpires.domain.Aluno;

/**
 * @author rodrigo.pires
 *
 */
public interface IAlunoDAO {

    Integer create(Aluno aluno) throws Exception;

    Aluno buscarPorId(Long id) throws Exception;

    List<Aluno> buscarTodos() throws Exception;

    Integer update(Aluno aluno) throws Exception;

    Integer delete(Long id) throws Exception;
}
