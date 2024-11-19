package bd.pi.gameverse.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bd.pi.gameverse.Entities.Jogo;
import bd.pi.gameverse.Service.JogoService;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoService JogosService;

    @PostMapping("upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Arquivo está vazio");
        }

        try {
            // Salvar o arquivo localmente (ou em outro local desejado)
            Path path = Paths.get("uploads/" + file.getOriginalFilename());
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
            return ResponseEntity.ok("Arquivo enviado com sucesso: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao enviar o arquivo");
        }
    }

    @PostMapping
    public ResponseEntity<Jogo> create(@RequestBody Jogo Jogo) {
        return ResponseEntity.ok(JogosService.cadastrarJogo(Jogo));
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Jogo> getNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(JogosService.listarJogosPorNome(nome).get(0));
    }

    @GetMapping
    public ResponseEntity<List<Jogo>> getAll() {
        return ResponseEntity.ok().body(JogosService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(JogosService.deletarJogo(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterarJogo(@PathVariable long id, @RequestBody Jogo jogo) {
        return ResponseEntity.ok().body(JogosService.alterarJogo(jogo));
    }

    @GetMapping("id")
    public ResponseEntity<?> getById(@RequestParam long id) {
        return ResponseEntity.ok().body(JogosService.listById(id));
    }
}
