package bd.pi.gameverse.Entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
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
@Entity(name = "administrador")
@Table(name = "tb_administrador")
public class Administrador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @Column(unique = true)
    private String nickname;
    @Column(unique = true)
    private String email;
    private String senha;
    @ManyToOne
    private Status idStatus;
    /*
     * Para usar a classe enum para Status
     * 
     * @ElementCollection(targetClass = Status.class)
     * 
     * @Enumerated(EnumType.STRING)
     * private List<Status> status;
     */

    @JsonIgnore

    public Administrador(String nome, String nickname, String email, String senha, Status idStatus ) {
        this.nome = nome;
        this.nickname = nickname;
        this.email = email;
        this.senha = senha;
        this.idStatus = idStatus;
    }

}
