package br.com.petshop.entidades;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Cliente extends Pessoa implements Serializable{

	private ArrayList<Integer> animais =  new ArrayList<>();
	private double saldo;
	public Cliente() {
	}
	public Cliente(int id, String nome, char sexo, String cpf, String telefone, String email, Endereco endereco) {
		super.setId(id);
		super.setNome(nome);
		super.setSexo(sexo);
		super.setCpf(cpf);
		super.setTelefone(telefone);
		super.setEmail(email);
		this.setAnimais(animais);
		this.setSaldo(0.d);
		super.setEndereco(endereco);
	}

	public static boolean consultarClienteJaCadastrado(ArrayList<Cliente> cli, String auxCPF){
		for(Cliente clientes : cli)
			if(clientes.getCpf().equals(auxCPF))
				return true;
		return false;
	}

	public String toString(Cliente cli){
		return "ID: " + cli.getId() + ", Nome: " + cli.getNome() + ", CPF: " + cli.getCpf() +", Endereço: " + cli.getEndereco() +", Telefone: " + cli.getTelefone();
	}

	public ArrayList<Integer> getAnimais() {
		return animais;
	}

	public void setAnimais(int animais) {
		this.animais.add(animais);
	}
	public void setAnimais(ArrayList<Integer> animais) {
		this.animais = animais;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
}