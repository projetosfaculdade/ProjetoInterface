package br.com.petshop.logica;

import java.util.ArrayList;


import br.com.petshop.dados.DadosCliente;
import br.com.petshop.entidades.Cliente;
import br.com.petshop.utils.Utils;

public class LogicaCliente {
	private DadosCliente dc = new DadosCliente();

	ArrayList<Cliente> clientes = null;
	public boolean inserir(Cliente cliente) throws ClassNotFoundException {
		if(dc.insere(cliente))
			return true;
		else
			return false;
	}

	public boolean alterar(int id) throws ClassNotFoundException {
		/*		clientes = dc.retorna();
		Cliente formulario = formulario(id);
		formulario.setAnimais(clientes.get(id).getAnimais());
		if(formulario != null){
			if (dc.altera(id, formulario))
				return true;
		}*/
		return false;
	}

	public boolean exclui(int id) throws ClassNotFoundException {
		return dc.exclui(id);
	}

	public ArrayList<Object> JTableCliente() {
		Object[] row;
		ArrayList<Object> dados = new ArrayList<Object>();
		try {
			clientes = dc.retorna();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		for (Cliente cli : clientes) {
			row  = new Object[6];
			row[0] = cli.getId();
			row[1] = cli.getNome();
			//row[2] = cli.getEndereco();
			row[2] = Utils.mascaraTelefone(cli.getTelefone());
			row[3] = cli.getSexo();
			row[4] = Utils.mascaraCpf(cli.getCpf());
			row[5] = cli.getEmail();

			dados.add(row);
		}
		return dados;
	}

	public boolean validaDados(Cliente cliente) {

		return true;
	}

	public boolean organizaIndex() throws ClassNotFoundException {
		return dc.organizaIndex();
	}
}
