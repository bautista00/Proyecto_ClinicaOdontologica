package repository;

import entity.Turno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurnoRepository implements IRepository<Turno> {
    private Map<Long, Turno> turnos = new HashMap<>();

    @Override
    public void guardar(Turno t) {
        turnos.put(t.getId(), t);
    }

    @Override
    public Turno buscarPorId(Long id) {
        return turnos.get(id);
    }

    @Override
    public List<Turno> listar() {
        return new ArrayList<>(turnos.values());
    }

    @Override
    public void actualizar(Turno t) {
        turnos.put(t.getId(), t);
    }

    @Override
    public void eliminar(Long id) {
        turnos.remove(id);
    }
}

