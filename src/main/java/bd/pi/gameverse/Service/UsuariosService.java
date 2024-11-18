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
        Optional<Usuario> obj1 = usuariosRepository.findById(usuario.getId());
        Usuario obj2 = obj1.get();
        obj2.setNome(usuario.getNome());
        obj2.setNickname(usuario.getNickname());
        obj2.setEmail(usuario.getEmail());
        obj2.setTelefone(usuario.getTelefone());
        obj2.setDataNascimento(usuario.getDataNascimento());
        return usuariosRepository.save(obj2);
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
    
    //Busca por NickName e Senha
    public Usuario loginUsuario(String nickname, String senha) {
        return usuariosRepository.findByNicknameAndSenha(nickname,senha);
    }

    //  public Usuario login(String nickname, String senha) {
    //      if (nickname == null || senha == null) {
    //          return null;
    //      }

    //      try {
    //         Usuario usuario = usuariosRepository.findByNicknameAndSenha(nickname, senha);
    //          if (usuario.getNickname().equals(senha) && usuario.getSenha().equals(senha)) {
                 
    //              return usuario;
    //          }
    //      } catch (Exception e) {
    //           //Log the exception (use a logging framework)
    //          e.printStackTrace();
    //      }

    //      return null;
    //  }

    


    public List<Usuario> getAll() {
        // Buscando todos os objetos no banco
        return usuariosRepository.findAll();
    }
}
