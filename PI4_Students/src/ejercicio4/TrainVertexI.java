package ejercicio4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import us.lsi.common.List2;

public record TrainVertexI(Integer index, List<Integer> path, Double acumCost) implements TrainVertex {
	
	private static TrainVertexI of(int i, List<Integer> newPath, Double newAcumCost) {
		// TODO Auto-generated method stub
		return new TrainVertexI(i,newPath, newAcumCost);
	}
	
	public Boolean goal() {
		return this.index().equals(DatosTren.getNumStations());
	}
	
	public Boolean goalHasSolution() {
		List<Integer> allSations = IntStream.range(0, DatosTren.getNumStations()).boxed().toList();
		Integer firstStation = this.path().getFirst();
		Double c = 0.75*DatosTren.costAllSections();
		if(!DatosTren.areConnected(this.path().getLast(), firstStation)) {
			return false;
		} else if (this.acumCost > c) {
			return false;
		} else if (!twoConsecutiveStationSatifactionSeven(this.path())) {
			return false;
		} 
		
		/*if (!this.path().containsAll(allSations)) {
			return false;
		}*/
		return true;
	}

	private boolean twoConsecutiveStationSatifactionSeven(List<Integer> path2) {
		for(int i = 0; i < path2.size(); i++) {
			if(i < path2.size()-1) {
				Estacion current = DatosTren.getStation(i);
				Estacion next = DatosTren.getStation(i + 1);
				if(current.satisfaccionClientes() >= 7.0 && next.satisfaccionClientes() >= 7.0) {
					return true;
				} 
			} else {
				Estacion current = DatosTren.getStation(i);
				Estacion next = DatosTren.getStation(0);
				if(current.satisfaccionClientes() >= 7.0 && next.satisfaccionClientes() >= 7.0) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<Integer> actions() {
		List<Integer> result = new ArrayList<>();
		if(this.index() == DatosTren.getNumStations() - 1) {
			return List2.empty();
		} else if (this.index() == DatosTren.getNumStations() - 2) {
			Estacion first = DatosTren.getStation(this.path().getFirst());
			Estacion last = DatosTren.getStation(this.path().getLast());
			if(DatosTren.getGraph().containsEdge(last, first)) {
				return List2.of(this.path().getFirst());
			}
		} else {
			for(int e = 0; e < DatosTren.getStations().size(); e++) {
				if(DatosTren.areConnected(this.path().getLast(), e ) && !this.path().contains(e)) {
					result.add(e);
				}

			}
		}
		return result;
	}

	@Override
	public TrainVertex neighbor(Integer a) {
		List<Integer> newPath = new ArrayList<>(this.path());
		newPath.add(a);
		Double newAcumCost = acumCost + DatosTren.getGraph().getEdge(DatosTren.getStation(this.path().getLast()), DatosTren.getStation(a)).costeBillete();
		return TrainVertexI.of(this.index + 1, newPath, newAcumCost);
	}



	@Override
	public TrainEdge edge(Integer a) {
		return TrainEdge.of(this, neighbor(a), a);
	}

}
