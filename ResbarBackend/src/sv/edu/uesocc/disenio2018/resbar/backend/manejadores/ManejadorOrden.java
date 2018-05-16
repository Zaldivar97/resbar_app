package sv.edu.uesocc.disenio2018.resbar.backend.manejadores;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sv.edu.uesocc.disenio2018.resbar.backend.Orden;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.OrdenJpaController;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorApplication;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.NonexistentEntityException;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.PreexistingEntityException;

/**
 *
 * @author danm
 */
public class ManejadorOrden {

    private EntityManagerFactory EMF = Persistence.createEntityManagerFactory("ResbarBackendPU");

    private OrdenJpaController controlador = new OrdenJpaController(EMF);

    public  List<Orden> obtenerActivas() {
        return controlador.obtenerActivas();
    }

    public  void actualizar(Orden orden) {
        try {
            controlador.edit(orden);
        } catch (NonexistentEntityException ex) {
            throw new ErrorApplication("Entidad no existente");
        } catch (Exception ex) {
            throw new ErrorApplication("Error al actualizar orden");
        }
    }

    public  List<Orden> buscarActivas(String parametro) {
        if (!parametro.isEmpty()) {
            return controlador.buscarActivas(parametro);
        }
        throw new ErrorApplication("Falta el parámetro de búsqueda");
    }

    public  void insertar(Orden orden) {

        if (!orden.getMesero().isEmpty() || !orden.getMesa().isEmpty() || !orden.getCliente().isEmpty()) {
            if (orden.getDetalleOrdenList() != null) {
                try {
                    controlador.create(orden);
                } catch (PreexistingEntityException ex) {
                    Logger.getLogger(ManejadorOrden.class.getName()).log(Level.SEVERE, null, ex);
                    throw new ErrorApplication("Orden ya existente");
                } catch (Exception ex) {
                    throw new ErrorApplication("Error al crear la orden\n" + ex.getMessage());
                }
                
            }else{
                throw new ErrorApplication("La orden debe tener al menos un producto");
            }
        } else {
            throw new ErrorApplication("Al menos uno de los siguientes campos debe tener un valor: mesero, mesa, cliente");
        }
    }

    public  void eliminar(Orden orden) {

        try {
            controlador.destroy(orden.getIdOrden());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ManejadorOrden.class.getName()).log(Level.SEVERE, null, ex);
            throw new ErrorApplication("La orden con id " + orden.getIdOrden() + " no existe");
        }

    }

    public  int obtenerId() {
        return controlador.obtenerId() + 1;
    }

    public  List<Orden> ObtenerVentas(Date inicio, Date fin) {
        return controlador.obtenerVentas(inicio, fin);
    }

}
