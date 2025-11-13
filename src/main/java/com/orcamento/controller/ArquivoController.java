package com.orcamento.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class ArquivoController {

    @GetMapping("/pdf/{nome}")
    public ResponseEntity<Resource> baixarPdf(@PathVariable String nome) {

        Path local = Path.of("data/saida/" + nome);
        Path render = Path.of("/var/data/saida/" + nome);

        Path caminho = Files.exists(local) ? local : render;

        Resource arquivo = new FileSystemResource(caminho.toFile());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + nome)
                .contentType(MediaType.APPLICATION_PDF)
                .body(arquivo);
    }
}
