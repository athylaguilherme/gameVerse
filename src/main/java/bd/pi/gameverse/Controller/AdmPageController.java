package bd.pi.gameverse.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdmPageController {

    @GetMapping("/administradoreshome")
    public String plataformasPage() {
        return "adm/administradores.html"; // Renderiza a página HTML
    }
}
