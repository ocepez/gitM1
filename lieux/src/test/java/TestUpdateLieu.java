import com.projetsi.lieux.entity.Lieu;
import com.projetsi.lieux.service.LieuService;

import java.util.List;

/**
 * Classe de test pour le service updateLieu.
 *
 * @author Maxime A.
 */
public class TestUpdateLieu {

    public static void main(String argv[]) throws Exception {
        LieuService ls = new LieuService();

        Lieu lieu = ls.getLieu(1);
        lieu.setNom(lieu.getNom());
        lieu.setAdresse("15, Avenue des Oies");
        lieu.setCapacite(lieu.getCapacite());

        ls.updateLieu(lieu);

        List<Lieu> lieux = ls.getAllLieux();
        for (Lieu l : lieux) {
            System.out.println("Lieu id = " + l.getId());
            System.out.println(" - Nom : " + l.getNom());
            System.out.println((" - Adresse : " + l.getAdresse()));
            System.out.println(" - Capacité : " + l.getCapacite());
        }
    }
}
