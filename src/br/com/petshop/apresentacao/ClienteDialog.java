package br.com.petshop.apresentacao;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.petshop.logica.LogicaAnimal;
import br.com.petshop.logica.LogicaCliente;
import br.com.petshop.utils.Utils;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class ClienteDialog extends JDialog {
	private JTable tableCliente;
	private JTable tableAnimal;
	private LogicaAnimal la = new LogicaAnimal();
	private LogicaCliente lc = new LogicaCliente();
	DefaultTableModel modeloCliente = new DefaultTableModel(new Object[][] {},
			new Object[] { "ID", "Nome"/*, "Endere\u00E7o"*/, "Telefone", "Sexo", "CPF", "E-mail" });
	DefaultTableModel modeloAnimais = new DefaultTableModel(new Object[][] {},
			new String[] { "ID", "Nome", "Ra\u00E7a", "Esp\u00E9cie", "Sexo", "Idade" });

	public ClienteDialog() {
		setResizable(false);
		setModal(true);
		setTitle(Utils.nomePrograma + "Cliente");
		setBounds(100, 100, 900, 475);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 894, 443);
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Clientes ",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 874, 214);
		panel.add(panel_1);

		JButton listarButtonCliente = new JButton("Listar");
		listarButtonCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				preencheJTableCliente();
			}
		});
		listarButtonCliente.setBounds(10, 20, 130, 23);
		panel_1.add(listarButtonCliente);

		JButton cadastrarButtonCliente = new JButton("Cadastrar");
		cadastrarButtonCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteCadastroDialog ccd = new ClienteCadastroDialog();
				ccd.setVisible(true);

			}
		});
		cadastrarButtonCliente.setBounds(150, 20, 130, 23);
		panel_1.add(cadastrarButtonCliente);

		JButton editarButtonCliente = new JButton("Editar");
		editarButtonCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = tableCliente.getSelectedRow();
					if (row >= 0) {
						if (lc.alterar(row))
							JOptionPane.showMessageDialog(null, "Alteração realizada.");
						else
							JOptionPane.showMessageDialog(null, "Erro: alteração não realizada.");
					} else
						JOptionPane.showMessageDialog(null, "Selecione algum usuário da lista clientes.");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		editarButtonCliente.setBounds(290, 20, 130, 23);
		panel_1.add(editarButtonCliente);

		JButton excluirButtonCliente = new JButton("Excluir");
		excluirButtonCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableCliente.getSelectedRow();
				if (row >= 0) {
					int id = Integer.parseInt(tableCliente.getValueAt(row, 0).toString());
					try {

						if (lc.exclui(id)) {
							JOptionPane.showMessageDialog(null, "Excluído com sucesso.");
							lc.organizaIndex();
							preencheJTableCliente();
						} else
							JOptionPane.showMessageDialog(null, "Erro: falha ao excluir.");
						;
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(null, "Selecione algum usuário da lista clientes.");
			}
		});
		excluirButtonCliente.setBounds(431, 20, 130, 23);
		panel_1.add(excluirButtonCliente);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setBounds(10, 54, 854, 149);
		panel_1.add(scrollPane);

		tableCliente = new JTable();
		tableCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCliente.setModel(modeloCliente);
		tableCliente.getColumnModel().getColumn(0).setMaxWidth(35);
		tableCliente.getColumnModel().getColumn(0).setMinWidth(70);

		tableCliente.getColumnModel().getColumn(1).setMaxWidth(130);
		tableCliente.getColumnModel().getColumn(1).setMinWidth(130);

		tableCliente.getColumnModel().getColumn(3).setMaxWidth(40);
		tableCliente.getColumnModel().getColumn(3).setMinWidth(40);

		scrollPane.setViewportView(tableCliente);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Animais ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 226, 874, 206);
		panel.add(panel_2);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 20, 714, 175);
		panel_2.add(scrollPane_1);

		tableAnimal = new JTable();
		tableAnimal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableAnimal.setModel(modeloAnimais);
		scrollPane_1.setViewportView(tableAnimal);

		JButton listarButtonAnimal = new JButton("Listar");
		listarButtonAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableCliente.getSelectedRow();
				if (row >= 0) {
					int id = Integer.parseInt(tableCliente.getValueAt(row, 0).toString());
					try {
						preencheJTableAnimal(id);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}

				} else
					JOptionPane.showMessageDialog(null, "Selecione algum usuário da lista clientes.");
			}
		});
		listarButtonAnimal.setBounds(734, 20, 130, 23);
		panel_2.add(listarButtonAnimal);

		JButton editarButtonAnimal = new JButton("Editar");
		editarButtonAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = tableAnimal.getSelectedRow();
					if (row >= 0) {
						if (la.alterar(Integer.parseInt(tableAnimal.getValueAt(row, 0).toString())))
							JOptionPane.showMessageDialog(null, "Alteração realizada.");
						else
							JOptionPane.showMessageDialog(null, "Erro: alteração não realizada.");
					} else
						JOptionPane.showMessageDialog(null, "Selecione algum animal da lista animais.");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		editarButtonAnimal.setBounds(734, 91, 130, 23);
		panel_2.add(editarButtonAnimal);

		JButton voltarButtonCliente = new JButton("Voltar");
		voltarButtonCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		voltarButtonCliente.setBounds(734, 172, 130, 23);
		panel_2.add(voltarButtonCliente);

		JButton cadastrarButtonAnimal = new JButton("Cadastrar");
		cadastrarButtonAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = tableCliente.getSelectedRow();
				if (row >= 0) {
					try {
						int id = Integer.parseInt(tableCliente.getValueAt(row, 0).toString());
						if (la.inserir(id))
							JOptionPane.showMessageDialog(null, "Inserido com sucesso.");
						else
							JOptionPane.showMessageDialog(null, "Erro: inserção não realizada.");
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(null, "Selecione algum usuário da lista clientes.");

			}
		});
		cadastrarButtonAnimal.setBounds(734, 57, 130, 23);
		panel_2.add(cadastrarButtonAnimal);
	}

	private void preencheJTableCliente() {
		modeloCliente.setNumRows(0);
		ArrayList<Object> row = lc.JTableCliente();
		for (int i = 0; i < row.size(); i++) {
			modeloCliente.addRow((Object[]) row.get(i));
		}
	}

	private void preencheJTableAnimal(int id) throws ClassNotFoundException {
		modeloAnimais.setNumRows(0);
		ArrayList<Object> row = la.JtableAnimal(id);
		for (int i = 0; i < row.size(); i++) {
			modeloAnimais.addRow((Object[]) row.get(i));
		}
	}
}
