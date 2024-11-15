package bd.pi.gameverse.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bd.pi.gameverse.Entities.*;

public interface StatusRepository extends JpaRepository<Status, Long> {

    // Querry para a busca por nome
    //List<Plataforma> findByNome(String nome);

    /*
     * Jogo findByPlataforma(String email);
     */

}
