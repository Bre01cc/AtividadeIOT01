package br.dev.breno.model;

import java.text.DecimalFormat;

public class ClassificarEnderecoIP {

	// Atributos da Classe
	private String ip;
	private int primeiroOcteto;
	private int cidr;
	private String mascaraDecimal;
	private String mascaraBinario;
	private double ipDisponiveis;
	private String classeIp;

	public String getClasseIp() {
		return classeIp;
	}

	// Gets e Sets
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	// PRIMEIRO OCTETO
	public int getPrimeiroOcteto() {
		return primeiroOcteto;
	}

	public void setPrimeiroOcteto(int primeiroOcteto) {
		this.primeiroOcteto = primeiroOcteto;
	}

	// CIDR
	public int getCidr() {
		return cidr;
	}

	public void setCidr(int cidr) {
		this.cidr = cidr;
	}

	// MÃ�SCARA DECIMAL

	public String getMascaraDecimal() {
		return mascaraDecimal;
	}

	public void setMascaraDecimal(String mascaraDecimal) {
		this.mascaraDecimal = mascaraDecimal;
	}

	// MÃ�SCARA BINARIO
	public String getMascaraBinario() {
		return mascaraBinario;
	}

	public void setMascaraBinario(String mascaraBinario) {
		this.mascaraBinario = mascaraBinario;
	}

	// IP DISPONIVEIS
	public double getIpDisponiveis() {
		return ipDisponiveis;
	}

	// METODOS

	// SEPARAR O IP
	public void SeparadorDeIPClasse(String ip) {

		// pegar o IP e substituir as virgulas por ponto
		String ipSeparacao = ip.replace(',', '.');

		// Vetor vai receber o IP e separar ele de acordo com os pontos
		// (No caso o split vai quebrar a String de acordos com os parametros que
		// inserimos dentro dos parantese)
		String[] partes = ipSeparacao.split("\\.");

		// Criar uma variavel ipInt que vai receber o vetor 0 e transformar em um int
		int ipInt = Integer.parseInt(partes[0]);

		// primeiroOcteto vai receber o ipdouble
		primeiroOcteto = ipInt;
	}

	public void SeparaIPCidr(String ip) {
		// Substitui vÃ­rgulas por pontos no IP, se necessÃ¡rio
		String ipSeparacao = ip.replace(',', '.');

		// Divide a string do IP em partes, usando o ponto como delimitador
		String[] partes = ipSeparacao.split("\\.");

		// ObtÃ©m a Ãºltima parte do vetor, que contÃ©m o CIDR (ex: "0/24")
		String parteCidr = partes[3];

		// Divide a parte do CIDR em duas partes, separadas por "/"
		String[] partes1 = parteCidr.split("/");

		// Extrai a parte numÃ©rica do CIDR (ex: "24") e converte para inteiro
		// O replaceAll remove tudo que nÃ£o for dÃ­gito
		int cirPartesInt = Integer.parseInt(partes1[1].replaceAll("[^\\d]", ""));

		// Por fim cidr vai receber esse nÃºmero
		cidr = cirPartesInt;
	}

	// Classificar o IP com base no valor do primeiro octecto utilizando de if e
	// else if
	public String classificarIp(int primeiroOcteto) {
		if (primeiroOcteto >= 1 && primeiroOcteto <= 126) {
			classeIp = "A";
		} else if (primeiroOcteto >= 127 && primeiroOcteto <= 191) {
			classeIp = "B";
		} else if (primeiroOcteto >= 192 && primeiroOcteto <= 223) {
			classeIp = "C";
		} else if (primeiroOcteto >= 224 && primeiroOcteto <= 239) {
			classeIp = "D";
		} else {
			System.out.println("Invalido");

		}
		return classeIp;

	}

