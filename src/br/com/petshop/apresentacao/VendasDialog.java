package br.com.petshop.apresentacao;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.petshop.dados.DadosEstoque;
import br.com.petshop.dados.DadosVenda;
import br.com.petshop.entidades.Cliente;
import br.com.petshop.entidades.Estoque;
import br.com.petshop.entidades.Produto;
import br.com.petshop.entidades.Vendas;
import br.com.petshop.logica.LogicaVendas;
import br.com.petshop.utils.Utils;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.text.DecimalFormat;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import java.awt.ComponentOrientation;

@SuppressWarnings("serial")
public class VendasDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField codBarrasVendas;
	private JTable tableVendas;
	private LogicaVendas lv = new LogicaVendas();
	private DadosVenda dv = new DadosVenda();
	private DadosEstoque de = new DadosEstoque();
	private ArrayList<Estoque> dadosEstoque;
	private double subTotal = 0.d;
	private JTextField qtdTextField;
	private DecimalFormat df = new DecimalFormat("###,##0.00");
	private JTextField txtInformeOId;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public static void main(String[] args) throws ClassNotFoundException {
		JDialog vendas = new VendasDialog();
		vendas.setVisible(true);
	}
	public VendasDialog() throws ClassNotFoundException {
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		dadosEstoque = de.retorna();
		setResizable(false);
		setModal(true);
		setTitle(Utils.nomePrograma + "Vendas");
		setBounds(100, 100, 740, 435);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setFont(new Font("Tahoma", Font.TRUETYPE_FONT, 11));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		codBarrasVendas = new JTextField();
		codBarrasVendas.setBounds(83, 7, 78, 20);
		contentPanel.add(codBarrasVendas);
		codBarrasVendas.setColumns(10);

		JLabel codBarrasLabelVendas = new JLabel("Cod Barras:");
		codBarrasLabelVendas.setHorizontalAlignment(SwingConstants.LEFT);
		codBarrasLabelVendas.setBounds(10, 11, 71, 14);
		contentPanel.add(codBarrasLabelVendas);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(592, 86, 132, 55);
		contentPanel.add(panel_1);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "SubTotal", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setLayout(null);

		JLabel valorSubTotalText = new JLabel("R$ 0.0");
		valorSubTotalText.setHorizontalAlignment(SwingConstants.RIGHT);
		valorSubTotalText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		valorSubTotalText.setBounds(10, 11, 112, 33);
		panel_1.add(valorSubTotalText);

		JButton adicionarButtonVendas = new JButton("Adicionar ao carrinho");
		adicionarButtonVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Integer.parseInt(qtdTextField.getText()) > 0) {
					try {
						if (preencheJTableEstoque(Long.parseLong(codBarrasVendas.getText()), dadosEstoque))
							dadosEstoque.get(lv.retornaIndex(Long.parseLong(codBarrasVendas.getText()), dadosEstoque))
							.setQtd(dadosEstoque
									.get(lv.retornaIndex(Long.parseLong(codBarrasVendas.getText()),
											dadosEstoque))
									.getQtd() - Integer.parseInt(qtdTextField.getText()));
						subTotal = 0.d;
						for(int i = 0; i < tableVendas.getModel().getRowCount(); i++)
							subTotal += Double.parseDouble(tableVendas.getValueAt(i, Utils.indexTotal).toString());
						valorSubTotalText.setText("R$" + String.valueOf(df.format(subTotal)));
					} catch (NumberFormatException | HeadlessException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(null, "Informe uma quantidade maior que 0.");
			}
		});
		adicionarButtonVendas.setBounds(171, 7, 170, 46);
		contentPanel.add(adicionarButtonVendas);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 86, 572, 294);
		contentPanel.add(scrollPane);

		tableVendas = new JTable();
		tableVendas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableVendas.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Cod de Barras", "Nome", "Qtd", "Categoria", "Pre\u00E7o", "Total"
				}
				));
		tableVendas.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableVendas.getColumnModel().getColumn(0).setMinWidth(100);
		tableVendas.getColumnModel().getColumn(0).setMaxWidth(150);
		tableVendas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableVendas.getColumnModel().getColumn(1).setMinWidth(100);
		tableVendas.getColumnModel().getColumn(1).setMaxWidth(170);
		tableVendas.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableVendas.getColumnModel().getColumn(2).setMinWidth(50);
		tableVendas.getColumnModel().getColumn(2).setMaxWidth(60);
		tableVendas.getColumnModel().getColumn(3).setPreferredWidth(90);
		tableVendas.getColumnModel().getColumn(3).setMinWidth(90);
		tableVendas.getColumnModel().getColumn(3).setMaxWidth(120);
		tableVendas.getColumnModel().getColumn(4).setPreferredWidth(70);
		tableVendas.getColumnModel().getColumn(4).setMinWidth(70);
		tableVendas.getColumnModel().getColumn(4).setMaxWidth(80);
		tableVendas.getColumnModel().getColumn(5).setPreferredWidth(70);
		tableVendas.getColumnModel().getColumn(5).setMinWidth(70);
		tableVendas.getColumnModel().getColumn(5).setMaxWidth(80);

		scrollPane.setViewportView(tableVendas);

		JButton cancelarButtonVendas = new JButton("Cancelar Venda");
		cancelarButtonVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelarButtonVendas.setBounds(592, 346, 130, 35);
		contentPanel.add(cancelarButtonVendas);

		JButton finalizaButtonVendas = new JButton("Finalizar Venda");
		finalizaButtonVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean flagVendeu = false;
				for (int i = 0; i < tableVendas.getModel().getRowCount(); i++) {
					Produto row = new Produto();
					ArrayList<Produto> dadosCarrinho = new ArrayList<Produto>();
					double valorTotal = 0.f, subTotal;

					for (int ii = 0; ii < tableVendas.getRowCount(); ii++) {
						row.setCodBarras(Long.parseLong(tableVendas.getModel().getValueAt(ii, 0).toString()));
						row.setNome(String.valueOf(tableVendas.getModel().getValueAt(ii, 1)));
						row.setCategoria(lv.retornarIndexCategoria(tableVendas.getModel().getValueAt(ii, 3).toString()));
						row.setPreco(Double.parseDouble(tableVendas.getModel().getValueAt(ii, 4).toString()));
						dadosCarrinho.add(row);
						valorTotal += Double.parseDouble(tableVendas.getModel().getValueAt(ii, 4).toString());
					}
					try {
						subTotal = valorTotal;
						Vendas venda = new Vendas(dv.retorna().size(), dadosCarrinho, subTotal, valorTotal);
						if (dv.insere(venda)) {
							de.salva(dadosEstoque);
							flagVendeu = true;
						}

					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
				if (flagVendeu)
					JOptionPane.showMessageDialog(null, "Venda finalizada.");
				else
					JOptionPane.showMessageDialog(null, "Erro: a venda não pode ser feita.");
			}
		});
		finalizaButtonVendas.setBounds(592, 254, 132, 35);
		contentPanel.add(finalizaButtonVendas);

		JLabel qtdTextVendas = new JLabel("Quantidade:");
		qtdTextVendas.setHorizontalAlignment(SwingConstants.LEFT);
		qtdTextVendas.setBounds(10, 33, 71, 14);
		contentPanel.add(qtdTextVendas);

		qtdTextField = new JTextField();
		qtdTextField.setBounds(83, 30, 78, 20);
		contentPanel.add(qtdTextField);
		qtdTextField.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "Pagamento", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(592, 153, 132, 90);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);

		JRadioButton dinheiroRadioVendas = new JRadioButton("Dinheiro");
		buttonGroup.add(dinheiroRadioVendas);
		dinheiroRadioVendas.setBounds(5, 20, 100, 23);
		panel_2.add(dinheiroRadioVendas);

		JRadioButton cartaoRadioVendas = new JRadioButton("Cart\u00E3o");
		buttonGroup.add(cartaoRadioVendas);
		cartaoRadioVendas.setBounds(5, 40, 100, 23);
		panel_2.add(cartaoRadioVendas);

		JRadioButton prazoRadioVendas = new JRadioButton("Prazo");
		buttonGroup.add(prazoRadioVendas);
		prazoRadioVendas.setBounds(5, 60, 100, 23);
		panel_2.add(prazoRadioVendas);

		JLabel qtdNoCarrinhoTextVendas = new JLabel("0");
		qtdNoCarrinhoTextVendas.setBounds(141, 385, 43, 14);
		contentPanel.add(qtdNoCarrinhoTextVendas);
		qtdNoCarrinhoTextVendas.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel qtdProdutosCarrinhoTextVendas = new JLabel("Produtos no carrinho: ");
		qtdProdutosCarrinhoTextVendas.setHorizontalAlignment(SwingConstants.LEFT);
		qtdProdutosCarrinhoTextVendas.setBounds(12, 385, 127, 14);
		contentPanel.add(qtdProdutosCarrinhoTextVendas);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(436, 0, 288, 76);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel nomeCpfClienteVendas = new JLabel("Nome: Usu\u00E1rio / Cpf: 000.000.000-00");
		nomeCpfClienteVendas.setBounds(10, 38, 268, 14);
		panel.add(nomeCpfClienteVendas);
		nomeCpfClienteVendas.setVisible(false);

		nomeCpfClienteVendas.setHorizontalAlignment(SwingConstants.LEFT);
		JCheckBox clienteCadastradoVendas = new JCheckBox("Cliente cadastrado");
		clienteCadastradoVendas.setBounds(4, 13, 139, 23);
		panel.add(clienteCadastradoVendas);

		JLabel saldoClienteVendas = new JLabel("Saldo: R$ 0,00");
		saldoClienteVendas.setHorizontalAlignment(SwingConstants.LEFT);
		saldoClienteVendas.setBounds(10, 56, 268, 14);
		panel.add(saldoClienteVendas);
		saldoClienteVendas.setVisible(false);

		JButton buscarButtonVendas = new JButton("Buscar");
		buscarButtonVendas.setBounds(189, 13, 89, 23);
		panel.add(buscarButtonVendas);
		buscarButtonVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Cliente cliente = lv.informacoesClienteVenda(Integer.parseInt(txtInformeOId.getText()));
					if(cliente != null){
						nomeCpfClienteVendas.setText("Nome: " + cliente.getNome() + " / Cpf: " + Utils.mascaraCpf(cliente.getCpf()));
						saldoClienteVendas.setText("Saldo: R$ " + df.format(cliente.getSaldo()));
					}else{
						nomeCpfClienteVendas.setText("");
						saldoClienteVendas.setText("");
						JOptionPane.showMessageDialog(null, "Cliente não encontrado.");

					}
				} catch (NumberFormatException | ClassNotFoundException e) {
					e.printStackTrace();
				}

			}
		});
		buscarButtonVendas.setEnabled(false);

		txtInformeOId = new JTextField();
		txtInformeOId.setBounds(144, 13, 35, 22);
		panel.add(txtInformeOId);
		txtInformeOId.setEnabled(false);
		txtInformeOId.setText("ID");
		txtInformeOId.setColumns(10);

		clienteCadastradoVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clienteCadastradoVendas.isSelected()){
					txtInformeOId.setEnabled(true);
					buscarButtonVendas.setEnabled(true);
					nomeCpfClienteVendas.setVisible(true);
					saldoClienteVendas.setVisible(true);
					txtInformeOId.setText("");
				}else{
					txtInformeOId.setEnabled(false);
					buscarButtonVendas.setEnabled(false);
					nomeCpfClienteVendas.setVisible(false);
					saldoClienteVendas.setVisible(false);
					txtInformeOId.setText("ID");
				}
			}
		});
	}
	private boolean preencheJTableEstoque(long codBarras, ArrayList<Estoque> dadosEstoque)
			throws NumberFormatException, HeadlessException, ClassNotFoundException {
		if (lv.consultarCodBarras(Long.parseLong(codBarrasVendas.getText()), dadosEstoque)) {
			if (lv.consultaQuantidadeNoEstoque(Long.parseLong(codBarrasVendas.getText()),
					Integer.parseInt(qtdTextField.getText()), dadosEstoque)) {
				ArrayList<Object> row = lv.JTableVendas(Integer.parseInt(qtdTextField.getText()),
						Long.parseLong(codBarrasVendas.getText()), dadosEstoque);
				for (int i = 0; i < row.size(); i++) {
					((DefaultTableModel) tableVendas.getModel()).addRow((Object[]) row.get(i));
				}
				return true;
			} else
				JOptionPane.showMessageDialog(null, "Quantidade insuficiente em estoque.");

		} else
			JOptionPane.showMessageDialog(null, "Código de barras não encontrado");
		return false;
	}
}