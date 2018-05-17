package sv.edu.uesocc.disenio2018.resbar.backend.manejadores;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sv.edu.uesocc.disenio2018.resbar.backend.Orden;
import sv.edu.uesocc.disenio2018.resbar.backend.Parametro;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ParametroJpaController;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorApplication;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.NonexistentEntityException;

/**
 *
 * @author gochez
 */
public class ManejadorParametros {

    private EntityManagerFactory EMF = Persistence.createEntityManagerFactory("ResbarBackendPU");

    private ParametroJpaController controlador = new ParametroJpaController(EMF);

    public List<Parametro> Obtener() {
        return controlador.Obtener();
    }

    public List<Parametro> ObtenerXId(int parametro) {
        return controlador.ObtenerPorId(parametro);
    }
    public  void actualizar(Parametro parametro) {
        try {
            controlador.actualizar(parametro);
        } catch (NonexistentEntityException ex) {
            throw new ErrorApplication("Entidad no existente");
        } catch (Exception ex) {
            throw new ErrorApplication("Error al actualizar parametro");
        }
    }
}
