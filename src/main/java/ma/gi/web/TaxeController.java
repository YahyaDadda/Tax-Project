package ma.gi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ma.gi.dao.EntrepriseRepository;
import ma.gi.entities.Entreprise;

@Controller
public class TaxeController {

	@Autowired
	EntrepriseRepository entrepriseRepository;
	
	@RequestMapping(value="/entreprises",method=RequestMethod.GET)
	public String index(Model model,
						@RequestParam(name="motCle",defaultValue="") String key,
						@RequestParam(name="page",defaultValue="0") int p,
						@RequestParam(name="size",defaultValue="3") int s) {
		Page<Entreprise> PageEntreprises = entrepriseRepository.chercher("%"+key+"%", PageRequest.of(p, s));
		model.addAttribute("ListEntreprise", PageEntreprises.getContent());
		int[] pages = new int[PageEntreprises.getTotalPages()];
		model.addAttribute("pageCourante",p);
		model.addAttribute("motCle",key);
		model.addAttribute("pages",pages);
		return "entreprises";
	}
	
}
