package bd.pi.gameverse.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bd.pi.gameverse.Entities.Usuario;
import bd.pi.gameverse.Repository.UsuariosRepository;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuariosRepository.save(usuario);
    }

    public Usuario alterarUsuario(Usuario usuario) {
        Optional<Usuario> obj2 = usuariosRepository.findById(usuario.getId());
        updateUsuario(obj2, usuario);
        // Salvando o novo objeto no banco
        return usuariosRepository.save(obj2.get());
    }

    private void updateUsuario(Optional<Usuario> newObj, Usuario obj) {
        newObj.get().setNome(obj.getNome());
        // newObj.setAutor(obj.getAutor());
    }

    public Boolean deletarUsuario(Long id) {
        if (usuariosRepository.existsById(id)) {
            usuariosRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Busca por nome
    public List<Usuario> listarUsuariosPorNome(String nome) {
        return usuariosRepository.findByNomeLike(nome);
    }

    public Boolean login(String email, String senha) {
        if (email == null || senha == null) {
            return false;
        }

        try {
            Usuario usuario = usuariosRepository.findByEmail(email);
            if (usuario != null && usuario.getSenha().equals(senha)) {
                return true;
            }
        } catch (Exception e) {
            // Log the exception (use a logging framework)
            e.printStackTrace();
        }

        return false;
    }

    public List<Usuario> getAll() {
        // Buscando todos os objetos no banco
        return usuariosRepository.findAll();
    }
}
