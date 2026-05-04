package repository;

import java.util.List;

public interface IRepository<T>{

    void guardar(T t);
    T buscarPorId(Long id);
    List<T> listar();
    void actualizar(T t);
    void eliminar(Long id);
}
