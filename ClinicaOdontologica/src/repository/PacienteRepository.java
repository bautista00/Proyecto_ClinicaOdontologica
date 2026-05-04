package repository;

import entity.Paciente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PacienteRepository implements IRepository<Paciente> {
    private Map<Long, Paciente> pacientes = new HashMap<>();


    @Override
    public void guardar(Paciente p) {
        pacientes.put(p.getId(),p);

    }

    @Override
    public Paciente buscarPorId(Long id) {
        return pacientes.get(id);
    }

    @Override
    public List<Paciente> listar() {
        return new ArrayList<>(pacientes.values());
    }

    @Override
    public void actualizar(Paciente p) {
        pacientes.put(p.getId(), p);
    }

    @Override
    public void eliminar(Long id) {
        pacientes.remove(id);

    }
}
