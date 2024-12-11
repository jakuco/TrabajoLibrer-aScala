package ctrl

import model.{Archivo, Entidades, Validar}

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.WindowConstants
import view.VentanaMostrarEntidad

import javax.swing.table.DefaultTableModel
import ctrl.Ctrl_editar_eliminar_entidades

class Ctrl_mostrar_entidades extends ActionListener{
  var view = new VentanaMostrarEntidad
  var archivo = new Archivo
  var ctrl= new Ctrl_editar_eliminar_entidades

  view.setVisible(true)
  view.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE)

  view.btn_editar.addActionListener(this)
  view.btn_eliminar.addActionListener(this)

  var entidades=archivo.recuperar("entidades").asInstanceOf[Entidades]
  var modelo = view.jgd_entidades.getModel.asInstanceOf[DefaultTableModel]
  for (entidad<-entidades.lista_entidades){
    modelo.addRow(Array[Object](entidad.codigo,entidad.nombre,entidad.telefono,entidad.correo,entidad.direccion))
  }
  override def actionPerformed(e: ActionEvent): Unit = {
    var fila = view.jgd_entidades.getSelectedRow
    if (e.getSource == view.btn_editar) {
      if(fila==(-1:Int)){

      } else {
        ctrl = new Ctrl_editar_eliminar_entidades
        ctrl.view.txt_CI_RUC.setText(view.jgd_entidades.getValueAt(fila, 0).toString)
        ctrl.view.btn_buscar.doClick()
        ctrl.view.btn_editar.doClick()

        ctrl.view.btn_eliminar.setVisible(false)
        ctrl.view.btn_buscar.setVisible(false)
        ctrl.view.btn_editar.setVisible(false)

        ctrl.view.btn_aceptar.addActionListener(this)
        ctrl.view.btn_cancelar.addActionListener(this)

        ctrl.view.setVisible(true)
      }

    } else if (e.getSource == view.btn_eliminar) {
      ctrl = new Ctrl_editar_eliminar_entidades
      ctrl.view.txt_CI_RUC.setText(view.jgd_entidades.getValueAt(fila, 0).toString)
      ctrl.view.btn_buscar.doClick()
      ctrl.view.btn_eliminar.doClick()

      ctrl.view.btn_eliminar.setVisible(false)
      ctrl.view.btn_buscar.setVisible(false)
      ctrl.view.btn_editar.setVisible(false)

      ctrl.view.btn_aceptar.addActionListener(this)
      ctrl.view.btn_cancelar.addActionListener(this)

      ctrl.view.setVisible(true)

    } else if(e.getSource==ctrl.view.btn_aceptar || e.getSource==ctrl.view.btn_cancelar){
      ctrl.view.setVisible(false)
      if(ctrl.estado_editar) {
        view.jgd_entidades.setValueAt(ctrl.view.txt_nombre.getText, fila, 1)
        view.jgd_entidades.setValueAt(ctrl.view.txt_telefono.getText, fila, 2)
        view.jgd_entidades.setValueAt(ctrl.view.txt_correo.getText, fila, 3)
        view.jgd_entidades.setValueAt(ctrl.view.txt_direccion.getText, fila, 4)
      } else if(ctrl.estado_eliminar){
        ctrl.view.setVisible(false)
        modelo.removeRow(fila)
      }
    }


  }
}
