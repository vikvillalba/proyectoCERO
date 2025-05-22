package Utilerias;

import DTOs.GestionarClases.ClaseListaDTO;
import com.mycompany.presentacion.ControlNavegacion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Jack Murrieta
 */
public class JpanelClaseLista extends javax.swing.JPanel {

    private ClaseListaDTO clase;

    public JpanelClaseLista(ClaseListaDTO clase) {
        initComponents();
        this.clase = clase;
        setOpaque(false);

        // NO elimines los componentes si ya los agregaste desde NetBeans
        JpanelHeader.setLayout(new GridLayout(1, 6, 0, 0));
        JpanelDatos.setLayout(new GridLayout(1, 6, 0, 0));

        configurarDatos(clase);

        JpanelDatos.revalidate();
        JpanelDatos.repaint();
        JpanelHeader.revalidate();
        JpanelHeader.repaint();
    }

    private void configurarHeader() {
        JpanelHeader.setBackground(new Color(30, 47, 86));
        JpanelHeader.setLayout(new GridLayout(1, 5, 10, 0));

        lblColumnNombreClase = new JLabel("NOMBRE CLASE", SwingConstants.CENTER);
        lblColumnHorario = new JLabel("HORARIO", SwingConstants.CENTER);
        lblColumnMaestro = new JLabel("MAESTRO", SwingConstants.CENTER);
        lblColumnCupo = new JLabel("CUPO", SwingConstants.CENTER);
        lblColumnPeriodo = new JLabel("PERIODO", SwingConstants.CENTER);
        lblColumnAula = new JLabel("AULA", SwingConstants.CENTER);

        configurarLabel(lblColumnNombreClase);
        configurarLabel(lblColumnHorario);
        configurarLabel(lblColumnMaestro);
        configurarLabel(lblColumnCupo);
        configurarLabel(lblColumnPeriodo);
        configurarLabel(lblColumnAula);

        JpanelHeader.add(lblColumnNombreClase);
        JpanelHeader.add(lblColumnHorario);
        JpanelHeader.add(lblColumnMaestro);
        JpanelHeader.add(lblColumnCupo);
        JpanelHeader.add(lblColumnPeriodo);
        JpanelHeader.add(lblColumnAula);
    }

    private void configurarDatos(ClaseListaDTO clase) {
        JpanelDatos.setBackground(new Color(30, 47, 86));

        //Si el nombre es uy largo tiene Salto de linea 
        //Nombre config
        String nombreClase = "<html><div style='text-align: center;'>"
                + clase.getNombreClase().replace(" ", "<br>")
                + "</div></html>";
        lblNombreClase.setText(nombreClase);

        //Horario config
        //obtener los dias clases hora inicio y hora fin
        String horario = "<html><div style='text-align: center;'>"
                + clase.getHorario().replace(" ", "<br>")
                + "</div></html>";
        lblHorario.setText(horario);

        //Dato nombre Maestro
        String nombreMaestro = "<html><div style='text-align: center;'>"
                + clase.getNombreMaestro().replace(" ", "<br>")
                + "</div></html>";
        lblMaestro.setText(nombreMaestro);

        //Dato cupo
        int cupo = clase.getCupo();
        String cupos = String.valueOf(cupo);
        lblCupo.setText(cupos);

        //Dato Periodo
        String periodo = "<html><div style='text-align: center;'>"
                + clase.getPeriodo().replace(" ", "<br>")
                + "</div></html>";
        lblPeriodo.setText(periodo);

        //dato Aula
        lblAula.setText(clase.getNombreAula());

        configurarLabelDatos(lblNombreClase);
        configurarLabelDatos(lblCupo);
        configurarLabelDatos(lblMaestro);
        configurarLabelDatos(lblCupo);
        configurarLabelDatos(lblHorario);
        configurarLabelDatos(lblPeriodo);
        configurarLabelDatos(lblAula);
    }

    // configura los labels de los datos
    private void configurarLabelDatos(JLabel label) {
        label.setFont(new Font("Menlo", Font.PLAIN, 12));
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        label.setBackground(new Color(30, 47, 86));
        label.setPreferredSize(new Dimension(150, 40)); // Tamaño uniforme para datos
    }

    private void configurarBoton() {
        btnSeleccionarClase.setText("Seleccionar clase");
        btnSeleccionarClase.setFont(new Font("Menlo", Font.BOLD, 14));
        btnSeleccionarClase.setForeground(Color.WHITE);
        btnSeleccionarClase.setBackground(new Color(30, 47, 86));
        btnSeleccionarClase.setFocusPainted(false);
    }

