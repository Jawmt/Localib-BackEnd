package com.m2iformation.ecf.Localib.client;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    /**
     * Methode permettant de recuperer l'ensemble des clients sauvegarder en bdd
     * @return La liste de l'ensemble des clients sauvegarder en base de données
     */
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    /**
     * Methode permettant de sauvegarder un client en base de donnees
     * @param entity Le client a sauvegarder en bdd
     * @return le client sauvegarder
     */
    public Client save(Client entity) {
        return clientRepository.save(entity);
    }

    /**
     * Methode permettant de supprimer un client en base de données grace a son id
     * @param aLong id du client
     */
    public void deleteById(Long aLong) {
        clientRepository.deleteById(aLong);
    }

    /**
     * Methode permettant de recuperer un client via son id
     * @param aLong l'id du client
     * @return le client
     */
    public Optional<Client> findById(Long aLong) {
        return clientRepository.findById(aLong);
    }

    /**
     * Methode permettant de modifier les informations d'un client
     * @param client le client avec nouvelles infos
     * @param idClient l'id du client a modifier
     * @return le client modifier
     */
    public Client modifierClient(Client client, Long idClient){
        Optional<Client> clientOptional = this.findById(idClient);
        if(clientOptional.isEmpty()){
            return clientRepository.save(client);
        }
        Client clientAModifier = clientOptional.get();
        clientAModifier.setNom(client.getNom());
        clientAModifier.setPrenom(client.getPrenom());
        clientAModifier.setEmail(client.getEmail());
        clientAModifier.setNumeroTel(client.getNumeroTel());
        clientAModifier.setDateDeNaissance(client.getDateDeNaissance());

        return clientRepository.save(client);
    }

}
