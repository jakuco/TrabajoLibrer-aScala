package model

import scala.collection.mutable.ListBuffer

class Transacciones() extends Serializable{
  var lista_transacciones=ListBuffer[Transaccion]()

  def agregar_transaccion(transaccion: Transaccion): Unit ={
    lista_transacciones += transaccion
  }

  def obtener_transaccion(indice:Int): Transaccion ={
    return lista_transacciones(indice)
  }

  def obtener_Abasteciminetos_Ventas(tipo_transaccion:String): ListBuffer[Transaccion] ={
    return lista_transacciones.filter(_.tipo_trasanccion==tipo_transaccion)
  }

}
