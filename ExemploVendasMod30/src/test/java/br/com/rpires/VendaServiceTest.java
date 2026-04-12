package br.com.rpires;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import br.com.rpires.dao.VendaDaoMock;
import br.com.rpires.domain.Venda;
import br.com.rpires.domain.Venda.Status;
import br.com.rpires.exceptions.DAOException;
import br.com.rpires.exceptions.TipoChaveNaoEncontradaException;
import br.com.rpires.services.IVendaService;
import br.com.rpires.services.VendaService;

public class VendaServiceTest {

    private IVendaService vendaService;

    @Before
    public void init() {
        vendaService = new VendaService(new VendaDaoMock());
    }

    @Test
    public void salvarVenda() throws TipoChaveNaoEncontradaException, DAOException {
        Venda venda = criarVenda("V001");
        Boolean retorno = vendaService.cadastrar(venda);

        assertTrue(retorno);
        assertNotNull(vendaService.consultar("V001"));
        assertEquals("V001", venda.getCodigo());
    }

    @Test
    public void finalizarVenda() throws TipoChaveNaoEncontradaException, DAOException {
        Venda venda = criarVenda("V002");
        vendaService.cadastrar(venda);

        vendaService.finalizarVenda("V002");

        Venda vendaConsultada = vendaService.consultar("V002");
        assertNotNull(vendaConsultada);
        assertEquals(Status.CONCLUIDA, vendaConsultada.getStatus());
    }

    @Test
    public void cancelarVenda() throws TipoChaveNaoEncontradaException, DAOException {
        Venda venda = criarVenda("V003");
        vendaService.cadastrar(venda);

        vendaService.cancelarVenda("V003");

        Venda vendaConsultada = vendaService.consultar("V003");
        assertNotNull(vendaConsultada);
        assertEquals(Status.CANCELADA, vendaConsultada.getStatus());
    }

    @Test
    public void buscarTodasVendas() throws TipoChaveNaoEncontradaException, DAOException {
        Venda primeiraVenda = criarVenda("V004");
        Venda segundaVenda = criarVenda("V005");
        vendaService.cadastrar(primeiraVenda);
        vendaService.cadastrar(segundaVenda);

        assertEquals(2, vendaService.buscarTodos().size());
    }

    private Venda criarVenda(String codigo) {
        Venda venda = new Venda();
        venda.setCodigo(codigo);
        venda.setStatus(Status.INICIADA);
        venda.setDataVenda(Instant.now());
        return venda;
    }
}
