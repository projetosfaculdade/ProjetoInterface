package br.com.petshop.apresentacao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.petshop.logica.LogicaFornecedor;
import br.com.petshop.utils.Utils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class FornecedorDialog extends JDialog {

	private LogicaFornecedor lf = new LogicaFornecedor();
	private final JPanel contentPanel = new JPanel();
	private JTable tableFornecedor;
	DefaultTableModel modeloFornecedor = new DefaultTableModel(new Object[][] {},
			new Object[] { "ID", "CNPJ", "Razão Social", "Nome Fantasia", "Telefone", "Email"});


	public FornecedorDialog() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(Utils.nomePrograma + "Fornecedor");
		setModal(true);
		setBounds(100, 100, 790, 370);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 647, 309);
		contentPanel.add(scrollPane);

		tableFornecedor = new JTable();
		tableFornecedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableFornecedor.setModel(modeloFornecedor);
		scrollPane.setViewportView(tableFornecedor);

		JButton inserirButtonFornecedor = new JButton("Novo");
		inserirButtonFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(lf.inserir()){
						preencheJTableEstoque();
						JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso.");
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});

		inserirButtonFornecedor.setBounds(667, 27, 100, 23);
		contentPanel.add(inserirButtonFornecedor);

		JButton listarButtonFornecedor = new JButton("Listar");
		listarButtonFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preencheJTableEstoque();
			}
		});
		listarButtonFornecedor.setBounds(667, 73, 100, 23);
		contentPanel.add(listarButtonFornecedor);

		JButton editarButtonFornecedor = new JButton("Editar");
		editarButtonFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableFornecedor.getSelectedRow();
				try {
					lf.editar(Integer.parseInt(tableFornecedor.getValueAt(row, 0).toString()), tableFornecedor.getValueAt(tableFornecedor.getSelectedRow(), 1).toString().replace(".", "").replace("-", "").replace("/", ""));
				} catch (NumberFormatException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		editarButtonFornecedor.setBounds(667, 120, 100, 23);
		contentPanel.add(editarButtonFornecedor);

		JButton excluirButtonFornecedor = new JButton("Excluir");
		excluirButtonFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableFornecedor.getSelectedRow();
				try {
					if(lf.excluir(Integer.parseInt(tableFornecedor.getValueAt(row, 0).toString()))){
						preencheJTableEstoque();
						JOptionPane.showMessageDialog(null, "Cadastro removido com sucesso.");
					}
				} catch (NumberFormatException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		excluirButtonFornecedor.setBounds(667, 169, 100, 23);
		contentPanel.add(excluirButtonFornecedor);

		JButton voltarButtonFuncionario = new JButton("Voltar");
		voltarButtonFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		voltarButtonFuncionario.setBounds(667, 297, 100, 23);
		contentPanel.add(voltarButtonFuncionario);

	}

	private void preencheJTableEstoque() {
		ArrayList<Object> row = lf.JTableFornecedor();
		modeloFornecedor.setNumRows(0);
		for (int i = 0; i < row.size(); i++) {
			modeloFornecedor.addRow((Object[]) row.get(i));
		}
	}

}