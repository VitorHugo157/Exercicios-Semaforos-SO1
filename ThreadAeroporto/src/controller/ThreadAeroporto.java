package controller;

import java.util.concurrent.Semaphore;

public class ThreadAeroporto extends Thread{
	private int aviao;
	private Semaphore semaforo;
	private static int pistaSelecionada = 0;
	
	public ThreadAeroporto(int aviao, Semaphore semaforo) {
		this.aviao = aviao;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		try {
			semaforo.acquire();
				selecionarPista();
				pista();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}
	
	private synchronized void selecionarPista() {
		if(pistaSelecionada % 2 == 0) {
			System.out.println("A aeronave #" +aviao +" entrou na pista Norte.");
		}else {
			System.out.println("A aeronave #" +aviao +" entrou na pista Sul.");
		}
		pistaSelecionada++;
	}
	
	private void pista() {
		manobrar();
		taxiar();
		decolagem();
		afastamentoArea();
	}
	
	private void manobrar() {
		System.out.println("A aeronave #" +aviao +" está manobrando.");
		int tempo = (int)((Math.random() * 7001) + 3000);
		try {
			Thread.sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("A aeronave #" +aviao +" manobrou.");
	}
	
	private void taxiar() {
		System.out.println("A aeronave #" +aviao +" está taxiando.");
		int tempo = (int)((Math.random() * 10001) + 5000);
		try {
			Thread.sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("A aeronave #" +aviao +" taxiou.");
	}
	
	private void decolagem() {
		System.out.println("A aeronave #" +aviao +" vai decolar.");
		int tempo = (int)((Math.random() * 4001) + 1000);
		try {
			Thread.sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("A aeronave #" +aviao +" decolou.");
	}
	
	private void afastamentoArea() {
		System.out.println("A aeronave #" +aviao +" está se afastando da área.");
		int tempo = (int)((Math.random() * 8001) + 3000);
		try {
			Thread.sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("A aeronave #" +aviao +" se afastou da área.");
	}
}
