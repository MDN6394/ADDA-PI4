package ejercicio3;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import us.lsi.common.List2;

public record TicketVertexI(Integer index, List<Integer> ticketType, List<Integer> ticketArea) implements TicketVertex {
	
	public static TicketVertexI of(Integer index, List<Integer> ticketType, List<Integer> ticketArea) {
		return new TicketVertexI(index, ticketType, ticketArea);
	}
	
	public Boolean goal() {
		return this.index == DatosFestival.getNumTiposEntrada()*DatosFestival.getNumAreas();
	}
	
	public Boolean goalHasSolution() {
		
		for(int k = 0; k < this.ticketType().size(); k++) {
			if(this.ticketType().get(k) < DatosFestival.getCuotaMinima(k)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<Integer> actions() {
		if(this.index >= DatosFestival.getNumTiposEntrada()*DatosFestival.getNumAreas()) {
			return List2.empty();
		}
		Integer i = this.index/DatosFestival.getNumAreas();
		Integer jPrima = this.index%DatosFestival.getNumAreas();
		Integer j = DatosFestival.indOrd(i).get(jPrima);
		if(this.index >= DatosFestival.getNumTiposEntrada()*DatosFestival.getNumAreas()-1) {
			if(!chekRestQuotasMeet()) {
				return List2.empty();
			}
			Integer left = DatosFestival.getCuotaMinima(i) - this.ticketType().get(i);
			if(left <= DatosFestival.getAforoMaximoArea(j) - this.ticketArea().get(j)) {
				return List2.of(left);
			} else {
				return List2.empty();
			}
		}
		if(this.ticketType().get(i) == DatosFestival.getCuotaMinima(i)) {
			return List2.of(0);
		} else if (this.ticketArea().get(j) >= DatosFestival.getAforoMaximoArea(j)) {
			return List2.of(0);
		}
		Integer minimumQuoteNeed = DatosFestival.getCuotaMinima(i) - this.ticketType().get(i);
		Integer spaceRemaining = DatosFestival.getAforoMaximoArea(j) - this.ticketArea().get(j);
		int end = minimumQuoteNeed;
		if(spaceRemaining < minimumQuoteNeed) {
			end = spaceRemaining;
		}
		
		
		return IntStream.rangeClosed(0, end).boxed().toList();	
	}

	private boolean chekRestQuotasMeet() {
		Integer i = this.index()/DatosFestival.getNumAreas();
		for(int k = 0; k < this.ticketType().size(); k++) {
			if(k == i) {continue;}
			if(this.ticketType().get(k) < DatosFestival.getCuotaMinima(k)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public TicketVertexI neighbor(Integer a) {
		Integer i = this.index/DatosFestival.getNumAreas();
		Integer jPrima = this.index%DatosFestival.getNumAreas();
		Integer j = DatosFestival.indOrd(i).get(jPrima);
		List<Integer> newTicketType = new ArrayList<>(ticketType);
		List<Integer> newTicketArea = new ArrayList<>(ticketArea);
		newTicketType.set(i, ticketType.get(i) + a);
		newTicketArea.set(j, ticketArea.get(j) + a);
		return TicketVertexI.of(this.index+1, newTicketType, newTicketArea);
	}

	@Override
	public TicketEdge edge(Integer a) {
		return TicketEdge.of(this, this.neighbor(a), a);
	}
	
	public Double weight(Integer a) {
		TicketEdge edge = edge(a);
		return edge.weight();
	}

}
