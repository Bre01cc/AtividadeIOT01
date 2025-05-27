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
	private String ipClass;

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

	// MÁSCARA DECIMAL

	public String getMascaraDecimal() {
		return mascaraDecimal;
	}

	public void setMascaraDecimal(String mascaraDecimal) {
		this.mascaraDecimal = mascaraDecimal;
	}

	// MÁSCARA BINARIO
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
		// Substitui vírgulas por pontos no IP, se necessário
		String ipSeparacao = ip.replace(',', '.');

		// Divide a string do IP em partes, usando o ponto como delimitador
		String[] partes = ipSeparacao.split("\\.");

		// Obtém a última parte do vetor, que contém o CIDR (ex: "0/24")
		String parteCidr = partes[3];

		// Divide a parte do CIDR em duas partes, separadas por "/"
		String[] partes1 = parteCidr.split("/");

		// Extrai a parte numérica do CIDR (ex: "24") e converte para inteiro
		// O replaceAll remove tudo que não for dígito
		int cirPartesInt = Integer.parseInt(partes1[1].replaceAll("[^\\d]", ""));

		// Por fim cidr vai receber esse número
		cidr = cirPartesInt;
	}

	// Classificar o IP com base no valor do primeiro octecto utilizando de if e
	// else if
	public String classificarIp() {
		if (primeiroOcteto >= 1 && primeiroOcteto <= 126) {
			ipClass = "A";
		} else if (primeiroOcteto >= 127 && primeiroOcteto <= 191) {
			ipClass = "B";
		} else if (primeiroOcteto >= 192 && primeiroOcteto <= 223) {
			ipClass = "C";
		} else if (primeiroOcteto >= 224 && primeiroOcteto <= 239) {
			ipClass = "D";
		} else {
			System.out.println("Invalido");

		}
		return ipClass;

	}

	// CONVERTENDO PARA BINÁRIO
	public void converterBinario(int cidr) {
		StringBuilder binario = new StringBuilder();
		// StringBuilder é usado por diversas a razões, por conta de ser
		// mutavél(conseguir modifica-la de diversas formas)
		// acaba sendo melhor de ser usada nessa situação.
		for (int i = 0; i < 32; i++) {
			// For é usado quando você sabe exatamente a quantidade de vezes que um loop
			// deve ocorrer. O loop do for não depende de condições externas para acontecer
			// que nem o while
			// No caso o loop nessa situação deve ocorrer enquanto i for menor 32, pois 32
			// que é a
			// quantidade de bits no ipv4.

			// Criando uma condição que enquanto i for menor que o cidr o seu valor será
			// substituido por 1
			if (i < cidr)

				// append está susbstituindo o valor por 1
				binario.append('1');

			else
				// É o restante será substituido por 0
				binario.append('0');

			// No if estão ocorrendo duas verificações em relação ao valor i
			// % 8 == 0 garantir que i seja dividido por 8 que o resto dessa divisão deverá
			// ser 0
			// ((i + 1) garantir i seram um mútiplo de 8, primeiro octeto inicia 0 até 7,
			// pegamos 7+1 é que será divido por 8.
			// Esse processo vai ocorrer com od demais octeto
			// Formatação do binario para a cada grupo de 8 bits adicionar um ponto
			// i<31 é para que no final não seja imprimido um ponto
			if ((i + 1) % 8 == 0 && i < 31) {
				// Após essa verificação for verdadeira o ponto será inserido
				binario.append('.');

			}
			// Convertento a variavel binário para uma String
			mascaraBinario = binario.toString();

		}

	}

	// CONVERTER MÁSCARA DECIMAL
//	
	public void converterCidrDecimal(String binario) {
		StringBuilder decimal = new StringBuilder();

		// Removendo os pontos da máscara binária
		String binarioPontos = mascaraBinario.replace(".", "");

		// um loop que percore os 4 octetos
		for (int i = 0; i < 4; i++) {
			
			// Criando uma variavel que vai receber a mascará binario sem a separação por
			// pontos
			// substring médoto para extrair uma parte da String
			// No substring estão sendo passados os parametros de inicio e fim
			if (i == 0) {
				String octetoDecimal = binarioPontos.substring(0, 8);

				// Objetivo desse trecho é transformar octeto binario para decimal.
				// A base de um sistema numérico indica quantos dígitos diferentes ele pode
				// usar(usaremos a base do binario).
				// 2 é base, pois em binário é usado apenas os números 0 e 1.
				// No caso o número extraido pelo octetoDecimal está sendo convertido para
				// decimal,pela base 2(binario).
				// Integer.parseInt converte uma String para int(número inteiro) ou Converte uma
				// string de qualquer base para decimal, que nesse caso a base é 2
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

	// Formatação para decimal criada para o ipDisponiveis
	DecimalFormat df = new DecimalFormat("#,###");


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
//		System.out.println("Sua máscara padrão:" + mascaraDecimal);
//		System.out.println("A máscara no padrão binario:" + mascaraBinario);
//		System.out.println("IPs disponíveis: " + df.format(ipDisponiveis));
//		System.out.println("================================================");
//		return ip;
	

}
