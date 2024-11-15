package bd.pi.gameverse.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bd.pi.gameverse.Entities.Usuario;
import bd.pi.gameverse.Service.UsuariosService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuariosService usuariosService;

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuariosService.cadastrarUsuario(usuario));
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Usuario> getNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(usuariosService.listarUsuariosPorNome(nome).get(0));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok().body(usuariosService.getAll());
    }

    @GetMapping("/login/{email}/{senha}")
    public ResponseEntity<Boolean> login(@PathVariable String email, @PathVariable String senha) {
        return ResponseEntity.ok().body(usuariosService.login(email, senha));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(usuariosService.deletarUsuario(id));
    }
}
