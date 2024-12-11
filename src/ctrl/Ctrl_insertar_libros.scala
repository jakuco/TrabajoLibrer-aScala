package ctrl
import view.VentanaIngresarLibro
import model.{Archivo, Biblioteca, Libro, Validar}

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.JFileChooser
import java.nio.file.{FileAlreadyExistsException, Files, Path, Paths}
import javax.swing._
import javax.swing.WindowConstants

class Ctrl_insertar_libros extends ActionListener{
  var view = new VentanaIngresarLibro

  var validar = new Validar
  view.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE)
  mostrar_imagen("src/imagenes_iconos/21735.png")

  view.setVisible(true)
  view.btn_elegir_imagen.addActionListener(this)
  view.btn_ingresar.addActionListener(this)
  view.btn_cancelar.addActionListener(this)

  var sourcePath = Paths.get("")
  var targetPath =Paths.get("")

  var ruta=""
  val archivo= new Archivo

  override def actionPerformed(e: ActionEvent): Unit ={
    if(e.getSource==view.btn_ingresar){
      ruta="src/imagenes_libros/"+view.txt_isbn.getText+".png"
      if (ruta!="") {
        if(view.txt_isbn.getText.toString!="")
        insertar_libro()
        else
          JOptionPane.showMessageDialog(null,"Error: ISBN mal ingresado")
      }else{
        JOptionPane.showMessageDialog(null,"Error: no se ha seleccionado ninguna portada.")
      }
      //println("Ingresate un libro")
    }else if (e.getSource==view.btn_cancelar){
      view.setVisible(false)
    }else if (e.getSource==view.btn_elegir_imagen){
      seleccionar_imagen()

    }

  }

  def insertar_libro(): Unit ={
    var biblioteca = new Biblioteca
    if(validar.isbn(view.txt_isbn.getText)) {
      if(validar.cantidad(view.txt_cantidad.getText)) {
        if(validar.precios(view.txt_precio_compra.getText) && validar.precios(view.txt_precio_venta.getText)) {
          if(view.txt_titulo.getText.length>0) {
            targetPath = Paths.get(ruta)
            guardar_imagen()

              if (archivo.recuperar("biblioteca") == null) {
                biblioteca.agregar_libro(Libro(view.txt_isbn.getText,
                  view.txt_titulo.getText,
                  ruta,
                  view.txt_precio_venta.getText.toFloat,
                  view.txt_precio_compra.getText.toFloat,
                  view.txt_cantidad.getText.toInt))
                archivo.guardar("biblioteca", biblioteca)
              } else {
                val biblioteca = archivo.recuperar("biblioteca").asInstanceOf[Biblioteca]
                biblioteca.agregar_libro(Libro(view.txt_isbn.getText,
                  view.txt_titulo.getText,
                  ruta,
                  view.txt_precio_venta.getText.toFloat,
                  view.txt_precio_compra.getText.toFloat,
                  view.txt_cantidad.getText.toInt))
                archivo.guardar("biblioteca", biblioteca)
              }

          }else {JOptionPane.showMessageDialog(null,"Error: Nombre no ingresado")}
        } else {JOptionPane.showMessageDialog(null,"Error: Precio venta o Precio compra mal ingresado.")}
      } else {JOptionPane.showMessageDialog(null,"Error: Cantidad mal ingresada")}
    }else {JOptionPane.showMessageDialog(null,"Error: ISBN incorrecto")}
    limpiar_campos()
  }
  def mostrar_imagen(ruta:String): Unit ={
    val icon = new ImageIcon(ruta)
    val image = icon.getImage()
    val newIcon = new ImageIcon(image.getScaledInstance(view.lbl_imagen.getWidth, view.lbl_imagen.getHeight, java.awt.Image.SCALE_SMOOTH))
    view.lbl_imagen.setIcon(newIcon)
    view.lbl_imagen.setText("")
  }

  def seleccionar_imagen(): Unit ={
    val fileChooser = new JFileChooser()
    val result = fileChooser.showOpenDialog(null)
    if (result == JFileChooser.APPROVE_OPTION) {
      val selectedFile = fileChooser.getSelectedFile()
      sourcePath = Paths.get(selectedFile.getAbsolutePath)
      mostrar_imagen(selectedFile.getAbsolutePath)
    }

  }
  def guardar_imagen(): Unit ={
    try {
      Files.copy(sourcePath, targetPath)
    } catch {
      case ex: FileAlreadyExistsException=>{
        JOptionPane.showMessageDialog(null,"Error: Libro ya ingresado")
      }

    }
  }
  def limpiar_campos(): Unit ={
    view.txt_isbn.setText("")
    view.txt_titulo.setText("")
    view.txt_cantidad.setText("")
    view.txt_precio_compra.setText("")
    view.txt_precio_venta.setText("")
    ruta=""
  }
}
