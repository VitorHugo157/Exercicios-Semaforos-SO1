package controller;

import java.util.concurrent.Semaphore;
import java.lang.Thread;

public class ThreadJokenpo extends Thread{
	private String jogadorTimeA;
	private String jogadorTimeB;
	private Semaphore semaforo;
	private String jogadaTimeA;
	private String jogadaTimeB;
	private static int pontosTimeA = 0;
	private static int pontosTimeB = 0;
	private int pontosA = 0;
	private int pontosB = 0;
	private int i;		// contador para verificar quando mostrar o resultado()
	private String jokenpo[] = {"Pedra", "Papel", "Tesoura"};
	
	public ThreadJokenpo(String jogadorTimeA, String jogadorTimeB, int i, Semaphore semaforo) {
		this.jogadorTimeA = jogadorTimeA;
		this.jogadorTimeB = jogadorTimeB;
		this.i = i;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		duelo();
	}

	private void escolherJokenpo() {
		jogadaTimeA = jokenpo[(int)(Math.random() * 3)];
		jogadaTimeB = jokenpo[(int)(Math.random() * 3)];
	}

	private void duelo() {
		while(pontosA < 3 || pontosB < 3) {	
			escolherJokenpo();
			if(jogadaTimeA == "Pedra" && jogadaTimeB == "Papel") {
				System.out.println("Jogador #" +jogadorTimeA +" Time A [" +jogadaTimeA +" x " +jogadaTimeB +"] jogador #" +jogadorTimeB +" Time B");
				pontosB++;
			}else if(jogadaTimeA == "Pedra" && jogadaTimeB == "Tesoura") {
				System.out.println("Jogador #" +jogadorTimeA +" Time A [" +jogadaTimeA +" x " +jogadaTimeB +"] jogador #" +jogadorTimeB +" Time B");
				pontosA++;
			}else if(jogadaTimeA == "Papel" && jogadaTimeB == "Pedra") {
				System.out.println("Jogador #" +jogadorTimeA +" Time A [" +jogadaTimeA +" x " +jogadaTimeB +"] jogador #" +jogadorTimeB +" Time B");
				pontosA++;
			}else if(jogadaTimeA == "Papel" && jogadaTimeB == "Tesoura") {
				System.out.println("Jogador #" +jogadorTimeA +" Time A [" +jogadaTimeA +" x " +jogadaTimeB +"] jogador #" +jogadorTimeB +" Time B");
				pontosB++;
			}else if(jogadaTimeA == "Tesoura" && jogadaTimeB == "Pedra") {
				System.out.println("Jogador #" +jogadorTimeA +" Time A [" +jogadaTimeA +" x " +jogadaTimeB +"] jogador #" +jogadorTimeB +" Time B");
				pontosB++;
			}else if(jogadaTimeA == "Tesoura" && jogadaTimeB == "Papel") {
				System.out.println("Jogador #" +jogadorTimeA +" Time A [" +jogadaTimeA +" x " +jogadaTimeB +"] jogador #" +jogadorTimeB +" Time B");
				pontosA++;
			}
			
			verificaPontos();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void verificaPontos() {
		if(pontosA == 3) {
			System.out.println("O jogador #" +jogadorTimeA +" do Time A" +" venceu o duelo contra o jogador #" +jogadorTimeB +" do Time B");
			pontosTimeA++;
			System.out.println("Time A fez " +pontosTimeA +" ponto(s)");
			if(i == 4) {
				try {
					semaforo.acquire();
					resultado();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Thread.currentThread().stop();
		}else if(pontosB == 3) {
			System.out.println("O jogador #" +jogadorTimeB +" do Time B" +" venceu o duelo contra o jogador #" +jogadorTimeA +" do Time A");
			pontosTimeB++;
			System.out.println("Time B fez " +pontosTimeB +" ponto(s)");
			if(i == 4) {
				try {
					semaforo.acquire();
					resultado();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Thread.currentThread().stop();
		}
	}
	
	private void resultado() {
		System.out.println("\n                   Resultado Final");
		if(pontosTimeA > pontosTimeB) {
			System.out.println("O time A ganhou a guerra com " +pontosTimeA +" pontos.");
			System.err.println("O time B perdeu a guerra com " +pontosTimeB +" pontos.");
		}else {
			System.out.println("O time B ganhou a guerra com " +pontosTimeB +" pontos.");
			System.err.println("O time A perdeu a guerra com " +pontosTimeA +" pontos.");
		}
	}
}
