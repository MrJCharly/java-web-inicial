package tp_02;

import tp_01.Agente;

public class ConsFinal extends Agente {
	protected String apellido;
	protected String nombre;
	
	public ConsFinal(int id, String apellido, String nombre) {
		super(id, null, null);
		this.apellido = apellido;
		this.nombre = nombre;
	}
	
	
}
