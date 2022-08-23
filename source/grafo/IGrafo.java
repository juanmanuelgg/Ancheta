package grafo;

import java.io.Serializable;

import exceptions.ElementoRepetidoException;

public interface IGrafo<T, K> extends Serializable, Iterable<IVertice<T>>{
	
	public void agregar(K llave,T elemento)throws ElementoRepetidoException;
	
	
}
