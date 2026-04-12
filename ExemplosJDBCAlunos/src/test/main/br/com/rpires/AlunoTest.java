/**
 * 
 */
package br.com.rpires;

import java.util.List;

import br.com.rpires.dao.AlunoDAO;
import br.com.rpires.dao.IAlunoDAO;
import br.com.rpires.domain.Aluno;

/**
 * @author rodrigo.pires
 *
 */
public class AlunoTest {

    public static void main(String[] args) {
        try {
            IAlunoDAO alunoDAO = new AlunoDAO();

            Aluno aluno = new Aluno();
            aluno.setNome("João Silva");
            aluno.setIdade(22);
            aluno.setEmail("joao.silva@example.com");
            Integer qtdInserida = alunoDAO.create(aluno);
            System.out.println("Aluno inserido: " + qtdInserida);

            List<Aluno> alunos = alunoDAO.buscarTodos();
            alunos.forEach(a -> System.out.println(formatAluno(a)));

            if (!alunos.isEmpty()) {
                Aluno primeiro = alunos.get(0);
                primeiro.setNome("João Silva Atualizado");
                primeiro.setIdade(23);
                primeiro.setEmail("joao.silva.atualizado@example.com");
                Integer qtdAtualizada = alunoDAO.update(primeiro);
                System.out.println("Aluno atualizado: " + qtdAtualizada);

                System.out.println("Alunos após atualização:");
                alunoDAO.buscarTodos().forEach(a -> System.out.println(formatAluno(a)));

                Integer qtdExcluida = alunoDAO.delete(primeiro.getId());
                System.out.println("Aluno excluído: " + qtdExcluida);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String formatAluno(Aluno aluno) {
        return String.format("id=%d, nome=%s, idade=%d, email=%s",
                aluno.getId(), aluno.getNome(), aluno.getIdade(), aluno.getEmail());
    }
}
