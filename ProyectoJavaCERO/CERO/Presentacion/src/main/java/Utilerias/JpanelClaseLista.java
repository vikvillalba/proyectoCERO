/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Utilerias;

import com.mycompany.negocio.dtos.ClaseDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jack Murrieta
 */
public class JpanelClaseLista extends javax.swing.JPanel {
    
    private ClaseDTO clase;

    public JpanelClaseLista(ClaseDTO clase) {
        initComponents();
        this.clase = clase;
        setOpaque(false);
        JpanelDatos.removeAll();
        JpanelDatos.revalidate();
        JpanelDatos.repaint();
        JpanelHeader.removeAll();
        configurarHeader();
        configurarDatos();
        
    }


    // 👉 MÉTODO PARA CONFIGURAR EL ENCABEZADO
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
    

    // 👉 MÉTODO PARA CONFIGURAR LOS DATOS
    private void configurarDatos() {
        String id= String.valueOf(clase.getCodigo());
        String horario = String.valueOf(clase.getHoraInicio())+ "-" + String.valueOf(clase.getHoraFin());
        String dias = clase.getDias().toString();
        
        JpanelDatos.setBackground(new Color(30, 47, 86));
        JpanelDatos.setLayout(new GridLayout(1, 5, 10, 0));

        lblIdClase = new JLabel(id, SwingConstants.CENTER);
        lblNombreClase = new JLabel(clase.getNombre(), SwingConstants.CENTER);
        lblHorario = new JLabel(horario, SwingConstants.CENTER);
        lblDias = new JLabel(dias, SwingConstants.CENTER);
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

    // 👉 MÉTODO PARA CONFIGURAR EL BOTÓN
    private void configurarBoton() {
        btnSeleccionarClase.setText("Seleccionar clase");
        btnSeleccionarClase.setFont(new Font("Menlo", Font.BOLD, 14));
        btnSeleccionarClase.setForeground(Color.WHITE);
        btnSeleccionarClase.setBackground(new Color(30, 47, 86));
        btnSeleccionarClase.setFocusPainted(false);
    }

    // 👉 MÉTODO PARA CONFIGURAR LOS LABELS DEL HEADER
    private void configurarLabel(JLabel label) {
        label.setFont(new Font("Menlo", Font.BOLD, 14));
        label.setForeground(new Color(148, 197, 227));
        label.setOpaque(true);
        label.setBackground(new Color(30, 47, 86));
        label.setPreferredSize(new Dimension(150, 40)); // Espacio suficiente para que no se encimen
    }

    // 👉 MÉTODO PARA CONFIGURAR LOS LABELS DE DATOS
    private void configurarLabelDatos(JLabel label) {
        label.setFont(new Font("Menlo", Font.PLAIN, 14));
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        label.setBackground(new Color(30, 47, 86));
        label.setPreferredSize(new Dimension(150, 40)); // Tamaño uniforme para datos
    }

    // Método main para ejecutar la interfaz gráfica
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Lista de Clases");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1200, 500);
                // clase para probar. borrar después. 
                List<DayOfWeek> dias = Arrays.asList(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY);
                ClaseDTO clase = new ClaseDTO(1, "Contemporáneo principiante", dias, LocalTime.of(18, 00), LocalTime.of(19, 15), "César Díaz", new BigDecimal(500.00));
                frame.add(new JpanelClaseLista(clase));
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpanelHeader = new javax.swing.JPanel();
        lblColumnID = new javax.swing.JLabel();
        lblColumnNombre = new javax.swing.JLabel();
        lblColumnHorario = new javax.swing.JLabel();
        lblColumnDias = new javax.swing.JLabel();
        lblColumnMaestro = new javax.swing.JLabel();
        JpanelDatos = new javax.swing.JPanel();
        lblIdClase = new javax.swing.JLabel();
        lblNombreClase = new javax.swing.JLabel();
        lblHorario = new javax.swing.JLabel();
        lblDias = new javax.swing.JLabel();
        lblMaestro = new javax.swing.JLabel();
        btnSeleccionarClase = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));

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

        JpanelDatos.setBackground(new java.awt.Color(30, 47, 86));
        JpanelDatos.setLayout(new java.awt.GridLayout(1, 5));

        lblIdClase.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblIdClase.setForeground(new java.awt.Color(255, 255, 255));
        lblIdClase.setText("1");
        lblIdClase.setToolTipText("");
        JpanelDatos.add(lblIdClase);

        lblNombreClase.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblNombreClase.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreClase.setText("Danza Contemporanea");
        lblNombreClase.setToolTipText("");
        JpanelDatos.add(lblNombreClase);

        lblHorario.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblHorario.setForeground(new java.awt.Color(255, 255, 255));
        lblHorario.setText("7:00 pm - 8:00 pm");
        lblHorario.setToolTipText("");
        JpanelDatos.add(lblHorario);

        lblDias.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblDias.setForeground(new java.awt.Color(255, 255, 255));
        lblDias.setText("1");
        lblDias.setToolTipText("");
        JpanelDatos.add(lblDias);

        lblMaestro.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        lblMaestro.setForeground(new java.awt.Color(255, 255, 255));
        lblMaestro.setText("1");
        lblMaestro.setToolTipText("");
        JpanelDatos.add(lblMaestro);

        btnSeleccionarClase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilerias/botones/btnSeleccionarClase.png"))); // NOI18N
        btnSeleccionarClase.setContentAreaFilled(false);
        btnSeleccionarClase.setFocusPainted(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JpanelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JpanelDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnSeleccionarClase, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JpanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JpanelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionarClase, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpanelDatos;
    private javax.swing.JPanel JpanelHeader;
    private javax.swing.JButton btnSeleccionarClase;
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
