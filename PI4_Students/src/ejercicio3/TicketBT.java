package ejercicio3;

import java.util.Locale;

public class TicketBT {
	public static TicketBT of() {
		return new TicketBT();
	}
	
	private TicketState state;
	private SolucionFestival bestSol;
	private Double bestOF;

	
	private TicketBT() {
		super();
	}
	
	public void btm(TicketVertexI v1) {
		state = TicketState.of(v1);
		bestOF = TicketHeuristic.voraz(v1).getCosteTotal();
		bestSol = TicketHeuristic.voraz(v1);
		btm();
	}
	
	public void btm() {
		
		if(this.state.vertex().goal() && this.state.vertex().goalHasSolution()) {
			if(this.state.acumOF() < bestOF) {
				bestOF = this.state.acumOF();
				bestSol = SolucionFestival.create(this.state.actions());
			}
		} else {
			for(Integer a : this.state.vertex().actions()) {
				if(this.state.acumOF() + this.state.vertex().weight(a) + 
						TicketHeuristic.heuristic(this.state.vertex().neighbor(a), null, null) > bestOF) {
					continue;
				}
				state.foward(a);
				btm();
				state.back(a);
			}
		}
	}
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		for(int id_fichero = 1; id_fichero <= 3; id_fichero++) {
			DatosFestival.iniDatos("resources/ejercicio3/DatosEntrada"+id_fichero+".txt");
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
			TicketBT bt = TicketBT.of();
			bt.btm(TicketVertex.start());
			System.out.println(bt.bestSol);
			
		}

	}

}
