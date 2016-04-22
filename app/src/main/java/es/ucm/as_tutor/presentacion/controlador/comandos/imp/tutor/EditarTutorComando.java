package es.ucm.as_tutor.presentacion.controlador.comandos.imp.tutor;

import es.ucm.as_tutor.negocio.factoria.FactoriaSA;
import es.ucm.as_tutor.negocio.tutor.SATutor;
import es.ucm.as_tutor.negocio.tutor.TransferTutorT;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.exceptions.commandException;

/**
 * Created by msalitu on 15/04/2016.
 */
public class EditarTutorComando implements Command{
    @Override
    public Object ejecutaComando(Object datos) throws commandException {
        SATutor saTutor = FactoriaSA.getInstancia().nuevoSATutor();
        saTutor.editarTutor((TransferTutorT) datos);
        return null;
    }
}