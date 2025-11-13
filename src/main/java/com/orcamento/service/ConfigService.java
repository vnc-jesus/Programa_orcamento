package com.orcamento.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orcamento.model.Empresa;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ConfigService {

    private static final Path BASE_DIR = Path.of("/var/data");
    private static final Path UPLOADS = BASE_DIR.resolve("uploads");
    private static final Path LOGO_FILE = UPLOADS.resolve("logo.png");
    private static final Path CONFIG_FILE = BASE_DIR.resolve("empresa.json");


    public Empresa carregarConfig() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (Files.exists(CONFIG_FILE)) {
                return mapper.readValue(CONFIG_FILE.toFile(), Empresa.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Empresa();
    }

    public void salvarConfig(Empresa empresa, MultipartFile logo) throws Exception {
        // Cria a pasta "uploads" se não existir
        Files.createDirectories(UPLOADS);

        if (logo != null && !logo.isEmpty()) {
            logo.transferTo(LOGO_FILE.toFile());
            empresa.setCaminhoLogo("/data/uploads/logo.png");
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(CONFIG_FILE.toFile(), empresa);

    }
}
