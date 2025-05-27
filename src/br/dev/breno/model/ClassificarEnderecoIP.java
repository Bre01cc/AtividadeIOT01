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
	private String ipRede;
	private String ipOneHost;
	private String ipLastHost;
	private String ipBroadcast;

	public String getCp() {
		return classeIp;
	}

	// Getters e Setters
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	// Primeiro Octeto
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

	// Máscara Decimal
	public String getMascaraDecimal() {
		return mascaraDecimal;
	}

	public void setMascaraDecimal(String mascaraDecimal) {
		this.mascaraDecimal = mascaraDecimal;
	}

	// Máscara Binária
	public String getMascaraBinario() {
		return mascaraBinario;
	}

	public void setMascaraBinario(String mascaraBinario) {
		this.mascaraBinario = mascaraBinario;
	}

	// IPs Disponíveis
	public double getIpDisponiveis() {
		return ipDisponiveis;
	}

	public String getIpRede() {
		return ipRede;
	}

	public String getIpOneHost() {
		return ipOneHost;
	}

	public String getIpLastHost() {
		return ipLastHost;
	}

	public String getIpBroadcast() {
		return ipBroadcast;
	}

	// Métodos

	// Separar o IP
	public void SeparadorDeIPClasse(String ip) {

		// Pegar o IP e substituir as vírgulas por ponto
		String ipSeparacao = ip.replace(',', '.');

		// Vetor vai receber o IP e separar ele de acordo com os pontos
		// (No caso, o split vai quebrar a String de acordo com os parâmetros que
		// inserimos dentro dos parênteses)
		String[] partes = ipSeparacao.split("\\.");

		// Criar uma variável ipInt que vai receber o vetor posição 0 e transformar em
		// um int
		int ipInt = Integer.parseInt(partes[0]);

		// primeiroOcteto vai receber o ipInt
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

		// Por fim, cidr vai receber esse número
		cidr = cirPartesInt;
	}

	// Classificar o IP com base no valor do primeiro octeto utilizando if e else if
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
			System.out.println("Inválido");
		}
		return classeIp;
	}

	// Convertendo para Binário
	public void converterBinario(int cidr) {
		StringBuilder binario = new StringBuilder();
		// StringBuilder é usado por diversas razões, por conta de ser
		// mutável (consegue-se modificá-lo de diversas formas)
		// e acaba sendo melhor de ser usado nessa situação.
		for (int i = 0; i < 32; i++) {
			// For é usado quando você sabe exatamente a quantidade de vezes que um loop
			// deve ocorrer. O loop do for não depende de condições externas para acontecer
			// como o while.
			// No caso, o loop nessa situação deve ocorrer enquanto i for menor que 32,
			// que é a quantidade de bits no IPv4.

			// Criando uma condição em que, enquanto i for menor que o CIDR, seu valor será
			// substituído por 1
			if (i < cidr)
				// append está substituindo o valor por 1
				binario.append('1');
			else
				// E o restante será substituído por 0
				binario.append('0');

			// No if estão ocorrendo duas verificações em relação ao valor de i
			// % 8 == 0 garante que i seja divisível por 8 (resto 0)
			// ((i + 1) garante que seja o final de cada octeto (0-7, 8-15, etc.)
			// Esse processo vai ocorrer com os demais octetos
			// Formatação do binário: para cada grupo de 8 bits adicionar um ponto
			// i < 31 evita adicionar ponto ao final
			if ((i + 1) % 8 == 0 && i < 31) {
				// Após essa verificação ser verdadeira, o ponto será inserido
				binario.append('.');
			}
		}
		// Convertendo a variável binário para uma String
		mascaraBinario = binario.toString();
	}

	// Converter Máscara Decimal
	public void converterCidrDecimal(String binario) {
		StringBuilder decimal = new StringBuilder();

		// Removendo os pontos da máscara binária
		String binarioPontos = mascaraBinario.replace(".", "");

		// Um loop que percorre os 4 octetos
		for (int i = 0; i < 4; i++) {

			// Criando uma variável que vai receber a máscara binária sem a separação por
			// pontos
			// substring é método para extrair uma parte da String
			// No substring estão sendo passados os parâmetros de início e fim
			if (i == 0) {
				String octetoDecimal = binarioPontos.substring(0, 8);
				// Objetivo desse trecho é transformar octeto binário para decimal.
				// A base de um sistema numérico indica quantos dígitos diferentes ele pode
				// usar (usaremos a base do binário).
				// 2 é base, pois em binário é usado apenas os números 0 e 1.
				// No caso, o número extraído pelo octetoDecimal está sendo convertido para
				// decimal, pela base 2 (binário).
				// Integer.parseInt converte uma String para int (número inteiro) ou converte
				// uma
				// string de qualquer base para decimal, que nesse caso a base é 2
				decimal.append(Integer.parseInt(octetoDecimal, 2));
			} else if (i == 1) {
				String octetoDecimal = binarioPontos.substring(8, 16);
				decimal.append(Integer.parseInt(octetoDecimal, 2));
			} else if (i == 2) {
				String octetoDecimal = binarioPontos.substring(16, 24);
				decimal.append(Integer.parseInt(octetoDecimal, 2));
			} else if (i == 3) {
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
		// Cálculo para IPs disponíveis para hosts
		ipDisponiveis = Math.pow(2, 32 - cidr) - 2;
	}

	public void InfoIp(String ip) {
		String ipSeparacao = ip.replace(',', '.');
		String[] partes = ipSeparacao.split("\\.");

		ipRede = partes[0] + "." + partes[1] + "." + partes[2] + "." + "0";
		
		ipOneHost = partes[0] + "." + partes[1] + "." + partes[2] + "." + "1";
		
		ipLastHost = partes[0] + "." + partes[1] + "." + partes[2] + "." + "254";
		
		ipBroadcast = partes[0] + "." + partes[1] + "." + partes[2] + "." + "255";

	}

}
