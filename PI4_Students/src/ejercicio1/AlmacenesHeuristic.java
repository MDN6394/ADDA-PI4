package ejercicio1;

import java.util.function.Predicate;
import java.util.stream.IntStream;



public class AlmacenesHeuristic {
	
	public static Double heuristic(AlmacenesVertex v1, Predicate<AlmacenesVertex> goal, AlmacenesVertex v2) {
		/*int possible = 0;
		for(int i = v1.index(); i < DatosAlmacenes.getNumProductos(); i++) {
			int volumeProd = DatosAlmacenes.getMetrosCubicosProducto(i);
			final int product = i;
			
			for(int j = 0; j < v1.remainSpace().size(); j++) {
				if(v1.remainSpace().get(j) >= volumeProd) {
					Boolean compatible = v1.storedProducts().get(j).stream().noneMatch(k->DatosAlmacenes.sonIncompatibles(k, product)
							|| DatosAlmacenes.sonIncompatibles(product, k));
					if(compatible) {
						possible++;
					}
				}
			}
		}
		return (double) possible;*/
		
		return IntStream.range(v1.index(), DatosAlmacenes.getNumProductos())
                .mapToDouble(i -> 1.0) 
                .sum();
		
	} 

}
