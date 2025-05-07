package br.dev.breno.model;

public class ClassificarEnderecoIP {

	// Atributos da Classe
	private String ip;
	private int primeiroOcteto;
	private int cidr;
	private String mascaraDecimal;
	private String mascaraBinario;
	private int ipDisponiveis;
	private String ipClass;

	// Gets e Sets

	// IP
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
	public int getIpDisponiveis() {
		return ipDisponiveis;
	}

	public void setIpDisponiveis(int ipDisponiveis) {
		this.ipDisponiveis = ipDisponiveis;
	}

	// METODOS

	// SEPARAR O IP
	public void SeparadorDeIPClasse() {

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

	public void SeparaIPCidr() {
		// mesmo processo de separar a String
		String ipSeparacao = ip.replace(',', '.');
		String[] partes = ipSeparacao.split("\\.");

		// Nessa etapa eu criei uma variavel String(partesCidr) ele vai associar a
		// ultima parte do vetor
		String parteCidr = partes[4];

		// Nessa criei um vetor que vai separar o CDIR em duas partes já que ele estará
		// assim(0/24).
		String[] partes1 = parteCidr.split("/");

		// Aqui vai pegar o segunda perte e converte ela em uma variavel int. ("[^\\d]",
		// "") Essa parte é para tudo que não for digito seja ignorado.
		// replaceAll é uma forma de substituir dentro do parentese por um padrão
		// definido
		int cirPartesInt = Integer.parseInt(partes1[1].replaceAll("[^\\d]", ""));

		// Por fim cidr vai receber esse número
		cidr = cirPartesInt;
	}

	// Classificar o IP com base no valor do primeiro octecto utilizando de if e
	// else if
	public void classificarIp() {
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

	}

	public void converterBinario() {
		String bin = Integer.toBinaryString(cidr);
		String formatacao =String.format("%32", bin).replace(' ', '0');
		String breakOcteto
		mascaraBinario = bin;
	}
	

		public void mostrarDados() {
			SeparaIPCidr();
			SeparadorDeIPClasse();
		    classificarIp();
		    converterBinario();
			System.out.println("===============================================");
			System.out.println("Seu ip: " + ip);
			System.out.println("Sua classe de ip:"+ ipClass);
			System.out.println("Sua máscara padrão:" + mascaraDecimal);
			System.out.println("A máscara no padrão binario:" + mascaraBinario);
			System.out.println();
			System.out.println("================================================");
		}
}

//	}
