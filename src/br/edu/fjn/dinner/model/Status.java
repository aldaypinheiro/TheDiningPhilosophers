package br.edu.fjn.dinner.model;

public enum Status {
	
	THINKING {
		
		@Override
		public String toString() {
			return "Pensando";
		}
		
	}, EATING {
		
		@Override
		public String toString() {
			return "Comendo";
		}
		
	}, HUNGRY {
		
		@Override
		public String toString() {
			return "Faminto";
		}
		
	}

}
