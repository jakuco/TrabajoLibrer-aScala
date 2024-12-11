package model

import scala.collection.mutable.ListBuffer
import java.util.Calendar
import java.time.LocalDate
case class Factura(id_factura:String, codigo_entidad:String, nombre_entidad:String, forma_de_pago:String,_IVA:Float) extends Serializable{
  var lista_de_detalles = ListBuffer[Detalle_factura]()
  //
  // val fecha = Calendar.getInstance().getTime.
  val fecha = LocalDate.now()
  //fecha.getMonth.toString
  def adicionar_detalles(cantidad:Int,codigo_libro:String,nombre_libro:String,precio_de_venta_unitario:Float): Unit ={
    lista_de_detalles += new Detalle_factura(codigo_libro, nombre_libro, cantidad, precio_de_venta_unitario)
  }

  def eliminar_detalle(indice:Int): Unit ={
    lista_de_detalles.remove(indice)
  }

  def obtener_detalle(indice:Int): Detalle_factura ={
    return lista_de_detalles(indice)
  }

  def calcular_total_factura(): Float ={
    var total=0F
    for(detalle<-lista_de_detalles){
      total= total + detalle.calcular_costo_detalle()
    }
    return total*_IVA
  }
}
