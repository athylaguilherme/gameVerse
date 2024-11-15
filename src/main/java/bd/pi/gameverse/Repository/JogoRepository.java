package bd.pi.gameverse.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bd.pi.gameverse.Entities.*;

public interface JogoRepository extends JpaRepository<Jogo, Long> {

    // Querry para a busca por nome
    List<Jogo> findByNome(String nome);

    /*
     * Jogo findByPlataforma(String email);
     */

}
