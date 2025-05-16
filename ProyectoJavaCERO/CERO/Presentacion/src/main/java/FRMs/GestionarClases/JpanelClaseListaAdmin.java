package FRMs.GestionarClases;

import DTOs.GestionarClases.ClaseListaDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author Jack Murrieta
 */
public class JpanelClaseListaAdmin extends javax.swing.JPanel {

    private ClaseListaDTO clase;

    public JpanelClaseListaAdmin(ClaseListaDTO clase) {
        initComponents();
        this.clase = clase;
        setOpaque(false);

        configurarDatos();

        JpanelDatos.revalidate();
        JpanelDatos.repaint();
        JpanelHeader.revalidate();
        JpanelHeader.repaint();

        //hacer inclickeables los btns si la clase esta inactiva
        if (clase.isActiva() == false) {
            btnEditar.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnVerInscritos.setEnabled(false);

            //Poner la imagen de editar y eliminar
        }

    }

    private void configurarDatos() {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpanelTabla = new javax.swing.JPanel();
        JpanelDatos = new javax.swing.JPanel();
        lblNombreClase = new javax.swing.JLabel();
        lblHorario = new javax.swing.JLabel();
        lblMaestro = new javax.swing.JLabel();
        lblCupo = new javax.swing.JLabel();
        lblPeriodo = new javax.swing.JLabel();
        lblAula = new javax.swing.JLabel();
        panelBtnInscripciones = new javax.swing.JPanel();
        btnVerInscritos = new javax.swing.JButton();
        panelEditarBtn = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        panelBtnEliminar = new javax.swing.JPanel();
        btnEliminar = new javax.swing.JButton();
        JpanelHeader = new javax.swing.JPanel();
        lblColumnNombre = new javax.swing.JLabel();
        lblColumnHorario = new javax.swing.JLabel();
        lblColumnMaestro = new javax.swing.JLabel();
        lblColumnCupo = new javax.swing.JLabel();
        lblColumnPeriodo = new javax.swing.JLabel();
        lblColumnAula = new javax.swing.JLabel();
        lblColumnInscripciones = new javax.swing.JLabel();
        lblColumnEditar = new javax.swing.JLabel();
        lblColumnEliminar = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JpanelTabla.setLayout(new java.awt.BorderLayout());

        JpanelDatos.setBackground(new java.awt.Color(30, 47, 86));
        JpanelDatos.setLayout(new java.awt.GridLayout(1, 5));

        lblNombreClase.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblNombreClase.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreClase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreClase.setToolTipText("");
        JpanelDatos.add(lblNombreClase);

        lblHorario.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
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

        panelBtnInscripciones.setBackground(new java.awt.Color(30, 47, 86));
        panelBtnInscripciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnVerInscritos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilerias/botones/btnVerInscritos.png"))); // NOI18N
        btnVerInscritos.setBorderPainted(false);
        btnVerInscritos.setContentAreaFilled(false);
        btnVerInscritos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerInscritosActionPerformed(evt);
            }
        });
        panelBtnInscripciones.add(btnVerInscritos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 120, 50));

        JpanelDatos.add(panelBtnInscripciones);

        panelEditarBtn.setBackground(new java.awt.Color(30, 47, 86));
        panelEditarBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilerias/botones/btnEditarClase.png"))); // NOI18N
        btnEditar.setBorderPainted(false);
        btnEditar.setContentAreaFilled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        panelEditarBtn.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 120, 50));

        JpanelDatos.add(panelEditarBtn);

        panelBtnEliminar.setBackground(new java.awt.Color(30, 47, 86));
        panelBtnEliminar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilerias/botones/btnEliminarClase.png"))); // NOI18N
        btnEliminar.setBorderPainted(false);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        panelBtnEliminar.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 130, 50));

        JpanelDatos.add(panelBtnEliminar);

        JpanelTabla.add(JpanelDatos, java.awt.BorderLayout.CENTER);

        JpanelHeader.setBackground(new java.awt.Color(30, 47, 86));
        JpanelHeader.setLayout(new java.awt.GridLayout(1, 5));

        lblColumnNombre.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnNombre.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnNombre.setText("NOMBRE CLASE");
        JpanelHeader.add(lblColumnNombre);

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

        lblColumnInscripciones.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnInscripciones.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnInscripciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnInscripciones.setText("INSCRIPCIONES");
        JpanelHeader.add(lblColumnInscripciones);

        lblColumnEditar.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnEditar.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnEditar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnEditar.setText("EDITAR");
        JpanelHeader.add(lblColumnEditar);

        lblColumnEliminar.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnEliminar.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnEliminar.setText("ELIMINAR");
        JpanelHeader.add(lblColumnEliminar);

        JpanelTabla.add(JpanelHeader, java.awt.BorderLayout.PAGE_START);

        add(JpanelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 1140, 130));
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnVerInscritosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerInscritosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerInscritosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpanelDatos;
    private javax.swing.JPanel JpanelHeader;
    private javax.swing.JPanel JpanelTabla;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnVerInscritos;
    private javax.swing.JLabel lblAula;
    private javax.swing.JLabel lblColumnAula;
    private javax.swing.JLabel lblColumnCupo;
    private javax.swing.JLabel lblColumnEditar;
    private javax.swing.JLabel lblColumnEliminar;
    private javax.swing.JLabel lblColumnHorario;
    private javax.swing.JLabel lblColumnInscripciones;
    private javax.swing.JLabel lblColumnMaestro;
    private javax.swing.JLabel lblColumnNombre;
    private javax.swing.JLabel lblColumnPeriodo;
    private javax.swing.JLabel lblCupo;
    private javax.swing.JLabel lblHorario;
    private javax.swing.JLabel lblMaestro;
    private javax.swing.JLabel lblNombreClase;
    private javax.swing.JLabel lblPeriodo;
    private javax.swing.JPanel panelBtnEliminar;
    private javax.swing.JPanel panelBtnInscripciones;
    private javax.swing.JPanel panelEditarBtn;
    // End of variables declaration//GEN-END:variables
}
