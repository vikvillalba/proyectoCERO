
package FRMs.GestionarClases;

import javax.swing.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import javax.swing.JSpinner.NumberEditor;

public class CustomSpinner extends JSpinner {

    public enum Tipo {
        HORA, MINUTO
    }

    public CustomSpinner(Tipo tipo) {
        super(getModel(tipo));

        // Configurar el editor para mostrar ceros a la izquierda (dos dígitos)
        NumberFormat format = new DecimalFormat("00");
        NumberEditor editor = new NumberEditor(this, "00");
        this.setEditor(editor);
    }

    private static SpinnerNumberModel getModel(Tipo tipo) {
        if (tipo == Tipo.HORA) {
            return new SpinnerNumberModel(1, 1, 24, 1); // Horas: 1–24
        } else {
            return new SpinnerNumberModel(0, 0, 55, 5); // Minutos: 0–55 (de 5 en 5)
        }
    }

    public int getValor() {
        return (int) getValue();
    }
}
