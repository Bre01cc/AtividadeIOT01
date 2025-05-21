package br.dev.breno;

import br.dev.breno.model.ClassificarEnderecoIP;

public class Main {

	public static void main(String[] args) {
		
//		int contador = 0;
//		for(int i =0; i<30; i++) {
//contador+=1;
//
//			System.out.println(contador);
//		}
	ClassificarEnderecoIP javaIp1 = new ClassificarEnderecoIP();
	
	javaIp1.setIp("192.168.10.0.(0/1)");
	 javaIp1.mostrarDados();

	}

}
