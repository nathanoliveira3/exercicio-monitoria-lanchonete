package br.com.serratec.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.serratec.models.Bebida;
import br.com.serratec.models.Cliente;
import br.com.serratec.models.Lanche;

public class Principal {
	public static void main(String[] args) throws IOException {
		List<Lanche> lanches = new ArrayList<>();
		List<Bebida> bebidas = new ArrayList<>();
		
		List<Lanche> lanchesPedido = new ArrayList<>();
		List<Bebida> bebidasPedido = new ArrayList<>();
		
		Double valorTotal = 0.0;
		
		String arquivo = "arquivo.txt";
		BufferedReader reader =  new BufferedReader(new FileReader(arquivo));
		
		String linha = "";
		while(true) {
			linha = reader.readLine();
			if(linha != null) {
				String[] vetor = linha.split(";");
				if(vetor[0].equalsIgnoreCase("Lanche")) { 
					lanches.add(new Lanche(vetor[1], Double.parseDouble(vetor[2])));
				}else if(vetor[0].equalsIgnoreCase("Bebida")) {
					bebidas.add(new Bebida(vetor[1], Double.parseDouble(vetor[2])));
				}
			}else {
				break;
			}
		}
		
		reader.close();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Informe o seu nome: ");
		String nome =  scan.nextLine();	
		
		Cliente cliente = new Cliente(nome);
		System.out.println("\nSeja bem vindo(a) " + cliente.getNome());
		
		int option = 0;
		while(option != -1) {
			System.out.println("\nSelecione o seu lanche ou digite -1 para sair: ");
			
			int cont = 0;
			for(Lanche lanche : lanches) {
				cont++;
				System.out.printf("%d - %s\n", cont, lanche);
			}
			option = scan.nextInt();
			
			if(option >= 0) {
				lanchesPedido.add(lanches.get(option - 1));
			}
			
			System.out.println("Escolha a bebida ou digite -1 para sair: ");
			
			int cont2 = 0;
			for(Bebida bebida : bebidas) {
				cont2++;
				System.out.printf("%d - %s\n", cont2, bebida);
			}
			option = scan.nextInt();
			
			if(option >= 0) {
				bebidasPedido.add(bebidas.get(option - 1));
			}
			
			System.out.println("\nSeu pedido foi: ");
			Double totalLanche = 0.0;
			for(Lanche lanche :  lanchesPedido) {
				totalLanche += lanche.getValor();
				System.out.println(lanche);
			}
			Double totalBebida = 0.0;
			for(Bebida bebida : bebidasPedido) {
				totalBebida += bebida.getValor();
				System.out.println(bebida);
			}
			valorTotal = totalBebida + totalLanche;
			
			System.out.printf("Valor total - %.2f", valorTotal);
		}
		
		String nota = "nota.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(nota));
		
		for(Lanche lanche : lanchesPedido) {
			writer.append(lanche.getNome()).append(" - ").append(lanche.getValor().toString()).append("\n");
		}
		for(Bebida bebida : bebidasPedido) {
			writer.append(bebida.getNome()).append(" - ").append(bebida.getValor().toString()).append("\n");
		}
		
		writer.append("Valor total - ").append(valorTotal.toString());
		
		writer.close();
		
		System.out.println("\nNota fiscal emitida!");
		
		scan.close();	
		
	}
}
