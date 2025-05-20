package FRM.GestionarAlumnos;

import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.presentacion.ControlNavegacion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Jack Murrieta
 */
public class JpanelAlumnoLista extends javax.swing.JPanel {

    private AlumnoDTO alumno;

    public JpanelAlumnoLista(AlumnoDTO alumno) {
        initComponents();
        this.alumno = alumno;
        setOpaque(false);

        configurarDatos();

        JpanelDatos.revalidate();
        JpanelDatos.repaint();
        JpanelHeader.revalidate();
        JpanelHeader.repaint();

    }

    private void configurarDatos() {
        JpanelDatos.setBackground(new Color(30, 47, 86));

        //Si el nombre es uy largo tiene Salto de linea 
        //Nombre config
        String nombreAlumno = "<html><div style='text-align: center;'>"
                + alumno.getNombreCompleto().replace(" ", "<br>")
                + "</div></html>";
        lblNombreAlumno.setText(nombreAlumno);

        //Horario config
        //obtener los dias clases hora inicio y hora fin
        String correo = "<html><div style='text-align: center;'>"
                + alumno.getCorreoElectronico().replace(" ", "<br>")
                + "</div></html>";
        lblCorreo.setText(correo);

        //Dato nombre Maestro
        String telefono = "<html><div style='text-align: center;'>"
                + alumno.getTelefono().replace(" ", "<br>")
                + "</div></html>";
        lblTelefono.setText(telefono);

        Integer id = alumno.getCodigo();
        String idAlumno = String.valueOf(id);
        lblID.setText(idAlumno);

        int edadAlumno = alumno.getEdad();
        String edad = String.valueOf(edadAlumno);

        lblEdad.setText(edad);

        //dato Aula
        configurarLabelDatos(lblEdad);
        configurarLabelDatos(lblNombreAlumno);
        configurarLabelDatos(lblID);
        configurarLabelDatos(lblTelefono);
        configurarLabelDatos(lblCorreo);
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
        lblID = new javax.swing.JLabel();
        lblNombreAlumno = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        panelBtnInscripciones = new javax.swing.JPanel();
        btnVerInscritos = new javax.swing.JButton();
        panelEditarBtn = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        panelBtnEliminar = new javax.swing.JPanel();
        btnEliminar = new javax.swing.JButton();
        JpanelHeader = new javax.swing.JPanel();
        lblColumnNombre = new javax.swing.JLabel();
        lblColumnNombre1 = new javax.swing.JLabel();
        lblColumnHorario = new javax.swing.JLabel();
        lblColumnMaestro = new javax.swing.JLabel();
        lblColumnCupo = new javax.swing.JLabel();
        lblColumnInscripciones = new javax.swing.JLabel();
        lblColumnEditar = new javax.swing.JLabel();
        lblColumnEliminar = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JpanelTabla.setLayout(new java.awt.BorderLayout());

        JpanelDatos.setBackground(new java.awt.Color(30, 47, 86));
        JpanelDatos.setLayout(new java.awt.GridLayout(1, 5));

        lblID.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblID.setForeground(new java.awt.Color(255, 255, 255));
        lblID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblID.setToolTipText("");
        JpanelDatos.add(lblID);

        lblNombreAlumno.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblNombreAlumno.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreAlumno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreAlumno.setToolTipText("");
        JpanelDatos.add(lblNombreAlumno);

        lblEdad.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblEdad.setForeground(new java.awt.Color(255, 255, 255));
        lblEdad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEdad.setToolTipText("");
        JpanelDatos.add(lblEdad);

        lblTelefono.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTelefono.setToolTipText("");
        JpanelDatos.add(lblTelefono);

        lblCorreo.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblCorreo.setForeground(new java.awt.Color(255, 255, 255));
        lblCorreo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCorreo.setToolTipText("");
        JpanelDatos.add(lblCorreo);

        panelBtnInscripciones.setBackground(new java.awt.Color(30, 47, 86));
        panelBtnInscripciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnVerInscritos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilerias/botones/btnVerClases.75.png"))); // NOI18N
        btnVerInscritos.setBorderPainted(false);
        btnVerInscritos.setContentAreaFilled(false);
        btnVerInscritos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerInscritosActionPerformed(evt);
            }
        });
        panelBtnInscripciones.add(btnVerInscritos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 140, 50));

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
        panelEditarBtn.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 120, 50));

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
        panelBtnEliminar.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 130, 50));

        JpanelDatos.add(panelBtnEliminar);

        JpanelTabla.add(JpanelDatos, java.awt.BorderLayout.CENTER);

        JpanelHeader.setBackground(new java.awt.Color(30, 47, 86));
        JpanelHeader.setLayout(new java.awt.GridLayout(1, 5));

        lblColumnNombre.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnNombre.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnNombre.setText("ID");
        JpanelHeader.add(lblColumnNombre);

        lblColumnNombre1.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnNombre1.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnNombre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnNombre1.setText("NOMBRE ALUMNO");
        JpanelHeader.add(lblColumnNombre1);

        lblColumnHorario.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnHorario.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnHorario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnHorario.setText("EDAD");
        JpanelHeader.add(lblColumnHorario);

        lblColumnMaestro.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnMaestro.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnMaestro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnMaestro.setText("TELEFONO");
        JpanelHeader.add(lblColumnMaestro);

        lblColumnCupo.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnCupo.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnCupo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnCupo.setText("CORREO");
        JpanelHeader.add(lblColumnCupo);

        lblColumnInscripciones.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnInscripciones.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnInscripciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnInscripciones.setText("CLASES");
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
        //Eliminar un alumno
        ControlNavegacion.eliminarAlumno(alumno);
        JOptionPane.showMessageDialog(panelEditarBtn, "Alumno Eliminado con exito");
        ControlNavegacion.mostrarFrmAdminAlumnos();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
       //Mostrar editar Alumno
        ControlNavegacion.mostrarFrmEditarAlumno(alumno);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnVerInscritosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerInscritosActionPerformed
        // TODO add your handling code here:
        // mostrar clases
    }//GEN-LAST:event_btnVerInscritosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpanelDatos;
    private javax.swing.JPanel JpanelHeader;
    private javax.swing.JPanel JpanelTabla;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnVerInscritos;
    private javax.swing.JLabel lblColumnCupo;
    private javax.swing.JLabel lblColumnEditar;
    private javax.swing.JLabel lblColumnEliminar;
    private javax.swing.JLabel lblColumnHorario;
    private javax.swing.JLabel lblColumnInscripciones;
    private javax.swing.JLabel lblColumnMaestro;
    private javax.swing.JLabel lblColumnNombre;
    private javax.swing.JLabel lblColumnNombre1;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblNombreAlumno;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JPanel panelBtnEliminar;
    private javax.swing.JPanel panelBtnInscripciones;
    private javax.swing.JPanel panelEditarBtn;
    // End of variables declaration//GEN-END:variables
}