    //Configura los Labels del Header
    private void configurarLabel(JLabel label) {
        label.setFont(new Font("Menlo", Font.BOLD, 14));
        label.setForeground(new Color(148, 197, 227));
        label.setOpaque(true);
        label.setBackground(new Color(30, 47, 86));
        label.setPreferredSize(new Dimension(150, 40));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSeleccionarClase = new javax.swing.JButton();
        JpanelTabla = new javax.swing.JPanel();
        JpanelDatos = new javax.swing.JPanel();
        lblNombreClase = new javax.swing.JLabel();
        lblHorario = new javax.swing.JLabel();
        lblMaestro = new javax.swing.JLabel();
        lblCupo = new javax.swing.JLabel();
        lblPeriodo = new javax.swing.JLabel();
        lblAula = new javax.swing.JLabel();
        JpanelHeader = new javax.swing.JPanel();
        lblColumnNombreClase = new javax.swing.JLabel();
        lblColumnHorario = new javax.swing.JLabel();
        lblColumnMaestro = new javax.swing.JLabel();
        lblColumnCupo = new javax.swing.JLabel();
        lblColumnPeriodo = new javax.swing.JLabel();
        lblColumnAula = new javax.swing.JLabel();

        setBackground(null);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSeleccionarClase.setBackground(null);
        btnSeleccionarClase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilerias/botones/btnSeleccionarClase.png"))); // NOI18N
        btnSeleccionarClase.setBorder(null);
        btnSeleccionarClase.setContentAreaFilled(false);
        btnSeleccionarClase.setFocusPainted(false);
        btnSeleccionarClase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarClaseActionPerformed(evt);
            }
        });
        add(btnSeleccionarClase, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 30, 240, 80));

        JpanelTabla.setLayout(new java.awt.BorderLayout());

        JpanelDatos.setBackground(new java.awt.Color(30, 47, 86));
        JpanelDatos.setPreferredSize(new java.awt.Dimension(1230, 0));
        JpanelDatos.setLayout(new java.awt.GridLayout(1, 5));

        lblNombreClase.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblNombreClase.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreClase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreClase.setToolTipText("");
        JpanelDatos.add(lblNombreClase);

        lblHorario.setFont(new java.awt.Font("Menlo", 1, 12)); // NOI18N
        lblHorario.setForeground(new java.awt.Color(255, 255, 255));
        lblHorario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHorario.setToolTipText("");
        JpanelDatos.add(lblHorario);

        lblMaestro.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblMaestro.setForeground(new java.awt.Color(255, 255, 255));
        lblMaestro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaestro.setToolTipText("");
        JpanelDatos.add(lblMaestro);

        lblCupo.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblCupo.setForeground(new java.awt.Color(255, 255, 255));
        lblCupo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCupo.setToolTipText("");
        JpanelDatos.add(lblCupo);

        lblPeriodo.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblPeriodo.setForeground(new java.awt.Color(255, 255, 255));
        lblPeriodo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPeriodo.setToolTipText("");
        JpanelDatos.add(lblPeriodo);

        lblAula.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblAula.setForeground(new java.awt.Color(255, 255, 255));
        lblAula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAula.setToolTipText("");
        JpanelDatos.add(lblAula);

        JpanelTabla.add(JpanelDatos, java.awt.BorderLayout.CENTER);

        JpanelHeader.setBackground(new java.awt.Color(30, 47, 86));
        JpanelHeader.setLayout(new java.awt.GridLayout(1, 5));

        lblColumnNombreClase.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnNombreClase.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnNombreClase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnNombreClase.setText("NOMBRE CLASE");
        lblColumnNombreClase.setToolTipText("");
        JpanelHeader.add(lblColumnNombreClase);

        lblColumnHorario.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnHorario.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnHorario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnHorario.setText("HORARIO");
        JpanelHeader.add(lblColumnHorario);

        lblColumnMaestro.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnMaestro.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnMaestro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnMaestro.setText("MAESTRO");
        JpanelHeader.add(lblColumnMaestro);

        lblColumnCupo.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnCupo.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnCupo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnCupo.setText("CUPO");
        JpanelHeader.add(lblColumnCupo);

        lblColumnPeriodo.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnPeriodo.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnPeriodo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnPeriodo.setText("PERIODO");
        JpanelHeader.add(lblColumnPeriodo);

        lblColumnAula.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnAula.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnAula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnAula.setText("AULA");
        JpanelHeader.add(lblColumnAula);

        JpanelTabla.add(JpanelHeader, java.awt.BorderLayout.PAGE_START);

        add(JpanelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 940, 130));
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarClaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarClaseActionPerformed
        // TODO add your handling code here:

        ControlNavegacion.mostrarDatosClase(this.clase);

        // SI LA CLASE ESTA LLENA DESHABILITAR EL BTN

    }//GEN-LAST:event_btnSeleccionarClaseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpanelDatos;
    private javax.swing.JPanel JpanelHeader;
    private javax.swing.JPanel JpanelTabla;
    private javax.swing.JButton btnSeleccionarClase;
    private javax.swing.JLabel lblAula;
    private javax.swing.JLabel lblColumnAula;
    private javax.swing.JLabel lblColumnCupo;
    private javax.swing.JLabel lblColumnHorario;
    private javax.swing.JLabel lblColumnMaestro;
    private javax.swing.JLabel lblColumnNombreClase;
    private javax.swing.JLabel lblColumnPeriodo;
    private javax.swing.JLabel lblCupo;
    private javax.swing.JLabel lblHorario;
    private javax.swing.JLabel lblMaestro;
    private javax.swing.JLabel lblNombreClase;
    private javax.swing.JLabel lblPeriodo;
    // End of variables declaration//GEN-END:variables
}
