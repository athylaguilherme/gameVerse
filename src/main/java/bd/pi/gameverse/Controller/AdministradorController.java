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

import bd.pi.gameverse.Entities.Administrador;
import bd.pi.gameverse.Service.AdministradorService;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService AdministradorsService;

    @PostMapping
    public ResponseEntity<Administrador> create(@RequestBody Administrador Administrador) {
        return ResponseEntity.ok(AdministradorsService.cadastrarAdministrador(Administrador));
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Administrador> getNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(AdministradorsService.listarAdministradorPorEmail(nome).get(0));
    }

    @GetMapping
    public ResponseEntity<List<Administrador>> getAll() {
        return ResponseEntity.ok().body(AdministradorsService.getAll());
    }

    @GetMapping("/login/{nome}/{senha}")
    public ResponseEntity<Boolean> login(@PathVariable String nome, @PathVariable String senha) {
        return ResponseEntity.ok().body(AdministradorsService.login(nome, senha));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(AdministradorsService.deletarAdministrador(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable long id, @RequestBody Administrador admin) {
        return ResponseEntity.ok().body(AdministradorsService.alterarAdministrador(admin));
    }
}
