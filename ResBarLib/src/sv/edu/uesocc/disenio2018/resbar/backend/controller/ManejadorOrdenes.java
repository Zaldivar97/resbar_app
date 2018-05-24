package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorAplicacion;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Orden;

/**
 * ManejadorOrdenes. Clase encargada de los servicios para las órdenes, los
 * métodos de esta clase también serán STATIC.
 */
public class ManejadorOrdenes {

    private static EntityManager getEM() {
        return Persistence.createEntityManagerFactory("ResbarBackendPU").createEntityManager();
    }

    /**
     * Método: ObtenerActivas(): Orden[] Va a la base de datos y filtra todas
     * las ordenes cuyo campo Activa=TRUE, y devuelve un colección de objetos
     * Orden.
     */
    public static List<Orden> ObtenerActivas() {
        EntityManager eml = getEM();
        try {
            Query q = eml.createNamedQuery("Orden.findByEstado");
            q.setParameter("estado", true);
            return q.getResultList();
        } catch (Exception e) {
            throw new ErrorAplicacion("ManejadorOrdenes.ObtenerActivas()$Error al obtener ordenes activas");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    /**
     * Método: Obtener(:int):Orden Recibe un entero que indica el ID de la orden
     * y luego devuelve el objeto orden completo que corresponde.
     */
    public static Orden Obtener(int id) {
        EntityManager eml = getEM();
        try {
            Query q = eml.createNamedQuery("Orden.findByIdOrden");
            q.setParameter("idOrden", id);
            return (Orden) q.getSingleResult();
        } catch (Exception e) {
            throw new ErrorAplicacion("ManejadorOrdenes.Obtener(:int)$Error al obtener orden con id " + id);
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    /**
     * Método: Insertar(:orden) Inserta el objeto orden en la base de datos,
     * inserta una tupla en la tabla Orden y una o varias tuplas en la tabla
     * Detalle Orden, además verifica que la orden tenga al menos uno de los
     * campos con valor: mesero, mesa o cliente, no permite insertar ordenes con
     * un total de cero o negativo, o que NO posean ningún producto en su
     * detalle.
     */
    public static void Insertar(Orden orden) {
        if (!orden.mesero.isEmpty() || !orden.mesa.isEmpty() || !orden.cliente.isEmpty()) {
            if (orden.detalle != null) {
                if (orden.detalle.size() > 0) {
                    EntityManager eml = getEM();
                    EntityTransaction et = eml.getTransaction();
                    try {
                        if (!et.isActive()) {
                            et.begin();
                        }
                        eml.persist(orden);
                        et.commit();
                    } catch (Exception ex) {
                        if (et.isActive()) {
                            et.rollback();
                        }
                        throw new ErrorAplicacion("ManejadorOrdenes.Insertar(:orden)$Algo fallo intentando insertar un nueva orden");
                    } finally {
                        if (eml.isOpen()) {
                            eml.close();
                        }

                    }
                } else {
                    throw new ErrorAplicacion("ManejadorOrdenes.Insertar(:orden)$La orden debe tener al menos un producto");
                }
            } else {
                throw new ErrorAplicacion("ManejadorOrdenes.Insertar(:orden)$La orden debe tener al menos un producto");
            }
        } else {
            throw new ErrorAplicacion("ManejadorOrdenes.Insertar(:orden)$Al menos uno de los siguientes campos debe tener un valor: mesero, mesa, cliente");
        }
    }

    /**
     * Método: Eliminar(o: orden) Elimina dicha orden de la base de datos,
     * eliminando sus detalles también.
     */
    public static void Eliminar(Orden orden) {
        EntityManager eml = getEM();
        EntityTransaction trans = eml.getTransaction();
        try {
            if (!trans.isActive()) {
                trans.begin();
            }
            eml.remove(eml.merge(orden));

            trans.commit();

        } catch (Exception ex) {
            if (trans.isActive()) {
                trans.rollback();
            }
            throw new ErrorAplicacion("ManejadorOrdenes.Eliminar(:orden)$Error al eliminar orden");

        } finally {

            eml.close();
        }
    }

    /**
     * Método: Actualizar(o: orden) Toma un objeto orden que ya existe en la
     * tabla Orden de la base de datos, luego verifica que el objeto orden tenga
     * productos, que su total sea mayor que cero, en la base de datos hace
     * update de la tabla orden, y para la tabla Detalle Orden, lo que se hace
     * es que se eliminan las tuplas de dicha orden y luego se insertan de
     * nuevo.
     */
    public static void Actualizar(Orden orden) {

        if (!orden.mesero.isEmpty() || !orden.mesa.isEmpty() || !orden.cliente.isEmpty()) {
            if (orden.detalle != null) {

                EntityManager eml = getEM();
                EntityTransaction et = eml.getTransaction();
                try {
                    if (!et.isActive()) {
                        et.begin();
                    }
                    eml.merge(orden);
                    et.commit();
                } catch (Exception ex) {
                    if (et.isActive()) {
                        et.rollback();
                    }
                } finally {
                    if (eml.isOpen()) {
                        eml.close();
                    }
                }

            } else {
                throw new ErrorAplicacion("ManejadorOrdenes.Actualizar(:orden)$La orden debe tener al menos un producto");
            }
        } else {
            throw new ErrorAplicacion("ManejadorOrdenes.Actualizar(:orden)$Al menos uno de los siguientes campos debe tener un valor: mesero, mesa, cliente");
        }
    }

    /**
     * Método: ObtenerId(): integer Va a la base de datos y obtiene el ultimo Id
     * de orden y le suma 1.
     */
    public static int ObtenerId() {
        EntityManager eml = getEM();
        try {
            CriteriaQuery cq = eml.getCriteriaBuilder().createQuery(Integer.class);
            Root producto = cq.from(Orden.class);
            cq.select(eml.getCriteriaBuilder().max(producto.get("idOrden")));
            Query q = eml.createQuery(cq);
            return ((int) q.getSingleResult()) + 1;
        } catch (Exception e) {
            throw new ErrorAplicacion("ManejadorOrdenes.ObtenerId()$Error al obtener ID de Orden");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    /**
     * Método: BuscarActivas(:string):Orden[] Toma el string que tiene el
     * criterio de búsqueda y va a la base de datos a buscar todas aquellas
     * ordenes que cumplan con dicho criterio ya sea en el mesero, mesa, cliente
     * o comentario. Devuelve una colección de órdenes que cumplen con dicho
     * criterio sin duplicados.
     */
    public static List<Orden> BuscarActivas(String parametro) {
        EntityManager eml = getEM();
        try {
            Query q = eml.createNamedQuery("Orden.findByParametro");
            q.setParameter("parametro", parametro);
            return q.getResultList();
        } catch (Exception e) {
            throw new ErrorAplicacion("ManejadorOrdenes.BuscarActivas(:String)$Algo fallo intentando buscar ordenes activas");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    /**
     * Método: ObtenerVentas(:Date;Date) : Orden[] Igual que el metodo
     * ObtenerVentas(:Date): Orden[] pero filtrando por un rango de fechas.
     * Importante en este método es que los objetos Orden NO tienen cargado el
     * detalle de sus productos.
     */
    public static List<Orden> ObtenerVentas(Date inicio, Date fin) {
        EntityManager eml = getEM();
        try {
            Query q = eml.createNamedQuery("Orden.obtenerVentas");
            q.setParameter("inicio", inicio, TemporalType.DATE);
            q.setParameter("fin", fin, TemporalType.DATE);
            return q.getResultList();
        } catch (Exception e) {
            throw new ErrorAplicacion("ManejadorOrdenes.ObtenerVentas(:Date,:Date)$Error al obtenerVentas con fechas " + inicio + " - " + fin);
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    /**
     * Método: ObtenerVentas(:Date): Orden[] Obtiene todas las ventas realizadas
     * para una fecha determinada, se filtra solo por día mes y año, las ordenes
     * devueltas tienen que tener el campo activa en FALSE, pues son ordenes que
     * ya fueron cobradas.
     */
    public static List<Orden> ObtenerVentas(Date inicio) {
        return ManejadorOrdenes.ObtenerVentas(inicio, inicio);
    }
}
