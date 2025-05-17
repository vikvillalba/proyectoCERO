/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRMs.GestionarClases;

import javax.swing.*;
import javax.swing.JSpinner.NumberEditor;
import java.text.DecimalFormat;

public class CustomPrecioSpinner extends JSpinner {

    public CustomPrecioSpinner() {
        super(new SpinnerNumberModel(100.0, 100.0, Double.MAX_VALUE, 0.5));

        // Editor personalizado con formato decimal para dos decimales
        NumberEditor editor = new NumberEditor(this, "#,##0.00");
        this.setEditor(editor);

        // Evita errores al usar Double.MAX_VALUE mostrando solo un rango razonable
        JFormattedTextField textField = ((NumberEditor) getEditor()).getTextField();
        textField.setColumns(10); // Para que tenga buen ancho por defecto
    }

    public double getPrecio() {
        return (double) getValue();
    }

    public void setPrecio(double precio) {
        setValue(precio);
    }
}
