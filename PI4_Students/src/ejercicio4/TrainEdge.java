package ejercicio4;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record TrainEdge(TrainVertex source, TrainVertex target, Integer action, Double weight) implements SimpleEdgeAction<TrainVertex, Integer> {

}
