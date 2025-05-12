package ejercicio3;

import java.util.ArrayList;
import java.util.List;
import us.lsi.graphs.virtual.VirtualVertex;

public interface TicketVertex extends VirtualVertex<TicketVertex, TicketEdge, Integer>{
	
	Integer index();
	List<Integer> ticketType();
	List<Integer> ticketArea();
	
	public static TicketVertexI start() {
		List<Integer> ticketType = new ArrayList<>();
		while(ticketType.size() < DatosFestival.getNumTiposEntrada()) {
			ticketType.add(0);
		}
		List<Integer> ticketArea = new ArrayList<>();
		while(ticketArea.size() < DatosFestival.getNumAreas()) {
			ticketArea.add(0);
		}
		return new TicketVertexI(0,ticketType,ticketArea);
	}

}
