package model
import collection.mutable.ListBuffer

case class Biblioteca() extends Serializable{
  var lista_libros = ListBuffer[Libro]()
  //println(lista_libros)


  def agregar_libro(libro:Libro): Unit = {
    lista_libros = lista_libros +=libro
  }

  def eliminar_libro(_ISBN:String): Unit ={
    lista_libros.remove(lista_libros.map(_._isbn).indexOf(_ISBN))
  }

  def imprimir_libros(): Unit ={
    for(libro<-lista_libros){
      println(libro._isbn+"|"+libro.titulo+"|"+libro.cantidad)
    }
  }

  def obtener_libro(_ISBN:String): Libro={
    if(lista_libros.map(_._isbn).contains(_ISBN))
     return lista_libros(lista_libros.map(_._isbn).indexOf(_ISBN))
    else
      return null
  }

  def editar_libro(_ISBN:String, libro:Libro): Unit={
    //lista_libros.indexOf(obtener_libro(_ISBN))
    lista_libros.update(lista_libros.indexOf(obtener_libro(_ISBN)),libro)
  }

  def obtener_libro_mas_costoso(): ListBuffer[Libro] ={
    return lista_libros.filter(_.precio_venta==lista_libros.map(_.precio_venta).max)
  }

  def obtener_libros_menos_costosos():ListBuffer[Libro]= {
    return lista_libros.filter(_.precio_venta==lista_libros.map(_.precio_venta).min)
    //lista_libros.filter()
  }

  def actualizar_biblioteca(_ISBN :String, cantidad:Int, tipo_transaccion:String): Unit ={
    if (tipo_transaccion == "Abastecimiento"){
      obtener_libro(_ISBN).cantidad = obtener_libro(_ISBN).cantidad+ cantidad

    } else if (tipo_transaccion == "Venta"){
      println("Se hizo una venta")
      obtener_libro(_ISBN).cantidad = obtener_libro(_ISBN).cantidad- cantidad
    }
  }
}


