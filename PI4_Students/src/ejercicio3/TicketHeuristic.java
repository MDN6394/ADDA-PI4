package ejercicio3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class TicketHeuristic {
	
	public static Double heuristic(TicketVertex t1, Predicate<TicketVertex> goal, TicketVertex t2) {
		Double result = 0.0;
		for(int k = 0; k < DatosFestival.getNumTiposEntrada(); k++) {
			Integer remain = DatosFestival.getCuotaMinima(k) - t1.ticketType().get(k);
			Integer min = DatosFestival.getTipoEntrada(k).costeAsignacion().values().stream().min(Comparator.comparing(Integer::valueOf)).get();
			result += remain*min;
		}
		
		return result;
	}
	
	public static SolucionFestival voraz(TicketVertexI v1) {
		List<Integer> actions = new ArrayList<>();
		TicketVertexI v = v1;
		while(v.index() < DatosFestival.getNumAreas()*DatosFestival.getNumTiposEntrada()-1) {
			Integer a;
			Integer i = v.index()/DatosFestival.getNumAreas();
			Integer jPrima = v.index()%DatosFestival.getNumAreas();
			Integer j = DatosFestival.indOrd(i).get(jPrima);
			Integer remaining = DatosFestival.getCuotaMinima(i) - v.ticketType().get(i);
			Integer remainingSpace = DatosFestival.getAforoMaximoArea(j) - v.ticketArea().get(j);
			if(remaining > remainingSpace) {
				a = remainingSpace;
			} else {
				a = remaining;
			}
			
			actions.add(a);
			v = v.neighbor(a);
		}
		return SolucionFestival.create(actions);
	}

}
