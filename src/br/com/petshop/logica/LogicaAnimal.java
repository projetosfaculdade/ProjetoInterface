package br.com.petshop.logica;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.petshop.dados.DadosAnimal;
import br.com.petshop.dados.DadosCliente;
import br.com.petshop.entidades.Animal;
import br.com.petshop.entidades.Cliente;
import br.com.petshop.utils.Utils;

public class LogicaAnimal {
	DadosAnimal da = new DadosAnimal();
	DadosCliente dc = new DadosCliente();

	public ArrayList<Object> JtableAnimal(int id) throws ClassNotFoundException {
		Object[] row;
		ArrayList<Object> dados = new ArrayList<Object>();
		ArrayList<Animal> animais = null;
		ArrayList<Integer> lista = null;
		ArrayList<Cliente> clientes = null;

		try {
			animais = da.retorna();
			clientes = dc.retorna();
			lista = animaisDoCliente(clientes.get(id).getAnimais(), clientes.get(id).getAnimais().size());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < lista.size(); i++) {
			row = new Object[6];
			row[0] = animais.get(lista.get(i)).getId();
			row[1] = animais.get(lista.get(i)).getNomeAnimal();
			row[2] = animais.get(lista.get(i)).getRacaAnimal();
			row[3] = animais.get(lista.get(i)).getEspecieAnimal();
			row[4] = animais.get(lista.get(i)).getSexoAnimal();
			row[5] = animais.get(lista.get(i)).getIdadeAnimal();
			dados.add(row);
		}
		return dados;
	}

	public boolean inserir(int id) throws ClassNotFoundException {
		Animal animal = formulario(da.qtdCadastrados());
		Cliente cli = dc.retorna().get(id);
		cli.setAnimais(animal.getId());

		if (da.insere(animal) && dc.altera(id, cli))
			return true;
		else
			return false;
	}

	public boolean alterar(int id) throws ClassNotFoundException {
		if (da.altera(id, formulario(id)))
			return true;
		else
			return false;
	}

	public ArrayList<Integer> animaisDoCliente(ArrayList<Integer> animais, int qtdAnimais)
			throws ClassNotFoundException {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for (int i = 0; i < qtdAnimais; i++) {
			for (Animal ani : da.retorna()) {
				if (animais.get(i).equals(ani.getId())) {
					lista.add(animais.get(i));
				}
			}
		}
		return lista;
	}

	private Animal formulario(int id) throws ClassNotFoundException {
		String nome = JOptionPane.showInputDialog(null, "Nome: ", Utils.nomePrograma + "Cadastro Animal",
				JOptionPane.PLAIN_MESSAGE);
		String especie = JOptionPane.showInputDialog(null, "Espécie: ", Utils.nomePrograma + "Cadastro Animal",
				JOptionPane.PLAIN_MESSAGE);
		String raca = JOptionPane.showInputDialog(null, "Raça: ", Utils.nomePrograma + "Cadastro Animal",
				JOptionPane.PLAIN_MESSAGE);
		char sexo = JOptionPane
				.showInputDialog(null, "Sexo (m/f): ", Utils.nomePrograma + "Cadastro Animal", JOptionPane.PLAIN_MESSAGE)
				.charAt(0);
		int idade = Integer.parseInt(JOptionPane.showInputDialog(null, "Idade: ",
				Utils.nomePrograma + "Cadastro Animal", JOptionPane.PLAIN_MESSAGE));

		Animal animal = new Animal(id, nome, raca, especie, sexo, idade);
		return animal;
	}
}
