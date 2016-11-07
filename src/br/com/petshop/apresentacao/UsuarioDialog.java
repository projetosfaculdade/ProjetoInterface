package br.com.petshop.apresentacao;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.petshop.logica.LogicaUsuario;
import br.com.petshop.utils.Utils;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

@SuppressWarnings("serial")
public class UsuarioDialog extends JDialog {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modeloUsuario = new DefaultTableModel(new Object[][] {},
			new Object[] { "ID", "Login", "Senha", "Grupo" });
	private LogicaUsuario lu = new LogicaUsuario();

	JCheckBox mostrarSenhaCkbxUsu = new JCheckBox("Mostrar senha");

	public UsuarioDialog() {
		setModal(true);

		setResizable(false);
		setTitle(Utils.nomePrograma + "Usuários");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 454, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton listarButtonUsuario = new JButton("Listar");
		listarButtonUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Object> row = lu.JTableUsuario(mostrarSenhaCkbxUsu.isSelected());
				modeloUsuario.setNumRows(0);
				for (int i = 0; i < row.size(); i++) {
					modeloUsuario.addRow((Object[]) row.get(i));
				}
			}
		});
		listarButtonUsuario.setBounds(10, 11, 150, 23);
		contentPane.add(listarButtonUsuario);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 317, 205);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setModel(modeloUsuario);

		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		scrollPane.setViewportView(table);

		JButton editarButtonUsuario = new JButton("Editar");
		editarButtonUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = table.getSelectedRow();
					if (row >= 0) {
						String login = JOptionPane.showInputDialog(null, "Informe o login: ",
								Utils.nomePrograma + "Login", JOptionPane.PLAIN_MESSAGE);
						char[] senha = JOptionPane.showInputDialog(null, "Informe a senha: ",
								Utils.nomePrograma + "Senha", JOptionPane.PLAIN_MESSAGE).toCharArray();
						int grupo = Integer
								.parseInt(JOptionPane.showInputDialog(null, "Informe o grupo 0 adm, 1 normal: ",
										Utils.nomePrograma + "Grupo", JOptionPane.PLAIN_MESSAGE));

						if (lu.alterar(Integer.parseInt(table.getValueAt(row, 0).toString()), login, senha, grupo))
							JOptionPane.showMessageDialog(null, "Modificado com sucesso.");
						else
							JOptionPane.showMessageDialog(null, "Erro: falha na modificação.",
									Utils.nomePrograma + "Error", JOptionPane.ERROR_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "Selecione algum usuário da lista usuários.",
								Utils.nomePrograma + "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		editarButtonUsuario.setBounds(337, 45, 100, 23);
		contentPane.add(editarButtonUsuario);

		JButton excluirButtonUsuario = new JButton("Excluir");
		excluirButtonUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row >= 0) {
					int id = Integer.parseInt(table.getValueAt(row, 0).toString());
					try {
						if (lu.excluir(id)) {
							JOptionPane.showMessageDialog(null, "Excluído com sucesso.");
						} else
							JOptionPane.showMessageDialog(null, "Erro: falha ao excluir.", Utils.nomePrograma + "Error",
									JOptionPane.ERROR_MESSAGE);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(null, "Selecione algum usuário da lista usuários.",
							Utils.nomePrograma + "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		excluirButtonUsuario.setBounds(337, 79, 100, 23);
		contentPane.add(excluirButtonUsuario);

		JButton cadastrarButtonUsuario = new JButton("Cadastrar");
		cadastrarButtonUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = JOptionPane.showInputDialog(null, "Informe o login: ", Utils.nomePrograma + "Login",
						JOptionPane.PLAIN_MESSAGE);
				if (!login.equals("")) {
					try {
						if (!lu.consultaLogin(login)) {
							char[] senha = JOptionPane.showInputDialog(null, "Informe a senha: ",
									Utils.nomePrograma + "Senha", JOptionPane.PLAIN_MESSAGE).toCharArray();
							if (senha.length > 0) {
								int grupo = -1;
								grupo = Integer
										.parseInt(JOptionPane.showInputDialog(null, "Informe o grupo 0 adm, 1 normal: ",
												Utils.nomePrograma + "Grupo", JOptionPane.PLAIN_MESSAGE));
								if (login != null && senha.length > 0 && grupo != -1) {

									if (lu.inserir(login, senha, grupo))
										JOptionPane.showMessageDialog(null, "Cadastro realizado.",
												Utils.nomePrograma + "Informação", JOptionPane.INFORMATION_MESSAGE);
									else
										JOptionPane.showMessageDialog(null, "Erro: Cadastro não realizado.",
												Utils.nomePrograma + "Error", JOptionPane.ERROR_MESSAGE);
								} else
									JOptionPane.showMessageDialog(null, "Erro: Digite todos os dados.",
											Utils.nomePrograma + "Error", JOptionPane.ERROR_MESSAGE);
							} else
								JOptionPane.showMessageDialog(null, "Erro: Informe uma senha válida.",
										Utils.nomePrograma + "Error", JOptionPane.ERROR_MESSAGE);
						} else
							JOptionPane.showMessageDialog(null, "Erro: Login já cadastrado.",
									Utils.nomePrograma + "Error", JOptionPane.ERROR_MESSAGE);
					} catch (HeadlessException | NumberFormatException | ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(null, "Erro: Digite todos os dados.", Utils.nomePrograma + "Error",
							JOptionPane.ERROR_MESSAGE);
			}
		});
		cadastrarButtonUsuario.setBounds(177, 11, 150, 23);
		contentPane.add(cadastrarButtonUsuario);

		JButton voltarButtonUsu = new JButton("Voltar");
		voltarButtonUsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		voltarButtonUsu.setBounds(337, 227, 100, 23);
		contentPane.add(voltarButtonUsu);

		mostrarSenhaCkbxUsu.setBounds(10, 255, 172, 23);
		contentPane.add(mostrarSenhaCkbxUsu);
		mostrarSenhaCkbxUsu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getRowCount() > 0) {
					ArrayList<Object> row = lu.JTableUsuario(mostrarSenhaCkbxUsu.isSelected());
					modeloUsuario.setNumRows(0);
					for (int i = 0; i < row.size(); i++) {
						modeloUsuario.addRow((Object[]) row.get(i));
					}
				}
			}
		});
	}
}