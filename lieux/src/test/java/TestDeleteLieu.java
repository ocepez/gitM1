import com.projetsi.lieux.entity.Lieu;
import com.projetsi.lieux.service.LieuService;

import java.util.List;

/**
 * Classe de test pour le service deleteLieu.
 *
 * @author Maxime A.
 */
public class TestDeleteLieu {

    public static void main(String argv[]) throws Exception {
        LieuService ls = new LieuService();

        Lieu lieu = ls.getLieu(7);

        ls.deleteLieu(lieu);

        List<Lieu> lieux = ls.getAllLieux();
        for (Lieu l : lieux) {
            System.out.println("Lieu id = " + l.getId());
            System.out.println(" - Nom : " + l.getNom());
            System.out.println((" - Adresse : " + l.getAdresse()));
            System.out.println(" - Capacité : " + l.getCapacite());
        }
    }
}
