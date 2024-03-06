package comm.dtos;



import lombok.Data;

@Data
public class CommentairesDto {

    private String contenu_commentaire;
    private String id_evenement;
    private String email_membre;


}