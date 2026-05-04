package repository;

import entity.Odontologo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OdontologoRepository implements IRepository<Odontologo> {
    private Map<Long,Odontologo> odontologos = new HashMap<>();

    @Override
    public void guardar(Odontologo o) {
        odontologos.put(o.getId(), o);
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        return odontologos.get(id);
    }

    @Override
    public List<Odontologo> listar() {
        return new ArrayList<>(odontologos.values());
    }

    @Override
    public void actualizar(Odontologo o) {
        odontologos.put(o.getId(), o);
    }

    @Override
    public void eliminar(Long id) {
        odontologos.remove(id);
    }
}
