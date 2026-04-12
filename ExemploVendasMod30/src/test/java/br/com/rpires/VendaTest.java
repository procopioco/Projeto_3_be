package br.com.rpires;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import br.com.rpires.domain.Produto;
import br.com.rpires.domain.Venda;
import br.com.rpires.domain.Venda.Status;

public class VendaTest {

    private Venda venda;
    private Produto produto;

    @Before
    public void init() {
        venda = new Venda();
        venda.setCodigo("TEST01");
        venda.setStatus(Status.INICIADA);
        venda.setDataVenda(Instant.now());

        produto = new Produto();
        produto.setCodigo("P01");
        produto.setNome("Produto Teste");
        produto.setValor(BigDecimal.TEN);
    }

    @Test
    public void adicionarProdutoAtualizaQuantidadeEValor() {
        venda.adicionarProduto(produto, 2);

        assertEquals(2, venda.getQuantidadeTotalProdutos().intValue());
        assertEquals(BigDecimal.valueOf(20), venda.getValorTotal());
    }

    @Test
    public void removerProdutoAtualizaQuantidadeEValor() {
        venda.adicionarProduto(produto, 3);
        venda.removerProduto(produto, 1);

        assertEquals(2, venda.getQuantidadeTotalProdutos().intValue());
        assertEquals(BigDecimal.valueOf(20), venda.getValorTotal());
    }

    @Test
    public void removerTodosProdutosZeraValor() {
        venda.adicionarProduto(produto, 3);
        venda.removerTodosProdutos();

        assertEquals(0, venda.getQuantidadeTotalProdutos().intValue());
        assertEquals(BigDecimal.ZERO, venda.getValorTotal());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void naoPermiteAdicionarProdutoVendaFinalizada() {
        venda.setStatus(Status.CONCLUIDA);
        venda.adicionarProduto(produto, 1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void naoPermiteAdicionarProdutoVendaCancelada() {
        venda.setStatus(Status.CANCELADA);
        venda.adicionarProduto(produto, 1);
    }
}
