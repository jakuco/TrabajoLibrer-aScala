package ctrl
import view.VentanaCaja
import model.{Archivo, Caja}

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.WindowConstants
class Ctrl_caja extends ActionListener{
  var view= new VentanaCaja
  view.btn_ingresar_caja.addActionListener(this)
  view.setVisible(true)
  var estado_caja = false
  var archivo= new Archivo
  view.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE)
  if (archivo.recuperar("caja").asInstanceOf[Caja] == null) {
    var caja = new Caja(view.txt_caja.getText.toFloat)
    archivo.guardar("caja", caja)
    println(caja.caja)
  } else {
    var caja = archivo.recuperar("caja").asInstanceOf[Caja]
    archivo.guardar("caja", caja)
    println(caja.caja)
    view.txt_caja.setText(caja.caja.toString)
  }

  override def actionPerformed(e: ActionEvent): Unit = {
    if (e.getSource == view.btn_ingresar_caja && estado_caja) {
      estado_caja = false
      if (archivo.recuperar("caja").asInstanceOf[Caja] == null) {
        var caja = new Caja(view.txt_caja.getText.toFloat)
        println(caja.caja)
        archivo.guardar("caja", caja)
      } else {
        var caja = archivo.recuperar("caja").asInstanceOf[Caja]
        caja.caja = view.txt_caja.getText.toFloat
        println(caja.caja)
        archivo.guardar("caja", caja)
      }
      view.btn_ingresar_caja.setText("Ingresar Caja")
      view.txt_caja.setEditable(false)

    } else if (e.getSource == view.btn_ingresar_caja && !estado_caja) {
      view.txt_caja.setEditable(true)
      estado_caja=true
      view.btn_ingresar_caja.setText("Aceptar")
    }
  }
}
