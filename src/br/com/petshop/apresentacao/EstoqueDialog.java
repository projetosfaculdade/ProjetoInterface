package br.com.petshop.apresentacao;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.petshop.entidades.Produto;
import br.com.petshop.logica.LogicaEstoque;
import br.com.petshop.utils.Utils;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class EstoqueDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableEstoque;
	private LogicaEstoque le = new LogicaEstoque();
	DefaultTableModel modeloEstoque = new DefaultTableModel(new Object[][] {},
			new Object[] { "ID", "Código de Barras", "Nome", "Preço", "Categoria", "Quantidade" });
	JRadioButton normalRadioEstoque;
	JRadioButton categoriaRadioEstoque;
	JComboBox<String> categoriaComboBoxEstoque;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public EstoqueDialog(boolean adm) {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(Utils.nomePrograma + "Estoque");
		setBounds(100, 100, 905, 370);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 728, 283);
		contentPanel.add(scrollPane);

		tableEstoque = new JTable();
		tableEstoque.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEstoque.setModel(modeloEstoque);
		scrollPane.setViewportView(tableEstoque);

		JButton entradaButtonEstoque = new JButton("Entrada");
		entradaButtonEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableEstoque.getSelectedRow();
				if (row >= 0) {
					int id = Integer.parseInt(tableEstoque.getValueAt(row, 0).toString());
					int qtd = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade: ",
							Utils.nomePrograma + "Quantidade", JOptionPane.PLAIN_MESSAGE));
					if (le.entrada(row, id, qtd)) {
						JOptionPane.showMessageDialog(null, "Entrada realizada.");
					} else
						JOptionPane.showMessageDialog(null, "Erro: entrada não realizada.");
				} else
					JOptionPane.showMessageDialog(null, "Selecione algum produto da lista estoque.");
			}
		});
		entradaButtonEstoque.setBounds(758, 167, 100, 23);
		contentPanel.add(entradaButtonEstoque);

		JButton cencelarButtonEstoque = new JButton("Cancelar");
		cencelarButtonEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cencelarButtonEstoque.setBounds(748, 271, 121, 23);
		contentPanel.add(cencelarButtonEstoque);

		JButton alterarQtdButtonEstoque = new JButton("Alterar Quantidade");
		alterarQtdButtonEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableEstoque.getSelectedRow();
				if (row >= 0) {
					int id = Integer.parseInt(tableEstoque.getValueAt(row, 0).toString());
					int qtd = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade: ",
							Utils.nomePrograma + "Quantidade", JOptionPane.PLAIN_MESSAGE));
					if (le.alteraQuantidade(qtd, id))
						JOptionPane.showMessageDialog(null, "Quantidade alterada com sucesso.");
					else
						JOptionPane.showMessageDialog(null, "Erro: não foi possível alterar quantidade.");
				} else
					JOptionPane.showMessageDialog(null, "Selecione algum produto da lista estoque.");
			}
		});
		if (adm)
			alterarQtdButtonEstoque.setVisible(true);
		else
			alterarQtdButtonEstoque.setVisible(false);
		alterarQtdButtonEstoque.setBounds(10, 297, 150, 23);
		contentPanel.add(alterarQtdButtonEstoque);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listagem", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(748, 11, 131, 145);
		contentPanel.add(panel);
		panel.setLayout(null);

		JButton listarButtonEstoque = new JButton("Listar");
		listarButtonEstoque.setBounds(10, 108, 100, 23);
		panel.add(listarButtonEstoque);

		categoriaComboBoxEstoque = new JComboBox<String>();
		categoriaComboBoxEstoque.setEnabled(false);
		categoriaComboBoxEstoque.setBounds(10, 76, 100, 20);
		panel.add(categoriaComboBoxEstoque);
		Produto prod = new Produto();
		categoriaComboBoxEstoque.setModel(new DefaultComboBoxModel<String>(prod.getListaCategorias()));
		listarButtonEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloEstoque.setNumRows(0);
				ArrayList<Object> row = le.JTableEstoque(normalRadioEstoque.isSelected(), categoriaRadioEstoque.isSelected(), categoriaComboBoxEstoque.getSelectedIndex());
				for (int i = 0; i < row.size(); i++) {
					modeloEstoque.addRow((Object[]) row.get(i));
				}
			}
		});

		normalRadioEstoque = new JRadioButton("Normal");
		buttonGroup.add(normalRadioEstoque);
		normalRadioEstoque.setSelected(true);
		normalRadioEstoque.setBounds(10, 20, 109, 23);
		panel.add(normalRadioEstoque);
		normalRadioEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoriaComboBoxEstoque.setEnabled(false);
			}
		});

		categoriaRadioEstoque = new JRadioButton("Categoria");
		buttonGroup.add(categoriaRadioEstoque);
		categoriaRadioEstoque.setBounds(10, 46, 94, 23);
		panel.add(categoriaRadioEstoque);
		categoriaRadioEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoriaComboBoxEstoque.setEnabled(true);
			}
		});
	}
}