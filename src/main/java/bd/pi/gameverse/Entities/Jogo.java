package bd.pi.gameverse.Entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Entity(name = "jogo")
@Table(name = "tb_jogo")
public class Jogo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private int notaAvaliacao;
    private Date dataLancamento;
    private String descricao;
    private String imagem;
    @ManyToOne
    //@Column(name = "id_Status")
    private Status idStatus;

    @ManyToMany
    @JoinTable(name = "TB_jogo_Plataforma",
            joinColumns = @JoinColumn(name = "jogo_id"),
            inverseJoinColumns = @JoinColumn(name = "plataforma_id"))
    private Set<Plataforma> plataformas;

    /*
     * @OneToMany(mappedBy = "idJogo")
     * 
     * @JsonIgnore
     * private List<Status> jogosfavoritos = new ArrayList<Status>();
     */
    public Jogo(String nome, int notaAvaliacao, Date dataLancamento, String descricao, String imagem, Status idStatus, Set<Plataforma> plataformas) {
        this.nome = nome;
        this.notaAvaliacao = notaAvaliacao;
        this.dataLancamento = dataLancamento;
        this.descricao = descricao;
        this.imagem = imagem;
        this.idStatus = idStatus;
        this.plataformas = plataformas;
    }

}
