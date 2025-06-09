package br.dev.breno.model;

import java.text.DecimalFormat;

import javax.swing.DefaultListModel;

public class ClassificarEnderecoIP {

	// Atributos da Classe
	private String ip;
	private int primeiroOcteto;
	private int cidr;
	private String mascaraDecimal;
	private String mascaraBinario;
	private double ipDisponiveis;
	private String classeIp;
	private int subRede;

	public String getClasseIP() {
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

	public int getIpRede() {
	    return subRede;
	}

	// Métodos

	// Método para separar o IP e extrair o primeiro octeto
	// Recebe o IP como String e substitui vírgulas por pontos para padronizar
	// Depois divide o IP em partes, usando o ponto como separador
	// O primeiro octeto é convertido para inteiro e armazenado para classificação futura
	public void SeparadorDeIPClasse(String ip) {
	    String ipSeparacao = ip.replace(',', '.');
	    String[] partes = ipSeparacao.split("\\.");
	    int ipInt = Integer.parseInt(partes[0]);
	    primeiroOcteto = ipInt;
	}

	// Método para extrair o valor do CIDR do IP
	// Substitui vírgulas por pontos e divide o IP em partes
	// Pega a última parte, que contém o último octeto e o CIDR, no formato "x/yy"
	// Divide essa parte para separar o último octeto do CIDR
	// Remove tudo que não for número no CIDR, e converte para inteiro
	// Armazena o valor do CIDR para uso futuro
	public void SeparaIPCidr(String ip) {
	    String ipSeparacao = ip.replace(',', '.');
	    String[] partes = ipSeparacao.split("\\.");
	    String parteCidr = partes[3];
	    String[] partes1 = parteCidr.split("/");
	    int cirPartesInt = Integer.parseInt(partes1[1].replaceAll("[^\\d]", ""));
	    cidr = cirPartesInt;
	}

	// Classifica o IP com base no primeiro octeto, usando estruturas condicionais
	// Define a classe do IP conforme a faixa em que o primeiro octeto se encontra
	// Caso o valor não se encaixe nas faixas esperadas, imprime "Inválido"
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

	// Converte o valor do CIDR para uma máscara binária no formato "11111111.11111111.11111111.00000000"
	// Usamos StringBuilder porque ele é eficiente para construir strings mutáveis
	// O loop percorre 32 bits (tamanho do IPv4) e adiciona '1' para os bits correspondentes ao CIDR
	// e '0' para os bits restantes
	// Para facilitar a leitura, um ponto '.' é inserido a cada 8 bits, separando os octetos
	public void converterBinario(int cidr) {
	    StringBuilder binario = new StringBuilder();

	    for (int i = 0; i < 32; i++) {
	        if (i < cidr)
	            binario.append('1');  // Para os primeiros 'cidr' bits, adiciona 1
	        else
	            binario.append('0');  // Para os bits restantes, adiciona 0

	        // A cada 8 bits (exceto no final), adiciona um ponto para separar os octetos
	        if ((i + 1) % 8 == 0 && i < 31) {
	            binario.append('.');
	        }
	    }

	    // Converte o StringBuilder para String e armazena na variável mascaraBinario
	    mascaraBinario = binario.toString();
	}

	// Converte a máscara binária em máscara decimal, para facilitar a visualização
	// Remove os pontos da máscara binária para formar uma string contínua de bits
	// Divide essa string em 4 octetos de 8 bits cada
	// Cada octeto binário é convertido para decimal usando Integer.parseInt com base 2
	// Os octetos decimais são concatenados em formato decimal com pontos entre eles
	// O resultado é armazenado em mascaraDecimal
	public void converterCidrDecimal(String binario) {
	    StringBuilder decimal = new StringBuilder();
	    String binarioPontos = mascaraBinario.replace(".", "");

	    for (int i = 0; i < 4; i++) {
	        String octetoDecimal = binarioPontos.substring(i * 8, (i + 1) * 8);
	        decimal.append(Integer.parseInt(octetoDecimal, 2));

	        if (i < 3)
	            decimal.append(".");
	    }

	    mascaraDecimal = decimal.toString();
	}

	// Calcula o número de IPs disponíveis para hosts com base no CIDR
	// Faz o cálculo 2^(32 - cidr) - 2 para excluir o endereço de rede e broadcast
	// O resultado é armazenado em ipDisponiveis
	public void IpHost(int cidr) {
	    ipDisponiveis = Math.pow(2, 32 - cidr) - 2;
	}

	// Calcula o número de subredes baseado na classe do IP e no CIDR
	// Para os valores padrão de CIDR 8, 16 e 24, subrede é 0, pois não há subdivisão
	// Caso contrário, calcula a quantidade de bits usados para subredes, 
	// subtraindo o padrão da classe (8,16,24) do CIDR e elevando 2 a essa potência
	// O resultado indica quantas subredes foram criadas e é armazenado em subRede
	public void calculoDeSubRede() {
	    if (cidr == 8 || cidr == 16 || cidr == 24) {
	        subRede = 0;
	    } else {
	        if (classeIp.equals("A")) {
	            int bits = cidr - 8;
	            subRede = (int) Math.pow(2, bits);
	        } else if (classeIp.equals("B")) {
	            int bits = cidr - 16;
	            subRede = (int) Math.pow(2, bits);
	        } else if (classeIp.equals("C")) {
	            int bits = cidr - 24;
	            subRede = (int) Math.pow(2, bits);
	        }
	    }
	}

	// Gera uma lista dinâmica de informações das subredes para exibir na interface gráfica
	// Usa DefaultListModel para armazenar strings que representam cada subrede
	// A string do IP é separada para extrair os octetos fixos e o último que será variado
	// Calcula o tamanho de cada subrede dividindo 256 pelo número de subredes
	// Para cada subrede, calcula o IP de rede, primeiro IP válido, último IP válido e broadcast
	// Adiciona essas informações no modelo que será exibido para o usuário
	public DefaultListModel<String> gerarListaSubRedes() {
	    DefaultListModel<String> dadosDaSubRede = new DefaultListModel<>();
	    String ipSeparacao = ip.replace(',', '.');
	    String[] partes = ipSeparacao.split("\\.");
	    String parteCidr = partes[3];
	    String[] partes1 = parteCidr.split("/");

	    String octetoEstatico = partes[0] + "." + partes[1] + "." + partes[2];
	    String octetoVar = partes1[0];
	    int octetoVariado = Integer.parseInt(octetoVar);
	    octetoVariado = 0;
	    int idDaRede = 0;

	    // Loop para criar informações para cada subrede
	    for (int i = 0; i < subRede; i++) {
	        idDaRede++;
	        int divisao = 256 / subRede;

	        // Calcula o valor do broadcast como múltiplo da divisão - 1
	        int valorBroadcast = divisao * idDaRede;
	        String broadcast = String.valueOf(valorBroadcast - 1);
	        String ipDeBroadcast = octetoEstatico + "." + broadcast;

	        // Calcula o IP de rede multiplicando o índice do loop pela divisão e somando ao octeto variável
	        int calcularIpDaRede = i * divisao;
	        int ipDeRede = octetoVariado + calcularIpDaRede;
	        String ipDeRedeCompleto = octetoEstatico + "." + ipDeRede;

	        // Calcula o primeiro IP válido e o último IP válido da subrede
	        int intBroadcast = Integer.parseInt(broadcast);
	        String ultimoIpDisponivel = String.valueOf(intBroadcast - 1);
	        String primeiroIpDisponivel = String.valueOf(ipDeRede + 1);
	        String ipOneHost = octetoEstatico + "." + primeiroIpDisponivel;
	        String ipLastHost = octetoEstatico + "." + ultimoIpDisponivel;

	        // Adiciona as informações da subrede no modelo para exibição
	        dadosDaSubRede.addElement("ID da Rede:" + idDaRede);
	        dadosDaSubRede.addElement("IP de rede: " + ipDeRedeCompleto);
	        dadosDaSubRede.addElement("Primeiro IP da rede: " + ipOneHost);
	        dadosDaSubRede.addElement("Último IP da rede: " + ipLastHost);
	        dadosDaSubRede.addElement("IP de broadcast: " + ipDeBroadcast);
	        dadosDaSubRede.addElement("==============================================");
	    }

	    return dadosDaSubRede;
	}
}