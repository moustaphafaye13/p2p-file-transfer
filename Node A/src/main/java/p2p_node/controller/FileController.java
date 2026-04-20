package p2p_node.controller;


import org.springframework.web.client.RestTemplate;
import p2p_node.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable String filename) {
        byte[] data = fileService.getFile(filename);
        return ResponseEntity.ok(data);
    }

//    @GetMapping("/{filename}")
//    public ResponseEntity<byte[]> download(@PathVariable String filename) {
//        byte[] data = fileService.getFile(filename);
//        return ResponseEntity.ok(data);
//    }

    @GetMapping("/ask")
    public String askNodeB() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(
                "http://localhost:5001/files/test",
                String.class
        );
        return "Réponse de Node B: " + response;
    }
    @PostMapping("/send")
    public String sendFileToNodeB() {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:5001/files/b.txt";

        String content = "Bonjour depuis Node A 🚀";

        restTemplate.postForObject(url, content.getBytes(), String.class);

        return "Fichier envoyé à Node B";
    }
}