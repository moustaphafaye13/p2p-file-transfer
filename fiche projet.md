
# FICHE DE PROJET : Système P2P avec Réplication
[cite_start]**Thème :** Architecture P2P et Réplication de fichiers [cite: 3]
[cite_start]**Cours :** Systèmes d'Information Distribués [cite: 2]

---

## 1. Intitulé et Objectifs
[cite_start]**Intitulé :** Système distribué de partage de fichiers en architecture Peer-to-Peer (P2P) avec réplication et tolérance aux pannes[cite: 4, 5].

### Objectif Général
[cite_start]Le but est de comprendre et d'implémenter les concepts fondamentaux des systèmes distribués via la conception d'un système de partage de fichiers[cite: 6, 7].

### Objectifs Pédagogiques
[cite_start]À l'issue de ce projet, vous devrez être capable de[cite: 8, 9]:
* [cite_start]Comprendre les principes de l'architecture P2P[cite: 10].
* [cite_start]Mettre en œuvre une communication entre nœuds distribués[cite: 10].
* [cite_start]Implémenter un mécanisme de réplication de données[cite: 11].
* [cite_start]Gérer la tolérance aux pannes[cite: 11].
* [cite_start]Simuler un environnement distribué sur une seule machine[cite: 11].

---

## 2. Architecture et Description du Système
[cite_start]Le système se compose de plusieurs nœuds indépendants exécutant la même application[cite: 12, 13]. [cite_start]Chaque nœud dispose de son propre espace de stockage local, d'une API de communication et d'une liste de nœuds voisins (peers)[cite: 14].

### Principes Fondamentaux
* [cite_start]**Architecture P2P :** Aucun serveur central ; tous les nœuds sont égaux et agissent à la fois comme client et serveur[cite: 16, 17, 18, 19, 20].
* [cite_start]**Distribution :** Les fichiers sont répartis ; chaque nœud ne possède qu'une partie des données globales[cite: 21, 22, 23].
* [cite_start]**Réplication :** Chaque fichier doit être copié sur au moins un autre nœud pour garantir la disponibilité[cite: 24, 25, 26].
* [cite_start]**Tolérance aux pannes :** Le système doit rester opérationnel même si un nœud est arrêté[cite: 27, 28].

---

## 3. Environnement Technique
* [cite_start]**Langage :** Java (Spring Boot recommandé)[cite: 30].
* [cite_start]**Communication :** API REST[cite: 32].
* [cite_start]**Stockage :** Système de fichiers local[cite: 33].
* [cite_start]**Déploiement :** Simulation sur une seule machine en utilisant des ports différents (ex: Node A sur 5000, Node B sur 5001, etc.)[cite: 34, 35].

---

## 4. Fonctionnalités Attendues

| Catégorie | Fonctionnalités |
| :--- | :--- |
| **Base** | [cite_start]Ajouter un fichier, lire un fichier, lister les fichiers locaux[cite: 37, 38, 39, 40]. |
| **Distribué** | [cite_start]Envoi de fichiers, réplication automatique, recherche chez les voisins[cite: 41, 42, 43, 44]. |
| **Résilience** | [cite_start]Continuité de service en cas de panne d'un nœud[cite: 45, 46]. |

---

## 5. Modalités de Livraison et Évaluation

### Livrables
[cite_start]**Date limite :** 01 mai 2026[cite: 54].
[cite_start]Les travaux sont à soumettre par groupe de 4 maximum (avec INE, mail, noms) sur la plateforme TD2 ou par mail à `mahamadou.toure@unchk.edu.sn`[cite: 54, 58].
* [cite_start]Code source complet[cite: 55].
* [cite_start]Rapport technique (architecture et explications)[cite: 56].
* [cite_start]Scénario de démonstration[cite: 57].

### Grille d'Évaluation
* [cite_start]**Architecture P2P :** 30% [cite: 63]
* [cite_start]**Réplication :** 25% [cite: 63]
* [cite_start]**Recherche distribuée :** 20% [cite: 63]
* [cite_start]**Tolérance aux pannes :** 15% [cite: 63]
* [cite_start]**Qualité du code & démo :** 10% [cite: 63]

---

## 6. Conseils et Bonus
* [cite_start]**Méthodologie :** Commencez par un nœud simple, testez chaque fonction, puis ajoutez progressivement la distribution et les logs[cite: 65, 66, 67, 68].
* [cite_start]**Bonus (Facultatif) :** Réplication multi-nœuds, interface utilisateur, journalisation réseau ou stratégie de réplication intelligente[cite: 69, 70].

<FollowUp label="Souhaites-tu que je t'aide à concevoir le diagramme d'architecture ou la structure des API REST pour ce projet ?" query="Peux-tu me proposer une structure d'API REST et un diagramme d'architecture pour un système P2P de partage de fichiers avec réplication ?" />