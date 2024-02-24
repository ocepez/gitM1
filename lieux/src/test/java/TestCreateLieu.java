import com.projetsi.lieux.entity.Lieu;
import com.projetsi.lieux.service.LieuService;

import java.util.List;

/**
 * Classe de test pour le service createLieu.
 *
 * @author Maxime A.
 */
public class TestCreateLieu {

    public static void main(String argv[]) throws Exception {
        LieuService ls = new LieuService();

        Lieu lieu = new Lieu();
        lieu.setNom("Villa des Alpes");
        lieu.setAdresse("6, Rue Montaigne");
        lieu.setCapacite(25);

        ls.createLieu(lieu);

        List<Lieu> lieux = ls.getAllLieux();
        for (Lieu l : lieux) {
            System.out.println("Lieu id = " + l.getId());
            System.out.println(" - Nom : " + l.getNom());
            System.out.println((" - Adresse : " + l.getAdresse()));
            System.out.println(" - Capacité : " + l.getCapacite());
        }
    }
}
