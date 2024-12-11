package ctrl
import model.{Archivo, Biblioteca, Caja, Detalle_factura, Entidades, Transaccion, Transacciones, Validar}
import view.VentanaFacturar

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.{JOptionPane, WindowConstants}
import javax.swing.table.DefaultTableModel
import scala.collection.mutable.ListBuffer
class Ctrl_facturar() extends ActionListener{
  var view = new VentanaFacturar
  var ctrl_buscar_libro = new Ctrl_editar_eliminar_libros
  var ctrl_buscar_entidad= new Ctrl_insertar_entidad
  ctrl_buscar_entidad.view.setVisible(false)
  ctrl_buscar_libro.view.setVisible(false)

  var archivo = new Archivo

  ctrl_buscar_libro.view.btn_aceptar.addActionListener(this)
  ctrl_buscar_libro.view.btn_cancelar.addActionListener(this)
  ctrl_buscar_entidad.view.btn_ingresar.addActionListener(this)

  var validar = new Validar
  var lista_detalles =new ListBuffer[Detalle_factura]()

  view.btn_facturar.addActionListener(this)
  view.btn_anular_facturacion.addActionListener(this)
  view.btn_buscar.addActionListener(this)
  view.btn_ingresar_libro.addActionListener(this)
  view.btn_eliminar_detalle.addActionListener(this)

  view.btn_eliminar_detalle.setEnabled(true)

