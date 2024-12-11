package model

case class Detalle_factura (var codigo_libro:String, var nombre_libro: String, var cantidad_libros:Int, var precio_de_venta:Float) extends Serializable{
  def calcular_costo_detalle(): Float ={
    return (precio_de_venta*cantidad_libros)
  }
}
