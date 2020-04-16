package view;

import java.util.concurrent.Semaphore;

import controller.ThreadAeroporto;

public class Principal {

	public static void main(String[] args) {
		int permissoes = 2;
		Semaphore semaforo = new Semaphore(permissoes);
		for(int aviao = 0; aviao < 12; aviao++) {
			Thread aeronave = new ThreadAeroporto(aviao, semaforo);
			aeronave.start();
		}
	}
}
