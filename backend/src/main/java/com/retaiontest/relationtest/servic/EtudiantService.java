package com.retaiontest.relationtest.servic;

import com.retaiontest.relationtest.EXception.ConnectBdException;
import com.retaiontest.relationtest.EXception.DataInvalid;
import com.retaiontest.relationtest.EXception.NotFoundException;
import com.retaiontest.relationtest.Entreprise;
import com.retaiontest.relationtest.Etudiant;
import com.retaiontest.relationtest.User;
import com.retaiontest.relationtest.repository.EtudiantRepository;

import com.retaiontest.relationtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public EtudiantService(UserService userService) {
        this.userService = userService;
    }

    public List<Etudiant> AfficherListEtudiant() {
        return etudiantRepository.findAll();
    }

    public Optional<Etudiant> AfficheEtudiant(Long id_etudiant) throws ConnectBdException {
        if (etudiantRepository.findById(id_etudiant) == null) throw new ConnectBdException("Il y a aucun etudiant avec id "+id_etudiant);
        return etudiantRepository.findById(id_etudiant);
    }
    public Etudiant RegisterEtudiant(Etudiant etudiant) throws DataInvalid {
        if (etudiant == null) throw new DataInvalid("Il faut remplir les champs !!");
        User user= etudiant.getUser();
        User registeredUser= userService.registerUser(user);
        etudiant.setUser(registeredUser);
        return etudiantRepository.save(etudiant);
    }
   public Etudiant modifyEtudiant(Long id_etudiant, Etudiant newEtudiant) throws DataInvalid{
        if (id_etudiant <= 0 || newEtudiant == null)throw new DataInvalid("il faut remplire les champs ");
        Etudiant oldEtudiant = etudiantRepository.findById(id_etudiant)
                .orElseThrow(() -> new NotFoundException("l'etudiant est non trouvée avec l'ID : " + id_etudiant));

        oldEtudiant.setNom(newEtudiant.getNom());
        oldEtudiant.setPrenom(newEtudiant.getPrenom());
       oldEtudiant.setTele(newEtudiant.getTele());
       oldEtudiant.setNiveau(newEtudiant.getNiveau());
       oldEtudiant.setDateNais(newEtudiant.getDateNais());

       User user=oldEtudiant.getUser();
       userService.editUser(user.getId(), newEtudiant.getUser());

       return etudiantRepository.save(oldEtudiant);
   }
    public void deleteEtudiant(Long id_etudiant) {
        Optional<Etudiant> Opetudiant = etudiantRepository.findById(id_etudiant);
        if (Opetudiant.isPresent()) {
            Etudiant etudiant = Opetudiant.get();
            User user = etudiant.getUser();

            //supp etud
            etudiantRepository.deleteById(id_etudiant);

            // Supprime l'utilisateur associé
            if (user != null) {
                try {
                    userService.deleteUser(user.getId());
                } catch (Exception e) {

                    e.printStackTrace();
                    throw new NotFoundException("Erreur lors de la suppression de l'utilisateur associé à l'etudiant.");
                }
            }
        } else {
            throw new NotFoundException("Etudiant non trouvée avec l'ID : " + id_etudiant);
        }
    }
}
