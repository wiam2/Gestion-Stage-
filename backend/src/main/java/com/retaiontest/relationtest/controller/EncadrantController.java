package com.retaiontest.relationtest.controller;

import com.retaiontest.relationtest.EXception.ConnectBdException;
import com.retaiontest.relationtest.EXception.NotFoundException;
import com.retaiontest.relationtest.Encadrant;

import com.retaiontest.relationtest.repository.EncadrantRepository;
import com.retaiontest.relationtest.servic.EncadrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/encadrants")
public class EncadrantController {
    @Autowired
    private final EncadrantService encadrantService;
    @Autowired
    private EncadrantRepository EncadrantRepository;

    @Autowired
    public EncadrantController(EncadrantService encadrantService) {
        this.encadrantService = encadrantService;
    }

    @PostMapping("/register")
    public ResponseEntity<Encadrant> registerEncadrantWithUser(@RequestBody Encadrant entreprise) {
        try {
          Encadrant registeredEncadrant = encadrantService.registerEncadrantWithUser(entreprise);
            return new ResponseEntity<>(registeredEncadrant, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/afficherEncadrants")
    public List<Encadrant> obtenirTousLesEncadrants() throws ConnectBdException {
        return encadrantService.obtenirTousEncadrants();
    }

    @DeleteMapping("/supprimer/{id}")
        public ResponseEntity<Map<String,String>> deleteEncadrant(@PathVariable Long id) {
            try {
               encadrantService.supprimerEncadrantParId(id);
                return new ResponseEntity<>(Collections.singletonMap("message","Encadrant supprimée avec succès."), HttpStatus.OK);
            } catch (NotFoundException e) {
                return new ResponseEntity<>(Collections.singletonMap("message",e.getMessage()), HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                return new ResponseEntity<>(Collections.singletonMap("message","Une erreur s'est produite lors de la suppression de l'encadrant."), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }



    @GetMapping("/afficherEncadrant/{id_Encadrant}")
    public ResponseEntity<Encadrant> afficherProfileEncadrant(@PathVariable Long id_Encadrant) {
        try {
            Optional<Encadrant> optionalEncadrant =EncadrantRepository.findById(id_Encadrant) ;

            if (optionalEncadrant.isPresent()) {
                return new ResponseEntity<>(optionalEncadrant.get() , HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateEncadrant/{id_encadrant}")
    public ResponseEntity<Map<String,String>> updateEncadrant(@PathVariable Long id_encadrant, @RequestBody Encadrant newEncadrant) {
        try {
            encadrantService.updateEncadrant(id_encadrant, newEncadrant);
            return new ResponseEntity<>(Collections.singletonMap("message","Encadrant mise à jour avec succès."), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(Collections.singletonMap("message",e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("message","Une erreur s'est produite lors de la mise à jour de l'encadrant."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
