package br.com.petshop.logica;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.petshop.dados.DadosFuncionario;
import br.com.petshop.entidades.Funcionario;
import br.com.petshop.utils.Utils;

public class LogicaFuncionario {
	DadosFuncionario df = new DadosFuncionario();
	ArrayList<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
	public ArrayList<Object> JTableFuncionario() {
		Object[] row;
		ArrayList<Funcionario> funcionarios = null;
		ArrayList<Object> dados = new ArrayList<Object>();
		try {
			funcionarios = df.retorna();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		for (Funcionario func : funcionarios) {
			row = new Object[8];
			row[0] = func.getId();
			row[1] = Utils.mascaraCpf(func.getCpf());
			row[2] = func.getNome();
			row[3] = func.getSexo();
			row[4] = func.getCargo();
			row[5] = func.getSalario();
			row[6] = Utils.mascaraTelefone(func.getTelefone());
			row[7] = func.getEmail();

			dados.add(row);
		}
		return dados;
	}

	public boolean inserir() throws ClassNotFoundException {
		String cpf = JOptionPane.showInputDialog(null, "Informe o cpf: ", Utils.nomePrograma + "CPF",
				JOptionPane.PLAIN_MESSAGE);
		if(Utils.validaCpf(cpf)){
			if(!consultaCpfCadastrado(cpf)){
				Funcionario funcionario = formulario(cpf, df.retorna().size());
				funcionario.setId(df.qtdCadastrados());
				if(funcionario != null){
					if(df.insere(funcionario))
						return true;
				}
			}else
				JOptionPane.showMessageDialog(null, "Funcionário já cadastrado.",
						Utils.nomePrograma + "Error", JOptionPane.ERROR_MESSAGE);
		}else
			JOptionPane.showMessageDialog(null, "CPF inválido.",
					Utils.nomePrograma + "Error", JOptionPane.ERROR_MESSAGE);
		return false;
	}
	public boolean editar(int id, String cpf) throws ClassNotFoundException {
		Funcionario funcionario = formulario(cpf, id);
		funcionario.setId(id);
		if(funcionario != null){
			if(df.altera(id, funcionario))
				return true;
		}
		return false;
	}
	public boolean excluir(int id) throws ClassNotFoundException {
		if(df.exclui(id)){
				return true;
		}
		return false;
	}
	
	private Funcionario formulario(String cpf, int id) throws ClassNotFoundException {
		String nome = null, cargo = null, telefone = null, email = null;
		char sexo = 'n';
		double salario = 0;
		Funcionario funcionario = null;

		nome = JOptionPane.showInputDialog(null, "Nome: ", Utils.nomePrograma + "Cadastro Funcionário",
				JOptionPane.PLAIN_MESSAGE);
		if(nome != null){
			cargo = JOptionPane.showInputDialog(null, "Cargo: ", Utils.nomePrograma + "Cadastro Funcionário",
					JOptionPane.PLAIN_MESSAGE);
			if(cargo != null){
				salario = Double.parseDouble(JOptionPane.showInputDialog(null, "Salário: ",
						Utils.nomePrograma + "Cadastro Funcionário", JOptionPane.PLAIN_MESSAGE));
				if(salario != 0){
					sexo = JOptionPane
							.showInputDialog(null, "Ssexo (m/f): ", Utils.nomePrograma + "Cadastro Funcionário", JOptionPane.PLAIN_MESSAGE)
							.charAt(0);
					if(sexo != 0.0){

						telefone = JOptionPane.showInputDialog(null, "Telefone: ", Utils.nomePrograma + "Cadastro Funcionário",
								JOptionPane.PLAIN_MESSAGE);
						email = JOptionPane.showInputDialog(null, "E-mail: ", Utils.nomePrograma + "Cadastro Funcionário",
								JOptionPane.PLAIN_MESSAGE);

						funcionario  = new Funcionario(id, cpf, nome, cargo, salario, telefone, sexo, email);
					}
				}
			}
		}
		return funcionario;
	}
	private boolean consultaCpfCadastrado(String cpf) throws ClassNotFoundException{
		listaFuncionarios = df.retorna();
		for(Funcionario func : listaFuncionarios){
			if(cpf.equals(func.getCpf()))
				return true;
		}
		return false;
	}
}
