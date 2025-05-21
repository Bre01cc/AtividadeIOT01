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

	// CONVERTENDO PARA BINÁRIO
	public void converterBinario() {
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
	public void converterCidrDecimal() {
	    StringBuilder decimal = new StringBuilder();
	    
	    // Remover os pontos da máscara binária antes de processar
	    String binarioPontos = mascaraBinario.replace(".", "");

	    //um loop que percore os 4 octetos
	    for (int i = 0; i < 4; i++) {
	    	//pegando os octetos transformando int
	    	//significado literal e subcadeia de caracteres
	        String octetoDecimal = binarioPontos.substring(i * 8, (i + 1) * 8);
	        decimal.append(Integer.parseInt(octetoDecimal, 2));
	        
	        //
	        if (i < 3)
	            decimal.append(".");
	    }

	    mascaraDecimal = decimal.toString();
	}

//TESTE PARA EXIBIR NO TERMINAL
	public void mostrarDados() {
		SeparaIPCidr();
		converterBinario();
		converterCidrDecimal();
		SeparadorDeIPClasse();
		classificarIp();
		System.out.println("===============================================");
		System.out.println("Seu ip: " + ip);
		System.out.println("Sua classe de ip:" + ipClass);
		System.out.println("Sua máscara padrão:" + mascaraDecimal);
		System.out.println("A máscara no padrão binario:" + mascaraBinario);
		System.out.println("================================================");
	}
}
