/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

/**
 *
 * @author Jorge
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_caja = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jFile = new javax.swing.JMenu();
        j_todas_facturas = new javax.swing.JMenuItem();
        j_facturar = new javax.swing.JMenuItem();
        jEdit = new javax.swing.JMenu();
        j_insertar_libros = new javax.swing.JMenuItem();
        j_editar_libros = new javax.swing.JMenuItem();
        j_eliminar_libros = new javax.swing.JMenuItem();
        j_mostrar_libros = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        j_insertar_entidad = new javax.swing.JMenuItem();
        j_editar_entidad = new javax.swing.JMenuItem();
        j_eliminar_entidad = new javax.swing.JMenuItem();
        j_mostrar_entidad = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btn_caja.setText("Caja");
        btn_caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cajaActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes_iconos/43073.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jFile.setText("Facturación");
        jFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileActionPerformed(evt);
            }
        });

        j_todas_facturas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes_iconos/171610.png"))); // NOI18N
        j_todas_facturas.setText("Todas las facturas");
        j_todas_facturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j_todas_facturasActionPerformed(evt);
            }
        });
        jFile.add(j_todas_facturas);

        j_facturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes_iconos/1230398.png"))); // NOI18N
        j_facturar.setText("Facturar");
        j_facturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j_facturarActionPerformed(evt);
            }
        });
        jFile.add(j_facturar);

        jMenuBar1.add(jFile);

        jEdit.setText("Biblioteca");

        j_insertar_libros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes_iconos/51695.png"))); // NOI18N
        j_insertar_libros.setText("Insertar libros");
        j_insertar_libros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j_insertar_librosActionPerformed(evt);
            }
        });
        jEdit.add(j_insertar_libros);

        j_editar_libros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes_iconos/30148.png"))); // NOI18N
        j_editar_libros.setText("Editar libros");
        jEdit.add(j_editar_libros);

        j_eliminar_libros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes_iconos/51354.png"))); // NOI18N
        j_eliminar_libros.setText("Eliminar libros");
        jEdit.add(j_eliminar_libros);

        j_mostrar_libros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes_iconos/16543.png"))); // NOI18N
        j_mostrar_libros.setText("Mostrar libros");
        jEdit.add(j_mostrar_libros);

        jMenuBar1.add(jEdit);

        jMenu1.setText("Comprador/Vendedor");

        j_insertar_entidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes_iconos/40358.png"))); // NOI18N
        j_insertar_entidad.setText("Insertar Comprador/Vendedor");
        j_insertar_entidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j_insertar_entidadActionPerformed(evt);
            }
        });
        jMenu1.add(j_insertar_entidad);

        j_editar_entidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes_iconos/482979.png"))); // NOI18N
        j_editar_entidad.setText("Editar Comprador/Vendedor");
        jMenu1.add(j_editar_entidad);

        j_eliminar_entidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes_iconos/23146.png"))); // NOI18N
        j_eliminar_entidad.setText("Eliminar Comprador/Vendedor");
        jMenu1.add(j_eliminar_entidad);

        j_mostrar_entidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes_iconos/46773.png"))); // NOI18N
        j_mostrar_entidad.setText("Mostrar Comprador/Vendedor");
        j_mostrar_entidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j_mostrar_entidadActionPerformed(evt);
            }
        });
        jMenu1.add(j_mostrar_entidad);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_caja)
                .addGap(301, 301, 301))
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btn_caja)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cajaActionPerformed

    private void jFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileActionPerformed
        // TODO add your handling code here:
        if(jFile.isCursorSet()){
            
        }
    }//GEN-LAST:event_jFileActionPerformed

    private void j_todas_facturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j_todas_facturasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_j_todas_facturasActionPerformed

    private void j_facturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j_facturarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_j_facturarActionPerformed

    private void j_insertar_librosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j_insertar_librosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_j_insertar_librosActionPerformed

    private void j_insertar_entidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j_insertar_entidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_j_insertar_entidadActionPerformed

    private void j_mostrar_entidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j_mostrar_entidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_j_mostrar_entidadActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_caja;
    public javax.swing.JMenu jEdit;
    public javax.swing.JMenu jFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuItem j_editar_entidad;
    public javax.swing.JMenuItem j_editar_libros;
    public javax.swing.JMenuItem j_eliminar_entidad;
    public javax.swing.JMenuItem j_eliminar_libros;
    public javax.swing.JMenuItem j_facturar;
    public javax.swing.JMenuItem j_insertar_entidad;
    public javax.swing.JMenuItem j_insertar_libros;
    public javax.swing.JMenuItem j_mostrar_entidad;
    public javax.swing.JMenuItem j_mostrar_libros;
    public javax.swing.JMenuItem j_todas_facturas;
    // End of variables declaration//GEN-END:variables
}
