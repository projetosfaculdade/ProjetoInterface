package br.com.petshop.entidades;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Funcionario extends Pessoa implements Serializable {

	private String cargo;
	private double salario;

	public Funcionario () {}
	public Funcionario (int id, String cpf, String nome, String cargo, double salario, String telefone, char sexo, String email){
		setId(id);
		setNome(nome);
		setCpf(cpf);
		setCargo(cargo);
		setSalario(salario);
		setTelefone(telefone);
		setSexo(sexo);
		setEmail(email);

	}
	public String toString(){
		return "CPF: "+ getCpf() +" Nome: " + getNome() + ", Sexo: " + getSexo() + ", Cargo: "+ getCargo() + ", Salário: "+ getSalario() + ", Telefone: "+ getTelefone() + ", E-mail: "+ getEmail();

	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}

}