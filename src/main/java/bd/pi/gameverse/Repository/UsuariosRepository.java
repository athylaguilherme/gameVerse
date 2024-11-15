package bd.pi.gameverse.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bd.pi.gameverse.Entities.*;

public interface UsuariosRepository extends JpaRepository<Usuario, Long> {

    // Querry para a busca por nome utilizando o LIKE
    List<Usuario> findByNomeLike(String nome);

    Usuario findByEmail(String email);

    /*
     *
     * 
     * Usuario findByNickname(String nickname);
     */

}
