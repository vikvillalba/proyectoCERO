package FRMs.registroAsistencia;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.presentacion.ControlNavegacion;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author daniel
 */
public class FrmReporteAsistencias extends javax.swing.JFrame {

    private Image imagenFondo;

    public FrmReporteAsistencias() {
        initComponents();
        this.txtIdAlumno.setForeground(Color.GRAY);
        this.txtIdAlumno.setText("ingresa nombre clase...");
        this.setTitle("Reporte de asistencias");

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
        getContentPane().add(pnlFondo, new AbsoluteConstraints(0, 0, getWidth(), getHeight()));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    
//    private void llenarTabla(List<ClienteReporteDTO> clientes) {
//        if (clientes == null) {
//            return;
//        }
//
//        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblReporte.getModel();
//        if (modeloTabla.getRowCount() > 0) {
//            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
//                modeloTabla.removeRow(i);
//            }
//        }
//        clientes.forEach(row -> {
//            Object[] fila = new Object[5];
//            fila[0] = row.getNombreCompleto();
//            fila[1] = row.getNumeroVisitas();
//            fila[2] = row.getTotalGastado();
//            fila[3] = row.getPuntosFidelidad();
//            fila[4] = convertirFecha(row.getUltimaComanda());
//
//            modeloTabla.addRow(fila);
//        });
//    }

//    private void cargarDatos() {
//        String nombre = this.txtNombre.getText();
//        int visitas = (int) numeroVisitas.getValue();
//        List<ClienteReporteDTO> clientes = comandasBO.obtenerReporteClientesFrecuentes(nombre, visitas);
//
//        if (clientes.isEmpty()) {
//             JOptionPane.showMessageDialog(null, "No se encontraron registros relacionados", "Sin registros.", JOptionPane.INFORMATION_MESSAGE);
//             this.txtNombre.setText(" ");
//        }
//        llenarTabla(clientes);
//    }
    
        private String convertirFecha(Calendar fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(fecha.getTime());
    }

    public static BigDecimal convertToBigDecimal(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("El objeto no puede ser nulo.");
        }

        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;  // Ya es un BigDecimal
        } else if (obj instanceof String) {
            return new BigDecimal((String) obj);  // Convertir desde String
        } else if (obj instanceof Number) {
            return BigDecimal.valueOf(((Number) obj).doubleValue());  // Convertir desde Number
        } else {
            throw new IllegalArgumentException("El objeto no se puede convertir a BigDecimal.");
        }
    }

    private void addTableHeader(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(new BaseColor(65, 70, 105));
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    private void addTableCell(PdfPTable table, String text) {
        addTableCell(table, text, Element.ALIGN_LEFT);
    }

    private void addTableCell(PdfPTable table, String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        table.addCell(cell);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtIdAlumno = new javax.swing.JTextField();
        btnGenerarReporte = new javax.swing.JButton();
        fechaFin = new com.toedter.calendar.JDateChooser();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        cbxFecha = new javax.swing.JCheckBox();
        cbxIdAlumno = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReporte = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();
        btnGuardarReporte = new javax.swing.JButton();

        setBackground(new java.awt.Color(148, 197, 227));
        setPreferredSize(new java.awt.Dimension(1280, 832));
        getContentPane().setLayout(null);

        jLabel9.setFont(new java.awt.Font("Menlo", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(30, 47, 86));
        jLabel9.setText("a");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(710, 160, 30, 40);

        jLabel10.setFont(new java.awt.Font("Menlo", 1, 55)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(30, 47, 86));
        jLabel10.setText("REPORTE DE ASISTENCIAS");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(290, 40, 750, 65);

        txtIdAlumno.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtIdAlumno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdAlumno.setBorder(null);
        txtIdAlumno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIdAlumnoMouseClicked(evt);
            }
        });
        getContentPane().add(txtIdAlumno);
        txtIdAlumno.setBounds(120, 160, 300, 40);

        btnGenerarReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilerias/botones/btnGenerarReporte.png"))); // NOI18N
        btnGenerarReporte.setBorderPainted(false);
        btnGenerarReporte.setContentAreaFilled(false);
        btnGenerarReporte.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGenerarReporte.setFocusPainted(false);
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });
        getContentPane().add(btnGenerarReporte);
        btnGenerarReporte.setBounds(950, 140, 220, 70);
        getContentPane().add(fechaFin);
        fechaFin.setBounds(740, 160, 150, 40);
        getContentPane().add(fechaInicio);
        fechaInicio.setBounds(540, 160, 150, 40);

        cbxFecha.setFont(new java.awt.Font("Menlo", 1, 24)); // NOI18N
        cbxFecha.setForeground(new java.awt.Color(30, 47, 86));
        cbxFecha.setText("Fecha");
        getContentPane().add(cbxFecha);
        cbxFecha.setBounds(540, 120, 120, 33);

        cbxIdAlumno.setFont(new java.awt.Font("Menlo", 1, 24)); // NOI18N
        cbxIdAlumno.setForeground(new java.awt.Color(30, 47, 86));
        cbxIdAlumno.setText("ID del alumno");
        getContentPane().add(cbxIdAlumno);
        cbxIdAlumno.setBounds(120, 120, 270, 33);

        tblReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID alumno", "Nombre", "Fecha clase", "Asistencia", "Justificación"
            }
        ));
        tblReporte.setPreferredSize(new java.awt.Dimension(450, 400));
        tblReporte.setSize(new java.awt.Dimension(450, 400));
        jScrollPane1.setViewportView(tblReporte);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(50, 230, 1200, 480);

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilerias/botones/btnRegresar.png"))); // NOI18N
        btnRegresar.setBorderPainted(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegresar.setFocusPainted(false);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegresar);
        btnRegresar.setBounds(300, 710, 330, 80);

        btnGuardarReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilerias/botones/btnGuardarReporte.png"))); // NOI18N
        btnGuardarReporte.setBorderPainted(false);
        btnGuardarReporte.setContentAreaFilled(false);
        btnGuardarReporte.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarReporte.setFocusPainted(false);
        btnGuardarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarReporteActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardarReporte);
        btnGuardarReporte.setBounds(660, 710, 330, 80);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
