package bd.pi.gameverse.Entities;

import java.io.Serializable;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
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
@Entity(name = "usuario")
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String nome;

    @Column(unique = true)
    @NotBlank
    private String nickname;
    
    @PastOrPresent
    private LocalDate dataNascimento;
    
    @Column(unique = true)
    @NotBlank
    @Email
    private String email;


    private String telefone;

    @NotBlank
    private String senha;

    @ManyToOne
    private Status idStatus;

    /*
     * @OneToMany(mappedBy = "idUsuario")
     * 
     * @JsonIgnore
     * private List<JogoFavorito> jogosfavoritos = new ArrayList<JogoFavorito>();
     */
    public Usuario( String nome, String nickname, LocalDate dataNascimento, String email, String telefone,
            String senha, Status idStatus) {
        //this.id = id;
        this.nome = nome;
        this.nickname = nickname;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.idStatus = idStatus;
    }

}
