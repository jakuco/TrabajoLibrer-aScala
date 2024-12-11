package ctrl
import view.VentanaPrincipal

import java.awt.event.{ActionEvent, ActionListener, WindowAdapter, WindowEvent}
import model.{Archivo, Caja}

class Ctrl_principal extends ActionListener{
  var vista= new VentanaPrincipal
  var estado_caja=false
  var archivo = new Archivo

  //var ctr_facturar = new Ctrl_facturar
  //ctr_facturar.view.btn_facturar.addActionListener(this)

  if (archivo.recuperar("caja").asInstanceOf[Caja] == null) {

  } else {
    //vista.txt_caja.setText(archivo.recuperar("caja").asInstanceOf[Caja].caja.toString)
  }

  vista.j_facturar.addActionListener(this)
  vista.j_todas_facturas.addActionListener(this)
  vista.j_editar_libros.addActionListener(this)
  vista.j_eliminar_libros.addActionListener(this)
  vista.j_insertar_libros.addActionListener(this)
  vista.j_mostrar_libros.addActionListener(this)
  vista.j_editar_entidad.addActionListener(this)
  vista.j_eliminar_entidad.addActionListener(this)
  vista.j_insertar_entidad.addActionListener(this)
  vista.j_mostrar_entidad.addActionListener(this)

  vista.btn_caja.addActionListener(this)
  vista.setVisible(true)
  override def actionPerformed(e: ActionEvent): Unit = {
    if(e.getSource==vista.btn_caja){
      var ctr = new Ctrl_caja
      /*vista.txt_caja.setEditable(true)
      estado_caja=true
      vista.btn_ingresar_caja.setText("Aceptar")*/
      println("Le has dado click al botón de ingresar caja")
    } else if(e.getSource==vista.j_facturar){
      var ctrl = new Ctrl_facturar
      ctrl.view.setVisible(true)
    } else if(e.getSource==vista.j_todas_facturas){
      var ctrl=new Ctrl_mostrar_transacciones
    } else if(e.getSource==vista.j_insertar_libros){
      var ctrl=new Ctrl_insertar_libros
    } else if(e.getSource==vista.j_editar_libros){

      var ctrl= new Ctrl_editar_eliminar_libros
      ctrl.view.btn_eliminar.setVisible(false)
      ctrl.view.txt_cantidad_deseada.setVisible(false)
      ctrl.view.lbl_cant_deseada.setVisible(false)
      ctrl.view.setVisible(true)
    } else if(e.getSource==vista.j_eliminar_libros){
      var ctrl= new Ctrl_editar_eliminar_libros
      ctrl.view.btn_editar.setVisible(false)
      ctrl.view.txt_cantidad_deseada.setVisible(false)
      ctrl.view.lbl_cant_deseada.setVisible(false)
      ctrl.view.setVisible(true)
      
    } else if(e.getSource==vista.j_mostrar_libros){
      var ctrl=new Ctrl_mostrar_libros

    } else if(e.getSource==vista.j_insertar_entidad){
      var ctrl= new Ctrl_insertar_entidad

    } else if(e.getSource==vista.j_editar_entidad){
      var ctrl = new Ctrl_editar_eliminar_entidades
      ctrl.view.btn_eliminar.setVisible(false)
      ctrl.view.setVisible(true)

    } else if(e.getSource==vista.j_eliminar_entidad){
      var ctrl = new Ctrl_editar_eliminar_entidades
      ctrl.view.btn_editar.setVisible(false)
      ctrl.view.setVisible(true)

    } else if(e.getSource==vista.j_mostrar_entidad){
      var ctrl = new Ctrl_mostrar_entidades
      
    } else if(e.getSource==vista.btn_caja && estado_caja){
      estado_caja=false

    }
  }

}
