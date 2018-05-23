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
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorApplication;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Orden;

/**
 * @author zaldivar
 */
public class ManejadorOrdenes {

    private static EntityManager getEM() {
        return Persistence.createEntityManagerFactory("ResbarBackendPU").createEntityManager();
    }

    public static List<Orden> ObtenerActivas() {
        EntityManager eml = getEM();
        try {
            Query q = eml.createNamedQuery("Orden.findByEstado");
            q.setParameter("estado", true);
            return q.getResultList();
        } catch (Exception e) {
            throw new ErrorApplication("Error al obtener ordenes activas --> $ManejadorOrden.obtenerActivas() \n" + e.getMessage());
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    public static Orden Obtener(int id) {
        EntityManager eml = getEM();
        try {
            Query q = eml.createNamedQuery("Orden.findByIdOrden");
            q.setParameter("idOrden", id);
            return (Orden) q.getSingleResult();
        } catch (Exception e) {
            throw new ErrorApplication("Error al obtener orden con id " + id + " --> $ManejadorOrden.obtenerVentas()");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

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
                        throw new ErrorApplication("Algo fallo intentando insertar un nueva orden --> $ManejadorOrden.insertar() ---> " + ex.getMessage());
                    } finally {
                        if (eml.isOpen()) {
                            eml.close();
                        }

                    }
                } else {
                    throw new ErrorApplication("La orden debe tener al menos un producto --> $ManejadorOrden.insertar()");
                }
            } else {
                throw new ErrorApplication("La orden debe tener al menos un producto --> $ManejadorOrden.insertar()");
            }
        } else {
            throw new ErrorApplication("Al menos uno de los siguientes campos debe tener un valor: mesero, mesa, cliente --> $ManejadorOrden.insertar()");
        }
    }

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
            throw new ErrorApplication("Error al eliminar orden  --> $ManejadorOrden.eliminar()");

        } finally {

            eml.close();
        }
    }

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
                throw new ErrorApplication("La orden debe tener al menos un producto");
            }
        } else {
            throw new ErrorApplication("Al menos uno de los siguientes campos debe tener un valor: mesero, mesa, cliente");
        }
    }

    public static int ObtenerId() {
        EntityManager eml = getEM();
        try {
            CriteriaQuery cq = eml.getCriteriaBuilder().createQuery(Integer.class);
            Root producto = cq.from(Orden.class);
            cq.select(eml.getCriteriaBuilder().max(producto.get("idOrden")));
            Query q = eml.createQuery(cq);
            return ((int) q.getSingleResult()) + 1;
        } catch (Exception e) {
            throw new ErrorApplication("Error al obtenerID de Orden --> $ManejadorOrden.obtenerID()");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    public static List<Orden> BuscarActivas(String parametro) {
        EntityManager eml = getEM();
        try {
            Query q = eml.createNamedQuery("Orden.findByParametro");
            q.setParameter("parametro", parametro);
            return q.getResultList();
        } catch (Exception e) {
            throw new ErrorApplication("Algo fallo intentando buscar ordenes activas --> $ManejadorOrden.buscarActivas(String parametro)");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    public static List<Orden> ObtenerVentas(Date inicio, Date fin) {
        EntityManager eml = getEM();
        try {
            Query q = eml.createNamedQuery("Orden.obtenerVentas");
            q.setParameter("inicio", inicio, TemporalType.DATE);
            q.setParameter("fin", fin, TemporalType.DATE);
            return q.getResultList();
        } catch (Exception e) {
            throw new ErrorApplication("Error al obtenerVentas con fechas " + inicio + " - " + fin + " --> $ManejadorOrden.obtenerVentas()");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    public static List<Orden> ObtenerVentas(Date inicio) {
        return ManejadorOrdenes.ObtenerVentas(inicio, inicio);
    }
}
