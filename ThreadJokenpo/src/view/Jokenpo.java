package view;

import java.util.concurrent.Semaphore;

import controller.ThreadJokenpo;

public class Jokenpo {

	public static void main(String[] args) {
		int acessos = 1;
		Semaphore semaforo = new Semaphore(acessos);
		String jogadorTimeA[] = {"Jubileu", "Juca", "Doca", "Tuca", "Smith"};
		String jogadorTimeB[] = {"Irineu", "Joao", "Texugo", "Fofao", "Genisvaldo"};
		for(int i = 0; i < 5; i++) {
			Thread duelos = new ThreadJokenpo(jogadorTimeA[i], jogadorTimeB[i], i, semaforo);
			duelos.start();
			System.out.println("\n                   Duelo " +(i + 1));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
