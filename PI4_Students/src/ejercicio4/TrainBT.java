package ejercicio4;



public class TrainBT {
	public static TrainBT of() {
		return new TrainBT();
	}
	
	private TrainState state;
	private SolucionEstaciones bestSol;
	private Double bestOF;
	
	private TrainBT() {
		super();
	}
	
	public void btm(TrainVertexI v1) {
		state = TrainState.of(v1);
		bestOF = Double.MAX_VALUE;
		bestSol = null;
		btm();
	}
	
	private void btm() {
		if(this.state.vertex().goal() && this.state.vertex().goalHasSolution()) {
			if(this.state.acumOF() < bestOF) {
				bestOF = this.state.acumOF();
				bestSol = SolucionEstaciones.create(this.state.actions());
				
			}
		} else {
			for(Integer a : this.state.vertex().actions()) {
				if(this.state.acumOF() + this.state.vertex().weight(a) + TrainHeuristic.heuristic(this.state.vertex().neighbor(a), null, null) > bestOF) {
					continue;
				}
				state.foward(a);
				btm();
				state.back(a);
			}
		}
	}
	
	public static void main(String[] args) {
		for(int id_fichero = 1; id_fichero <= 3; id_fichero++) {
			DatosTren.iniDatos("resources/ejercicio4/DatosEntrada"+id_fichero+".txt");
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
			TrainBT bt = TrainBT.of();
			bt.btm(TrainVertex.start());
			System.out.println(bt.bestSol);
		
		
		}
	}
		

}
