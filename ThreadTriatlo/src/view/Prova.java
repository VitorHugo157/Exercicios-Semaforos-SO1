package view;

import java.util.concurrent.Semaphore;

import controller.ThreadTriatlo;

public class Prova {

	public static void main(String[] args) {
		int permissoes = 5;
		Semaphore semaforo = new Semaphore(permissoes);
		for(int i = 1; i < 26; i++) {
			Thread atleta = new ThreadTriatlo(i, semaforo, 0);
			atleta.start();
		}
	}

}
