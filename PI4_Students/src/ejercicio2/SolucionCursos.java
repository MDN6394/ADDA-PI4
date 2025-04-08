package ejercicio2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SolucionCursos {

    public static SolucionCursos create(List<Integer> ls) {
        return new SolucionCursos(ls);
    }

    private Integer numCursos;
    private Map<Integer, Integer> solucion;
    private Double puntuacionTotal;
    private Integer costeTotal;

    private SolucionCursos(List<Integer> ls) {
        //TODO
    	 numCursos = 0;
         solucion = new HashMap<Integer, Integer>(); 
         puntuacionTotal = 0.0; 
         costeTotal = 0; 
         for(int i=0; i<ls.size();i++) {
      	   
      	   if(ls.get(i)==1) {
      		   numCursos+=1;
      		   solucion.put(DatosCursos.getCurso(i).id(), 1);
      		   costeTotal+= DatosCursos.getCoste(i);
      		   puntuacionTotal+=DatosCursos.getRelevancia(i);
      		   
      	   } 
      	   
         }
    
    }

    @Override
    public String toString() {
        return solucion.entrySet().stream()
                .map(p -> "Curso " + p.getKey() + ": Seleccionado")
                .collect(Collectors.joining("\n", "Cursos seleccionados:\n", String.format("\nTotal de cursos seleccionados: %d\nPuntuación total: %.2f\nCoste total: %d", numCursos, puntuacionTotal, costeTotal)));
    }

    public Integer getNumCursos() {
        return numCursos;
    }

    public Map<Integer, Integer> getSolucion() {
        return solucion;
    }

    public Double getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public Integer getCosteTotal() {
        return costeTotal;
    }
}
