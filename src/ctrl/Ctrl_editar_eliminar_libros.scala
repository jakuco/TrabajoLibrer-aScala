package ctrl

import java.awt.event.{ActionEvent, ActionListener}
import view.Ventana_editar_eliminar_libros
import model.{Archivo, Biblioteca, Libro, Validar}

import javax.swing.{ImageIcon, JOptionPane, WindowConstants}

class Ctrl_editar_eliminar_libros extends ActionListener{
  var estado_eliminar = false
  var estado_editar = false
  var abastecimiento = false
  var ruta=""

  var view =new Ventana_editar_eliminar_libros

  var validar = new Validar

  view.btn_editar.addActionListener(this)
  view.btn_eliminar.addActionListener(this)
  view.btn_elegir_imagen.addActionListener(this)
  view.btn_aceptar.addActionListener(this)
  view.btn_cancelar.addActionListener(this)
  view.btn_buscar.addActionListener(this)

  view.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE)
  override def actionPerformed(e: ActionEvent): Unit = {
    if (e.getSource == view.btn_editar) {
      estado_editar = true
      view.btn_aceptar.setEnabled(true)
      habilitar_campos(true)
      view.btn_editar.setEnabled(false)
    } else if (e.getSource == view.btn_eliminar) {
      estado_eliminar = true
      view.btn_eliminar.setEnabled(false)
      view.btn_aceptar.setEnabled(true)
    } else if (e.getSource == view.btn_aceptar) {
      //println("le has dado en aceptar")
      if (estado_eliminar) {
        eliminar()
      } else if (estado_editar) {
        editar()
      }
      limpiar_campos()
      habilitar_campos(false)
      reiniciar_estados_edit_elim()
      habilitar_btn_acep_can(false)
      habilitar_btn_elim_edit(false)
      view.btn_buscar.setEnabled(true)
      view.txt_isbn.setEnabled(true)

    } else if (e.getSource == view.btn_buscar) {
      buscar()

    } else if (e.getSource == view.btn_cancelar){
      limpiar_campos()
      habilitar_campos(false)
      reiniciar_estados_edit_elim()
      habilitar_btn_elim_edit(false)
      habilitar_btn_acep_can(false)
      view.btn_cancelar.setEnabled(false)
      view.btn_buscar.setEnabled(true)
      view.txt_isbn.setEnabled(true)
    }
  }

  def editar(): Unit = {
    val archivo = new Archivo
    val path = "biblioteca"
    var biblioteca = archivo.recuperar(path).asInstanceOf[Biblioteca]
    biblioteca.editar_libro(view.txt_isbn.getText,
      Libro(view.txt_isbn.getText,
        view.txt_titulo.getText,
        ruta,
        view.txt_precio_compra.getText.toFloat,
        view.txt_precio_venta.getText.toFloat,
        view.txt_cantidad.getText.toInt))

    archivo.guardar(path, biblioteca)

  }

  def eliminar(): Unit = {
    val archivo = new Archivo
    val path = "biblioteca"
    var biblioteca = archivo.recuperar(path).asInstanceOf[Biblioteca]
    biblioteca.eliminar_libro(view.txt_isbn.getText)
    archivo.guardar(path, biblioteca)
  }

  def buscar():Unit={
    val archivo = new Archivo
    val path = "biblioteca"
    var biblioteca = archivo.recuperar(path).asInstanceOf[Biblioteca]
    archivo.guardar(path, biblioteca)
    val libro = biblioteca.obtener_libro(view.txt_isbn.getText)
    if(libro!=null){
      view.txt_isbn.setText(libro._isbn)
      view.txt_titulo.setText(libro.titulo)
      view.txt_precio_compra.setText(libro.precio_compra.toString)
      view.txt_precio_venta.setText(libro.precio_venta.toString)
      view.txt_cantidad.setText(libro.cantidad.toString)
      mostrar_imagen(libro.ruta)


      habilitar_campos(true)
      reiniciar_estados_edit_elim()
      habilitar_btn_elim_edit(true)
      view.btn_cancelar.setEnabled(true)
      view.btn_buscar.setEnabled(false)
      view.txt_isbn.setEnabled(false)

    }else{
      JOptionPane.showMessageDialog(null,"Error: ISBN no registrado.")
    }
  }

  def habilitar_btn_elim_edit(estado:Boolean): Unit ={
    view.btn_editar.setEnabled(estado)
    view.btn_eliminar.setEnabled(estado)
  }

  def habilitar_btn_acep_can(estado:Boolean): Unit ={
    view.btn_cancelar.setEnabled(estado)
    view.btn_aceptar.setEnabled(estado)
  }

  def reiniciar_estados_edit_elim(): Unit ={
    estado_editar=false
    estado_eliminar=false
  }

  def habilitar_campos(estado:Boolean): Unit={
    //println("Si se editan los campos")
    view.txt_titulo.setEditable(estado)
    view.txt_cantidad.setEditable(estado)
    view.txt_precio_compra.setEditable(estado)
    view.txt_precio_venta.setEditable(estado)
    view.btn_elegir_imagen.setEnabled(estado)
  }

  def limpiar_campos():Unit={
    view.txt_cantidad_deseada.setText("")
    view.txt_titulo.setText("")
    view.txt_isbn.setText("")
    view.txt_precio_compra.setText("")
    view.txt_precio_venta.setText("")
    view.txt_cantidad.setText("")
  }

  def mostrar_imagen(ruta: String): Unit = {
    val icon = new ImageIcon(ruta)
    val image = icon.getImage()
    val newIcon = new ImageIcon(image.getScaledInstance(view.lbl_imagen.getWidth, view.lbl_imagen.getHeight, java.awt.Image.SCALE_SMOOTH))
    view.lbl_imagen.setIcon(newIcon)
    view.lbl_imagen.setText("")
  }
}
