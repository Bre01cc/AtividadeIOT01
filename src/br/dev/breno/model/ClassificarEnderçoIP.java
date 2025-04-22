package br.dev.breno.model;

public class ClassificarEnderçoIP {
	private String ip;
	private int cidr;
	private int primeiroOcteto;
	private int ipDisponiveis;
	private String mascaraDecimal;
	private String mascaraBinaria;
	private String ipClass;
	
	
	

	public void setIp(String ip) {
		this.ip = ip;
		String[]partes = ip.split("/");

	}


	public void ipParaHost() {
		ipDisponiveis = (int) Math.pow(2, 32 - cidr) - 2;
	}


	public void classificarIp() {
		if (primeiroOcteto >= 1 && primeiroOcteto <= 126) {
		    ipClass = "A";
		    mascaraDecimal = "255.0.0.0";
		    mascaraBinaria = "11111111.00000000.00000000.00000000";
		} else if (primeiroOcteto >= 128 && primeiroOcteto <= 191) {
		    ipClass = "B";
		    mascaraDecimal = "255.255.0.0";
		    mascaraBinaria = "11111111.11111111.00000000.00000000";
		} else if (primeiroOcteto >= 192 && primeiroOcteto <= 223) {
		    ipClass = "C";
		    mascaraDecimal = "255.255.255.0";
		    mascaraBinaria = "11111111.11111111.11111111.00000000";
		} else if (primeiroOcteto >= 224 && primeiroOcteto <= 239) {
		    ipClass = "D (Multicast)";
		    mascaraDecimal = "";
		    mascaraBinaria = "";
		} else {
			System.out.println("Inválido");
			
		}
		
		}


	public void mostrarDados() {
		System.out.println("===============================================");
		System.out.println("Seu ip: " + ip);
		System.out.println("Seu IP é da classe:" + ipClass);
		System.out.println("Sua máscara padrão:" + mascaraDecimal);
		System.out.println("A máscara no padrão binario:" + mascaraBinaria);
		System.out.println();
		System.out.println("================================================");
		System.out.println("");
		System.out.println("Continuar ou Sair");
	}

}
