package bd.pi.gameverse.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bd.pi.gameverse.Entities.Plataforma;
import bd.pi.gameverse.Repository.PlataformaRepository;

@Service
public class PlataformaService {

    @Autowired
    private PlataformaRepository PlataformasRepository;

    public Plataforma cadastrarPlataforma(Plataforma Plataforma) {
        return PlataformasRepository.save(Plataforma);
    }

    public Plataforma alterarPlataforma(Plataforma Plataforma) {
        Optional<Plataforma> obj2 = PlataformasRepository.findById(Plataforma.getId());
        updatePlataforma(obj2, Plataforma);
        // Salvando o novo objeto no banco
        return PlataformasRepository.save(obj2.get());
    }

    private void updatePlataforma(Optional<Plataforma> newObj, Plataforma obj) {
        newObj.get().setNome(obj.getNome());
        // newObj.setAutor(obj.getAutor());
    }

    public Boolean deletarPlataforma(Long id) {
        if (PlataformasRepository.existsById(id)) {
            PlataformasRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Busca por nome
    public List<Plataforma> listarPlataformasPorNome(String nome) {
        return PlataformasRepository.findByNome(nome);
    }

    public List<Plataforma> getAll() {
        // Buscando todos os objetos no banco
        return PlataformasRepository.findAll();
    }
}
