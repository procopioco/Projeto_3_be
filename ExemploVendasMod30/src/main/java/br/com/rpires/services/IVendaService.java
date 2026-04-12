package br.com.rpires.services;

import br.com.rpires.domain.Venda;
import br.com.rpires.exceptions.DAOException;
import br.com.rpires.exceptions.TipoChaveNaoEncontradaException;
import br.com.rpires.services.generic.IGenericService;

public interface IVendaService extends IGenericService<Venda, String> {

    void finalizarVenda(String codigo) throws TipoChaveNaoEncontradaException, DAOException;

    void cancelarVenda(String codigo) throws TipoChaveNaoEncontradaException, DAOException;

}
