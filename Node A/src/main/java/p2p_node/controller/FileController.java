package p2p_node.controller;


import org.springframework.web.client.RestTemplate;
import p2p_node.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/{filename}")
    public ResponseEntity<String> upload(
            @PathVariable String filename,
            @RequestBody byte[] data,
            @RequestHeader(value = "X-Replication-Call", required = false) String isReplication) {

        try {
            // 1. Sauvegarder le fichier localement
            fileService.saveFile(filename, data);

            // 2. Répliquer le fichier sur les autres noeuds, sauf si c'est déjà une réplication
            if (!"true".equals(isReplication)) {
                fileService.replicateFile(filename, data);
            }

            return ResponseEntity.ok("Fichier uploadé et répliqué avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de l'upload : " + e.getMessage());
        }
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(
            @PathVariable String filename,
            @RequestHeader(value = "X-Search-Call", required = false) String isSearch) {
        try {
            // 1. Essayer de récupérer le fichier localement
            byte[] data = fileService.getFile(filename);
            System.out.println("Fichier trouvé localement.");
            return ResponseEntity.ok(data);
        } catch (RuntimeException e) {
            // 2. Si non trouvé, le chercher chez les voisins (sauf si c'est déjà une recherche)
            if (!"true".equals(isSearch)) {
                System.out.println("Fichier non trouvé localement, recherche sur le réseau...");
                byte[] data = fileService.searchFile(filename);
                if (data != null) {
                    // 3. Si trouvé sur le réseau, le renvoyer et le sauvegarder localement pour le cache
                    System.out.println("Fichier trouvé sur le réseau, sauvegarde locale.");
                    fileService.saveFile(filename, data);
                    return ResponseEntity.ok(data);
                } else {
                    // 4. Si introuvable, renvoyer une erreur 404
                    System.out.println("Fichier introuvable sur le réseau.");
                    return ResponseEntity.notFound().build();
                }
            } else {
                // C'est déjà une recherche, ne pas continuer la recherche en cascade
                System.out.println("Fichier non trouvé localement (requête de recherche), pas de recherche en cascade.");
                return ResponseEntity.notFound().build();
            }
        }
    }
}