//        Date actual = new Date();
//        if (fechaInicio.getDate() == null || fechaFin.getDate() == null) {
//            JOptionPane.showMessageDialog(null, "Se debe de seleccionar una fecha", "Error", JOptionPane.ERROR_MESSAGE);
//        } else if (fechaInicio.getDate().after(actual)) {
//            JOptionPane.showMessageDialog(null, "La fecha de inicio del rango no puede estar después de la fecha actual.", "Error", JOptionPane.ERROR_MESSAGE);
//        } else {
//            cargarDatos();
//        }
    }//GEN-LAST:event_btnGenerarReporteActionPerformed


    private void txtIdAlumnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIdAlumnoMouseClicked

        txtIdAlumno.setText("");
        txtIdAlumno.setForeground(Color.BLACK);

    }//GEN-LAST:event_txtIdAlumnoMouseClicked

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnGuardarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarReporteActionPerformed
        String path = "";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(this);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();
        }

        if (path.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se seleccionó ninguna carpeta.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String fullPath = String.format("%s/ReporteCliente.pdf", path);
        Document doc = new Document();
        File file = new File(fullPath);

        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(fullPath));
            doc.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new BaseColor(65, 70, 105));
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
            Font totalFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, new BaseColor(227, 148, 147));
            Font subtitleFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.DARK_GRAY);

            Paragraph title = new Paragraph("Reporte de Cliente frecuente", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            doc.add(title);

//            Paragraph valores = new Paragraph(
//                    "Nombre: " + txtNombre.getText() + " Visitas mínimas: " + (int) numeroVisitas.getValue(),
//                    subtitleFont
//            );
//            valores.setAlignment(Element.ALIGN_CENTER);
//            doc.add(valores);

            doc.add(Chunk.NEWLINE);

            PdfPTable tbl = new PdfPTable(5);
            tbl.setWidthPercentage(100);
            tbl.setSpacingBefore(15f);
            tbl.setSpacingAfter(15f);

            float[] columnWidths = {1.2f, 2.5f, 1.5f, 2f, 1.5f};
            tbl.setWidths(columnWidths);

            addTableHeader(tbl, "Nombre", headerFont);
            addTableHeader(tbl, "Número de visitas", headerFont);
            addTableHeader(tbl, "Total gastado", headerFont);
            addTableHeader(tbl, "Puntos de fidelidad", headerFont);
            addTableHeader(tbl, "Última comanda", headerFont);

            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

            for (int i = 0; i < tblReporte.getRowCount(); i++) {
                addTableCell(tbl, tblReporte.getValueAt(i, 0).toString());
                addTableCell(tbl, tblReporte.getValueAt(i, 1).toString());
                BigDecimal total = convertToBigDecimal(tblReporte.getValueAt(i, 2));
                String formattedTotal = currencyFormat.format(total);
                addTableCell(tbl, formattedTotal, Element.ALIGN_RIGHT);
                addTableCell(tbl, tblReporte.getValueAt(i, 3).toString());

                addTableCell(tbl, tblReporte.getValueAt(i, 4).toString());

            }

            doc.add(tbl);

            JOptionPane.showMessageDialog(this, "Documento generado con éxito!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (doc != null && doc.isOpen()) {
                doc.close();
            }
        }

        if (Desktop.isDesktopSupported() && file.exists()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "El PDF se generó pero no se pudo abrir: " + ex.getMessage(),
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarReporteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JButton btnGuardarReporte;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JCheckBox cbxFecha;
    private javax.swing.JCheckBox cbxIdAlumno;
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReporte;
    private javax.swing.JTextField txtIdAlumno;
    // End of variables declaration//GEN-END:variables
}
