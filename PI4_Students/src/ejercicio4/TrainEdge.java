package ejercicio4;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record TrainEdge(TrainVertex source, TrainVertex target, Integer action, Double weight) implements SimpleEdgeAction<TrainVertex, Integer> {
	
	public static TrainEdge of(TrainVertex source, TrainVertex target, Integer action) {
		
		Estacion ultima = DatosTren.getStation(source.path().getLast());
		Estacion nueva = DatosTren.getStation(action);
		Tramo tramo = DatosTren.getGraph().getEdge(ultima, nueva);
		return new TrainEdge(source, target, action, tramo.tiempo());
	}
	
	
	

}
