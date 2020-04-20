package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadTriatlo extends Thread{
	private int atleta;
	private int corridaDistanciaPercorrida = 0;
	private static int corridaDistanciaTotal = 3000;
	private int ciclismoDistanciaPercorrida = 0;
	private static int ciclismoDistanciaTotal = 5000;
	private Semaphore semaforo;
	private static int rankingParcial = 0;
	private static int rankingFinal = 0;
	private int pontosTiroAoAlvo = 0;
	private int pontosTiroAoAlvoTotal;
	private static int pontosRanking = 250;
	private int qtdTiros = 1;
	private static int qtdMaxTiros = 3;
	private Random r = new Random();
	private int ranking[][] = new int [25][2];
	private static int x = 0;
	
	public ThreadTriatlo(int atleta, Semaphore semaforo, int pontosTiroAoAlvoTotal) {
		this.atleta = atleta;
		this.semaforo = semaforo;
		this.pontosTiroAoAlvoTotal = pontosTiroAoAlvoTotal;
	}
	
	public int getPontosTiroAoAlvoTotal() {
		return pontosTiroAoAlvoTotal;
	}

	public void setPontosTiroAoAlvoTotal(int pontosTiroAoAlvoTotal) {
		this.pontosTiroAoAlvoTotal = pontosTiroAoAlvoTotal;
	}

	@Override
	public void run() {
		corrida();
		try {
			semaforo.acquire();
				tiraAoAlvo();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		ciclismo();
		rankingParcial();
		mostrarRankingFinal();
	}

	private void corrida() {
		try {
			Thread.sleep(1);  // pra não pular nenhuma thread
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("O atleta #" +atleta +" iniciou a corrida.");
		int tempo = 30;
		while(corridaDistanciaPercorrida < corridaDistanciaTotal) {
			corridaDistanciaPercorrida += r.nextInt(6) + 20;  // número aleatório entre 20 e 25
			// System.out.println("O atleta #" +atleta +" percorreu " +corridaDistanciaPercorrida +" mts na corrida.");
			if(corridaDistanciaPercorrida >= corridaDistanciaTotal) {
				System.out.println("O atleta #" +atleta +" terminou a corrida e foi para o Tiro ao Alvo.");
			}
			try {
				Thread.sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void tiraAoAlvo() {
		while(qtdTiros <= qtdMaxTiros) {
			pontosTiroAoAlvo = r.nextInt(11);  // número aleatório entre 0 e 10
			System.out.println("O atleta #" +atleta +" efetuou o " +qtdTiros +"º tiro e ganhou " +pontosTiroAoAlvo +" pontos.");
			qtdTiros++;
			((ThreadTriatlo) currentThread()).setPontosTiroAoAlvoTotal(pontosTiroAoAlvoTotal += pontosTiroAoAlvo);
			int tempo = r.nextInt(2501) + 500;
			try {
				Thread.sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("O atleta #" +atleta +" terminou o tiro ao alvo e foi para o ciclismo.");
	}

	private void ciclismo() {
		int tempo = 40;
		while(ciclismoDistanciaPercorrida < ciclismoDistanciaTotal) {
			ciclismoDistanciaPercorrida += r.nextInt(11) + 30;
			// System.out.println("O atleta #" +atleta +" percorreu " +ciclismoDistanciaPercorrida +" mts no ciclismo.");
			if(ciclismoDistanciaPercorrida >= ciclismoDistanciaTotal) {
				System.out.println("O atleta #" +atleta +" terminou o ciclismo.");
			}
			try {
				Thread.sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized void rankingParcial() {
		rankingParcial++;
		for(int i = 0; i < 25; i++) {
			ranking[i][0] = atleta;
			ranking[i][1] = pontosRanking + ((ThreadTriatlo) currentThread()).getPontosTiroAoAlvoTotal();
		}
		System.out.println("O atleta #" +atleta +" terminou a prova de ciclismo em " +rankingParcial +"º lugar e ficou com " +pontosRanking +" pontos.");
		pontosRanking -= 10;
		
		if(rankingParcial == 25) {
			rankingFinal();
		}
	}
	
	private void rankingFinal() {
		for(int i = 0; i < 25; i++) {
			for(int j = (i + 1); j < 25; j++) {
				if(ranking[i][1] < ranking[j][1]) {
					int aux[][] = ranking;
					ranking[i][0] = ranking[j][0];
					ranking[i][1] = ranking[j][1];
					ranking[j][0] = aux[i][0];
					ranking[j][1] = aux[i][1];
				}
			}
		}
	}
	
	private void mostrarRankingFinal() {
		rankingFinal++;
		System.out.println("O atleta #" +ranking[x][0] +" terminou a prova de triatlo moderno em " +rankingFinal +"º lugar com " +ranking[x][1] +" pontos." );
		x++;
	}
}