	// CONVERTENDO PARA BINÃ�RIO
	public void converterBinario(int cidr) {
		StringBuilder binario = new StringBuilder();
		// StringBuilder Ã© usado por diversas a razÃµes, por conta de ser
		// mutavÃ©l(conseguir modifica-la de diversas formas)
		// acaba sendo melhor de ser usada nessa situaÃ§Ã£o.
		for (int i = 0; i < 32; i++) {
			// For Ã© usado quando vocÃª sabe exatamente a quantidade de vezes que um loop
			// deve ocorrer. O loop do for nÃ£o depende de condiÃ§Ãµes externas para acontecer
			// que nem o while
			// No caso o loop nessa situaÃ§Ã£o deve ocorrer enquanto i for menor 32, pois 32
			// que Ã© a
			// quantidade de bits no ipv4.

			// Criando uma condiÃ§Ã£o que enquanto i for menor que o cidr o seu valor serÃ¡
			// substituido por 1
			if (i < cidr)

				// append estÃ¡ susbstituindo o valor por 1
				binario.append('1');

			else
				// Ã‰ o restante serÃ¡ substituido por 0
				binario.append('0');

			// No if estÃ£o ocorrendo duas verificaÃ§Ãµes em relaÃ§Ã£o ao valor i
			// % 8 == 0 garantir que i seja dividido por 8 que o resto dessa divisÃ£o deverÃ¡
			// ser 0
			// ((i + 1) garantir i seram um mÃºtiplo de 8, primeiro octeto inicia 0 atÃ© 7,
			// pegamos 7+1 Ã© que serÃ¡ divido por 8.
			// Esse processo vai ocorrer com od demais octeto
			// FormataÃ§Ã£o do binario para a cada grupo de 8 bits adicionar um ponto
			// i<31 Ã© para que no final nÃ£o seja imprimido um ponto
			if ((i + 1) % 8 == 0 && i < 31) {
				// ApÃ³s essa verificaÃ§Ã£o for verdadeira o ponto serÃ¡ inserido
				binario.append('.');

			}
			// Convertento a variavel binÃ¡rio para uma String
			mascaraBinario = binario.toString();

		}

	}

	// CONVERTER MÃ�SCARA DECIMAL
//	
	public void converterCidrDecimal(String binario) {
		StringBuilder decimal = new StringBuilder();

		// Removendo os pontos da mÃ¡scara binÃ¡ria
		String binarioPontos = mascaraBinario.replace(".", "");

		// um loop que percore os 4 octetos
		for (int i = 0; i < 4; i++) {
			
			// Criando uma variavel que vai receber a mascarÃ¡ binario sem a separaÃ§Ã£o por
			// pontos
			// substring mÃ©doto para extrair uma parte da String
			// No substring estÃ£o sendo passados os parametros de inicio e fim
			if (i == 0) {
				String octetoDecimal = binarioPontos.substring(0, 8);

				// Objetivo desse trecho Ã© transformar octeto binario para decimal.
				// A base de um sistema numÃ©rico indica quantos dÃ­gitos diferentes ele pode
				// usar(usaremos a base do binario).
				// 2 Ã© base, pois em binÃ¡rio Ã© usado apenas os nÃºmeros 0 e 1.
				// No caso o nÃºmero extraido pelo octetoDecimal estÃ¡ sendo convertido para
				// decimal,pela base 2(binario).
				// Integer.parseInt converte uma String para int(nÃºmero inteiro) ou Converte uma
				// string de qualquer base para decimal, que nesse caso a base Ã© 2
				decimal.append(Integer.parseInt(octetoDecimal, 2));
			} else if (i == 1) {
				String octetoDecimal = binarioPontos.substring(8, 16);
				decimal.append(Integer.parseInt(octetoDecimal, 2));
			}
			if (i == 2) {
				String octetoDecimal = binarioPontos.substring(16, 24);
				decimal.append(Integer.parseInt(octetoDecimal, 2));
			}
			if (i == 3) {
				String octetoDecimal = binarioPontos.substring(24, 32);
				decimal.append(Integer.parseInt(octetoDecimal, 2));
			}

			// Adicionar ponto 3 vezes
			if (i < 3)

				decimal.append(".");
		}

		mascaraDecimal = decimal.toString();
	}

	public void IpHost(int cidr) {
		// Calculo para ips disponiveis para host
		ipDisponiveis = Math.pow(2, 32 - cidr) - 2;
	}



	public void PrimeiroIp() {
		
	}
	


//	public String mostrarDados() {

//		SeparaIPCidr(ip);
//		converterBinario(cidr);
////		converterCidrDecimal(mascaraBinario);
//		SeparadorDeIPClasse();
//		classificarIp();
//		IpHost(cidr);
//		System.out.println("===============================================");
//		System.out.println("Seu ip: " + ip);
//		System.out.println("Sua classe de ip:" + ipClass);
//		System.out.println("Sua mÃ¡scara padrÃ£o:" + mascaraDecimal);
//		System.out.println("A mÃ¡scara no padrÃ£o binario:" + mascaraBinario);
//		System.out.println("IPs disponÃ­veis: " + df.format(ipDisponiveis));
//		System.out.println("================================================");
//		return ip;
	

}
