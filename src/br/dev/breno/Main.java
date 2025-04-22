package br.dev.breno;

import br.dev.breno.model.ClassificarEnderçoIP;

public class Main {

	public static void main(String[] args) {
	
		ClassificarEnderçoIP ipInfo = new ClassificarEnderçoIP();

		ipInfo.setIp("192.168.0.1");           // Define o IP
		ipInfo.setCidr(24);                    // Define o CIDR
		ipInfo.processarPrimeiroOcteto();      // PROCESSA O PRIMEIRO OCTETO
		ipInfo.classificarIp();                // Classifica com base no octeto
		ipInfo.ipParaHost();                   // Calcula IPs disponíveis
		ipInfo.mostrarDados();                 // Exibe tudo

	}

}
