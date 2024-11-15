package bd.pi.gameverse.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bd.pi.gameverse.Entities.*;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    // Querry para a busca por nome utilizando o LIKE
    List<Administrador> findByNome(String nome);

    Administrador findByEmail(String email);

    /*
     *
     * 
     * Usuario findByNickname(String nickname);
     */

}
