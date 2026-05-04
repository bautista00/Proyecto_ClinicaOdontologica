package service;

import entity.Secretaria;
import repository.SecretariaRepository;

import java.util.List;

public class SecretariaServiceImpl implements IService<Secretaria> {

    private SecretariaRepository secretariaRepository;

    public SecretariaServiceImpl(SecretariaRepository secretariaRepository) {
        this.secretariaRepository = secretariaRepository;
    }

    @Override
    public Secretaria registrar(Secretaria secretaria) {
        if (!validarSecretaria(secretaria)) {
            return null;
        }

        if (secretariaRepository.existeDni(secretaria.getDni())) {
            System.out.println("Error: ya existe una secretaria con ese DNI.");
            return null;
        }

        secretariaRepository.guardar(secretaria);
        return secretaria;
    }

    @Override
    public Secretaria buscarPorId(Long id) {
        if (id == null || id <= 0) {
            System.out.println("Error: el ID de la secretaria es invalido.");
            return null;
        }

        Secretaria secretaria = secretariaRepository.buscarPorId(id);
        if (secretaria == null) {
            System.out.println("Error: no existe una secretaria con ese ID.");
            return null;
        }

        return secretaria;
    }

    public Secretaria buscarPorDni(Integer dni) {
        if (dni == null || dni <= 0) {
            System.out.println("Error: el DNI es invalido.");
            return null;
        }

        Secretaria secretaria = secretariaRepository.buscarPorDni(dni);
        if (secretaria == null) {
            System.out.println("Error: no existe una secretaria con ese DNI.");
            return null;
        }

        return secretaria;
    }

    @Override
    public List<Secretaria> listarTodos() {
        return secretariaRepository.listarTodos();
    }

    @Override
    public Secretaria actualizar(Secretaria secretaria) {
        if (!validarSecretaria(secretaria)) {
            return null;
        }

        Secretaria secretariaExistente = secretariaRepository.buscarPorId(secretaria.getId());
        if (secretariaExistente == null) {
            System.out.println("Error: no existe una secretaria con ese ID.");
            return null;
        }

        Secretaria secretariaConMismoDni = secretariaRepository.buscarPorDni(secretaria.getDni());
        if (secretariaConMismoDni != null && !secretariaConMismoDni.getId().equals(secretaria.getId())) {
            System.out.println("Error: ya existe otra secretaria con ese DNI.");
            return null;
        }

        secretariaRepository.actualizar(secretaria);
        return secretaria;
    }

    @Override
    public boolean eliminar(Long id) {
        if (id == null || id <= 0) {
            System.out.println("Error: el ID de la secretaria es invalido.");
            return false;
        }

        Secretaria secretariaExistente = secretariaRepository.buscarPorId(id);
        if (secretariaExistente == null) {
            System.out.println("Error: no existe una secretaria con ese ID.");
            return false;
        }

        secretariaRepository.eliminar(id);
        return true;
    }

    private boolean validarSecretaria(Secretaria secretaria) {
        if (secretaria == null) {
            System.out.println("Error: la secretaria no puede ser nula.");
            return false;
        }

        if (secretaria.getNombre() == null || secretaria.getNombre().isBlank()) {
            System.out.println("Error: el nombre de la secretaria no puede estar vacio.");
            return false;
        }

        if (secretaria.getApellido() == null || secretaria.getApellido().isBlank()) {
            System.out.println("Error: el apellido de la secretaria no puede estar vacio.");
            return false;
        }

        if (secretaria.getDni() == null || secretaria.getDni() <= 0) {
            System.out.println("Error: el DNI de la secretaria es invalido.");
            return false;
        }

        return true;
    }
}