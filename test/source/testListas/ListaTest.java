package testListas;
import java.util.Iterator;

import comun.ClasePrueba;

import junit.framework.TestCase;
import exceptions.ElementoRepetidoException;
import listasCYP.ListaYPila;

public class ListaTest extends TestCase {

	private ListaYPila<ClasePrueba> lista= new ListaYPila<ClasePrueba>();

	public void setupEscenario1(){

		ClasePrueba lol=new ClasePrueba(0, "Adriana");
		meterPaLaLista(lol);
		ClasePrueba lol2= new ClasePrueba(111, "Bernardo");
		meterPaLaLista(lol2);
		ClasePrueba lol3= new ClasePrueba(47, "Camilo");
		meterPaLaLista(lol3);
		ClasePrueba lol4= new ClasePrueba(23, "Diana");
		meterPaLaLista(lol4);
		ClasePrueba lol5= new ClasePrueba(14, "Elena");
		meterPaLaLista(lol5);
	}
	private void meterPaLaLista(Object elemnt)
	{
		try {
			lista.agregar((ClasePrueba) elemnt);
		} 
		catch (ElementoRepetidoException e) 
		{

		}
	}

	public void testMetodosVaciado(){
		assertEquals(true, lista.esVacia());
		assertEquals(0,lista.darCantidadDeElementos());
		setupEscenario1();
		assertEquals(5,lista.darCantidadDeElementos());
		assertEquals(false, lista.esVacia());
		lista.vaciar();
		assertEquals(0,lista.darCantidadDeElementos());
		assertEquals(true, lista.esVacia());
	}

	public void testAgregar()
	{
		setupEscenario1();
		assertEquals(5, lista.darCantidadDeElementos());
		Object[] versionArreglo=  lista.darEnArreglo();
		for (int i = 0; i < versionArreglo.length; i++) {
			ClasePrueba xxx = (ClasePrueba) versionArreglo[i];
			if(i==0)
			{
				assertEquals("Adriana", xxx.getText());
			}
			else if(i==1)
			{
				assertEquals("Bernardo", xxx.getText());
			}
			else if(i==2)
			{
				assertEquals("Camilo", xxx.getText());
			}
			else if(i==3)
			{
				assertEquals("Diana", xxx.getText());
			}
			else if(i==4)
			{
				assertEquals("Elena", xxx.getText());
			}
		}
	}
	public void testEliminar()
	{
		setupEscenario1();
		ClasePrueba t= new ClasePrueba(23, null);
		assertNotNull("no elimino", lista.eliminar(t));
		assertEquals(4, lista.darCantidadDeElementos());
		Object[] versionArreglo=  lista.darEnArreglo();
		for (int i = 0; i < versionArreglo.length; i++) {
			ClasePrueba xxx=  (ClasePrueba) versionArreglo[i];
			if(i==0)
			{
				assertEquals("Adriana", xxx.getText());
			}
			else if(i==1)
			{
				assertEquals("Bernardo", xxx.getText());
			}
			else if(i==2)
			{
				assertEquals("Camilo", xxx.getText());
			}
			else if(i==3)
			{
				assertEquals("Elena", xxx.getText());
			}
		}
		t= new ClasePrueba(14, null);
		assertNotNull("no elimino", lista.eliminar(t));
		assertEquals(3, lista.darCantidadDeElementos());
	}
	public void  testCopiaOrdenada()
	{
		setupEscenario1();
		Object[] acendente = lista.sort();
		for (int i = 0; i < acendente.length; i++) {
			ClasePrueba xxx=  (ClasePrueba) acendente[i];
			if(i==0)
			{
				assertEquals("Adriana", xxx.getText());
			}
			else if(i==1)
			{
				assertEquals("Elena", xxx.getText());
			}
			else if(i==2)
			{
				assertEquals("Diana", xxx.getText());
			}
			else if(i==3)
			{
				assertEquals("Camilo", xxx.getText());
			}
			else if(i==4)
			{
				assertEquals("Bernardo", xxx.getText());
			}
		}
		Object[] decendente= lista.sort();
		int j=0;
		for (int i = decendente.length-1; i >= 0; i--) {
			ClasePrueba xxx=  (ClasePrueba) decendente[i];
			if(j==0)
			{
				assertEquals("Bernardo", xxx.getText());
			}
			else if(j==1)
			{
				assertEquals("Camilo", xxx.getText());
			}
			else if(j==2)
			{
				assertEquals("Diana", xxx.getText());
			}
			else if(j==3)
			{
				assertEquals("Elena", xxx.getText());
			}
			else if(j==4)
			{
				assertEquals("Adriana", xxx.getText());
			}
			j++;
		}
	}
	public void testEdicionElementos()
	{
		setupEscenario1();
		Iterator<ClasePrueba> iter= lista.iterator();
		while (iter.hasNext()) {
			ClasePrueba esta = (ClasePrueba) iter.next();
			if(esta.getText().equals("Camilo"))
			{
				esta.setText("Modificado");
			}
		}
		Object[] versionArreglo=  lista.darEnArreglo();
		for (int i = 0; i < versionArreglo.length; i++) {
			ClasePrueba xxx=  (ClasePrueba) versionArreglo[i];
			if(i==2)
			{
				assertEquals("Modificado", xxx.getText());
			}
		}
	}
}
