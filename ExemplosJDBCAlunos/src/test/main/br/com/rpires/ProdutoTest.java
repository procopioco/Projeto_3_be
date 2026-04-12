/**
 * 
 */
package br.com.rpires;

import java.util.List;

import br.com.rpires.dao.IProdutoDAO;
import br.com.rpires.dao.ProdutoDAOImpl;
import br.com.rpires.domain.Produto;

/**
 * @author rodrigo.pires
 *
 */
public class ProdutoTest {

    public static void main(String[] args) {
        try {
            IProdutoDAO produtoDAO = new ProdutoDAOImpl();

            // Inserir produto
            Produto produto = new Produto();
            produto.setNome("Caderno");
            produto.setPreco(19.90);
            produto.setQuantidade(10);
            Integer qtdInserida = produtoDAO.create(produto);
            System.out.println("Inserido: " + qtdInserida);

            // Listar produtos
            System.out.println("Produtos cadastrados:");
            List<Produto> produtos = produtoDAO.buscarTodos();
            produtos.forEach(p -> System.out.println(formatProduto(p)));

            if (!produtos.isEmpty()) {
                Produto primeiro = produtos.get(0);

                // Atualizar produto
                primeiro.setNome(primeiro.getNome() + " - Atualizado");
                primeiro.setPreco(primeiro.getPreco() + 5.00);
                primeiro.setQuantidade(primeiro.getQuantidade() + 2);
                Integer qtdAtualizada = produtoDAO.update(primeiro);
                System.out.println("Atualizado: " + qtdAtualizada);

                // Listar após atualização
                System.out.println("Produtos após atualização:");
                produtoDAO.buscarTodos().forEach(p -> System.out.println(formatProduto(p)));

                // Excluir produto
                Integer qtdExcluida = produtoDAO.delete(primeiro.getId().intValue());
                System.out.println("Excluído: " + qtdExcluida);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String formatProduto(Produto produto) {
        return String.format("id=%d, nome=%s, preco=%.2f, quantidade=%d",
                produto.getId(), produto.getNome(), produto.getPreco(), produto.getQuantidade());
    }
}
