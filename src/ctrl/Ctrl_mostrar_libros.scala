package ctrl

import java.awt.event.{ActionEvent, ActionListener}
import model.{Archivo, Biblioteca}
import view.VentanaMostrarLibros

import javax.swing._
import javax.swing.WindowConstants
import javax.swing.event.{ListSelectionEvent, ListSelectionListener}
import javax.swing.table.DefaultTableModel

class Ctrl_mostrar_libros extends ActionListener{
  var view = new VentanaMostrarLibros
  var archivo = new Archivo
  var ctrl= new Ctrl_editar_eliminar_libros

  view.setVisible(true)
  view.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE)

  view.txt_libro_caro.setEnabled(false)
  view.txt_libro_barato.setEnabled(false)
  view.txt_libro_mas_vendido.setEnabled(false)

  view.btn_editar.addActionListener(this)
  view.btn_eliminar.addActionListener(this)

  var biblioteca = archivo.recuperar("biblioteca").asInstanceOf[Biblioteca]
  view.txt_libro_caro.setText(biblioteca.obtener_libro_mas_costoso().head._isbn+"|"+biblioteca.obtener_libro_mas_costoso().head.titulo)
  view.txt_libro_barato.setText(biblioteca.obtener_libros_menos_costosos().head._isbn+"|"+biblioteca.obtener_libros_menos_costosos().head.titulo)


  view.jgd_libros.getSelectionModel().addListSelectionListener(new

      ListSelectionListener {
    override def valueChanged(e: ListSelectionEvent): Unit = {
      mostrar_imagen(biblioteca.lista_libros(view.jgd_libros.getSelectedRow).ruta)
    }
  })



  val modelo = view.jgd_libros.getModel.asInstanceOf[DefaultTableModel]
  for(libro<-biblioteca.lista_libros){
    println(libro._isbn+"|"+libro.titulo)
    modelo.addRow(Array[Object](libro._isbn,libro.titulo,libro.precio_compra.toString,libro.precio_venta.toString,libro.cantidad.toString))
  }

  override def actionPerformed(e: ActionEvent): Unit = {
    var fila = view.jgd_libros.getSelectedRow
    if (e.getSource == view.btn_editar) {
      if (fila == (-1: Int)) {

      } else {
        ctrl = new Ctrl_editar_eliminar_libros
        ctrl.view.txt_isbn.setText(view.jgd_libros.getValueAt(fila, 0).toString)
        ctrl.view.btn_buscar.doClick()
        ctrl.view.btn_editar.doClick()

        ctrl.view.btn_eliminar.setVisible(false)
        ctrl.view.btn_buscar.setVisible(false)
        ctrl.view.btn_editar.setVisible(false)

        ctrl.view.btn_aceptar.addActionListener(this)
        ctrl.view.btn_cancelar.addActionListener(this)

        ctrl.view.setVisible(true)
      }

    } else if (e.getSource == view.btn_eliminar) {
      ctrl = new Ctrl_editar_eliminar_libros
      ctrl.view.txt_isbn.setText(view.jgd_libros.getValueAt(fila, 0).toString)
      ctrl.view.btn_buscar.doClick()
      ctrl.view.btn_eliminar.doClick()

      ctrl.view.btn_eliminar.setVisible(false)
      ctrl.view.btn_buscar.setVisible(false)
      ctrl.view.btn_editar.setVisible(false)

      ctrl.view.btn_aceptar.addActionListener(this)
      ctrl.view.btn_cancelar.addActionListener(this)

      ctrl.view.setVisible(true)

    } else if (e.getSource == ctrl.view.btn_aceptar || e.getSource == ctrl.view.btn_cancelar) {
      ctrl.view.setVisible(false)
      if (ctrl.estado_editar) {
        view.jgd_libros.setValueAt(ctrl.view.txt_titulo.getText, fila, 1)
        view.jgd_libros.setValueAt(ctrl.view.txt_precio_compra.getText, fila, 2)
        view.jgd_libros.setValueAt(ctrl.view.txt_precio_venta.getText, fila, 3)
        view.jgd_libros.setValueAt(ctrl.view.txt_cantidad.getText, fila, 4)
      } else if (ctrl.estado_eliminar) {
        ctrl.view.setVisible(false)
        modelo.removeRow(fila)
      }
    }

  }


  def mostrar_imagen(ruta: String): Unit = {
    val icon = new ImageIcon(ruta)
    val image = icon.getImage()
    val newIcon = new ImageIcon(image.getScaledInstance(view.lbl_imagen.getWidth, view.lbl_imagen.getHeight, java.awt.Image.SCALE_SMOOTH))
    view.lbl_imagen.setIcon(newIcon)
    view.lbl_imagen.setText("")
  }

}
