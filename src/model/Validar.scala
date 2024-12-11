package model

import scala.collection.mutable.ListBuffer

class Validar{
  def cedula(cedula: String): Boolean = {
    if (cedula.length != 10) return false
    var i = 0
    var residuo = 0
    var aux = 0
    var sumador = 0
    var multiplicadorCadena = ""
    i = 0
    while ( {
      i < 10
    }) {
      if (i % 2 == 0) if (Character.getNumericValue(cedula.charAt(i)) > 4) aux = (Character.getNumericValue(cedula.charAt(i)) * 2) - 9
      else aux = Character.getNumericValue(cedula.charAt(i)) * 2
      else aux = Character.getNumericValue(cedula.charAt(i))
      multiplicadorCadena += Character.forDigit(aux, 10)

      i += 1
    }
    i = 0
    while ( {
      i < 9
    }) {
      sumador += Character.getNumericValue(multiplicadorCadena.charAt(i))

      i += 1
    }
    residuo = sumador % 10
    if (10 - residuo == Character.getNumericValue(cedula.charAt(9))) true
    else false
  }

  def ruc(ruc: String): Boolean = {
    if (ruc.length != 13) {
      return false
    }
    val digitos = ruc.substring(0, 12).map(_.asDigit)
    val coeficientes = Array(3, 2, 7, 6, 5, 4, 3, 2)
    val verificador = ruc(12).asDigit
    var suma = 0
    for (i <- 0 until 8) {
      suma += digitos(i) * coeficientes(i)
    }
    val mod = suma % 11
    val digito = if (mod > 1) 11 - mod else 0
    digito == verificador
  }
  def isbn(isbn:String): Boolean ={
    //val digitos = isbn.filter(_.isDigit)
    if (isbn.filter(_.isDigit).length != 10) return false
    val suma = isbn.filter(_.isDigit).zipWithIndex.map {
      case (c, i) => (10 - i) * c.asDigit
    }.sum
    suma % 11 == 0
  }


  def cantidad (entero: String): Boolean = {
    if (entero.matches("-?\\d+")) {
      return true
    } else {
      return false
    }
  }
  def telefono(telefono:String): Boolean ={
    val digitos = telefono.filter(_.isDigit) // elimina caracteres no numéricos
    if (digitos.length != 9) return false
    digitos.startsWith("09") || digitos.startsWith("07")||digitos.startsWith("02") ||
    digitos.startsWith("03") || digitos.startsWith("04")||digitos.startsWith("05") ||
    digitos.startsWith("06")
  }
  def precios(precio: String): Boolean = {
    scala.util.Try(precio.toDouble).isSuccess
  }

}
