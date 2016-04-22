package es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento;

import java.util.ArrayList;

import es.ucm.as_tutor.negocio.factoria.FactoriaSA;
import es.ucm.as_tutor.negocio.suceso.SASuceso;
import es.ucm.as_tutor.negocio.suceso.TransferEvento;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.exceptions.commandException;

/**
 * Created by Jeffer on 15/04/2016.
 */
public class CrearEventoComando implements Command {
    @Override
    public Object ejecutaComando(Object datos) throws commandException {
        SASuceso sa = FactoriaSA.getInstancia().nuevoSASuceso();
        return sa.crearEvento((TransferEvento) datos);
    }
}