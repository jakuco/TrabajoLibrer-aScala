package model

import scala.collection.mutable.ListBuffer

case class Transaccion (var id_factura:String, var codigo_entidad:String, var nombre_entidad:String, forma_de_pago:String, _IVA:Float, lista_de_lista_de_atributos_detalles :ListBuffer[Detalle_factura], tipo_trasanccion:String) extends Serializable{
  var factura=new Factura(id_factura, codigo_entidad, nombre_entidad, forma_de_pago, _IVA)
  factura.lista_de_detalles=lista_de_lista_de_atributos_detalles
}
