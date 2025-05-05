package ejercicio3;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record TicketEdge(TicketVertex source, TicketVertex target, Integer action, Double weight) implements SimpleEdgeAction<TicketVertex, Integer> {
	
	public static TicketEdge of(TicketVertex t1, TicketVertex t2, Integer action) {
		Integer i = t1.index()/DatosFestival.getNumAreas();
		Integer jPrima = t1.index()%DatosFestival.getNumAreas();
		Integer j = DatosFestival.indOrd(i).get(jPrima);
		return new TicketEdge(t1,t2,action, (double) action*DatosFestival.getCosteAsignacion(i, j));
	}
	

}
