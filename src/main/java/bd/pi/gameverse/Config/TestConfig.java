package bd.pi.gameverse.Config;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import bd.pi.gameverse.Entities.*;
import bd.pi.gameverse.Repository.AdministradorRepository;
import bd.pi.gameverse.Repository.PlataformaRepository;
import bd.pi.gameverse.Repository.StatusRepository;
import bd.pi.gameverse.Repository.UsuariosRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

    
    
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        
        // Vai aparecer valores trocados mesmo porque eu estava testando o banco de
        // dados
        
        Status ativo = new Status(StatusEnum.ATIVO);
        statusRepository.save(ativo);

        Status inativo = new Status(StatusEnum.INATIVO);
        statusRepository.save(inativo);
        
        Usuario usuario1 = new Usuario("João", "joa", LocalDate.of(2000, 5, 12), "aa@gmail.com", "123", "asad",new Status(1l,StatusEnum.ATIVO));
        usuariosRepository.save(usuario1);
        Usuario usuario2 = new Usuario("JoãoS", "joaS", LocalDate.of(2000, 5, 12), "aSa@gmail.com", "123", "asad",new Status(2l,StatusEnum.INATIVO));
        usuariosRepository.save(usuario2);

        
        //Usuario usuario2 = new Usuario(2, "João2", "joa", LocalDate.of(2000, 5, 12), "@gmail.com", "123", null);

        //usuariosRepository.save(usuario1);
        //usuariosRepository.save(usuario2);

        Administrador administrador1 = new Administrador( "João", "joSa", "@gmail.com", "123",new Status(1l,StatusEnum.ATIVO));
        Administrador administrador2 = new Administrador( "João", "joaS", "A@gmail.com", "123",new Status(2l,StatusEnum.INATIVO));

        administradorRepository.save(administrador1);
        administradorRepository.save(administrador2);

    }

}
