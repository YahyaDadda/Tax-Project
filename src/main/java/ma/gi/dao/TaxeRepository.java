package ma.gi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.gi.entities.Taxe;

public interface TaxeRepository extends JpaRepository<Taxe, Long> {

}
