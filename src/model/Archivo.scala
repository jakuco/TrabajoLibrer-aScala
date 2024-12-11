package model
import java.io.{File, FileOutputStream, ObjectOutputStream}
import java.io.{FileInputStream, ObjectInputStream}
import java.io.FileNotFoundException

class Archivo {
  def guardar(nombre:String, objeto:Object): Unit ={
    val archivo = new File(nombre): File
    val fos: FileOutputStream = new FileOutputStream(archivo)
    val ous: ObjectOutputStream = new ObjectOutputStream(fos)
    ous.writeObject(objeto)
    fos.close()
    ous.close()
  }

  def recuperar(nombre:String): Object={
      var objecto:Object=null
    try {
      val archivo_recuperar = new File(nombre): File
      val fis: FileInputStream = new FileInputStream(archivo_recuperar)
      val ois: ObjectInputStream = new ObjectInputStream(fis)
      val recuperar = ois.readObject()
      ois.close()
      fis.close()
      objecto = recuperar
    } catch {
      case ex: Exception=> {
        println(ex)
      }
    }
    return objecto
  }

}
