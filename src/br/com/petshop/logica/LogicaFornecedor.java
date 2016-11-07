package br.com.petshop.logica;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.petshop.dados.DadosFornecedor;
import br.com.petshop.entidades.Fornecedor;
import br.com.petshop.utils.Utils;

public class LogicaFornecedor {
	DadosFornecedor df = new DadosFornecedor();
	ArrayList<Fornecedor> listaFornecedores = new ArrayList<Fornecedor>();

	public ArrayList<Object> JTableFornecedor() {
		Object[] row;
		ArrayList<Fornecedor> fornecedores = null;
		ArrayList<Object> dados = new ArrayList<Object>();

		try {
			fornecedores = df.retornaCadastros();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		for (Fornecedor forn : fornecedores) {
			row = new Object[6];
			row[0] = forn.getId();
			row[1] = Utils.mascaraCnpj(forn.getCnpj());
			row[2] = forn.getRazaoSocial();
			row[3] = forn.getNomeFantasia();
			//row[4] = Utils.mascaraTelefone(Long.parseLong(forn.getTelefone()));
			row[4] = forn.getTelefone();
			row[5] = forn.getEmail();

			dados.add(row);
		}
		return dados;
	}

	public boolean inserir() throws ClassNotFoundException {
		String cnpj = JOptionPane.showInputDialog(null, "CNPJ: ", Utils.nomePrograma + "CNPJ",
				JOptionPane.PLAIN_MESSAGE);
		if(Utils.validaCnpj(cnpj)){
			if(!consultaCnpjCadastrado(cnpj)){
				Fornecedor fornecedor = formulario(cnpj);
				fornecedor.setId(df.qtdCadastrados());
				if(fornecedor.getCnpj() != null && fornecedor.getRazaoSocial() != null && fornecedor.getNomeFantasia() != null && fornecedor.getTelefone() != null && fornecedor.getEmail() != null){
					if(df.insereCadastro(fornecedor))
						return true;
				}
			}else
				JOptionPane.showMessageDialog(null, "Fornecedor já cadastrado.",
						Utils.nomePrograma + "Error", JOptionPane.ERROR_MESSAGE);
		}else
			JOptionPane.showMessageDialog(null, "CNPJ inválido.",
					Utils.nomePrograma + "Error", JOptionPane.ERROR_MESSAGE);
		return false;
	}
	public boolean editar(int id, String cnpj) throws ClassNotFoundException {
		Fornecedor fornecedor = formulario(cnpj);
		fornecedor.setId(id);
		if(fornecedor != null){
			if(df.alteraCadastro(id, fornecedor))
				return true;
		}
		return false;
	}

	public boolean excluir (int id) throws ClassNotFoundException{
		if(df.excluiCadastro(id)){
			return true;
		}
		return false;
	}

	private Fornecedor formulario(String cnpj) throws ClassNotFoundException {
		String razaoSocial = null, nomeFantasia = null, telefone = null, email = null;
		Fornecedor fornecedor = null;

		razaoSocial = JOptionPane.showInputDialog(null, "Razão Social: ", Utils.nomePrograma + "Cadastro Fornecedor",
				JOptionPane.PLAIN_MESSAGE);
		if(razaoSocial != null){
			nomeFantasia = JOptionPane.showInputDialog(null, "Nome Fantasia: ", Utils.nomePrograma + "Cadastro Fornecedor",
					JOptionPane.PLAIN_MESSAGE);
			if(nomeFantasia != null){
				telefone = JOptionPane.showInputDialog(null, "Telefone: ", Utils.nomePrograma + "Cadastro Fornecedor",
						JOptionPane.PLAIN_MESSAGE);
				email = JOptionPane.showInputDialog(null, "E-mail: ", Utils.nomePrograma + "Cadastro Fornecedor",
						JOptionPane.PLAIN_MESSAGE);

				fornecedor  = new Fornecedor(cnpj, razaoSocial, nomeFantasia, telefone, email);
			}
		}
		return fornecedor;
	}

	private boolean consultaCnpjCadastrado(String cnpj) throws ClassNotFoundException{
		listaFornecedores = df.retornaCadastros();
		for(Fornecedor forn : listaFornecedores){
			if(cnpj.equals(forn.getCnpj()))
				return true;
		}
		return false;
	}
}
