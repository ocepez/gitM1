package comm.repositories;


import comm.entities.Evenement;
import comm.entities.Lieu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LieuRepository extends JpaRepository<Lieu, Integer> {

}
