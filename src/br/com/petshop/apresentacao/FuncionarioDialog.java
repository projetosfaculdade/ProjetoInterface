package br.com.petshop.apresentacao;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.petshop.logica.LogicaFuncionario;
import br.com.petshop.utils.Utils;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FuncionarioDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableFuncionario;
	private LogicaFuncionario lf = new LogicaFuncionario();
	DefaultTableModel modeloFuncionario = new DefaultTableModel(new Object[][] {},
			new Object[] { "ID", "CPF", "Nome", "Sexo", "Cargo", "Salário", "Telefone", "E-mail" });

	public FuncionarioDialog() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(Utils.nomePrograma + "Funcionário");
		setModal(true);
		setBounds(100, 100, 790, 370);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 647, 309);
		contentPanel.add(scrollPane);

		tableFuncionario = new JTable();
		tableFuncionario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableFuncionario.setModel(modeloFuncionario);
		scrollPane.setViewportView(tableFuncionario);

		JButton novoButtonFuncionario = new JButton("Novo\r\n");
		novoButtonFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lf.inserir();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}

			}
		});
		novoButtonFuncionario.setBounds(667, 11, 100, 23);
		contentPanel.add(novoButtonFuncionario);

		JButton listarButtonFuncionario = new JButton("Listar");
		listarButtonFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preencheJTableEstoque();
			}
		});
		listarButtonFuncionario.setBounds(667, 45, 100, 23);
		contentPanel.add(listarButtonFuncionario);

		JButton editarButtonFuncionario = new JButton("Editar");
		editarButtonFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableFuncionario.getSelectedRow();
				try {
					lf.editar(Integer.parseInt(tableFuncionario.getValueAt(row, 0).toString()), tableFuncionario.getValueAt(tableFuncionario.getSelectedRow(), 1).toString().replace(".", "").replace("-", ""));
				} catch (NumberFormatException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		editarButtonFuncionario.setBounds(667, 79, 100, 23);
		contentPanel.add(editarButtonFuncionario);

		JButton excluirButtonFuncionario = new JButton("Excluir");
		excluirButtonFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableFuncionario.getSelectedRow();
				try {
					if(lf.excluir(Integer.parseInt(tableFuncionario.getValueAt(row, 0).toString()))){
						preencheJTableEstoque();
						JOptionPane.showMessageDialog(null, "Cadastro removido com sucesso.");
					}
				} catch (NumberFormatException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		excluirButtonFuncionario.setBounds(667, 113, 100, 23);
		contentPanel.add(excluirButtonFuncionario);

		JButton cancelarButtonFuncionario = new JButton("Cancelar");
		cancelarButtonFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelarButtonFuncionario.setBounds(667, 297, 100, 23);
		contentPanel.add(cancelarButtonFuncionario);
	}
	private void preencheJTableEstoque() {
		ArrayList<Object> row = lf.JTableFuncionario();
		modeloFuncionario.setNumRows(0);
		for (int i = 0; i < row.size(); i++) {
			modeloFuncionario.addRow((Object[]) row.get(i));
		}
	}
}