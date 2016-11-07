package br.com.petshop.apresentacao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.petshop.utils.Utils;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class Funcionalidades extends JFrame {

	private JPanel contentPane;
	public static void main(String[] args) throws FontFormatException, IOException {
		Funcionalidades frame = new Funcionalidades("Usuário", true, "Normal");
		frame.setVisible(true);
	}
	public Funcionalidades(String login, boolean adm, String grupo) throws FontFormatException, IOException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(Utils.nomePrograma + "Funcionalidades");
		setBounds(100, 100, 780, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 513);

		JButton desconectarButtonFuncionalidades = new JButton("Desconectar");
		desconectarButtonFuncionalidades.setBounds(679, 0, 125, 23);
		desconectarButtonFuncionalidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal framePrincipal;
				try {
					framePrincipal = new Principal();
					Funcionalidades.this.dispose();
					framePrincipal.setVisible(true);
				} catch (ClassNotFoundException | IOException e1) {

					e1.printStackTrace();
				}

			}
		});
		getContentPane().add(desconectarButtonFuncionalidades);

		JLabel bannerFuncionalidades = new JLabel();
		bannerFuncionalidades.setBorder(null);
		bannerFuncionalidades.setBounds(0, 0, 804, 237);

		ImageIcon imagem = new ImageIcon("img\\banner.jpg");
		Image img = imagem.getImage().getScaledInstance(bannerFuncionalidades.getWidth(),
				bannerFuncionalidades.getHeight(), Image.SCALE_SMOOTH);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 235, 804, 1);
		getContentPane().add(panel);

		getContentPane().add(bannerFuncionalidades);
		bannerFuncionalidades.setIcon(new ImageIcon(img));

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnVendas = new JMenu("Vendas");
		menuBar.add(mnVendas);

		JMenuItem mntmNovaVenda = new JMenuItem("Nova Venda");
		mntmNovaVenda.setIcon(new ImageIcon("img\\iconVendas.png"));
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VendasDialog vd = null;
				try {
					vd = new VendasDialog();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				vd.setVisible(true);
			}
		});
		mnVendas.add(mntmNovaVenda);

		JMenuItem relatorioMnVendasFuncionalidades = new JMenuItem("Relat\u00F3rio");
		relatorioMnVendasFuncionalidades.setIcon(new ImageIcon("img\\iconRelatorio.png"));
		mnVendas.add(relatorioMnVendasFuncionalidades);

		JMenu mnEstoque = new JMenu("Estoque");
		menuBar.add(mnEstoque);

		JMenuItem mntmGerenciamento = new JMenuItem("Gerenciamento");
		mntmGerenciamento.setIcon(new ImageIcon("img\\iconEstoque.png"));
		mntmGerenciamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstoqueDialog gd = new EstoqueDialog(adm);
				gd.setVisible(true);
			}
		});
		mnEstoque.add(mntmGerenciamento);

		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);

		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.setIcon(new ImageIcon("img\\iconCliente.png"));
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClienteDialog cd = new ClienteDialog();
				cd.setVisible(true);
			}
		});
		mnCadastro.add(mntmCliente);

		JMenuItem mntmFuncionario = new JMenuItem("Funcionario");
		mntmFuncionario.setIcon(new ImageIcon("img\\iconFuncionario.png"));
		mntmFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionarioDialog fd = new FuncionarioDialog();
				fd.setVisible(true);
			}
		});
		mnCadastro.add(mntmFuncionario);

		JMenuItem mntmFornecedor = new JMenuItem("Fornecedor");
		mntmFornecedor.setIcon(new ImageIcon("img\\iconFornecedor.png"));
		mntmFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FornecedorDialog fd = new FornecedorDialog();
				fd.setVisible(true);
			}
		});
		mnCadastro.add(mntmFornecedor);

		JMenuItem mntmProduto = new JMenuItem("Produto");
		mntmProduto.setIcon(new ImageIcon("img\\iconProduto.png"));
		mntmProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoDialog pd = new ProdutoDialog();
				pd.setVisible(true);
			}
		});
		mnCadastro.add(mntmProduto);

		JMenuItem mntmUsuario = new JMenuItem("Usu\u00E1rio");
		mntmUsuario.setIcon(new ImageIcon("img\\iconUser.png"));
		mntmUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioDialog ud = new UsuarioDialog();
				if (adm)
					ud.setVisible(true);
				else
					ud.setVisible(false);
			}

		});
		mnCadastro.add(mntmUsuario);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.setIcon(new ImageIcon("img\\iconAjuda.png"));
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SobreDialog sd = new SobreDialog();
				sd.setVisible(true);
			}
		});
		mnAjuda.add(mntmSobre);

		JLabel labelPrincipalFuncionalidades = new JLabel("PetShop JLR");
		labelPrincipalFuncionalidades.setForeground(new Color(0, 128, 128));
		labelPrincipalFuncionalidades.setHorizontalAlignment(SwingConstants.CENTER);

		Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/HYUNN___.TTF")).deriveFont(70f);

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/HYUNN___.TTF")));

		labelPrincipalFuncionalidades.setFont(customFont);
		labelPrincipalFuncionalidades.setBounds(56, 248, 698, 158);
		getContentPane().add(labelPrincipalFuncionalidades);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 440, 804, 23);
		getContentPane().add(separator);



		JLabel dataHoraTextFuncionalidades = new JLabel("00/00/0000 - 00:00");
		dataHoraTextFuncionalidades.setHorizontalAlignment(SwingConstants.LEFT);
		dataHoraTextFuncionalidades.setBounds(5, 445, 135, 15);
		getContentPane().add(dataHoraTextFuncionalidades);
		Timer timer = new Timer();
		TimerTask alterarHora = new TimerTask() {
			@Override
			public void run() {
				dataHoraTextFuncionalidades.setText(new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss").format(new Date()));
			}
		};
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(134, 440, 2, 23);
		getContentPane().add(separator_1);

		//JLabel informacoesTextFuncionalidades = new JLabel(login + " - " + grupo);
		JLabel informacoesTextFuncionalidades = new JLabel("Bem vindo ao sistema, " + login + ".");
		
		informacoesTextFuncionalidades.setHorizontalAlignment(SwingConstants.LEFT);
		informacoesTextFuncionalidades.setBounds(150, 445, 260, 15);
		getContentPane().add(informacoesTextFuncionalidades);
		
		timer.scheduleAtFixedRate(alterarHora, 0, 1000);
	}
}
