package FRMs.registroAsistencia;

import Utilerias.*;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.presentacion.ControlNavegacion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


/**
 *
 * @author Jack Murrieta
 */
public class PanelClaseAsistencias extends javax.swing.JPanel {
    
    private ClaseDTO clase;

    public PanelClaseAsistencias(ClaseDTO clase) {
        initComponents();
        this.clase = clase;
        setOpaque(false);

        // Eliminar todos los componentes previos de los paneles
        JpanelDatos.removeAll();
        JpanelHeader.removeAll();

        // Asegurar que no haya espacio entre los paneles
        JpanelHeader.setLayout(new GridLayout(1, 5, 0, 0)); // Sin espacio entre columnas
        JpanelDatos.setLayout(new GridLayout(1, 5, 0, 0));  // Sin espacio entre columnas

        // Configurar los paneles correctamente
        configurarHeader();
        configurarDatos();

        // Forzar actualización de la interfaz gráfica
        JpanelDatos.revalidate();
        JpanelDatos.repaint();
        JpanelHeader.revalidate();
        JpanelHeader.repaint();
    }


    private void configurarHeader() {
        JpanelHeader.setBackground(new Color(30, 47, 86));
        JpanelHeader.setLayout(new GridLayout(1, 5, 10, 0));

        lblColumnID = new JLabel("ID CLASE", SwingConstants.CENTER);
        lblColumnNombre = new JLabel("NOMBRE CLASE", SwingConstants.CENTER);
        lblColumnHorario = new JLabel("HORARIO", SwingConstants.CENTER);
        lblColumnDias = new JLabel("DÍAS", SwingConstants.CENTER);
        lblColumnMaestro = new JLabel("MAESTRO", SwingConstants.CENTER);

        configurarLabel(lblColumnID);
        configurarLabel(lblColumnNombre);
        configurarLabel(lblColumnHorario);
        configurarLabel(lblColumnDias);
        configurarLabel(lblColumnMaestro);

        JpanelHeader.add(lblColumnID);
        JpanelHeader.add(lblColumnNombre);
        JpanelHeader.add(lblColumnHorario);
        JpanelHeader.add(lblColumnDias);
        JpanelHeader.add(lblColumnMaestro);
    }
    
   private void configurarDatos() {
        JpanelDatos.setBackground(new Color(30, 47, 86));
        JpanelDatos.setLayout(new GridLayout(1, 5, 10, 0));

       lblIdClase = new JLabel(String.valueOf(clase.getCodigo()), SwingConstants.CENTER);
       lblNombreClase = new JLabel(clase.getNombre(), SwingConstants.CENTER);
       //JUNTAR HORA INICIO Y HORA FIN
       lblHorario = new JLabel(clase.getHoraInicio() + "-" + clase.getHoraFin(), SwingConstants.CENTER);
       List<DayOfWeek> diasSemana = clase.getDias();
       String diasTexto = (diasSemana != null) ? diasSemana.stream()
               .map(DayOfWeek::toString)
               .collect(Collectors.joining(", ")) : "Sin días";

       lblDias = new JLabel(diasTexto, SwingConstants.CENTER);
       lblMaestro = new JLabel(clase.getMaestro(), SwingConstants.CENTER);

        configurarLabelDatos(lblIdClase);
        configurarLabelDatos(lblNombreClase);
        configurarLabelDatos(lblHorario);
        configurarLabelDatos(lblDias);
        configurarLabelDatos(lblMaestro);

        JpanelDatos.add(lblIdClase);
        JpanelDatos.add(lblNombreClase);
        JpanelDatos.add(lblHorario);
        JpanelDatos.add(lblDias);
        JpanelDatos.add(lblMaestro);
    }

    private void configurarBoton() {
        btnVerAsistenciasActual.setText("Seleccionar clase");
        btnVerAsistenciasActual.setFont(new Font("Menlo", Font.BOLD, 14));
        btnVerAsistenciasActual.setForeground(Color.WHITE);
        btnVerAsistenciasActual.setBackground(new Color(30, 47, 86));
        btnVerAsistenciasActual.setFocusPainted(false);
    }
    
    //Configura los Labels del Header
    private void configurarLabel(JLabel label) {
        label.setFont(new Font("Menlo", Font.BOLD, 14));
        label.setForeground(new Color(148, 197, 227));
        label.setOpaque(true);
        label.setBackground(new Color(30, 47, 86));
        label.setPreferredSize(new Dimension(150, 40)); // Espacio suficiente para que no se encimen
    }

