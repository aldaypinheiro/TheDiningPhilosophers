package br.edu.fjn.dinner.model;

public class Philosopher implements Runnable {

	private Integer code;
	private String name;
	private Status status;
	private Integer hungryCounter;
	private final Integer delay = 60000;

	public Philosopher(Integer code, String name) {
		this.code = code;
		this.name = name;
		this.status = Status.THINKING;
		this.hungryCounter = 1;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getHungryCounter() {
		return hungryCounter;
	}

	public void setHungryCounter(Integer hungryCounter) {
		this.hungryCounter = hungryCounter;
	}

	public void eat() {
		try {
			while (!this.tryCatchForks()) {
				this.getHungry();
			}
			catchForks();
			this.status = Status.EATING;
			synchronized (this) {
				Table.showStates();
			}
			Thread.sleep((int) (delay * Math.random()));
			this.dropForks();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void think() {
		try {
			this.status = Status.THINKING;
			synchronized (this) {
				Table.showStates();
			}
			Thread.sleep((int) (delay * Math.random()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void getHungry() {
		this.status = Status.HUNGRY;
		if (hungryCounter == 1) {
			synchronized (this) {
				Table.showStates();
			}
		}
		this.hungryCounter++;
	}

	public void catchForks() {
		if (Table.philosophers[leftNeighborIndex()].getStatus() != Status.EATING
				&& Table.philosophers[rightNeighborIndex()].getStatus() != Status.EATING) {
			Table.forks -= 2;
		} else {
			this.getHungry();
		}
	}

	public boolean tryCatchForks() {
		boolean result = false;
		if (Table.philosophers[leftNeighborIndex()].getStatus() != Status.EATING
				&& Table.philosophers[rightNeighborIndex()].getStatus() != Status.EATING) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	public void dropForks() {
		Table.forks += 2;
	}

	public Integer leftNeighborIndex() {
		return leftNeighborCode() - 1;
	}

	private Integer leftNeighborCode() {
		Integer result = code + 1;

		if (code == 5) {
			result = 1;
		}

		return result;
	}

	public Integer rightNeighborIndex() {
		return rightNeighborCode() - 1;
	}

	private Integer rightNeighborCode() {
		Integer result = code - 1;

		if (code == 1) {
			result = 5;
		}

		return result;
	}

	@Override
	public void run() {
		while (!Table.close) {
			if (this.status == Status.HUNGRY) {
				this.eat();
				this.think();
			} else {
				this.think();
				this.getHungry();
			}
		}
	}

}
