package ejercicio4;

import java.util.ArrayList;
import java.util.List;

public class TrainState {
	private TrainVertexI vertex;
	private Double acumOF;
	private List<Integer> actions;
	private List<TrainVertexI> vertices;
	
	private TrainState(TrainVertexI vertex,Double acumOF,List<Integer> actions,List<TrainVertexI> vertices) {
		super();
		this.vertex = vertex;
		this.acumOF = acumOF;
		this.actions = actions;
		this.vertices = vertices;
	}
	
	public static TrainState of(TrainVertexI vertex) {
		List<TrainVertexI> vt = new ArrayList<>();
		vt.add(vertex);
		return new TrainState(vertex, 0.0, new ArrayList<>(), vt);
	}
	
	void foward(Integer a) {
		this.actions.add(a);
		TrainVertexI n = this.vertex.neighbor(a);
		this.vertices.add(n);
		this.acumOF = this.acumOF + DatosTren.getGraph().getEdge(DatosTren.getStation(this.vertex.path().getLast()), DatosTren.getStation(a)).tiempo();
		this.vertex = n;
	}
	
	void back(Integer a) {
		this.actions.remove(this.actions.size()-1);
		this.vertices.remove(this.vertices.size()-1);
		this.vertex = this.vertices.get(this.vertices.size()-1);
		this.acumOF = this.acumOF - DatosTren.getGraph().getEdge(DatosTren.getStation(this.vertex.path().getLast()), DatosTren.getStation(a)).tiempo();
	}
	
	public TrainVertexI vertex() {
		return vertex;
	}
	
	public Double acumOF() {
		return acumOF;
	}
	
	public List<Integer> actions(){
		return actions;
	}
}
