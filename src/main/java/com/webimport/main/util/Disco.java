package com.webimport.main.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Component
public class Disco {

    @Value("C:\\Users\\coelh\\Desktop\\WebImport\\src\\main\\resources")
    private String raiz;

    @Value("arquivos")
    private String diretorioFotos;

    public String salvarFoto(MultipartFile arquivo) {
        return this.salvar(this.diretorioFotos, arquivo);
    }

    public String salvar(String diretorio, MultipartFile arquivo) {
        Path diretorioPath = Paths.get(this.raiz, diretorio);
        Path arquivoPath = diretorioPath.resolve(Objects.requireNonNull(arquivo.getOriginalFilename()));

        try {
            Files.createDirectories(diretorioPath);
            arquivo.transferTo(arquivoPath.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
        }
        return arquivo.getOriginalFilename();
    }
}