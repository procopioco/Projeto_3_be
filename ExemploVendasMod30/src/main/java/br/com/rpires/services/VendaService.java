package br.com.rpires.services;

import br.com.rpires.dao.IVendaDAO;
import br.com.rpires.domain.Venda;
import br.com.rpires.exceptions.DAOException;
import br.com.rpires.exceptions.TipoChaveNaoEncontradaException;
import br.com.rpires.services.generic.GenericService;

public class VendaService extends GenericService<Venda, String> implements IVendaService {

    public VendaService(IVendaDAO vendaDAO) {
        super(vendaDAO);
    }

    @Override
    public void finalizarVenda(String codigo) throws TipoChaveNaoEncontradaException, DAOException {
        Venda venda = consultar(codigo);
        if (venda == null) {
            throw new DAOException("VENDA NÃO ENCONTRADA", new Exception("Venda não encontrada"));
        }
        ((IVendaDAO) this.dao).finalizarVenda(venda);
    }

    @Override
    public void cancelarVenda(String codigo) throws TipoChaveNaoEncontradaException, DAOException {
        Venda venda = consultar(codigo);
        if (venda == null) {
            throw new DAOException("VENDA NÃO ENCONTRADA", new Exception("Venda não encontrada"));
        }
        ((IVendaDAO) this.dao).cancelarVenda(venda);
    }

}
