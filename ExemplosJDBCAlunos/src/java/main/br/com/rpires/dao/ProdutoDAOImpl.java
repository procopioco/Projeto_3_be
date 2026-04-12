/**
 * 
 */
package br.com.rpires.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.rpires.dao.jdbc.ConnectionFactory;
import br.com.rpires.domain.Produto;

/**
 * @author rodrigo.pires
 *
 */
public class ProdutoDAOImpl implements IProdutoDAO {

    @Override
    public Integer create(Produto produto) throws Exception {
        String sql = "INSERT INTO tb_produto (nome, preco, quantidade) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, produto.getNome());
            stm.setDouble(2, produto.getPreco());
            stm.setInt(3, produto.getQuantidade());
            return stm.executeUpdate();
        }
    }

    @Override
    public Integer update(Produto produto) throws Exception {
        String sql = "UPDATE tb_produto SET nome = ?, preco = ?, quantidade = ? WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, produto.getNome());
            stm.setDouble(2, produto.getPreco());
            stm.setInt(3, produto.getQuantidade());
            stm.setLong(4, produto.getId());
            return stm.executeUpdate();
        }
    }

    @Override
    public Integer delete(int id) throws Exception {
        String sql = "DELETE FROM tb_produto WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            return stm.executeUpdate();
        }
    }

    @Override
    public List<Produto> buscarTodos() throws Exception {
        String sql = "SELECT id, nome, preco, quantidade FROM tb_produto";
        List<Produto> produtos = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getLong("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produtos.add(produto);
            }
        }
        return produtos;
    }
}
