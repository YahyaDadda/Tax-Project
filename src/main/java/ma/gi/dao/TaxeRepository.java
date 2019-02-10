package ma.gi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.gi.entities.Entreprise;
import ma.gi.entities.Taxe;

public interface TaxeRepository extends JpaRepository<Taxe, Long> {
	public List<Taxe> findByEntreprise(Entreprise e);
}
