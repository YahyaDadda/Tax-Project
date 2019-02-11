package ma.gi.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ma.gi.dao.EntrepriseRepository;
import ma.gi.dao.TaxeRepository;
import ma.gi.entities.Entreprise;
import ma.gi.entities.Taxe;

@Controller
public class TaxeController {

	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Autowired
	TaxeRepository taxeRepository;
	
	@RequestMapping(value="/entreprises",method=RequestMethod.GET)
	public String getEntreprises(Model model,
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
	
	@RequestMapping(value="/saisie",method=RequestMethod.GET)
	public String getEntrepriseFormVue(Model model) {
		model.addAttribute("entreprise", new Entreprise());
		return "formsaisie";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveEntreprise(Model model,@Valid Entreprise e,BindingResult BindingResult) {
		if(BindingResult.hasErrors())
			return "formsaisie";
		entrepriseRepository.save(e);
		return "redirect:/entreprises";
	}
	
	@RequestMapping(value="/taxes",method=RequestMethod.GET)
	public String getEntrepriseTaxes(Model model, @RequestParam(name="code", defaultValue="-1") Long code) {
		if(code==-1)
			model.addAttribute("texes",new ArrayList<Taxe>());
		else {
			Entreprise e  = new Entreprise();
			e.setCode(code);
			model.addAttribute("taxes",taxeRepository.findByEntreprise(e));
		}
		return "taxes";
	}
	
}
