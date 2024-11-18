package bd.pi.gameverse.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlataformaPageController {

    @GetMapping("/plataformas")
    public String plataformasPage() {
        return "adm/plataformas.html"; // Renderiza a página HTML
    }
}
