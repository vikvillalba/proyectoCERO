package FRMs;

import Utilerias.JpanelClaseLista;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.presentacion.ControlNavegacion;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Jack Murrieta
 */
public class FrmClasesExistentes extends javax.swing.JFrame {

    private Image imagenFondo;
    private List<ClaseDTO> clases;

    /**
     * Creates new form FrmClasesExistentes
     *
     * @param clases
     */
    public FrmClasesExistentes(List<ClaseDTO> clases) {
        initComponents();
        this.clases = clases;
        jScrollClases.setOpaque(true);
        this.setTitle("Clases Existentes");
        this.imagenFondo = new ImageIcon(getClass().getResource("/Utilerias/FondoCERO.jpeg")).getImage();

        // Crear un JPanel con la imagen de fondo y agregarlo al frame
        JPanel jPanel1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            }
        };

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1377, 600));
        pack();
        //
        llenarClasesExistentes();

    }

    private void llenarClasesExistentes() {
        // Crear un JPanel contenedor para la tabla
        JPanel contenedorTabla = new JPanel();
        contenedorTabla.setLayout(new BoxLayout(contenedorTabla, BoxLayout.Y_AXIS));

        // Agregar margen para que los elementos no se vean pegados
        contenedorTabla.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Recorrer la lista de clases y agregar filas a la tabla
        for (ClaseDTO clase : clases) {
            // Crear el panel para la clase
            JpanelClaseLista panelClase = new JpanelClaseLista(clase);

            // Agregar espacio entre los paneles
            panelClase.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            // Agregar el panel al contenedor
            contenedorTabla.add(panelClase);
        }

        // Configurar JScrollPane
        jScrollClases.setViewportView(contenedorTabla);
        jScrollClases.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollClases.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Asegurar que la tabla se muestre correctamente
        contenedorTabla.revalidate();
        contenedorTabla.repaint();
    }


    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollClases = new javax.swing.JScrollPane();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Menlo", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(30, 47, 86));
        jLabel1.setText("CLASES EXISTENTES");

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilerias/botones/btnRegresar.png"))); // NOI18N
        btnRegresar.setBorderPainted(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 31, Short.MAX_VALUE)
                .addComponent(jScrollClases, javax.swing.GroupLayout.PREFERRED_SIZE, 1220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(430, 430, 430))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(539, 539, 539))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollClases, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        ControlNavegacion.mostrarInscribirClase();
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed
//
//    /**
//         * @param args the command line arguments
//         */
//    public static void main(String args[]){
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrmClasesExistentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmClasesExistentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmClasesExistentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmClasesExistentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                
//                new FrmClasesExistentes().setVisible(true);
//            }
//        });
//    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollClases;
    // End of variables declaration//GEN-END:variables
}
