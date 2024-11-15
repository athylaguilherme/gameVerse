package bd.pi.gameverse.Entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity(name = "AvaliacaoJogoUsuario")
@Table(name = "tb_AvaliacaoJogoUsuario")
public class AvaliacaoJogoUsuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descricao;
    private Integer notaAvalicao;
    @ManyToOne
    private Jogo id_Jogo;
    @ManyToOne
    private Usuario id_Usuario;

    public AvaliacaoJogoUsuario(String descricao, Integer notaAvalicao, Jogo idJogo, Usuario idUser) {
        this.descricao = descricao;
        this.notaAvalicao = notaAvalicao;
        this.id_Jogo = idJogo;
        this.id_Usuario = idUser;
    }

    
}
