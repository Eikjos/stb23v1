package fr.univrouen.stb23v1.repositories;

import fr.univrouen.stb23v1.entities.STB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface StbRepository extends JpaRepository<STB, Long> {
    Optional<STB> findByTitleAndDateAndVersion(String title, Date date, double version);

}
