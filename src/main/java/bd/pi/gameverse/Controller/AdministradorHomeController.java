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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bd.pi.gameverse.Entities.Administrador;
import bd.pi.gameverse.Service.AdministradorService;

@RestController
@RequestMapping("/api/administradoreshome")
public class AdministradorHomeController {

    @Autowired
    private AdministradorService AdministradorsService;

    @PostMapping("/add")
    public ResponseEntity<Administrador> create(@RequestBody Administrador Administrador) {
        return ResponseEntity.ok(AdministradorsService.cadastrarAdministrador(Administrador));
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Administrador> getNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(AdministradorsService.listarAdministradorPorEmail(nome).get(0));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getEmail(@PathVariable String email) {
        List<Administrador> administradores = AdministradorsService.listarAdministradorPorEmail(email);

        if (administradores.isEmpty()) {
            return ResponseEntity.status(404).body("Nenhum administrador encontrado com o email fornecido.");
        }

        // Retorna apenas o primeiro administrador encontrado
        return ResponseEntity.ok(administradores.get(0));
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

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody Administrador administradorAtualizado) {
        try {
            Administrador updatedAdministrador = AdministradorsService.atualizarAdministrador(id,
                    administradorAtualizado.getNome(), administradorAtualizado.getEmail());
            return ResponseEntity.ok(updatedAdministrador);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
