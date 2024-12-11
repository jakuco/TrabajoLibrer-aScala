package ctrl
import view.VentanaInsertarEntidad
import model.{Archivo, Biblioteca, Entidad, Entidades, Libro, Validar}

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.JFrame
import javax.swing._
import javax.swing.WindowConstants


class Ctrl_insertar_entidad extends ActionListener{
  var view = new VentanaInsertarEntidad
  view.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE)
  view.setVisible(true)
  view.btn_ingresar.addActionListener(this)
  view.btn_cancelar.addActionListener(this)


  var validar = new Validar
  val archivo = new Archivo

  override def actionPerformed(e: ActionEvent): Unit = {
    if (e.getSource == view.btn_ingresar) {
      insertar_entidad()
      println("Ingresate un libro")
    } else if (e.getSource == view.btn_cancelar) {
      view.setVisible(false)
    }
    limpiar_campos()
  }

  def insertar_entidad(): Unit ={
    var entidades = new Entidades

    if((validar.ruc(view.txt_CI_RUC.getText)||validar.cedula(view.txt_CI_RUC.getText))) {
      if(validar.telefono(view.txt_telefono.getText)) {
        if (archivo.recuperar("entidades") == null) {
          entidades.agregar_entidad(Entidad(view.txt_nombre.getText,
            view.txt_direccion.getText,
            view.txt_telefono.getText,
            view.txt_correo.getText,
            view.txt_CI_RUC.getText))
          archivo.guardar("entidades", entidades)
        } else {
          val entidades = archivo.recuperar("entidades").asInstanceOf[Entidades]
          entidades.agregar_entidad(Entidad(view.txt_nombre.getText,
            view.txt_direccion.getText,
            view.txt_telefono.getText,
            view.txt_correo.getText,
            view.txt_CI_RUC.getText))
          archivo.guardar("entidades", entidades)
        }
      } else {JOptionPane.showMessageDialog(null, "Error: teléfono mal ingresado")}
    } else{JOptionPane.showMessageDialog(null,"Error: CI o RUC mal ingresados.")}
  }

  def limpiar_campos(): Unit = {
    view.txt_nombre.setText("")
    view.txt_telefono.setText("")
    view.txt_correo.setText("")
    view.txt_direccion.setText("")
    view.txt_CI_RUC.setText("")
  }
}
