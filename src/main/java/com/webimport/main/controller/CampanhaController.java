package com.webimport.main.controller;

import com.webimport.main.model.entity.Campanha;
import com.webimport.main.service.CampanhaService;
import com.webimport.main.util.Disco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RestController
@RequestMapping(value = "/api/webimport")
public class CampanhaController {


    @Autowired
    Disco disco = new Disco();

    @Autowired
    public CampanhaService service;

    @GetMapping()
    public ResponseEntity<List<Campanha>> findAll() {
        List<Campanha> campanha = service.buscarTodos();
        return ResponseEntity.ok().body(campanha);
    }

    @PostMapping
    public ResponseEntity<Void> upload(@RequestParam MultipartFile arquivo) {
        String filename = disco.salvarFoto(arquivo);
        service.insertCsv(filename);
        return ResponseEntity.noContent().build();
    }




//    @PostMapping("/upload")
//    public void upload(@RequestParam MultipartFile arquivo) {
//        StringBuilder filesName = new StringBuilder();
//        Path fileNameAndPath = Paths.get(diretorioUpload,arquivo.getOriginalFilename());
//        filesName.append(arquivo.getOriginalFilename());
//        try {
//            Files.write(fileNameAndPath, arquivo.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//	@GetMapping(value="/csv")
//	public ResponseEntity<Void> adicionarCsv(){
//		service.insertCsv();
//		return ResponseEntity.noContent().build();
//	}


}
