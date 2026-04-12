package br.com.rpires.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.rpires.domain.Venda;
import br.com.rpires.exceptions.TipoChaveNaoEncontradaException;

public class VendaDaoMock implements IVendaDAO {

    private final Map<String, Venda> vendas = new HashMap<>();

    @Override
    public Boolean cadastrar(Venda entity) throws TipoChaveNaoEncontradaException {
        if (entity.getId() == null) {
            entity.setId((long) (vendas.size() + 1));
        }
        vendas.put(entity.getCodigo(), entity);
        return true;
    }

    @Override
    public void excluir(String valor) {
        vendas.remove(valor);
    }

    @Override
    public void alterar(Venda entity) throws TipoChaveNaoEncontradaException {
        vendas.put(entity.getCodigo(), entity);
    }

    @Override
    public Venda consultar(String valor) {
        return vendas.get(valor);
    }

    @Override
    public Collection<Venda> buscarTodos() {
        return new ArrayList<>(vendas.values());
    }

    @Override
    public void finalizarVenda(Venda venda) {
        venda.setStatus(Venda.Status.CONCLUIDA);
        vendas.put(venda.getCodigo(), venda);
    }

    @Override
    public void cancelarVenda(Venda venda) {
        venda.setStatus(Venda.Status.CANCELADA);
        vendas.put(venda.getCodigo(), venda);
    }

}
