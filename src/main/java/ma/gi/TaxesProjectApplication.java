package ma.gi;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ma.gi.dao.EntrepriseRepository;
import ma.gi.dao.TaxeRepository;
import ma.gi.entities.Entreprise;
import ma.gi.entities.IR;
import ma.gi.entities.TVA;

@SpringBootApplication
public class TaxesProjectApplication implements CommandLineRunner {

	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Autowired
	TaxeRepository taxeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TaxesProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Entreprise e1 = entrepriseRepository.save(
				new Entreprise("E1","E1@gmail.com","SARL"));
		Entreprise e2 = entrepriseRepository.save(
				new Entreprise("E2","E2@gmail.com","SARL"));
		
		taxeRepository.save(new TVA("TVA Habitation",new Date(),4300,e1));
		taxeRepository.save(new TVA("TVA Voiture",new Date(),13400,e1));
		taxeRepository.save(new IR("IR 2018",new Date(),40300,e1));
		taxeRepository.save(new TVA("TVA Habitation",new Date(),1500,e2));
		
	}

}

