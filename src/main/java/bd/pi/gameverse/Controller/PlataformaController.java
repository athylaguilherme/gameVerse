package bd.pi.gameverse.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bd.pi.gameverse.Entities.Plataforma;
import bd.pi.gameverse.Service.PlataformaService;

@RestController
@RequestMapping("/api/plataformas")
public class PlataformaController {

    @Autowired
    private PlataformaService PlataformasService;

    @PostMapping("/add")
    public ResponseEntity<Plataforma> create(@RequestBody Plataforma Plataforma) {
        return ResponseEntity.ok(PlataformasService.cadastrarPlataforma(Plataforma));
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<Plataforma>> getNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(PlataformasService.listarPlataformasPorNome(nome));
    }

    @GetMapping
    public ResponseEntity<List<Plataforma>> getAll() {
        return ResponseEntity.ok().body(PlataformasService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(PlataformasService.deletarPlataforma(id));
    }

    
  @PutMapping("/{id}")
    public ResponseEntity<Plataforma> update(@PathVariable Long id, @RequestBody Plataforma plataforma) {
        plataforma.setId(id); // Garanta que o ID do corpo seja o mesmo do URL
        Plataforma updatedPlataforma = PlataformasService.alterarPlataforma(plataforma);
        return ResponseEntity.ok(updatedPlataforma);
    }
    
}
