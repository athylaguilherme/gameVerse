package bd.pi.gameverse.Service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bd.pi.gameverse.Entities.Jogo;
import bd.pi.gameverse.Repository.JogoRepository;

@Service
public class JogoService {

    @Autowired
    private JogoRepository JogosRepository;

    public Jogo cadastrarJogo(Jogo jogo) {
        return JogosRepository.save(jogo);
    }

    public Jogo alterarJogo(Jogo jogo) {
        Optional<Jogo> obj2 = JogosRepository.findById(jogo.getId());
        updateJogo(obj2, jogo);
        // Salvando o novo objeto no banco
        return JogosRepository.save(obj2.get());
    }

    private void updateJogo(Optional<Jogo> newObj, Jogo obj) {
        newObj.get().setNome(obj.getNome());
        // newObj.setAutor(obj.getAutor());
    }

    public Boolean deletarJogo(Long id) {
        if (JogosRepository.existsById(id)) {
            JogosRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Busca por nome
    public List<Jogo> listarJogosPorNome(String nome) {
        return JogosRepository.findByNome(nome);
    }

    public List<Jogo> getAll() {
        // Buscando todos os objetos no banco
        return JogosRepository.findAll();
    }

    public Optional<Jogo> listById(long id){
        return JogosRepository.findById(id);
    }
}
