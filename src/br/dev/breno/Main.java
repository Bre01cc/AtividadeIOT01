package br.dev.breno;

import java.text.DecimalFormat;

import br.dev.breno.gui.TelaIp;
import br.dev.breno.model.ClassificarEnderecoIP;

public class Main {

	public static void main(String[] args) {

//		int contador = 0;
//		for(int i =0; i<30; i++) {
//		contador+=1;
//
//		System.out.println(contador);
//		}
//		String texto = "me chamo breno oliveira";
//		String parteTest = texto.substring(10-1);
//		System.out.println(parteTest);

//		int numero= 1010;

		new TelaIp();
//		
//		String ip = "192.168.0.0/30";
//
//		String ipSeparacao = ip.replace(',', '.');
//		String[] partes = ipSeparacao.split("\\.");
//
//		// Obtém a última parte do vetor, que contém o CIDR (ex: "0/24")
//		String parteCidr = partes[3];
//
//		// Divide a parte do CIDR em duas partes, separadas por "/"
//		String[] partes1 = parteCidr.split("/");
//
//		String octetoEstatico = partes[0] + "." + partes[1] + "." + partes[2];
//		String octetoVar = partes1[0];
//		int octetoVariado = Integer.parseInt(octetoVar);
//		int subRede = 64;
//
//		int idDaRede = 0;
//		for (int i = 0; i < subRede; i++) {
//			idDaRede++;
//			int divisão = 256 / subRede;
//			if (i == 0) {
//				String redeIp = octetoEstatico + "." + "0";
//
//				// String.valueOf transforma numeros em string
//				String broadcast01 = String.valueOf(divisão - 1);
//				String broadcast = octetoEstatico + "." + broadcast01;
//				System.out.println("Id da Rede" + idDaRede);
//				System.out.println("ip de rede:" + redeIp);
//				System.out.println("ip de broadcast:" + broadcast);
//				System.out.println("===========================================================");
//			} else {
//				// Valor do brondcast vai ser definido ao multilicar valor da divisão(no caso
//				// seria 256 / pela quantidade de subredes)
//				// após multiplicar pegaria essse valor -1, assim obtendo o valor do brodcast
//				int valorBroadcast = divisão * idDaRede;
//				String broadcast = String.valueOf(valorBroadcast - 1);
//				String ipDeBroadcast = octetoEstatico + "." + broadcast;
//
//				// Ip de rede
//				// pegar o valor do loop para multiplicar com valor obtido através da divisão
//
//				int CalcularIpDaRede = i * divisão;
//				// octetoVariado variado tem valor 0 e somamos com o valorda multiplicação acima
//				int ipDeRede = octetoVariado + CalcularIpDaRede;
//				String stringIpDeRede = String.valueOf(ipDeRede);
//				String ipDeRedeCompleto = octetoEstatico + "." + ipDeRede;
//
//				// Primeiro e ultimo ip valido para host
//				int Intbroadcast = Integer.parseInt(broadcast);
//				// brondcast - 1 para saber o ultimo ip disponivel
//				String ultimoIpDisponivel = String.valueOf(Intbroadcast - 1);
//				// rede + 1 para saber o primeiro ip disponivel
//				String primeiroIpDisponivel = String.valueOf(ipDeRede + 1);
//
//				String ipOneHost = octetoEstatico + "." + primeiroIpDisponivel;
//				String ipLastHost = octetoEstatico + "." + ultimoIpDisponivel;
//
//				System.out.println("ID da Rede:" + idDaRede);
//				System.out.println("IP de rede:" + ipDeRedeCompleto);
//				System.out.println("Primeiro IP da rede:" + ipOneHost);
//				System.out.println("Ultimo Ip da rede:" + ipLastHost);
//				System.out.println("IP de broadcast:" + ipDeBroadcast);
//
//				System.out.println("===========================================================");
//			}
//
//		}

	}
}
