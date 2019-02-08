package ma.gi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.gi.entities.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

}
