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
import br.com.rpires.domain.Aluno;

/**
 * @author rodrigo.pires
 *
 */
public class AlunoDAO implements IAlunoDAO {

    @Override
    public Integer create(Aluno aluno) throws Exception {
        String sql = "INSERT INTO tb_aluno (nome, idade, email) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, aluno.getNome());
            stm.setInt(2, aluno.getIdade());
            stm.setString(3, aluno.getEmail());
            return stm.executeUpdate();
        }
    }

    @Override
    public Aluno buscarPorId(Long id) throws Exception {
        String sql = "SELECT id, nome, idade, email FROM tb_aluno WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setLong(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Aluno aluno = new Aluno();
                    aluno.setId(rs.getLong("id"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setIdade(rs.getInt("idade"));
                    aluno.setEmail(rs.getString("email"));
                    return aluno;
                }
                return null;
            }
        }
    }

    @Override
    public List<Aluno> buscarTodos() throws Exception {
        String sql = "SELECT id, nome, idade, email FROM tb_aluno";
        List<Aluno> alunos = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getLong("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setEmail(rs.getString("email"));
                alunos.add(aluno);
            }
        }
        return alunos;
    }

    @Override
    public Integer update(Aluno aluno) throws Exception {
        String sql = "UPDATE tb_aluno SET nome = ?, idade = ?, email = ? WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, aluno.getNome());
            stm.setInt(2, aluno.getIdade());
            stm.setString(3, aluno.getEmail());
            stm.setLong(4, aluno.getId());
            return stm.executeUpdate();
        }
    }

    @Override
    public Integer delete(Long id) throws Exception {
        String sql = "DELETE FROM tb_aluno WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setLong(1, id);
            return stm.executeUpdate();
        }
    }
}
