package ma.gi.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@GetMapping("/login")
	public String login(){
		return "login";
	}
	
	@GetMapping("/")
	public String index(){
		return "redirect:/entreprises";
	}
	
	@GetMapping("/403")
	public String error403(){
		return "403";
	}
}
