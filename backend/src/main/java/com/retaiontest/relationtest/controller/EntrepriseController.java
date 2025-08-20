package com.retaiontest.relationtest.controller;

import com.retaiontest.relationtest.EXception.ConnectBdException;
import com.retaiontest.relationtest.EXception.NotFoundException;
import com.retaiontest.relationtest.Entreprise;
import com.retaiontest.relationtest.servic.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import com.retaiontest.relationtest.servic.impl.EntrepriseService;
//import com.retaiontest.relationtest.servic.EntrepriseService;
import com.retaiontest.relationtest.repository.EntrepriseRepository;
import org.springframework.web.server.ResponseStatusException;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/entreprise")
public class EntrepriseController {

    @Autowired
    private EntrepriseService entrepriseService;
    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @PostMapping("/register")
    public ResponseEntity<Entreprise> registerEntrepriseWithUser(@RequestBody Entreprise entreprise) {
        try {
            Entreprise registeredEntreprise = entrepriseService.registerEntrepriseWithUser(entreprise);
            return new ResponseEntity<>(registeredEntreprise, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/afficherEntreprise")
    public List<Entreprise> AfficherEntrprise() throws ConnectBdException {
        if (entrepriseService.AfficherEntrprise() == null) throw new ConnectBdException("Il a aucun entreprise !!");
        return entrepriseService.AfficherEntrprise();
    }


    @GetMapping("/afficherEntreprise/{id_entreprise}")
    public ResponseEntity<Entreprise> afficherProfileEntrepris(@PathVariable Long id_entreprise) {
        try {
            Optional<Entreprise> optionalEntreprise = entrepriseRepository.findById(id_entreprise);

            if (optionalEntreprise.isPresent()) {
                return new ResponseEntity<>(optionalEntreprise.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/editEntreprise/{id_entreprise}")
    public ResponseEntity<Map<String,String>> updateEntreprise(@PathVariable Long id_entreprise, @RequestBody Entreprise newEntreprise) {
        try {
            entrepriseService.updateEntreprise(id_entreprise, newEntreprise);
            return new ResponseEntity<>(Collections.singletonMap("message","Entreprise mise à jour avec succès."), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(Collections.singletonMap("message",e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("message","Une erreur s'est produite lors de la mise à jour de l'entreprise."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteEntreprise(@PathVariable Long id) {
        try {
            entrepriseService.deleteEntreprise(id);
            return new ResponseEntity<>(Collections.singletonMap("message","Entreprise supprimée avec succès."), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(Collections.singletonMap("message",e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("message","Une erreur s'est produite lors de la suppression de l'entreprise."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

