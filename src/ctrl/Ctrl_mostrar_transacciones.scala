package ctrl

import model.{Archivo, Transaccion, Transacciones}

import java.awt.event.{ActionEvent, ActionListener}
import view.VentanaMostrarTransacciones

import javax.swing.event.{ListSelectionEvent, ListSelectionListener}
import javax.swing.{ListSelectionModel, WindowConstants}
import javax.swing.table.DefaultTableModel

class Ctrl_mostrar_transacciones extends ActionListener{
  var view = new VentanaMostrarTransacciones
  var fila=(-1):Int

  view.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE)
  view.setVisible(true)
  view.jgd_transacciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION)
  view.jgd_transacciones.getSelectionModel().addListSelectionListener(new

      ListSelectionListener {
    override def valueChanged(e: ListSelectionEvent): Unit = {
         mostra_detalles_total(view.jgd_transacciones.getSelectedRow)
    }
  })
  var archivo = new Archivo
  var transacciones = archivo.recuperar("transacciones").asInstanceOf[Transacciones]
  var modeloTransacciones = view.jgd_transacciones.getModel.asInstanceOf[DefaultTableModel]
  var modeloDetalles = view.jgd_detalles_transaccion.getModel.asInstanceOf[DefaultTableModel]

  for (transaccion <- transacciones.lista_transacciones) {
    modeloTransacciones.addRow(Array[Object](transaccion.factura.fecha.getYear.toString,
      transaccion.factura.fecha.getMonthValue.toString,
      transaccion.factura.fecha.getDayOfMonth.toString,
      transaccion.factura.codigo_entidad,
      transaccion.factura.nombre_entidad,
      transaccion.factura.forma_de_pago,
      transaccion.tipo_trasanccion))
  }

  override def actionPerformed(e: ActionEvent): Unit = {

  }
  def mostra_detalles_total(fila:Int): Unit ={
    var total=0F
    val filas = modeloDetalles.getRowCount-1
    for(i<-0 to (filas)) {
      modeloDetalles.removeRow(filas-i)
      println(fila-i)
    }

    for(detalle<-transacciones.lista_transacciones(fila).factura.lista_de_detalles){
      modeloDetalles.addRow(Array[Object](detalle.codigo_libro,
        detalle.nombre_libro,
        detalle.cantidad_libros.toString,
        detalle.precio_de_venta.toString))
      total= total +  detalle.cantidad_libros*detalle.precio_de_venta
    }

    view.txt_total.setText(total.toString)
  }
}
