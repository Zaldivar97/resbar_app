/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.io.Serializable;

/**
 *
 * @author zaldivar
 */
public class ManejadorCategoria implements Serializable {
//
    
}

//    public ManejadorCategoria(EntityManagerFactory emf) {
//        this.emf = emf;
//    }
//    private EntityManagerFactory emf = null;
//
//    public EntityManager getEntityManager() {
//        return emf.createEntityManager();
//    }
//
//    public void create(Categoria categoria) throws PreexistingEntityException, Exception {
//        if (categoria.getProductoList() == null) {
//            categoria.setProductoList(new ArrayList<Producto>());
//        }
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            List<Producto> attachedProductoList = new ArrayList<Producto>();
//            for (Producto productoListProductoToAttach : categoria.getProductoList()) {
//                productoListProductoToAttach = em.getReference(productoListProductoToAttach.getClass(), productoListProductoToAttach.getIdProducto());
//                attachedProductoList.add(productoListProductoToAttach);
//            }
//            categoria.setProductoList(attachedProductoList);
//            em.persist(categoria);
//            for (Producto productoListProducto : categoria.getProductoList()) {
//                Categoria oldIdCategoriaOfProductoListProducto = productoListProducto.getIdCategoria();
//                productoListProducto.setIdCategoria(categoria);
//                productoListProducto = em.merge(productoListProducto);
//                if (oldIdCategoriaOfProductoListProducto != null) {
//                    oldIdCategoriaOfProductoListProducto.getProductoList().remove(productoListProducto);
//                    oldIdCategoriaOfProductoListProducto = em.merge(oldIdCategoriaOfProductoListProducto);
//                }
//            }
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            if (findCategoria(categoria.getIdCategoria()) != null) {
//                throw new PreexistingEntityException("Categoria " + categoria + " already exists.", ex);
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public void edit(Categoria categoria) throws IllegalOrphanException, NonexistentEntityException, Exception {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Categoria persistentCategoria = em.find(Categoria.class, categoria.getIdCategoria());
//            List<Producto> productoListOld = persistentCategoria.getProductoList();
//            List<Producto> productoListNew = categoria.getProductoList();
//            List<String> illegalOrphanMessages = null;
//            for (Producto productoListOldProducto : productoListOld) {
//                if (!productoListNew.contains(productoListOldProducto)) {
//                    if (illegalOrphanMessages == null) {
//                        illegalOrphanMessages = new ArrayList<String>();
//                    }
//                    illegalOrphanMessages.add("You must retain Producto " + productoListOldProducto + " since its idCategoria field is not nullable.");
//                }
//            }
//            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
//            }
//            List<Producto> attachedProductoListNew = new ArrayList<Producto>();
//            for (Producto productoListNewProductoToAttach : productoListNew) {
//                productoListNewProductoToAttach = em.getReference(productoListNewProductoToAttach.getClass(), productoListNewProductoToAttach.getIdProducto());
//                attachedProductoListNew.add(productoListNewProductoToAttach);
//            }
//            productoListNew = attachedProductoListNew;
//            categoria.setProductoList(productoListNew);
//            categoria = em.merge(categoria);
//            for (Producto productoListNewProducto : productoListNew) {
//                if (!productoListOld.contains(productoListNewProducto)) {
//                    Categoria oldIdCategoriaOfProductoListNewProducto = productoListNewProducto.getIdCategoria();
//                    productoListNewProducto.setIdCategoria(categoria);
//                    productoListNewProducto = em.merge(productoListNewProducto);
//                    if (oldIdCategoriaOfProductoListNewProducto != null && !oldIdCategoriaOfProductoListNewProducto.equals(categoria)) {
//                        oldIdCategoriaOfProductoListNewProducto.getProductoList().remove(productoListNewProducto);
//                        oldIdCategoriaOfProductoListNewProducto = em.merge(oldIdCategoriaOfProductoListNewProducto);
//                    }
//                }
//            }
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            String msg = ex.getLocalizedMessage();
//            if (msg == null || msg.length() == 0) {
//                Integer id = categoria.getIdCategoria();
//                if (findCategoria(id) == null) {
//                    throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.");
//                }
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Categoria categoria;
//            try {
//                categoria = em.getReference(Categoria.class, id);
//                categoria.getIdCategoria();
//            } catch (EntityNotFoundException enfe) {
//                throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.", enfe);
//            }
//            List<String> illegalOrphanMessages = null;
//            List<Producto> productoListOrphanCheck = categoria.getProductoList();
//            for (Producto productoListOrphanCheckProducto : productoListOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This Categoria (" + categoria + ") cannot be destroyed since the Producto " + productoListOrphanCheckProducto + " in its productoList field has a non-nullable idCategoria field.");
//            }
//            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
//            }
//            em.remove(categoria);
//            em.getTransaction().commit();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public List<Categoria> findCategoriaEntities() {
//        return findCategoriaEntities(true, -1, -1);
//    }
//
//    public List<Categoria> findCategoriaEntities(int maxResults, int firstResult) {
//        return findCategoriaEntities(false, maxResults, firstResult);
//    }
//
//    private List<Categoria> findCategoriaEntities(boolean all, int maxResults, int firstResult) {
//        EntityManager em = getEntityManager();
//        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(Categoria.class));
//            Query q = em.createQuery(cq);
//            if (!all) {
//                q.setMaxResults(maxResults);
//                q.setFirstResult(firstResult);
//            }
//            return q.getResultList();
//        } finally {
//            em.close();
//        }
//    }
//
//    public Categoria findCategoria(Integer id) {
//        EntityManager em = getEntityManager();
//        try {
//            return em.find(Categoria.class, id);
//        } finally {
//            em.close();
//        }
//    }
//
//    public int getCategoriaCount() {
//        EntityManager em = getEntityManager();
//        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            Root<Categoria> rt = cq.from(Categoria.class);
//            cq.select(em.getCriteriaBuilder().count(rt));
//            Query q = em.createQuery(cq);
//            return ((Long) q.getSingleResult()).intValue();
//        } finally {
//            em.close();
//        }
//    }