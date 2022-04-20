package br.com.serratec.models;

public class Bebida {
	private String nome;
	private Double valor;
	
	public Bebida(String nome, Double valor) {
		super();
		this.nome = nome;
		this.valor = valor;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "nome = " + nome + ", valor = " + valor;
	}	
}