  view.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE)
  var caja_restante=0F

  var modelo = view.jgd_detalles.getModel.asInstanceOf[DefaultTableModel]


  override def actionPerformed(e: ActionEvent): Unit = {
    if(e.getSource==view.btn_ingresar_libro){
      if(view.cbx_tipo_transaccion.getSelectedItem.toString=="Abastecimiento") {
        ctrl_buscar_libro.abastecimiento = true
        ingresar_libro()
      } else if(view.cbx_tipo_transaccion.getSelectedItem.toString=="Venta"){
        ctrl_buscar_libro.abastecimiento = false
        ingresar_libro()
      }
    } else if(e.getSource==ctrl_buscar_libro.view.btn_aceptar
      && ctrl_buscar_libro.view.txt_cantidad.getText.toInt> ctrl_buscar_libro.view.txt_cantidad_deseada.getText.toInt){
      transaccionar()
    } else if (e.getSource == view.btn_eliminar_detalle){
      eliminar_detalle()
    } else if (e.getSource == view.btn_facturar){
      if(view.txt_CI_RUC.getText!="" && view.txt_nombre.getText!="") {

        facturar()
        limpiar_reiniciar_campos()
        view.setVisible(false)
      }else{
        JOptionPane.showMessageDialog(null,"Error:Nombre o CI/RUC mal ingresado.")
      }
    } else if (e.getSource == ctrl_buscar_libro.view.btn_aceptar
      && (ctrl_buscar_libro.view.txt_cantidad.getText.toInt <= ctrl_buscar_libro.view.txt_cantidad_deseada.getText.toInt)
      && !ctrl_buscar_libro.abastecimiento){
      JOptionPane.showMessageDialog(null,"Error: la cantidad supera el stock")
    } else if (e.getSource == ctrl_buscar_libro.view.btn_cancelar){
      ctrl_buscar_libro.limpiar_campos()
    } else if (e.getSource== view.btn_buscar) {
      if(archivo.recuperar("entidades").asInstanceOf[Entidades].obtener_entidad(view.txt_CI_RUC.getText)==null) {
        ctrl_buscar_entidad.view.setVisible(true)
      }else{
        var entidad =archivo.recuperar("entidades").asInstanceOf[Entidades].obtener_entidad(view.txt_CI_RUC.getText)
        view.txt_CI_RUC.setText(entidad.codigo)
        view.txt_nombre.setText(entidad.nombre)
      }
    } else if (e.getSource== ctrl_buscar_entidad.view.btn_ingresar){

      view.txt_CI_RUC.setText(ctrl_buscar_entidad.view.txt_CI_RUC.getText)
      view.txt_nombre.setText(ctrl_buscar_entidad.view.txt_nombre.getText)
      ctrl_buscar_entidad.view.setVisible(false)
    } else if (e.getSource == ctrl_buscar_libro.view.btn_aceptar
      && ctrl_buscar_libro.abastecimiento){
      transaccionar()
    } else if (e.getSource== view.btn_anular_facturacion){
      view.setVisible(false)
    }

  }

  def ingresar_libro(): Unit ={
    ctrl_buscar_libro.view.btn_editar.setVisible(false)
    ctrl_buscar_libro.view.btn_eliminar.setVisible(false)
    ctrl_buscar_libro.view.btn_aceptar.setEnabled(true)

    ctrl_buscar_libro.view.btn_elegir_imagen.setVisible(false)
    ctrl_buscar_libro.view.txt_cantidad.setEnabled(true)

    ctrl_buscar_libro.habilitar_campos(false)
    //ctrl_buscar_libro.view.txt_cantidad.setVisible(false)
    ctrl_buscar_libro.view.txt_cantidad_deseada.setEnabled(true)
    ctrl_buscar_libro.view.txt_cantidad_deseada.setVisible(true)
    ctrl_buscar_libro.view.setVisible(true)

  }

  def eliminar_detalle(): Unit ={
    var fila = view.jgd_detalles.getSelectedRow
    lista_detalles.remove(fila)
    modelo.removeRow(fila)
    //actualizar_detalles()
  }

  def actualizar_detalles(): Unit ={
    for (i <- 0 to (modelo.getRowCount - 1: Int)) {
      modelo.removeRow(i)
    }

    for (detalle <- lista_detalles) {
      modelo.addRow(Array[Object](
        detalle.codigo_libro,
        detalle.nombre_libro,
        detalle.cantidad_libros.toString,
        detalle.precio_de_venta.toString,
        (detalle.precio_de_venta * detalle.cantidad_libros).toString))
    }
    actualizar_total_subtotal()
  }

  def facturar(): Unit ={
    var transacciones = new Transacciones

    if (archivo.recuperar("transacciones")==null) {
      transacciones.agregar_transaccion(Transaccion(
        (transacciones.lista_transacciones.length+1).toString,
        view.txt_CI_RUC.getText,
        view.txt_nombre.getText,
        view.cbx_forma_pago.getSelectedItem.toString,
        view.txt_IVA.getText.toFloat,
        lista_detalles,
        view.cbx_tipo_transaccion.getSelectedItem.toString,
      ))
      archivo.guardar("transacciones", transacciones)
    } else {
      val transacciones = archivo.recuperar("transacciones").asInstanceOf[Transacciones]
      transacciones.agregar_transaccion(Transaccion(
        (transacciones.lista_transacciones.length + 1).toString,
        view.txt_CI_RUC.getText,
        view.txt_nombre.getText,
        view.cbx_forma_pago.getSelectedItem.toString,
        view.txt_IVA.getText.toFloat,
        lista_detalles,
        view.cbx_tipo_transaccion.getSelectedItem.toString,
      ))
      archivo.guardar("transacciones", transacciones)
    }

    if (view.cbx_tipo_transaccion.getSelectedItem.toString =="Abastecimiento"){
      var caja =archivo.recuperar("caja").asInstanceOf[Caja]
      caja.caja = caja.caja - view.txt_total.getText.toFloat
      caja_restante=caja.caja
      archivo.guardar("caja",caja)

      if(archivo.recuperar("caja").asInstanceOf[Caja].caja>=view.txt_total.getText.toFloat){

        var biblioteca = archivo.recuperar("biblioteca").asInstanceOf[Biblioteca]
        for (detalle <- lista_detalles) {
          println(view.cbx_tipo_transaccion.getSelectedItem.toString)
          biblioteca.actualizar_biblioteca(detalle.codigo_libro, detalle.cantidad_libros, view.cbx_tipo_transaccion.getSelectedItem.toString)
        }
        archivo.guardar("biblioteca", biblioteca)

      } else{
        JOptionPane.showMessageDialog(null,"Error: El total supera a la caja.")

      }
    } else {
      var caja = archivo.recuperar("caja").asInstanceOf[Caja]
      caja.caja = caja.caja + view.txt_total.getText.toFloat
      var biblioteca = archivo.recuperar("biblioteca").asInstanceOf[Biblioteca]
      for (detalle <- lista_detalles) {
        println(view.cbx_tipo_transaccion.getSelectedItem.toString)
        biblioteca.actualizar_biblioteca(detalle.codigo_libro, detalle.cantidad_libros, view.cbx_tipo_transaccion.getSelectedItem.toString)
      }
      archivo.guardar("biblioteca", biblioteca)
      caja_restante=caja.caja
      archivo.guardar("caja", caja)
    }
  }
  def actualizar_total_subtotal(): Unit ={
    var total=0F
    for(detalle<-lista_detalles){
      total = total + detalle.calcular_costo_detalle()
    }
    view.txt_subtotal.setText(total.toString)
    view.txt_total.setText((total*(1 + view.txt_IVA.getText.toFloat/100)).toString)
  }
  def transaccionar(): Unit ={
    println(view.cbx_tipo_transaccion.getSelectedItem.toString+":1")
    view.cbx_tipo_transaccion.setEnabled(false)
    view.txt_IVA.setEnabled(false)

    if (view.cbx_tipo_transaccion.getSelectedItem.toString == "Venta") {
      lista_detalles = lista_detalles += new Detalle_factura(
        ctrl_buscar_libro.view.txt_isbn.getText,
        ctrl_buscar_libro.view.txt_titulo.getText,
        ctrl_buscar_libro.view.txt_cantidad_deseada.getText.toInt,
        ctrl_buscar_libro.view.txt_precio_venta.getText.toFloat)
      actualizar_detalles()

    } else if (view.cbx_tipo_transaccion.getSelectedItem.toString == "Abastecimiento") {
      val detalle = new Detalle_factura(
        ctrl_buscar_libro.view.txt_isbn.getText,
        ctrl_buscar_libro.view.txt_titulo.getText,
        ctrl_buscar_libro.view.txt_cantidad_deseada.getText.toInt,
        ctrl_buscar_libro.view.txt_precio_venta.getText.toFloat)
      if (lista_detalles.map(_.codigo_libro).contains(detalle.codigo_libro)) {
        JOptionPane.showMessageDialog(null, "Error: el libro ya ha sido ingresado")
      } else {
        lista_detalles = lista_detalles += detalle
      }
      actualizar_detalles()
    }
    ctrl_buscar_libro.view.setVisible(false)
  }
  def limpiar_reiniciar_campos(): Unit ={
    for (i <- 0 to (modelo.getRowCount - 1: Int)) {
      modelo.removeRow(i)
    }
    view.txt_subtotal.setText("0")
    view.txt_total.setText("0")
    view.txt_IVA.setText("0")
    view.txt_subtotal.setText("")
    view.txt_nombre.setText("")
    view.txt_CI_RUC.setText("")
    view.cbx_tipo_transaccion.setEnabled(true)
    caja_restante=0F
    lista_detalles.empty
  }
}
