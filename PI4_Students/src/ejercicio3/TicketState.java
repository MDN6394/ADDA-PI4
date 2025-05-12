package ejercicio3;

import java.util.ArrayList;
import java.util.List;

public class TicketState {
	
	private TicketVertexI vertex;
	private Double acumOF;
	private List<Integer> actions;
	private List<TicketVertexI> vertices;
	
	private TicketState(TicketVertexI vertex, Double acumOF, List<Integer> actions, List<TicketVertexI> vertices) {
		super();
		this.vertex = vertex;
		this.acumOF = acumOF;
		this.actions = actions;
		this.vertices = vertices;
	
	}
	
	public static TicketState of(TicketVertexI vertex) {
		List<TicketVertexI> vt = new ArrayList<>();
		vt.add(vertex);
		return new TicketState(vertex, 0.0, new ArrayList<>(), vt);
	}
	
	 void foward(Integer a) {
		this.actions.add(a);
		TicketVertexI neighbor = this.vertex.neighbor(a);
		this.vertices.add(neighbor);
		Integer i = this.vertex.index()/DatosFestival.getNumAreas();
		Integer jPrima = this.vertex.index() % DatosFestival.getNumAreas();
		Integer j = DatosFestival.indOrd(i).get(jPrima);
		this.acumOF = this.acumOF + a*DatosFestival.getCosteAsignacion(i,j);
		this.vertex = neighbor;
	}
	
	 void back(Integer a) {
		 this.actions.remove(this.actions.size()-1);
		 this.vertices.remove(this.vertices.size()-1);
		 this.vertex = this.vertices.get(this.vertices.size()-1);
		 Integer i = this.vertex.index()/DatosFestival.getNumAreas();
		 Integer jPrima = this.vertex.index() % DatosFestival.getNumAreas();
		 Integer j = DatosFestival.indOrd(i).get(jPrima);
		 this.acumOF = this.acumOF - a*DatosFestival.getCosteAsignacion(i,j);
	 }
	 
	 public TicketVertexI vertex() {
		 return vertex;
	 }
	 
	 public Double acumOF() {
		 return acumOF;
	 }
	 
	 public List<Integer> actions(){
		 return actions;
	 }

}
