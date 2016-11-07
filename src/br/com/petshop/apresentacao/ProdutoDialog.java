package br.com.petshop.apresentacao;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.text.DecimalFormat;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import br.com.petshop.entidades.Produto;
import br.com.petshop.logica.LogicaProduto;
import br.com.petshop.utils.Utils;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ProdutoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField codBarrasProdutoTexLabel;
	private JTextField nomeProdutoTextLabel;
	private LogicaProduto lp = new LogicaProduto();

	public ProdutoDialog() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle(Utils.nomePrograma + "Produto");
		setBounds(100, 100, 380, 240);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		DecimalFormat dFormat = new DecimalFormat("#,###,###.00");
		NumberFormatter formatter = new NumberFormatter(dFormat);
		formatter.setFormat(dFormat);
		formatter.setAllowsInvalid(false);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Cadastro de produtos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 354, 189);
		contentPanel.add(panel);
		panel.setLayout(null);

		nomeProdutoTextLabel = new JTextField();
		nomeProdutoTextLabel.setBounds(163, 65, 100, 20);
		panel.add(nomeProdutoTextLabel);
		nomeProdutoTextLabel.setColumns(10);

		JComboBox<String> categoriaProdutoComboBox = new JComboBox<String>();
		categoriaProdutoComboBox.setBounds(163, 125, 100, 20);
		panel.add(categoriaProdutoComboBox);
		Produto prod = new Produto();
		categoriaProdutoComboBox.setModel(new DefaultComboBoxModel<String>(prod.getListaCategorias()));
		JFormattedTextField precoProdutoTextLabel = new JFormattedTextField();
		precoProdutoTextLabel.setBounds(163, 94, 100, 20);
		panel.add(precoProdutoTextLabel);
		precoProdutoTextLabel.setFormatterFactory(new DefaultFormatterFactory(formatter));

		codBarrasProdutoTexLabel = new JTextField();
		codBarrasProdutoTexLabel.setBounds(163, 34, 100, 20);
		panel.add(codBarrasProdutoTexLabel);
		codBarrasProdutoTexLabel.setColumns(10);

		JLabel labelLoginProduto = new JLabel("C\u00F3digo de Barras:");
		labelLoginProduto.setBounds(10, 37, 143, 14);
		panel.add(labelLoginProduto);
		labelLoginProduto.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblNomeProduto = new JLabel("Nome");
		lblNomeProduto.setBounds(53, 68, 100, 14);
		panel.add(lblNomeProduto);
		lblNomeProduto.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblCategoriaProduto = new JLabel("Categoria");
		lblCategoriaProduto.setBounds(53, 128, 100, 14);
		panel.add(lblCategoriaProduto);
		lblCategoriaProduto.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblPrecoProduto = new JLabel("Pre\u00E7o");
		lblPrecoProduto.setBounds(53, 97, 100, 14);
		panel.add(lblPrecoProduto);
		lblPrecoProduto.setHorizontalAlignment(SwingConstants.RIGHT);

		JCheckBox gerarCheckBox = new JCheckBox("Gerar");
		gerarCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gerarCheckBox.isSelected()) {
					try {
						codBarrasProdutoTexLabel.setText(String.valueOf(lp.gerarCodBarrasQuatroDigitos()));
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
					codBarrasProdutoTexLabel.setEnabled(false);
				} else {
					codBarrasProdutoTexLabel.setText("");
					codBarrasProdutoTexLabel.setEnabled(true);
				}
			}
		});

		gerarCheckBox.setBounds(269, 33, 79, 23);

		panel.add(gerarCheckBox);

		JButton cadastrarButtonProduto = new JButton("Cadastrar");
		cadastrarButtonProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long codBarras = Long.parseLong(codBarrasProdutoTexLabel.getText());
				try {
					if (!lp.consultaProdutoJaCadastrado(codBarras)) {
						Produto produto = new Produto(codBarras, nomeProdutoTextLabel.getText(),
								Double.parseDouble(precoProdutoTextLabel.getText().replace(",", ".")),
								categoriaProdutoComboBox.getSelectedIndex());

						if (lp.inserir(produto)) {
							JOptionPane.showMessageDialog(null, "Produto cadastrado.");
							dispose();
						} else
							JOptionPane.showMessageDialog(null, "Erro: produto não cadastrado.");

					} else
						JOptionPane.showMessageDialog(null, "Código de barras já cadastrado, informe outro.");
				} catch (NumberFormatException | HeadlessException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		cadastrarButtonProduto.setBounds(163, 155, 100, 23);
		panel.add(cadastrarButtonProduto);
	}
}
