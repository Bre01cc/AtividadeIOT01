package br.dev.breno.model;

public class ClassificarEnderçoIP {
	private String ip;
	private int cidr;
	private int primeiroOcteto;
	private int ipDisponiveis;
	private String mascaraDecimal;
	private String mascaraBinaria;
	private String ipClass;
	
	public int getPrimeiroOcteto() {
		return primeiroOcteto;
	}


	public void setPrimeiroOcteto(int primeiroOcteto) {
		this.primeiroOcteto = primeiroOcteto;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public int getCidr() {
		return cidr;
	}


	public void setCidr(int cidr) {
		this.cidr = cidr;
	}
	public void SeparadorDeIP() {
		String ipSeparacao = ip.replace(',', '.');
		String[] partes = ipSeparacao.split("\\.");
    
		double  ipDouble =  Double.parseDouble(partes[0]);
		 primeiroOcteto =  (int) ipDouble;
		 
		 // Essa parte para pegar o cidr do ip.O bagulho tá confuso, no entanto parece funcionar
		 String parteCidr = partes[4];

		 String[] partes2= parteCidr.split("/");
		 double cirPartesDouble = Double.parseDouble(partes2[1]);
		 cidr =  (int) cirPartesDouble;
		 
//		 ipDouble =  Double.parseDouble(partes[4]);
//		 cidr = (int) ipDouble;
		}
		
	
	public void classificarIp() {
		if (primeiroOcteto >= 1 && primeiroOcteto <= 126) {
		    ipClass = "A";
		} else if (primeiroOcteto >= 127 && primeiroOcteto <= 191) {
		    ipClass = "B";
		} else if (primeiroOcteto >= 192 && primeiroOcteto <= 223) {
		    ipClass = "C";
		} else if (primeiroOcteto >= 224 && primeiroOcteto <= 239) {
		    ipClass = "D (Multicast)";
		} else {
			System.out.println("Invalido");
			
		}	
		
		}
		
	//Provalvemente essa parte vai ser bem diferente e possivelmente será outra classe
		public void classificarCIDR() {
			switch (cidr) {
			case 8:
				mascaraDecimal = "255.0.0.0";				
				break;
			case 16:
				mascaraDecimal = "255.255.0.0";				
				break;
			case 24:
				mascaraDecimal = "255.255.255.0";				
				break;
			case 32:
				mascaraDecimal = "255.255.255.255";				
				break;

			default:
				System.out.println("Erro");
				break;
			}
			}
		


	public void mostrarDados() {
		SeparadorDeIP();
	    classificarIp();
	    classificarCIDR();
		System.out.println("===============================================");
		System.out.println("Seu ip: " + ip);
		System.out.println("Sua classe de ip:"+ ipClass);
		System.out.println("Sua máscara padrão:" + mascaraDecimal);
		System.out.println("A máscara no padrão binario:" + mascaraBinaria);
		System.out.println();
		System.out.println("================================================");
	}

}
