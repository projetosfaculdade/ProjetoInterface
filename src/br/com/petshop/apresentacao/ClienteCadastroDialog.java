package br.com.petshop.apresentacao;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.petshop.dados.DadosCliente;
import br.com.petshop.entidades.Cliente;
import br.com.petshop.entidades.Endereco;
import br.com.petshop.logica.LogicaCliente;
import br.com.petshop.utils.BuscarEndereco;
import br.com.petshop.utils.Utils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ClienteCadastroDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nomeTextFieldCadastro;
	private JTextField cpfTextFieldCadastro;
	private JTextField telefoneTextFieldCadastro;
	private JTextField emailTextFieldCadastro;
	private JTextField cepTextFieldEndereco;
	private JTextField municipioTextFieldEndereco;
	private JTextField bairroTextFieldEndereco;
	private JTextField logradouroTextFieldEndereco;
	private JTextField numeroTextFieldEndereco;
	private JTextField complementoTextFieldEndereco;
	private LogicaCliente lc = new LogicaCliente();
	private DadosCliente dc = new DadosCliente();

	public static void main(String[] args) {
		try {
			ClienteCadastroDialog dialog = new ClienteCadastroDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ClienteCadastroDialog() {
		setTitle(Utils.nomePrograma + "Cadastro de Clientes");
		setType(Type.POPUP);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 708, 260);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel dadosPessoaisPainel = new JPanel();
		dadosPessoaisPainel.setBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "Dados Pessoais",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		dadosPessoaisPainel.setBounds(10, 11, 220, 175);
		contentPanel.add(dadosPessoaisPainel);
		dadosPessoaisPainel.setLayout(null);

		nomeTextFieldCadastro = new JTextField();
		nomeTextFieldCadastro.setBounds(76, 21, 125, 20);
		dadosPessoaisPainel.add(nomeTextFieldCadastro);
		nomeTextFieldCadastro.setColumns(10);

		cpfTextFieldCadastro = new JTextField();
		cpfTextFieldCadastro.setBounds(76, 83, 125, 20);
		dadosPessoaisPainel.add(cpfTextFieldCadastro);
		cpfTextFieldCadastro.setColumns(10);

		telefoneTextFieldCadastro = new JTextField();
		telefoneTextFieldCadastro.setBounds(76, 114, 125, 20);
		dadosPessoaisPainel.add(telefoneTextFieldCadastro);
		telefoneTextFieldCadastro.setColumns(10);

		emailTextFieldCadastro = new JTextField();
		emailTextFieldCadastro.setBounds(76, 145, 125, 20);
		dadosPessoaisPainel.add(emailTextFieldCadastro);
		emailTextFieldCadastro.setColumns(10);

		JLabel nomeTextPessoaisCadastro = new JLabel("Nome: ");
		nomeTextPessoaisCadastro.setBounds(10, 24, 46, 14);
		dadosPessoaisPainel.add(nomeTextPessoaisCadastro);

		JLabel sexoTextPessoaisCadastro = new JLabel("Sexo:");
		sexoTextPessoaisCadastro.setBounds(10, 55, 46, 14);
		dadosPessoaisPainel.add(sexoTextPessoaisCadastro);

		JLabel cpfTextPessoaisCadastro = new JLabel("CPF:");
		cpfTextPessoaisCadastro.setBounds(10, 86, 46, 14);
		dadosPessoaisPainel.add(cpfTextPessoaisCadastro);

		JLabel telefoneTextPessoaisCadastro = new JLabel("Telefone:");
		telefoneTextPessoaisCadastro.setBounds(10, 117, 56, 14);
		dadosPessoaisPainel.add(telefoneTextPessoaisCadastro);

		JLabel emailTextPessoaisCadastro = new JLabel("E-mail");
		emailTextPessoaisCadastro.setBounds(10, 148, 46, 14);
		dadosPessoaisPainel.add(emailTextPessoaisCadastro);

		JComboBox<String> sexoComboBoxCadastro = new JComboBox<String>();
		sexoComboBoxCadastro.setMaximumRowCount(2);
		sexoComboBoxCadastro.setModel(new DefaultComboBoxModel<String>(new String[] { "Masculino", "Feminino" }));
		sexoComboBoxCadastro.setSelectedIndex(0);
		sexoComboBoxCadastro.setBounds(76, 52, 125, 20);
		dadosPessoaisPainel.add(sexoComboBoxCadastro);

		JPanel enderecoPainel = new JPanel();
		enderecoPainel.setBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "Endere\u00E7o",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		enderecoPainel.setBounds(240, 11, 452, 175);
		contentPanel.add(enderecoPainel);
		enderecoPainel.setLayout(null);


		JLabel cepTextEnderecoCadastro = new JLabel("CEP:");
		cepTextEnderecoCadastro.setBounds(10, 24, 46, 14);
		enderecoPainel.add(cepTextEnderecoCadastro);

		cepTextFieldEndereco = new JTextField();
		cepTextFieldEndereco.setColumns(10);
		cepTextFieldEndereco.setBounds(74, 21, 120, 20);
		enderecoPainel.add(cepTextFieldEndereco);

		JLabel estadoTextEnderecoCadastro = new JLabel("Estado:");
		estadoTextEnderecoCadastro.setBounds(10, 52, 46, 14);
		enderecoPainel.add(estadoTextEnderecoCadastro);

		JComboBox<String> estadoComboBoxEndereco = new JComboBox<String>();
		Endereco endereco = new Endereco();
		estadoComboBoxEndereco.setModel(new DefaultComboBoxModel<String>(endereco.getListaEstados()));
		estadoComboBoxEndereco.setSelectedIndex(0);
		estadoComboBoxEndereco.setMaximumRowCount(6);
		estadoComboBoxEndereco.setBounds(74, 49, 117, 20);
		enderecoPainel.add(estadoComboBoxEndereco);

		JLabel municipioTextEnderecoCadastro = new JLabel("Munic\u00EDcio:");
		municipioTextEnderecoCadastro.setBounds(225, 52, 65, 14);
		enderecoPainel.add(municipioTextEnderecoCadastro);

		municipioTextFieldEndereco = new JTextField();
		municipioTextFieldEndereco.setColumns(10);
		municipioTextFieldEndereco.setBounds(322, 49, 120, 20);
		enderecoPainel.add(municipioTextFieldEndereco);

		JLabel bairroTextEnderecoCadastro = new JLabel("Bairro:");
		bairroTextEnderecoCadastro.setBounds(10, 80, 46, 14);
		enderecoPainel.add(bairroTextEnderecoCadastro);

		bairroTextFieldEndereco = new JTextField();
		bairroTextFieldEndereco.setColumns(10);
		bairroTextFieldEndereco.setBounds(74, 77, 120, 20);
		enderecoPainel.add(bairroTextFieldEndereco);

		JLabel logradouroTextEnderecoCadastro = new JLabel("Logradouro:");
		logradouroTextEnderecoCadastro.setBounds(225, 80, 76, 14);
		enderecoPainel.add(logradouroTextEnderecoCadastro);

		logradouroTextFieldEndereco = new JTextField();
		logradouroTextFieldEndereco.setColumns(10);
		logradouroTextFieldEndereco.setBounds(322, 77, 120, 20);
		enderecoPainel.add(logradouroTextFieldEndereco);

		JButton btnNewButton = new JButton("Buscar CEP");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Utils.validaCep(cepTextFieldEndereco.getText().replace("-", ""))){
					try {
						bairroTextFieldEndereco.setText(BuscarEndereco.getBairro(cepTextFieldEndereco.getText()));
						municipioTextFieldEndereco.setText(BuscarEndereco.getCidade(cepTextFieldEndereco.getText()));
						estadoComboBoxEndereco.setSelectedIndex(retornaIndexEndereco(BuscarEndereco.getUF(cepTextFieldEndereco.getText())));
						logradouroTextFieldEndereco.setText(BuscarEndereco.getEndereco(cepTextFieldEndereco.getText()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		JLabel numeroTextEnderecoCadastro = new JLabel("N\u00FAmero:");
		numeroTextEnderecoCadastro.setBounds(10, 108, 68, 14);
		enderecoPainel.add(numeroTextEnderecoCadastro);

		numeroTextFieldEndereco = new JTextField();
		numeroTextFieldEndereco.setColumns(10);
		numeroTextFieldEndereco.setBounds(74, 105, 120, 20);
		enderecoPainel.add(numeroTextFieldEndereco);

		JLabel complementoTextEnderecoCadastro = new JLabel("Complemento:");
		complementoTextEnderecoCadastro.setBounds(225, 108, 86, 14);
		enderecoPainel.add(complementoTextEnderecoCadastro);

		complementoTextFieldEndereco = new JTextField();
		complementoTextFieldEndereco.setColumns(10);
		complementoTextFieldEndereco.setBounds(322, 105, 120, 20);
		enderecoPainel.add(complementoTextFieldEndereco);


		btnNewButton.setBounds(225, 20, 120, 23);
		enderecoPainel.add(btnNewButton);

		JButton cadastrarButtonCadastro = new JButton("Cadastrar");
		cadastrarButtonCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(Utils.validaCep(cepTextFieldEndereco.getText())){
						Endereco endereco = new Endereco(cepTextFieldEndereco.getText(), logradouroTextFieldEndereco.getText(),
								Integer.parseInt(numeroTextFieldEndereco.getText()), bairroTextFieldEndereco.getText(),
								municipioTextFieldEndereco.getText(), (short) estadoComboBoxEndereco.getSelectedIndex(),
								"Brasil", complementoTextFieldEndereco.getText());
						if(Utils.validaCpf(cpfTextFieldCadastro.getText())){
							Cliente cliente = new Cliente(dc.retorna().size(), nomeTextFieldCadastro.getText(),
									((sexoComboBoxCadastro.getSelectedIndex() == 0) ? 'm' : 'f'),
									cpfTextFieldCadastro.getText(), telefoneTextFieldCadastro.getText(),
									emailTextFieldCadastro.getText(), endereco);

							if(lc.inserir(cliente))
								JOptionPane.showMessageDialog(null, "Cadastrado com sucesso.");
						}
					}
				} catch (ClassNotFoundException classe) {
					JOptionPane.showMessageDialog(null, "Erro: (" + classe.getMessage() + ")");
				}catch (NumberFormatException numero){
					JOptionPane.showMessageDialog(null, "Dados inválidos (" + numero.getMessage() + ")");					
				}
			}
		});
		cadastrarButtonCadastro.setBounds(582, 197, 110, 23);
		contentPanel.add(cadastrarButtonCadastro);


		JButton limparPessoaisCadastro = new JButton("Limpar");
		limparPessoaisCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (JOptionPane.showConfirmDialog(null, "Deseja realmente limpar todos os campos?",
						Utils.nomePrograma + "Limpar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					nomeTextFieldCadastro.setText("");
					cpfTextFieldCadastro.setText("");
					telefoneTextFieldCadastro.setText("");
					emailTextFieldCadastro.setText("");
					cepTextFieldEndereco.setText("");
					estadoComboBoxEndereco.setSelectedIndex(0);
					sexoComboBoxCadastro.setSelectedIndex(0);
					municipioTextFieldEndereco.setText("");
					bairroTextFieldEndereco.setText("");
					logradouroTextFieldEndereco.setText("");
					numeroTextFieldEndereco.setText("");
					complementoTextFieldEndereco.setText("");
				}
			}
		});
		limparPessoaisCadastro.setBounds(483, 197, 89, 23);
		contentPanel.add(limparPessoaisCadastro);
	}
	private int retornaIndexEndereco(String estado){
		Endereco endereco = new Endereco();
		for(int i = 0; i < endereco.getListaEstados().length; i++){
			if(estado.equals(endereco.getListaEstados()[i]))
				return i;
		}
		return -1;
	}
}
