package com.services.impl;

import com.dtos.MembreDto;
import com.entities.Membre;
import com.repositories.MembreRepository;
import com.services.MembreService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation du service MembreService.
 *
 * @author Xavier F.
 */
@Service("membreService")
public class MembreServiceImpl implements MembreService {

    private final MembreRepository membreRepository;

    /**
     * Constructeur de la classe MembreServiceImpl.
     *
     * @param membreRepository Le repository utilisé pour interagir avec la base de données des membres.
     */
    public MembreServiceImpl(MembreRepository membreRepository) {
        this.membreRepository = membreRepository;
    }

    /**
     * Récupère un membre par son identifiant.
     *
     * @param membreId L'identifiant du membre à récupérer.
     * @return Le DTO du membre correspondant.
     * @throws EntityNotFoundException Si aucun membre n'est trouvé avec l'identifiant spécifié.
     */
    @Override
    public MembreDto getMembreById(Integer membreId) {
        Membre membre = membreRepository.findById(membreId).orElseThrow(() -> new EntityNotFoundException("Membre not found"));
        return membreEntityToDto(membre);
    }

    /**
     * Enregistre un nouveau membre.
     *
     * @param membreDto Le DTO du membre à enregistrer.
     * @return Le DTO du membre enregistré.
     */
    @Override
    public MembreDto saveMembre(MembreDto membreDto) {
        Membre membre = membreDtoToEntity(membreDto);
        membre = membreRepository.save(membre);
        return membreEntityToDto(membre);
    }

    /**
     * Met à jour les informations d'un membre existant.
     *
     * @param id         L'identifiant du membre à mettre à jour.
     * @param membreDto  Le DTO contenant les nouvelles informations du membre.
     * @return True si la mise à jour est réussie, sinon False.
     */
    public boolean updateMembre(int id, MembreDto membreDto) {
        Membre membre = membreDtoToEntity(membreDto);
        membre.setId_membre(id);
        membreRepository.save(membre);
        return true;
    }

    /**
     * Récupère la liste de tous les membres.
     *
     * @return La liste des DTO de tous les membres.
     */
    @Override
    public List<MembreDto> getAllMembres() {
        List<MembreDto> membreDtos = new ArrayList<>();
        List<Membre> membres = membreRepository.findAll();
        membres.forEach(membre -> {
            membreDtos.add(membreEntityToDto(membre));
        });
        return membreDtos;
    }

    /**
     * Supprime un membre par son identifiant.
     *
     * @param membreId L'identifiant du membre à supprimer.
     * @return True si la suppression est réussie, sinon False.
     */
    @Override
    public boolean deleteMembre(Integer membreId) {
        membreRepository.deleteById(membreId);
        return true;
    }

    private MembreDto membreEntityToDto(Membre membre) {
        MembreDto membreDto = new MembreDto();
        membreDto.setId(membre.getId_membre());
        membreDto.setEmail(membre.getEmail_membre());
        membreDto.setPassword(membre.getPassword_membre());
        membreDto.setNom(membre.getNom_membre());
        membreDto.setPrenom(membre.getPrenom_membre());
        membreDto.setRole(membre.getRole_membre());
        membreDto.setAdresse(membre.getAdresse_membre());
        membreDto.setPhoto(membre.getPhoto_membre());
        return membreDto;
    }

    private Membre membreDtoToEntity(MembreDto membreDto) {
        Membre membre = new Membre();
        membre.setId_membre(membreDto.getId());
        membre.setEmail_membre(membreDto.getEmail());
        membre.setPassword_membre(membreDto.getPassword());
        membre.setNom_membre(membreDto.getNom());
        membre.setPrenom_membre(membreDto.getPrenom());
        membre.setRole_membre(membreDto.getRole());
        membre.setAdresse_membre(membreDto.getAdresse());
        membre.setPhoto_membre(membreDto.getPhoto());
        return membre;
    }
}
