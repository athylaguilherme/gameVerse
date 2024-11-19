package bd.pi.gameverse.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bd.pi.gameverse.Entities.Status;
import bd.pi.gameverse.Entities.StatusEnum;
import bd.pi.gameverse.Entities.Usuario;
import bd.pi.gameverse.Service.UsuariosService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuariosService usuariosService;

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Status cadas = new Status(1l, StatusEnum.ATIVO);
        usuario.setIdStatus(cadas);
        return ResponseEntity.ok(usuariosService.cadastrarUsuario(usuario));
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Usuario> alterarDados(@PathVariable long id ,@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuariosService.alterarUsuario(usuario));
    }

    @PutMapping("/excluir/{id}")
    public ResponseEntity<Boolean> ExcluirUsuario(@PathVariable long id) {
        return ResponseEntity.ok(usuariosService.excluirUsuario(id));
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Usuario> getNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(usuariosService.listarUsuariosPorNome(nome).get(0));
    }
    
    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok().body(usuariosService.getAll());
    }

     @GetMapping("/login/{nickname}/{senha}")
     public ResponseEntity<Usuario> login(@PathVariable String nickname, @PathVariable String senha) {
         return ResponseEntity.ok().body(usuariosService.loginUsuario(nickname, senha));
     }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(usuariosService.deletarUsuario(id));
    }
}
