package tablaGrafo;

import java.io.Serializable;

import exceptions.ElementoRepetidoException;
import grafo.IVertice;

public interface ItablaHashG<K,V extends IVertice<T>, T> extends Serializable,Iterable<V>{
	
	public void put(K llave, V elem) throws ElementoRepetidoException;
	public V get (K llave);
	public V eliminar(K llave);
	public int darNumElementos();
}
