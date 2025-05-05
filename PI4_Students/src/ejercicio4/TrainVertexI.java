package ejercicio4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import us.lsi.common.List2;

public record TrainVertexI(Integer index, List<Integer> path, Integer acumCost) implements TrainVertex {
	
	public Boolean goal() {
		return this.index() == DatosTren.getNumStations();
	}
	
	public Boolean goalHasSolution() {
		return true;
	}

	@Override
	public List<Integer> actions() {
		List<Integer> result = new ArrayList<>();
		if(this.index() == DatosTren.getNumStations()) {
			return List2.empty();
		} else if (this.index() == DatosTren.getNumStations() - 1) {
			List<Integer> AllStations = IntStream.rangeClosed(0, DatosTren.getNumStations()).boxed().toList();
			List<Integer> remainStation = AllStations.stream().filter(s -> !this.path().contains(s)).toList();
			if(remainStation.size() > 1) {return List2.empty();}
			return remainStation;
		} else {
			for(int e = 0; e < DatosTren.getStations().size(); e++) {
				if(DatosTren.areConnected(this.path().getLast(), e)) {
					result.add(e);
				}

			}
		}
		return result;
	}

	@Override
	public TrainVertex neighbor(Integer a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TrainEdge edge(Integer a) {
		// TODO Auto-generated method stub
		return null;
	}

}
