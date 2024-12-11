package ctrl

import java.awt.event.{ActionEvent, ActionListener}
import view.Ventana_editar_eliminar_entidades
import model.{Archivo, Entidad, Entidades, Validar}

import javax.swing.{JOptionPane, WindowConstants}

class Ctrl_editar_eliminar_entidades extends ActionListener{
  var estado_eliminar=false:Boolean
  var estado_editar=false:Boolean

  var validar = new Validar

  var view = new Ventana_editar_eliminar_entidades

  view.btn_editar.addActionListener(this)
  view.btn_eliminar.addActionListener(this)
  view.btn_aceptar.addActionListener(this)
  view.btn_cancelar.addActionListener(this)
  view.btn_buscar.addActionListener(this)

  view.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE)
  override def actionPerformed(e: ActionEvent): Unit = {
    if(e.getSource==view.btn_editar){
      estado_editar=true
      view.btn_aceptar.setEnabled(true)
      view.btn_editar.setEnabled(false)
    } else if(e.getSource==view.btn_eliminar){
      estado_eliminar=true
      view.btn_aceptar.setEnabled(true)
      view.btn_eliminar.setEnabled(false)
    } else if(e.getSource==view.btn_aceptar){
      if(estado_eliminar){
        eliminar()
      }else if(estado_editar){
        editar()
      }
      limpiar_campos()
      habilitar_campos(false)
      habilitar_btn_acep_can(false)
      habilitar_btn_elim_edit(false)
      reiniciar_estados_edit_elim()
      view.btn_buscar.setEnabled(true)
      view.txt_CI_RUC.setEnabled(true)
    } else if(e.getSource==view.btn_buscar){
      buscar()

    } else if (e.getSource == view.btn_cancelar) {
      limpiar_campos()
      habilitar_campos(false)
      reiniciar_estados_edit_elim()
      habilitar_btn_elim_edit(false)
      habilitar_btn_acep_can(false)
      view.btn_cancelar.setEnabled(false)
      view.btn_buscar.setEnabled(true)
      view.txt_CI_RUC.setEnabled(true)
    }

  }

  def editar(): Unit ={
    val archivo = new Archivo
    val path = "entidades"
    var entidades = archivo.recuperar(path).asInstanceOf[Entidades]
    entidades.editar_entidad(view.txt_CI_RUC.getText,
      Entidad(view.txt_nombre.getText,
        view.txt_direccion.getText,
        view.txt_telefono.getText,
        view.txt_correo.getText,
        view.txt_CI_RUC.getText))

    archivo.guardar(path, entidades)

  }

  def eliminar():Unit= {
    val archivo = new Archivo
    val path = "entidades"
    var entidades = archivo.recuperar(path).asInstanceOf[Entidades]
    entidades.eliminar_entidad(view.txt_CI_RUC.getText)
    archivo.guardar(path, entidades)

  }

  def buscar(): Unit = {
    val archivo = new Archivo
    val path = "entidades"
    var entidades = archivo.recuperar(path).asInstanceOf[Entidades]
    archivo.guardar(path, entidades)
    val entidad = entidades.obtener_entidad(view.txt_CI_RUC.getText)
    if (entidad != null) {
      view.txt_CI_RUC.setText(entidad.codigo)
      view.txt_nombre.setText(entidad.nombre)
      view.txt_telefono.setText(entidad.telefono)
      view.txt_direccion.setText(entidad.direccion)
      view.txt_correo.setText(entidad.correo)

      habilitar_campos(true)
      reiniciar_estados_edit_elim()
      habilitar_btn_elim_edit(true)
      view.btn_cancelar.setEnabled(true)
      view.btn_buscar.setEnabled(false)
      view.txt_CI_RUC.setEnabled(false)
    } else {
      JOptionPane.showMessageDialog(null,"Error: Persona o empresa no ingresada.")
    }

  }

  def habilitar_btn_elim_edit(estado: Boolean): Unit = {
    view.btn_editar.setEnabled(estado)
    view.btn_eliminar.setEnabled(estado)
  }

  def habilitar_btn_acep_can(estado: Boolean): Unit = {
    view.btn_cancelar.setEnabled(estado)
    view.btn_aceptar.setEnabled(estado)
  }

  def reiniciar_estados_edit_elim(): Unit = {
    estado_editar = false
    estado_eliminar = false
  }

  def habilitar_campos(estado: Boolean): Unit = {
    println("Si se editan los campos")
    view.txt_nombre.setEditable(estado)
    view.txt_telefono.setEditable(estado)
    view.txt_direccion.setEditable(estado)
    view.txt_correo.setEditable(estado)
  }

  def limpiar_campos(): Unit = {
    view.txt_nombre.setText("")
    view.txt_telefono.setText("")
    view.txt_correo.setText("")
    view.txt_direccion.setText("")
    view.txt_CI_RUC.setText("")
  }
}
