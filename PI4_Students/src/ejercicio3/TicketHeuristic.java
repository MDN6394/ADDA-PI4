package ejercicio3;

import java.util.Comparator;
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

}
