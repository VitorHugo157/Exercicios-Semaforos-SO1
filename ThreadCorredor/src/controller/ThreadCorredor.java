package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadCorredor extends Thread{
	private String pessoa = "";
	private Semaphore semaforo;
	private Random r = new Random();
	
	public ThreadCorredor(String pessoa, Semaphore semaforo) {
		this.pessoa = pessoa;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		corredor();
		try {
			semaforo.acquire();
				porta();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void corredor() {
		int distanciaPercorrida = 0;
		int comprimentoCorredor = 200;
		System.out.println("O #" +pessoa +" está caminhando no corredor... \n");
		
		while(distanciaPercorrida < comprimentoCorredor) {
			distanciaPercorrida += r.nextInt(3) + 4;  //num aleatório entre 4 e 6 (inclusive)
			// System.out.println("O #" +pessoa +" já andou " +distanciaPercorrida +" mts.");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("O #" +pessoa +" chegou até a porta.\n");
	}

	private void porta() {
		System.err.println("O #" +pessoa +" está abrindo a porta...");
		int tempo = r.nextInt(1001) + 1000;  //num aleatório entre 1000 e 2000 (inclusive)
		
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("O #" +pessoa +" abriu e cruzou a porta. \n");
	}
}
