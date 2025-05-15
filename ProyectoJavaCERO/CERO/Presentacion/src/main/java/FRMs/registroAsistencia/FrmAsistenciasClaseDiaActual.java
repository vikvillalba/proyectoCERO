package FRMs.registroAsistencia;

import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.AsistenciaDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.InscripcionDTO;
import com.mycompany.dtos.TipoAsistenciaDTO;
import com.mycompany.presentacion.ControlNavegacion;
import java.awt.Graphics;
import java.awt.Image;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author victoria
 */
public class FrmAsistenciasClaseDiaActual extends javax.swing.JFrame {

    private Image imagenFondo;
    private LocalDate fecha;
    private ClaseDTO clase;
    private List<AsistenciaDTO> asistencias;
    private List<InscripcionDTO> inscripciones;

    public FrmAsistenciasClaseDiaActual(ClaseDTO clase, LocalDate fecha, List<AsistenciaDTO> asistencias, List<InscripcionDTO> inscripciones) {
        initComponents();
        jScrollAsistencias.setOpaque(false);
        this.clase = clase;
        this.fecha = fecha;
        this.asistencias = asistencias;
        this.inscripciones = inscripciones;

        this.setTitle("Asistencias de clase actual");
        this.imagenFondo = new ImageIcon(getClass().getResource("/Utilerias/FondoCERO.jpeg")).getImage();
        JPanel pnlFondo = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            }
        };

        getContentPane().setLayout(new AbsoluteLayout());
        pack();
        this.setSize(1100, 661);
        getContentPane().add(pnlFondo, new AbsoluteConstraints(0, 0, getWidth(), getHeight()));

        pack();

        this.setLocationRelativeTo(null);
        llenarAlumnos();
        mostrarDatos();

    }

    private void mostrarDatos() {
        DateTimeFormatter formatoDia = DateTimeFormatter.ofPattern("EEEE", Locale.forLanguageTag("es-ES"));
        DateTimeFormatter formatoMes = DateTimeFormatter.ofPattern("MMMM", Locale.forLanguageTag("es-ES"));
        String fechaTexto = this.fecha.format(formatoDia) + " " + String.valueOf(this.fecha.getDayOfMonth()) + " de " + this.fecha.format(formatoMes);
        this.lblFecha.setText(fechaTexto.toUpperCase());

        lblIDClase.setText(Integer.toString(clase.getCodigo()));
        lblNombreClase.setText(clase.getNombre());

        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        List<DayOfWeek> diasSemana = clase.getDias();
        StringBuilder diasAbreviados = new StringBuilder();

        for (DayOfWeek dia : diasSemana) {
            diasAbreviados.append(
                    dia.getDisplayName(TextStyle.SHORT, Locale.of("es"))
            );
        }

        String horario = String.format("%s %s %s",
                diasAbreviados.toString(),
                clase.getHoraInicio().format(formatoHora),
                clase.getHoraFin().format(formatoHora));

        lblHorarioClase.setText(horario);
        lblMaestro.setText(clase.getMaestro());
    }

    private void llenarAlumnos() {
        JPanel contenedorAsistencias = new JPanel();
        contenedorAsistencias.setOpaque(false);
        jScrollAsistencias.getViewport().setOpaque(false);
        contenedorAsistencias.setLayout(new BoxLayout(contenedorAsistencias, BoxLayout.Y_AXIS));
        
        for (InscripcionDTO inscripcion : inscripciones) {
            AlumnoDTO alumno = inscripcion.getAlumno();
            AsistenciaDTO asistenciaAlumno = null;

            for (AsistenciaDTO asistencia : asistencias) {
                if (asistencia.getAlumno().getCodigo().equals(alumno.getCodigo())) {
                    asistenciaAlumno = asistencia;
                    break;
                }
            }
            if (asistenciaAlumno == null) {
                asistenciaAlumno = new AsistenciaDTO(alumno, clase, TipoAsistenciaDTO.ASISTENCIA, LocalDateTime.now());
            }

            PnlAsistenciaEditable pnl = new PnlAsistenciaEditable(asistenciaAlumno);
            contenedorAsistencias.add(Box.createVerticalStrut(10));
            contenedorAsistencias.add(pnl);
        }
        jScrollAsistencias.setViewportView(contenedorAsistencias);
        jScrollAsistencias.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollAsistencias.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        contenedorAsistencias.revalidate();
        contenedorAsistencias.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFecha = new javax.swing.JLabel();
        jScrollAsistencias = new javax.swing.JScrollPane();
        btnRegresar = new javax.swing.JButton();
        PanelDatosClase = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblIDClase = new javax.swing.JTextField();
        lblHorarioClase = new javax.swing.JTextField();
        lblMaestro = new javax.swing.JTextField();
        lblNombreClase = new javax.swing.JTextField();
        btnRegistrarAsistencias = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblFecha.setFont(new java.awt.Font("Menlo", 1, 48)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(30, 47, 86));
        lblFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFecha.setText("MIERCOLES 00 SEPTIEMBRE");

        jScrollAsistencias.setBorder(null);
        jScrollAsistencias.setPreferredSize(new java.awt.Dimension(1041, 2));

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilerias/botones/btnRegresar.png"))); // NOI18N
        btnRegresar.setBorderPainted(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        PanelDatosClase.setBackground(new java.awt.Color(30, 47, 86));
        PanelDatosClase.setForeground(new java.awt.Color(30, 47, 86));

        jLabel3.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(148, 197, 227));
        jLabel3.setText("ID CLASE");

        jLabel6.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(148, 197, 227));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("NOMBRE CLASE");

        jLabel7.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(148, 197, 227));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("HORARIO");

        jLabel8.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(148, 197, 227));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("MAESTRO");

        lblIDClase.setEditable(false);
        lblIDClase.setBackground(new java.awt.Color(30, 47, 86));
        lblIDClase.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblIDClase.setForeground(new java.awt.Color(255, 255, 255));
        lblIDClase.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblIDClase.setText("ID");
        lblIDClase.setBorder(null);

        lblHorarioClase.setEditable(false);
        lblHorarioClase.setBackground(new java.awt.Color(30, 47, 86));
        lblHorarioClase.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHorarioClase.setForeground(new java.awt.Color(255, 255, 255));
        lblHorarioClase.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblHorarioClase.setText("NOMBRE");
        lblHorarioClase.setBorder(null);

        lblMaestro.setEditable(false);
        lblMaestro.setBackground(new java.awt.Color(30, 47, 86));
        lblMaestro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaestro.setForeground(new java.awt.Color(255, 255, 255));
        lblMaestro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblMaestro.setText("MAESTRO");
        lblMaestro.setBorder(null);

        lblNombreClase.setEditable(false);
        lblNombreClase.setBackground(new java.awt.Color(30, 47, 86));
        lblNombreClase.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNombreClase.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreClase.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblNombreClase.setText("NOMBRE");
        lblNombreClase.setBorder(null);

        javax.swing.GroupLayout PanelDatosClaseLayout = new javax.swing.GroupLayout(PanelDatosClase);
        PanelDatosClase.setLayout(PanelDatosClaseLayout);
        PanelDatosClaseLayout.setHorizontalGroup(
            PanelDatosClaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosClaseLayout.createSequentialGroup()
                .addGroup(PanelDatosClaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosClaseLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDatosClaseLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblIDClase, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(PanelDatosClaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosClaseLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(187, 187, 187)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114))
                    .addGroup(PanelDatosClaseLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNombreClase, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblHorarioClase, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(lblMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
        );
        PanelDatosClaseLayout.setVerticalGroup(
            PanelDatosClaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosClaseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDatosClaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosClaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDClase, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHorarioClase, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreClase, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btnRegistrarAsistencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilerias/botones/btnRegistrarAsistencias.png"))); // NOI18N
        btnRegistrarAsistencias.setBorderPainted(false);
        btnRegistrarAsistencias.setContentAreaFilled(false);
        btnRegistrarAsistencias.setFocusPainted(false);
        btnRegistrarAsistencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarAsistenciasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegistrarAsistencias, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollAsistencias, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 814, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelDatosClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(PanelDatosClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollAsistencias, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegistrarAsistencias)
                    .addComponent(btnRegresar))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed

        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnRegistrarAsistenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarAsistenciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarAsistenciasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelDatosClase;
    private javax.swing.JButton btnRegistrarAsistencias;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollAsistencias;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JTextField lblHorarioClase;
    private javax.swing.JTextField lblIDClase;
    private javax.swing.JTextField lblMaestro;
    private javax.swing.JTextField lblNombreClase;
    // End of variables declaration//GEN-END:variables
}
