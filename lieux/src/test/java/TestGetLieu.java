import com.projetsi.lieux.entity.Lieu;
import com.projetsi.lieux.service.LieuService;

/**
 * Classe de test pour le service getLieu.
 *
 * @author Maxime A.
 */
public class TestGetLieu {

    public static void main(String argv[]) throws Exception {
        LieuService ls = new LieuService();
        int idLieu = 1;

        Lieu lieu = ls.getLieu(idLieu);
        System.out.println("Lieu id = " + lieu.getId());
        System.out.println(" - Nom : " + lieu.getNom());
        System.out.println((" - Adresse : " + lieu.getAdresse()));
        System.out.println(" - Capacité : " + lieu.getCapacite());
    }
}