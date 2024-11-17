package bd.pi.gameverse.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginAdmPageController {

    @GetMapping("/administradores")
    public String plataformasPage() {
        return "loginAdm.html"; // Renderiza a página HTML
    }
}
