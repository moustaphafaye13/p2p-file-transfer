package p2p_node.service;

import p2p_node.config.NodeConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    @Autowired
    private NodeConfig config;

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
}