package br.dev.breno;

import br.dev.breno.model.ClassificarEnderçoIP;

public class Main {

	public static void main(String[] args) {
	
		ClassificarEnderçoIP ipInfo = new ClassificarEnderçoIP();

		ipInfo.setIp("193,168,0,1,0/32"); 
		ipInfo.setCidr(24); 
		ipInfo.getPrimeiroOcteto();
		ipInfo.getCidr();
		ipInfo.classificarCIDR();
		ipInfo.mostrarDados();                

	}

}
