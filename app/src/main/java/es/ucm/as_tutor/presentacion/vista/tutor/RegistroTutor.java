package es.ucm.as_tutor.presentacion.vista.tutor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.negocio.tutor.TransferTutorT;
import es.ucm.as_tutor.presentacion.controlador.Controlador;
import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.vista.main.MainActivity;

public class RegistroTutor extends Activity {

    private EditText correoTutor;
    private EditText constrasenha;
    private EditText nombreTutor;
    private ImageButton info;
    private static final String PATRON_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_registro);
        nombreTutor = (EditText) findViewById(R.id.nombreTutor);
        correoTutor = (EditText) findViewById(R.id.emailTutor);
        constrasenha = (EditText) findViewById(R.id.contrasenha);
    }

    public void realizarRegistro(View v) {
        //Leer los datos del "fomulario"
        String correo = String.valueOf(correoTutor.getText());
        String clave = String.valueOf(constrasenha.getText());
        String nombre = String.valueOf(nombreTutor.getText());
        String codigoSync = nombre.substring(0, 3);

        if (datosValidos(codigoSync, correo, clave)) {
            TransferTutorT tutor = new TransferTutorT();
            tutor.setNombre(nombre);
            tutor.setCorreo(correo);
            tutor.setContrasenha(clave);
            tutor.setCodigoSincronizacion(codigoSync);

            Controlador.getInstancia().ejecutaComando(ListaComandos.CREAR_TUTOR, tutor);
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }
    }

    private boolean datosValidos(String nombre, String correo, String clave) {
        if (!nombre.toString().matches("") &&
                !correo.toString().matches("") &&
                !clave.toString().matches("")) {
            if (correo.toString().matches(PATRON_EMAIL)) {
                return true;
            } else
                mostrarMensajeError("Campo email inválido");
        } else
            mostrarMensajeError("Algún campo es vacío");

        return false;
    }


    private void mostrarMensajeError(String msg) {
        Toast errorNombre =
                Toast.makeText(getApplicationContext(),
                        msg, Toast.LENGTH_SHORT);
        errorNombre.show();
    }

}
