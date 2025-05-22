package FRMs.GestionarClases;

import DTOs.GestionarClases.AlumnoClaseDTO;
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
public class JpanelNumeroListaAlumno extends javax.swing.JPanel {

    private AlumnoClaseDTO alumno;

    public JpanelNumeroListaAlumno(AlumnoClaseDTO alumno) {
        initComponents();
        this.alumno = alumno;
        setOpaque(false);

        JpanelDatos.revalidate();
        JpanelDatos.repaint();
        JpanelHeader.revalidate();
        JpanelHeader.repaint();

        String numLista = String.valueOf(alumno.getNumeroLista());
        
        lblNumeroLista.setText(numLista);
        
        lblAlumno.setText(alumno.getNombreAlumno());
        
        String id = String.valueOf(alumno.getCodigoAlumno());
        
        lblID.setText(id);
        
        configurarLabelDatos(lblID);
        
        configurarLabelDatos(lblAlumno);
        
        configurarLabelDatos(lblNumeroLista);
        
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
        lblNumeroLista = new javax.swing.JLabel();
        lblAlumno = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        JpanelHeader = new javax.swing.JPanel();
        lblColumnNumLista = new javax.swing.JLabel();
        lblColumnAlumno = new javax.swing.JLabel();
        lblColumnID = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JpanelTabla.setLayout(new java.awt.BorderLayout());

        JpanelDatos.setBackground(new java.awt.Color(30, 47, 86));
        JpanelDatos.setLayout(new java.awt.GridLayout(1, 5));

        lblNumeroLista.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblNumeroLista.setForeground(new java.awt.Color(255, 255, 255));
        lblNumeroLista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumeroLista.setToolTipText("");
        JpanelDatos.add(lblNumeroLista);

        lblAlumno.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblAlumno.setForeground(new java.awt.Color(255, 255, 255));
        lblAlumno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlumno.setToolTipText("");
        JpanelDatos.add(lblAlumno);

        lblID.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblID.setForeground(new java.awt.Color(255, 255, 255));
        lblID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblID.setToolTipText("");
        JpanelDatos.add(lblID);

        JpanelTabla.add(JpanelDatos, java.awt.BorderLayout.CENTER);

        JpanelHeader.setBackground(new java.awt.Color(30, 47, 86));
        JpanelHeader.setLayout(new java.awt.GridLayout(1, 5));

        lblColumnNumLista.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnNumLista.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnNumLista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnNumLista.setText("NUMERO DE LISTA");
        JpanelHeader.add(lblColumnNumLista);

        lblColumnAlumno.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnAlumno.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnAlumno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnAlumno.setText("ALUMNO");
        JpanelHeader.add(lblColumnAlumno);

        lblColumnID.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblColumnID.setForeground(new java.awt.Color(148, 197, 227));
        lblColumnID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColumnID.setText("ID");
        JpanelHeader.add(lblColumnID);

        JpanelTabla.add(JpanelHeader, java.awt.BorderLayout.PAGE_START);

        add(JpanelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 1140, 130));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpanelDatos;
    private javax.swing.JPanel JpanelHeader;
    private javax.swing.JPanel JpanelTabla;
    private javax.swing.JLabel lblAlumno;
    private javax.swing.JLabel lblColumnAlumno;
    private javax.swing.JLabel lblColumnID;
    private javax.swing.JLabel lblColumnNumLista;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblNumeroLista;
    // End of variables declaration//GEN-END:variables
}
