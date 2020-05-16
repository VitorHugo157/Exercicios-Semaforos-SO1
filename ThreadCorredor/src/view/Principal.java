package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCorredor;

public class Principal {

	public static void main(String[] args) {
		int acessos = 1;
		Semaphore semaforo = new Semaphore(acessos);
		String nome[] = {"Vitor", "Ricardo", "Petter", "Mateus"};
		
		for(int i = 0; i < 4; i++) {
			Thread pessoa = new ThreadCorredor(nome[i], semaforo);
			pessoa.start();
		}
	}

}
