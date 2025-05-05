package ejercicio4;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SolucionEstaciones {

    public static SolucionEstaciones create(List<Integer> ls) {
    	return new SolucionEstaciones(ls);
    }
    
    private Integer numEstaciones;
    private List<Estacion> camino;
    private Double tiempoTotal;
    private Double tiempoMedio;

    private SolucionEstaciones(List<Integer> ls) {
    	numEstaciones = DatosTren.getNumStations();
    	camino = new LinkedList<Estacion>();
    	tiempoTotal = 0.0;

    	camino.add(DatosTren.getStation(0));
    	tiempoTotal += DatosTren.getAverageTimeSection(0, ls.get(0));
    	for(int i=0;i<ls.size();i++) {
    		
    		camino.add(DatosTren.getStation(ls.get(i)));
    		try {
    		tiempoTotal+= DatosTren.getAverageTimeSection( i-1, i);
    		} catch(Exception e) {
    			tiempoTotal+=0;
    		}
    		
    	}
    	camino.add(DatosTren.getStation(0));
    	tiempoTotal += DatosTren.getAverageTimeSection(ls.getLast(), 0);
    	// tiempoMedio = tiempoTotal / numEstaciones;

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Resumen del recorrido:\n");

        result.append("Camino seguido: ").append(camino.stream()
                .map(Estacion::nombre)
                .collect(Collectors.joining(" -> "))).append("\n");

        result.append(String.format("Tiempo total: %.2f min\n", tiempoTotal));
        // result.append(String.format("Tiempo medio por estación: %.2f min\n", tiempoMedio));

        return result.toString();
    }

    public Integer getNumEstaciones() {
        return numEstaciones;
    }

    public List<Estacion> getCamino() {
        return camino;
    }

    public Double getTiempoTotal() {
        return tiempoTotal;
    }

    public Double getTiempoMedio() {
        return tiempoMedio;
    }
}
