package br.edu.fjn.dinner.model;




public class Table {

	static Boolean close = false;
	static Philosopher[] philosophers = new Philosopher[5];
	static Integer forks = 5;
	private static String prev = "";
	
	public Table() {

	}

	public Philosopher[] getPhilosophers() {
		return philosophers;
	}

	public void setPhilosophers(Philosopher[] philosophers) {
		Table.philosophers = philosophers;
	}

	public Integer getForks() {
		return forks;
	}

	public void setForks(Integer forks) {
		Table.forks = forks;
	}

	public void addPhilosopher(Philosopher philosopher) {
		Table.philosophers[philosopher.getCode()-1] = philosopher;
	}
	
	public static boolean isAnyoneHungry() {
		boolean result = false;
		
		for (int i = 0; i < Table.philosophers.length; i++) {
			if (Table.philosophers[i].getStatus() == Status.HUNGRY) {
				result = true;
			}
		}
		
		return result;
	}
	
	public synchronized static void showStates() {
		String out = "";
		
		for (int i = 0; i < 5; i++) {
			out += philosophers[i].getName() +" está "+ philosophers[i].getStatus()+"\n";
		}
		
		out += "--------------------------------------------------------------";
		
		
		if (!Table.prev.equals(out)) {
			System.out.println(out);
		}
		
		Table.prev = out;
	}
	
}