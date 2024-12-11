package model

import scala.collection.mutable.ListBuffer

class Entidades extends Serializable{
  var lista_entidades = ListBuffer[Entidad]()

  def agregar_entidad(entidad:Entidad): Unit ={
    lista_entidades = lista_entidades +=entidad
  }

  def obtener_entidad(codigo:String): Entidad ={
    if(lista_entidades.map(_.codigo).contains(codigo))
      return lista_entidades(lista_entidades.map(_.codigo).indexOf(codigo))
    else
      return null
  }

  def eliminar_entidad(codigo:String):Entidad={
    return lista_entidades.remove(lista_entidades.map(_.codigo).indexOf(codigo))
  }

  def editar_entidad(codigo:String, entidad:Entidad): Unit ={
    lista_entidades.update(lista_entidades.indexOf(obtener_entidad(codigo)), entidad)
  }

  def imprimir_entidades: Unit={
    for(entidad<-lista_entidades){
      println(entidad.codigo+"|"+entidad.nombre)
    }
  }
}
