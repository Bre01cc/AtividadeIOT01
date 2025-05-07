package br.dev.breno;

import br.dev.breno.model.ClassificarEnderecoIP;

public class Main {

	public static void main(String[] args) {
	ClassificarEnderecoIP javaIp1 = new ClassificarEnderecoIP();
	
	javaIp1.setIp("192.168.10.0.(0/24)");
	 javaIp1.mostrarDados();

	}

}