    // configura los labels de los datos
    private void configurarLabelDatos(JLabel label) {
        label.setFont(new Font("Menlo", Font.PLAIN, 14));
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        label.setBackground(new Color(30, 47, 86));
        label.setPreferredSize(new Dimension(150, 40)); // Tamaño uniforme para datos
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVerAsistenciasActual = new javax.swing.JButton();
        JpanelTabla = new javax.swing.JPanel();
        JpanelDatos = new javax.swing.JPanel();
        lblIdClase = new javax.swing.JLabel();
        lblNombreClase = new javax.swing.JLabel();
        lblHorario = new javax.swing.JLabel();
        lblDias = new javax.swing.JLabel();
        lblMaestro = new javax.swing.JLabel();
        JpanelHeader = new javax.swing.JPanel();
        lblColumnID = new javax.swing.JLabel();
        lblColumnNombre = new javax.swing.JLabel();
        lblColumnHorario = new javax.swing.JLabel();
        lblColumnDias = new javax.swing.JLabel();
        lblColumnMaestro = new javax.swing.JLabel();
        btnVerAsistenciasAnteriores = new javax.swing.JButton();

        setBackground(null);
        setPreferredSize(new java.awt.Dimension(1270, 137));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnVerAsistenciasActual.setBackground(null);
        btnVerAsistenciasActual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilerias/botones/btnVerAsistencias.png"))); // NOI18N
        btnVerAsistenciasActual.setBorder(null);
        btnVerAsistenciasActual.setContentAreaFilled(false);
        btnVerAsistenciasActual.setFocusPainted(false);
        btnVerAsistenciasActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerAsistenciasActualActionPerformed(evt);
            }
        });
        add(btnVerAsistenciasActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 380, 60));

        JpanelTabla.setLayout(new java.awt.BorderLayout());

        JpanelDatos.setBackground(new java.awt.Color(30, 47, 86));
        JpanelDatos.setPreferredSize(new java.awt.Dimension(1230, 0));
        JpanelDatos.setLayout(new java.awt.GridLayout(1, 5));

        lblIdClase.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblIdClase.setForeground(new java.awt.Color(255, 255, 255));
        lblIdClase.setToolTipText("");
        JpanelDatos.add(lblIdClase);

        lblNombreClase.setFont(new java.awt.Font("Menlo", 1, 12)); // NOI18N
        lblNombreClase.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreClase.setToolTipText("");
        JpanelDatos.add(lblNombreClase);

        lblHorario.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblHorario.setForeground(new java.awt.Color(255, 255, 255));
        lblHorario.setToolTipText("");
        JpanelDatos.add(lblHorario);

        lblDias.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblDias.setForeground(new java.awt.Color(255, 255, 255));
        lblDias.setToolTipText("");
        JpanelDatos.add(lblDias);

        lblMaestro.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblMaestro.setForeground(new java.awt.Color(255, 255, 255));
        lblMaestro.setToolTipText("");
        JpanelDatos.add(lblMaestro);

        JpanelTabla.add(JpanelDatos, java.awt.BorderLayout.CENTER);

        JpanelHeader.setBackground(new java.awt.Color(30, 47, 86));
        JpanelHeader.setLayout(new java.awt.GridLayout(1, 5));

        lblColumnID.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnID.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnID.setText("ID CLASE");
        lblColumnID.setToolTipText("");
        JpanelHeader.add(lblColumnID);

        lblColumnNombre.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnNombre.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnNombre.setText("NOMBRE CLASE");
        JpanelHeader.add(lblColumnNombre);

        lblColumnHorario.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnHorario.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnHorario.setText("HORARIO");
        JpanelHeader.add(lblColumnHorario);

        lblColumnDias.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnDias.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnDias.setText("DIAS");
        JpanelHeader.add(lblColumnDias);

        lblColumnMaestro.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnMaestro.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnMaestro.setText("MAESTRO");
        JpanelHeader.add(lblColumnMaestro);

        JpanelTabla.add(JpanelHeader, java.awt.BorderLayout.PAGE_START);

        add(JpanelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 901, 121));

        btnVerAsistenciasAnteriores.setBackground(null);
        btnVerAsistenciasAnteriores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilerias/botones/btnVerAsistenciasAnteriores.png"))); // NOI18N
        btnVerAsistenciasAnteriores.setBorder(null);
        btnVerAsistenciasAnteriores.setContentAreaFilled(false);
        btnVerAsistenciasAnteriores.setFocusPainted(false);
        btnVerAsistenciasAnteriores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerAsistenciasAnterioresActionPerformed(evt);
            }
        });
        add(btnVerAsistenciasAnteriores, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 70, 340, 70));
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerAsistenciasActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerAsistenciasActualActionPerformed
        // TODO add your handling code here:
        
        ControlNavegacion.mostrarDatosClase(clase);
        
        // SI LA CLASE ESTA LLENA DESHABILITAR EL BTN
        
    }//GEN-LAST:event_btnVerAsistenciasActualActionPerformed

    private void btnVerAsistenciasAnterioresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerAsistenciasAnterioresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerAsistenciasAnterioresActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpanelDatos;
    private javax.swing.JPanel JpanelHeader;
    private javax.swing.JPanel JpanelTabla;
    private javax.swing.JButton btnVerAsistenciasActual;
    private javax.swing.JButton btnVerAsistenciasAnteriores;
    private javax.swing.JLabel lblColumnDias;
    private javax.swing.JLabel lblColumnHorario;
    private javax.swing.JLabel lblColumnID;
    private javax.swing.JLabel lblColumnMaestro;
    private javax.swing.JLabel lblColumnNombre;
    private javax.swing.JLabel lblDias;
    private javax.swing.JLabel lblHorario;
    private javax.swing.JLabel lblIdClase;
    private javax.swing.JLabel lblMaestro;
    private javax.swing.JLabel lblNombreClase;
    // End of variables declaration//GEN-END:variables
}
