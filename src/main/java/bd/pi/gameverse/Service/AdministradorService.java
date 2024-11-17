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

    public Administrador alterarAdministrador(Administrador administrador) {
        Optional<Administrador> obj2 = AdministradorRepository.findById(administrador.getId());
        if (obj2.isPresent()) {
            Administrador existingAdministrador = obj2.get();
            updateAdministrador(existingAdministrador, administrador);
            // Salvando o novo objeto no banco
            return AdministradorRepository.save(existingAdministrador);
        } else {
            throw new RuntimeException("Administrador não encontrado com ID: " + administrador.getId());
        }
    }

    private void updateAdministrador(Administrador existingAdministrador, Administrador updatedAdministrador) {
        
        if (updatedAdministrador.getNome() != null) {
            existingAdministrador.setNome(updatedAdministrador.getNome());
        }
        if (updatedAdministrador.getNickname() != null) {
            existingAdministrador.setNickname(updatedAdministrador.getNickname());
        }
        if (updatedAdministrador.getEmail() != null) {
            existingAdministrador.setEmail(updatedAdministrador.getEmail());
        }
        if (updatedAdministrador.getSenha() != null) {
            existingAdministrador.setSenha(updatedAdministrador.getSenha());
        }
        if (updatedAdministrador.getIdStatus() != null) {
            existingAdministrador.setIdStatus(updatedAdministrador.getIdStatus());
        }
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

    public Boolean login(String nome, String senha) {
        if (nome == null || senha == null) {
            return false;
        }

        try {
            Administrador Administrador = AdministradorRepository.findByNomeAndSenha(nome, senha);
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
