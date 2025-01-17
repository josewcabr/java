/**
 * Un circuito es un componente electrónico que implementa a un Chip distintas entradas
 * para hacer posible una conexión entre estos.
 * 
 * Implementaremos una clase Circuito que tenga los siguientes atributos y métodos:
 * 
 * 		ATRIBUTOS (deben ser privados):
 * 		
 * 		- Número de entradas al circuito.
 * 		- Valor del voltaje.
 * 		- Una lista que almacene los ids de los circuitos conectados.
 * 		- Un valor que indique si se ha conectado al menos un circuito al nuestro.
 * 
 *  
 *  	MÉTODOS (deben ser públicos):
 *  
 *  	- Método constructor que reciba: el modelo, las conexiones, las entradas y el voltaje.
 *  	- Método conectarOtro que reciba otro circuito y nos permita conectarlo al nuestro
 *  	  dicho método debe modificar el valor de si esta 
 *  	  conectado o no.
 *  	- Método addConexion que reciba un id del circuito y modifique la lista de circuitos conectados.
 *  	- Getters y Setters (solo los necesarios).
 * 
 * @author Jose Cabrera Rojas
 * 
 */

package Chips;

import java.util.Arrays;

public class Circuito extends Chip {

	private int entradas;
	private double voltaje;
	private String[] conexiones;
	private boolean conectado;

	public Circuito(String _modelo, int _entradas, double _voltaje) {
		super(_modelo, 10);
		this.entradas = _entradas;
		this.voltaje = _voltaje;
		this.conectado = false;
		this.conexiones = new String [10];
	}

	public void conectarOtro(Circuito _otroCircuito) {

		if (this.conexiones.length < this.entradas) {
			
			this.conexiones[0] = "hola";
			System.out.println(this.conexiones[0]);

			_otroCircuito.addConexion(this.getModelo());
			_otroCircuito.setConectado(true);

			this.conexiones[this.conexiones.length] = _otroCircuito.getModelo();
			this.conectado = true;

		}

	}

	public void addConexion(String _modelo) {

		this.conexiones[this.conexiones.length] = _modelo;

	}

	public int getEntradas() {
		return entradas;
	}

	public double getVoltaje() {
		return voltaje;
	}

	public boolean isConectado() {
		return conectado;
	}

	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}

	public void setConexiones(String[] conexiones) {
		this.conexiones = conexiones;
	}

	@Override
	public String toString() {
		return " El circuito modelo " + this.getModelo() + " tiene un volatje de entrada de " + voltaje + " V.";
	}
	
	

}
