package br.edu.fjn.dinner.model;

import java.util.Scanner;

public class MainFrame {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Table table = new Table();

		Scanner input = new Scanner(System.in);
		String philosopher;
		
		
		System.out.println("Digite o nome nome do primeiro filósofo: ");
		philosopher = input.next();
		
		Philosopher f1 = new Philosopher(1, philosopher);
		
		System.out.println("Digite o nome nome do segundo filósofo: ");
		philosopher = input.next();
		
		Philosopher f2 = new Philosopher(2, philosopher);
		
		System.out.println("Digite o nome nome do terceiro filósofo: ");
		philosopher = input.next();
		
		Philosopher f3 = new Philosopher(3, philosopher);
		
		System.out.println("Digite o nome nome do quarto filósofo: ");
		philosopher = input.next();
		
		Philosopher f4 = new Philosopher(4, philosopher);
		
		System.out.println("Digite o nome nome do quinto filósofo: ");
		philosopher = input.next();
		
		Philosopher f5 = new Philosopher(5, philosopher);

		Thread t1 = new Thread(f1);
		Thread t2 = new Thread(f2);
		Thread t3 = new Thread(f3);
		Thread t4 = new Thread(f4);
		Thread t5 = new Thread(f5);

		table.addPhilosopher(f1);
		table.addPhilosopher(f2);
		table.addPhilosopher(f3);
		table.addPhilosopher(f4);
		table.addPhilosopher(f5);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}