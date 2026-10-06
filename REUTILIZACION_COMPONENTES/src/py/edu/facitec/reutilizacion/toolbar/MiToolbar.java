package py.edu.facitec.reutilizacion.toolbar;


import javax.swing.JToolBar;
import py.edu.facitec.reutilizacion.botones.MiBoton;
import py.edu.facitec.reutilizacion.interfaces.AccionesABM;

public class MiToolbar extends JToolBar{
	private MiBoton mbtnNuevo;
	private MiBoton mbtnModificar;
	private MiBoton mbtnEliminar;
	private MiBoton mbtnGuardar;
	private MiBoton mbtnCancelar; 
	private MiBoton mbtnSalir;
	
	private AccionesABM acciones;
	
	public void setAcciones(AccionesABM acciones) {
		this.acciones = acciones;
	}
	
	public MiToolbar() {
		setFloatable(false);
		
		mbtnNuevo = new MiBoton();
		mbtnNuevo.setText("Nuevo");
		add(mbtnNuevo);
		
		mbtnModificar = new MiBoton();
		mbtnModificar.setText("Modificar");
		add(mbtnModificar);
		
		mbtnEliminar = new MiBoton();
		mbtnEliminar.setText("Eliminar");
		add(mbtnEliminar);
		
		mbtnGuardar = new MiBoton();
		mbtnGuardar.setText("Guardar");
		add(mbtnGuardar);
		
		mbtnCancelar = new MiBoton();
		mbtnCancelar.setText("Cancelar");
		add(mbtnCancelar);
		
		mbtnSalir = new MiBoton();
		mbtnSalir.setText("Salir");
		add(mbtnSalir);
		
		//llamar al metodo setUpEvents
		setUpEvents();
	}
	
		//agregamos valiacion de null antes de llamar a los metodos de AccionesABM
	private void setUpEvents() {
		mbtnNuevo.addActionListener(e -> {
			if (acciones != null) acciones.nuevo(); 
			});
		mbtnModificar.addActionListener(e -> {
			if (acciones != null)acciones.modificar();
			});
		mbtnEliminar.addActionListener(e -> {
			if (acciones != null)acciones.eliminar();
			});
		mbtnGuardar.addActionListener(e -> {
			if (acciones != null)acciones.guardar();
			});
		mbtnCancelar.addActionListener(e -> {
			if (acciones != null)acciones.cancelar();
			});
		mbtnSalir.addActionListener(e -> {
			if (acciones != null)acciones.salir();
			});
	}
	public void estadoInicial(boolean esInicial) {
		// botones habilitados 
		mbtnNuevo.setEnabled(esInicial);
		mbtnModificar.setEnabled(esInicial);
		mbtnEliminar.setEnabled(esInicial);
		mbtnSalir.setEnabled(esInicial);
		// botones deshabilitados 
		mbtnGuardar.setEnabled(!esInicial);
		mbtnCancelar.setEnabled(!esInicial);
	}

}
