package bd.pi.gameverse.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bd.pi.gameverse.Entities.Administrador;

import bd.pi.gameverse.Repository.AdministradorRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository AdministradorRepository;

    public Administrador cadastrarAdministrador(Administrador Administrador) {
        return AdministradorRepository.save(Administrador);
    }

    public Administrador alterarAdministrador(Administrador Administrador) {
        Optional<Administrador> obj2 = AdministradorRepository.findById(Administrador.getId());
        updateAdministrador(obj2, Administrador);
        // Salvando o novo objeto no banco
        return AdministradorRepository.save(obj2.get());
    }

    private void updateAdministrador(Optional<Administrador> newObj, Administrador obj) {
        newObj.get().setNome(obj.getNome());
        // newObj.setAutor(obj.getAutor());
    }

    public Boolean deletarAdministrador(Long id) {
        if (AdministradorRepository.existsById(id)) {
            AdministradorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Busca por nome
    public List<Administrador> listarAdministradorPorNome(String nome) {
        return AdministradorRepository.findByNome(nome);
    }

    public Boolean login(String email, String senha) {
        if (email == null || senha == null) {
            return false;
        }

        try {
            Administrador Administrador = AdministradorRepository.findByEmail(email);
            if (Administrador != null && Administrador.getSenha().equals(senha)) {
                return true;
            }
        } catch (Exception e) {
            // Log the exception (use a logging framework)
            e.printStackTrace();
        }

        return false;
    }

    public List<Administrador> getAll() {
        // Buscando todos os objetos no banco
        return AdministradorRepository.findAll();
    }
}
