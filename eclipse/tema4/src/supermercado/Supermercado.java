package supermercado;

import java.util.Scanner;

/**
 * Clase que representa al supermercado, con todos los métodos necesarios 
 * para su gestión.
 * 
 * @author Jose Cabrera, Daniel Nevado
 *
 */

public class Supermercado {

	private Empleado[] empleados;
	private Caja[] cajas;
	private Productos[] productos;
	private ColaClientes[] colas;
	private double total;
	private int ultCola;
	private Scanner sc;

	/**
	 * Constructor: Inicializa el supermercado, cargando todos los datos de los prodcutos, cajeros, etc.
	 */
	
	public Supermercado() {
		this.iniciaSupermercado();
		this.total = 0;
		this.ultCola = 0;
		this.sc = new Scanner(System.in);
	}
	
	/**
	 * Devuelve los ingresos del supermercado hasta el momento 
	 * 
	 * @return Ingresos del supermercado.
	 */

	public double balance() {
		return this.total;
	}

	/**
	 * Método para colocar a un cliente en una cola, las colas son distribuidas igualitariamente.
	 * @param c Cliente a colocar en cola.
	 */
	
	public void clienteEnCola(Cliente c) {

		if (this.ultCola >= this.colas.length) {
			this.ultCola = 0;
		}
		this.colas[this.ultCola].add(c);
		this.ultCola++;
	}

	/**
	 * Método para realizar la compra de los productos que el cliente tenga en la cesta y colocar 
	 * al cliente en una cola.
	 */
	
	public void compra() {
		Cliente c = new Cliente();
		int p = -2;

		while (p != -1) {
			p = this.menuCompra();
			if (p != -1) {
				c.compra(this.productos[p]);
			}
		}

		this.clienteEnCola(c);

	}
	
	/**
	 * Método que cobra a todos los clientes que esten en la primera posición de una cola.
	 */

	public void despacha() {
		Cliente c;

		for (ColaClientes cola : this.colas) {
			if (!cola.isEmpty()) {

				c = cola.get();
				this.total += c.vaciaCesta();

			}
		}
		
		System.out.println("=========================================\n|El supermercado ha ingresado " + balance() + "€ |\n"
				+ 			"=========================================\n\n");
	}

	public Productos getProducto(int i) {
		Productos p = this.productos[i];

		return p;
	}

	/**
	 * Método que muestra en pantalla las colas y los clientes en ellas.
	 */
	
	public void verColas() {
		for (int i = 0; i < this.colas.length; i++) {
			System.out.println("En la cola " + (i + 1) + ", Atendida por " + colas[i].getEmpleado() + " en la "
					+ colas[i].getCaja() + "\n_________________________________________________________\n\n"
					+ colas[i].toString());
		}
	}
	
	/**
	 * Método que muestra los productos en stock del supermercado
	 */

	public void verProductos() {

		System.out.println(
				">-------------------Productos en stock-------------------<\n");
		for (int i = 0; i < this.productos.length; i++) {
			System.out.println((i + 1) + ". " + this.productos[i]);
		}
		System.out.println("__________________________________________________________\n");

	}
	
	

	private int menuCompra() {
		int opc;

		System.out.println("\nElige el n�mero del producto para comprarlo (pulsa 0 para salir):\n");
		this.verProductos();
		System.out.println();
		opc = sc.nextInt() - 1;

		return opc;
	}
	
	/**
	 * Menu principal, para la gestión del supermercado.
	 */

	public void menuPrincipal() {
		int opc;

		do {
			System.out.println("Bienvenido al gestor de supermercados\n-------------------------------------"
					+ "\nPor favor elige una opción:\n\n" + "1. Comprar\n" + "2. Despachar\n" + "0. Salir\n");

			opc = sc.nextInt();

			switch(opc) {
			case 1:
				this.compra();
				break;
				
			case 2: 
				this.verColas();
				this.despacha();
				break;
			}
		} while (opc != 0);
		
		

	}
	
	/**
	 * Método que inicializa todos los componentes del supermercado, es llamado en el constructor.
	 */

	private void iniciaSupermercado() {
		this.empleados = new Empleado[] { new Empleado(1, "Jorge"), new Empleado(2, "Juan"),
				new Empleado(3, "Alberto") };

		this.cajas = new Caja[] { new Caja(1), new Caja(2), new Caja(3) };

		this.productos = new Productos[] { 
				new Productos(1, Categoria.DROGUERIA, 3.10, "Shampoo", "Pantene"),
				new Productos(3, Categoria.DROGUERIA, 1.90, "Jabon", "Heno de Pravia"),
				new Productos(1, Categoria.PANADERIA, 0.40, "Pan", "Barra de pan"),
				new Productos(1, Categoria.GOLOSINAS, 2.90, "Bolsa de caramelos", "Caramelos"),
				new Productos(2, Categoria.PESCADERIA, 1.25, "Pack de Atun", "Atun"),
				new Productos(1, Categoria.FRUTERIA, 0.30, "Manzana", "Manzana") };
		
		this.colas = new ColaClientes [this.empleados.length];
		
		for(int i = 0; i < this.empleados.length; i++) {
			this.colas[i] = new ColaClientes(this.empleados[i], this.cajas[i]);
		}

		/*
		 * this.colas = new ColaClientes[] { new ColaClientes(this.empleados[0],
		 * this.cajas[0]), new ColaClientes(this.empleados[1], this.cajas[1]), new
		 * ColaClientes(this.empleados[2], this.cajas[2]) };
		 */
	}

	
}
