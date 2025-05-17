/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRMs.GestionarClases;

/**
 *
 * @author Jack Murrieta
 */
import javax.swing.*;
import javax.swing.JSpinner.NumberEditor;

public class CustomCapacidadSpinner extends JSpinner {

    public CustomCapacidadSpinner() {
        // Modelo de spinner: valor inicial 1, mínimo 1, máximo 18, paso 1
        super(new SpinnerNumberModel(5, 5, 18, 1));

        //formato normal
        NumberEditor editor = new NumberEditor(this, "0");
        this.setEditor(editor);

        // Ajustar ancho del campo
        JFormattedTextField textField = editor.getTextField();
        textField.setColumns(2);
    }

    public int getValor() {
        return (int) getValue();
    }

    public void setValor(int valor) {
        setValue(valor);
    }
}
