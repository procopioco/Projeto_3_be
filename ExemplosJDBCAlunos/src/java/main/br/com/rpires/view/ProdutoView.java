/**
 * 
 */
package br.com.rpires.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import br.com.rpires.controller.ProdutoController;
import br.com.rpires.domain.Produto;

/**
 * @author rodrigo.pires
 *
 */
public class ProdutoView extends JFrame {

    private static final long serialVersionUID = 1L;

    private final ProdutoController controller;
    private final JTextField txtId;
    private final JTextField txtNome;
    private final JTextField txtPreco;
    private final JTextField txtQuantidade;
    private final JTextArea txtResultado;

    public ProdutoView() {
        super("Cadastro de Produtos");
        this.controller = new ProdutoController();

        txtId = new JTextField(4);
        txtNome = new JTextField(20);
        txtPreco = new JTextField(10);
        txtQuantidade = new JTextField(6);
        txtResultado = new JTextArea(12, 50);
        txtResultado.setEditable(false);

        setLayout(new BorderLayout());
        add(createFormPanel(), BorderLayout.NORTH);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        listarProdutos();
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("ID:"));
        panel.add(txtId);
        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);
        panel.add(new JLabel("Preço:"));
        panel.add(txtPreco);
        panel.add(new JLabel("Quantidade:"));
        panel.add(txtQuantidade);
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarProduto());

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> atualizarProduto());

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> excluirProduto());

        JButton btnListar = new JButton("Listar");
        btnListar.addActionListener(e -> listarProdutos());

        panel.add(btnSalvar);
        panel.add(btnAtualizar);
        panel.add(btnExcluir);
        panel.add(btnListar);
        return panel;
    }

    private void salvarProduto() {
        try {
            Produto produto = new Produto();
            produto.setNome(txtNome.getText().trim());
            produto.setPreco(Double.parseDouble(txtPreco.getText().trim()));
            produto.setQuantidade(Integer.parseInt(txtQuantidade.getText().trim()));
            Integer resultado = controller.create(produto);
            JOptionPane.showMessageDialog(this, "Inseridos: " + resultado);
            limparCampos();
            listarProdutos();
        } catch (Exception e) {
            mostrarErro(e);
        }
    }

    private void atualizarProduto() {
        try {
            Long id = Long.parseLong(txtId.getText().trim());
            Produto produto = new Produto();
            produto.setId(id);
            produto.setNome(txtNome.getText().trim());
            produto.setPreco(Double.parseDouble(txtPreco.getText().trim()));
            produto.setQuantidade(Integer.parseInt(txtQuantidade.getText().trim()));
            Integer resultado = controller.update(produto);
            JOptionPane.showMessageDialog(this, "Atualizados: " + resultado);
            limparCampos();
            listarProdutos();
        } catch (Exception e) {
            mostrarErro(e);
        }
    }

    private void excluirProduto() {
        try {
            Long id = Long.parseLong(txtId.getText().trim());
            Integer resultado = controller.delete(id);
            JOptionPane.showMessageDialog(this, "Excluídos: " + resultado);
            limparCampos();
            listarProdutos();
        } catch (Exception e) {
            mostrarErro(e);
        }
    }

    private void listarProdutos() {
        try {
            List<Produto> produtos = controller.buscarTodos();
            StringBuilder builder = new StringBuilder();
            builder.append("ID | Nome | Preço | Quantidade\n");
            builder.append("-----------------------------------------------\n");
            for (Produto produto : produtos) {
                builder.append(String.format("%d | %s | %.2f | %d\n",
                        produto.getId(), produto.getNome(), produto.getPreco(), produto.getQuantidade()));
            }
            txtResultado.setText(builder.toString());
        } catch (Exception e) {
            mostrarErro(e);
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtPreco.setText("");
        txtQuantidade.setText("");
    }

    private void mostrarErro(Exception e) {
        JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProdutoView().setVisible(true));
    }
}
