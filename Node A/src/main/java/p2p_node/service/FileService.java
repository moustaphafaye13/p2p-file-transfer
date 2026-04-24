package p2p_node.service;

import p2p_node.config.NodeConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Service
public class FileService {

    @Autowired
    private NodeConfig config;
    
    private final RestTemplate restTemplate = new RestTemplate();

    // ✅ Sauvegarde fichier
    public void saveFile(String filename, byte[] data) {
        try {
            Path path = Paths.get(config.getStorage(), filename);

            // créer dossier si n'existe pas
            Files.createDirectories(path.getParent());

            // écrire fichier
            Files.write(path, data);

            System.out.println("Fichier sauvegardé : " + path);

        } catch (IOException e) {
            throw new RuntimeException("Erreur sauvegarde fichier", e);
        }
    }

    // ✅ Lecture fichier
    public byte[] getFile(String filename) {
        try {
            Path path = Paths.get(config.getStorage(), filename);

            if (Files.exists(path)) {
                return Files.readAllBytes(path);
            } else {
                throw new RuntimeException("Fichier introuvable");
            }

        } catch (IOException e) {
            throw new RuntimeException("Erreur lecture fichier", e);
        }
    }

    // ✅ ajouter la réplication 
    public void replicateFile(String filename, byte[] data) {
        // pour chaque pair connu, envoyer le fichier
        for (String peer : config.getPeers()) {
            try {
                String url = peer + "/files/" + filename;
                HttpHeaders headers = new HttpHeaders();
                headers.set("X-Replication-Call", "true");
                HttpEntity<byte[]> requestEntity = new HttpEntity<>(data, headers);
                restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
                System.out.println("Fichier répliqué à " + peer);
            } catch (Exception e) {
                System.err.println("Erreur réplication vers " + peer + ": " + e.getMessage());
            }
        }
    }

    // Recherche distribuée des fichiers
    // il faut implémenter un mécanisme de recherche qui interroge les pairs pour trouver un fichier
    public byte[] searchFile(String filename) {
        // interroger chaque pair
        for (String peer : config.getPeers()) {
            try {
                String url = peer + "/files/download/" + filename;
                
                // Créer des headers HTTP pour identifier cette requête comme une recherche
                HttpHeaders headers = new HttpHeaders();
                headers.set("X-Search-Call", "true");
                HttpEntity<String> entity = new HttpEntity<>(headers);
                
                ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);
                if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                    System.out.println("Fichier trouvé chez " + peer);
                    return response.getBody();
                }
            } catch (Exception e) {
                // si l'interrogation échoue, continue avec le pro pair
                System.err.println("Erreur recherche chez " + peer + ": " + e.getMessage());
            }
        }
        // si aucun pair a trouvé le fichier, renvoie null
        return null;
    }
}