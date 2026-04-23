
# PROJET : Système P2P avec Réplication (Spring Boot)
[cite_start]**Institution :** UNCHK - MASTER IL [cite: 71]
[cite_start]**Cours :** Systèmes d'Information Distribués [cite: 72]
[cite_start]**Enseignant :** Dr Mahamadou TOURE [cite: 72]

> **Important :** Ce projet est un starter partiel. [cite_start]Certaines fonctionnalités sont volontairement incomplètes ; votre travail est de compléter et d'améliorer le système[cite: 74, 75].

---

## 1. Objectif et Structure
[cite_start]L'objectif est de construire un système distribué en architecture P2P permettant le partage de fichiers, la réplication et la tolérance aux pannes[cite: 76, 77].

### Structure du projet (`p2p-node/`)
[cite_start]Le projet est organisé selon une architecture classique Spring Boot[cite: 78, 79]:
* [cite_start]**`controller/`** : `FileController.java` (Points d'entrée API)[cite: 80, 81].
* [cite_start]**`service/`** : `FileService.java` (**À compléter**)[cite: 82, 83].
* [cite_start]**`config/`** : `NodeConfig.java` (Gestion de la configuration)[cite: 84, 85].
* [cite_start]**`P2PNodeApplication.java`** : Classe principale[cite: 86].
* [cite_start]**`application.yml`** : Fichier de configuration[cite: 87].

---

## 2. Configuration et Code de Base

### Configuration (`application.yml`)
[cite_start]Chaque instance du nœud doit être configurée avec son propre port, son dossier de stockage et la liste de ses pairs[cite: 88, 89, 92, 93, 94]:
```yaml
server:
  [cite_start]port: 5000 # À adapter pour chaque nœud [cite: 91, 95]
node:
  [cite_start]storage: storage_node_5000 [cite: 93]
  peers:
    - [cite_start]http://localhost:5001 [cite: 94]
```

### CODE FOURNI
1. Classe principale
```java
@SpringBootApplication
public class P2PNodeApplication {
 public static void main(String[] args) {
 SpringApplication.run(P2PNodeApplication.class, args);
 }
}
```

2. Configuration des nœuds
```java
@Component
@ConfigurationProperties(prefix = "node")
@Data
public class NodeConfig {
 private String storage;
 private List<String> peers;
}
```

3. Controller (PARTIEL - À COMPLÉTER)
```java
@RestController
@RequestMapping("/files")
public class FileController {
 @Autowired
 private FileService fileService;
 @PostMapping("/{filename}")
 public ResponseEntity<String> upload(
 @PathVariable String filename,
 @RequestBody byte[] data) {
 fileService.saveFile(filename, data);
 return ResponseEntity.ok("OK");
 }
 @GetMapping("/{filename}")
 public ResponseEntity<byte[]> download(@PathVariable String filename) {
 byte[] data = fileService.getFile(filename);
 return ResponseEntity.ok(data);
 }
}
```
4. Service (PARTIEL - À COMPLÉTER)
```java
@Service
public class FileService {
 @Autowired
 private NodeConfig config;
 // TODO 1: Sauvegarder le fichier localement
 public void saveFile(String filename, byte[] data) {
 // À implémenter
 }
 // TODO 2: Lire un fichier local
 public byte[] getFile(String filename) {
 // À implémenter
 return null;
 }
 // TODO 3: Ajouter la réplication
 private void replicateFile(String filename, byte[] data) {
 // À implémenter
 }
// TODO 4: Recherche distribuée
 private byte[] searchInPeers(String filename) {
 // À implémenter
 return null;
 }
}
```


### Classes de Configuration
[cite_start]Le système utilise `@ConfigurationProperties` pour injecter les paramètres du nœud dans la classe `NodeConfig`[cite: 104, 106, 108, 110, 111].

---

## 3. Implémentation du Service (Travail à faire)
La logique métier réside dans `FileService.java`. [cite_start]Vous devez implémenter les quatre méthodes clés suivantes[cite: 130, 132]:

1.  [cite_start]**`saveFile(filename, data)`** : Sauvegarder le fichier sur le système de fichiers local[cite: 135, 136].
2.  [cite_start]**`getFile(filename)`** : Lire un fichier depuis le stockage local[cite: 139, 140].
3.  [cite_start]**`replicateFile(filename, data)`** : Envoyer automatiquement une copie du fichier vers un autre nœud[cite: 144, 145].
4.  [cite_start]**`searchInPeers(filename)`** : Si le fichier est absent localement, interroger les pairs pour le récupérer[cite: 148, 149].

---

## 4. Étapes du Travail (Roadmap)

<Steps>
[cite_start]{/* Reason: Le document insiste sur une progression par étapes logiques où le succès de la suivante dépend de la précédente. [cite: 154, 182] */}
  <Step title="Stockage Local" subtitle="Étape 1">
    Implémenter l'upload et le download. [cite_start]Résultat : ça fonctionne sur un seul nœud[cite: 155, 156, 157].
  </Step>
  <Step title="Multi-nœuds" subtitle="Étape 2">
    [cite_start]Lancer deux nœuds (ex: Port 5000 et 5001) et tester leur indépendance[cite: 158, 159, 160, 161].
  </Step>
  <Step title="Réplication" subtitle="Étape 3">
    Compléter `replicateFile()`. [cite_start]Règle : quand un fichier est ajouté, il est envoyé à un autre nœud (présent sur 2 nœuds)[cite: 162, 163, 164, 165].
  </Step>
  <Step title="Recherche Distribuée" subtitle="Étape 4">
    Compléter `searchInPeers()`. [cite_start]Si absent localement, le nœud doit interroger ses voisins[cite: 166, 167, 168].
  </Step>
  <Step title="Tolérance aux Pannes" subtitle="Étape 5">
    [cite_start]Simuler une panne : Ajouter un fichier, arrêter le nœud d'origine, et vérifier qu'il est toujours accessible via un autre nœud[cite: 169, 170, 171, 172].
  </Step>
</Steps>

---

## 5. Livrables et Bonus
* [cite_start]**Livrables :** Code source, rapport technique (architecture + choix) et une démonstration vidéo de 10 minutes maximum[cite: 173, 174, 175, 176].
* [cite_start]**Bonus :** Réplication multi-nœuds, logs des échanges réseau, interface graphique ou stratégie intelligente[cite: 177, 178, 179, 180].

> **Conseil :** Ne codez pas tout d'un coup. [cite_start]Avancez et testez étape par étape[cite: 181, 182].

<Elicitations message="Pour vous aider à démarrer le codage :">
  <Elicitation label="Générer le code Java pour le stockage local" query="Peux-tu m'aider à implémenter les méthodes saveFile et getFile en Java Spring Boot pour sauvegarder les fichiers dans le dossier spécifié par NodeConfig ?" />
  <Elicitation label="Implémenter la logique de réplication REST" query="Comment implémenter la méthode replicateFile pour qu'elle envoie un fichier à un pair via une requête RestTemplate ou WebClient ?" />
  <Elicitations label="Expliquer la recherche distribuée (Step 4)" query="Quelle est la meilleure stratégie pour implémenter searchInPeers sans créer de boucles infinies entre les nœuds ?" />
</Elicitations>