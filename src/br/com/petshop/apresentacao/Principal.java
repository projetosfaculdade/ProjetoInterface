package br.com.petshop.apresentacao;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.petshop.dados.DadosUsuario;
import br.com.petshop.logica.LogicaUsuario;
import br.com.petshop.utils.Utils;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField loginTextField;
	private JPasswordField senhaField;
	private JLabel lblNewLabel_1;
	String nome = Utils.nomeDiretorioDados() + "/salvarlogin.txt";
	private JLabel mensagensTextLabel;
	private LogicaUsuario lu = new LogicaUsuario();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal framePrincipal = new Principal();
					framePrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() throws IOException, ClassNotFoundException {
		DadosUsuario du = new DadosUsuario();
		Utils.criarDiretorio();
		File f = new File(nome);
		f.createNewFile();
		if(!f.exists())
			criarArquivo();

		setResizable(false);
		setTitle(Utils.nomePrograma + "Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 222);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		loginTextField = new JTextField();
		String dados1Aux = lerSalvarLogin();
		if(dados1Aux != null){
			String dados1[] =  dados1Aux.split(Pattern.quote(","));
			if(dados1[0].equals("true")){
				loginTextField.setText(dados1[1]);
			}else
				loginTextField.setText("");
		}
		loginTextField.setBounds(138, 59, 105, 20);
		contentPane.add(loginTextField);
		loginTextField.setColumns(10);

		senhaField = new JPasswordField();
		senhaField.setToolTipText("");
		senhaField.setBounds(138, 90, 105, 20);
		contentPane.add(senhaField);

		JLabel loginLabel = new JLabel("Login:");
		loginLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		loginLabel.setBounds(82, 62, 46, 14);
		contentPane.add(loginLabel);

		JLabel lblNewLabel = new JLabel("Senha:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(82, 93, 46, 14);
		contentPane.add(lblNewLabel);

		JCheckBox lembrarDadosCkbxLogin = new JCheckBox("Lembrar login");
		JButton acessarButton = new JButton("Acessar");
		acessarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int id = lu.retornaID(loginTextField.getText(), senhaField.getPassword());
					if(lu.autenticaUsuario(loginTextField.getText(), senhaField.getPassword())){
						Funcionalidades frameFuncionalidades;
						if(lembrarDadosCkbxLogin.isSelected())
							mudarSalvarLogin("true", loginTextField.getText());
						else
							mudarSalvarLogin("false");
						if(du.retorna().get(du.retornaIndex(id)).getGrupo() == 0)
							frameFuncionalidades = new Funcionalidades(du.retorna().get(du.retornaIndex(id)).getLogin(), true, "Administrador");
						else
							frameFuncionalidades = new Funcionalidades(du.retorna().get(du.retornaIndex(id)).getLogin(), false, "Normal");

						Principal.this.dispose();
						frameFuncionalidades.setVisible(true);
					}else
						mensagensTextLabel.setText("Login ou senha incorreto.");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (FontFormatException e) {
					e.printStackTrace();
				}
			}
		});
		acessarButton.setBounds(154, 121, 89, 23);
		contentPane.add(acessarButton);

		lblNewLabel_1 = new JLabel("Sistema de Controle");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_1.setBounds(50, 11, 249, 37);
		contentPane.add(lblNewLabel_1);

		mensagensTextLabel = new JLabel("");
		mensagensTextLabel.setForeground(Color.RED);
		mensagensTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mensagensTextLabel.setBounds(10, 154, 344, 14);
		contentPane.add(mensagensTextLabel);

		mensagensTextLabel.setText(lu.cadastraUsuarioAdmin());

		lembrarDadosCkbxLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		String dados0Aux = lerSalvarLogin();
		String dados0[];
		if(dados0Aux != null){
			dados0 = dados0Aux.split(Pattern.quote(","));
			lembrarDadosCkbxLogin.setSelected(Boolean.parseBoolean(dados0[0]));
		}else
			lembrarDadosCkbxLogin.setSelected(false);
		lembrarDadosCkbxLogin.setBounds(10, 121, 139, 23);
		contentPane.add(lembrarDadosCkbxLogin);

	}
	private void criarArquivo() throws IOException {

		FileWriter arq = new FileWriter(nome);
		BufferedWriter escreverArq = new BufferedWriter(arq);

		escreverArq.write("false, ");
		escreverArq.close();
		arq.close();
	}
	private String lerSalvarLogin() throws IOException {
		String linhaLogin = null;
		try {
			FileReader arq = new FileReader(nome);
			BufferedReader lerArq = new BufferedReader(arq);

			linhaLogin = lerArq.readLine();

			if(linhaLogin != null){
				if(linhaLogin.contains("false"))
					criarArquivo();
			}
			lerArq.close ();
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro: %s.\n",
					e.getMessage());
		}
		return linhaLogin;
	}
	private void mudarSalvarLogin(String opcao, String login) throws IOException{
		try {
			FileWriter arq = new FileWriter(nome);
			BufferedWriter escreverArq = new BufferedWriter(arq);

			escreverArq.write(opcao + "," + login);
			escreverArq.close ();
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro: %s.\n",
					e.getMessage());
		}
	}
	private void mudarSalvarLogin(String opcao) throws IOException{
		try {
			FileWriter arq = new FileWriter(nome);
			BufferedWriter escreverArq = new BufferedWriter(arq);

			escreverArq.write(opcao);
			escreverArq.close ();
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro: %s.\n",
					e.getMessage());
		}
	}
}
