package com.webimport.main.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.webimport.main.model.entity.Campanha;
import com.webimport.main.model.entity.Campo;
import com.webimport.main.model.entity.Telefone;
import com.webimport.main.model.repository.CampanhaRepository;
import com.webimport.main.model.repository.CampoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class CampanhaService {

    @Autowired
    public CampanhaRepository repository;

    @Autowired
    public CampoRepository repositoryCampo;

    @Autowired
    public TelefoneService telefoneService;

    public List<Campanha> buscarTodos() {
        return repository.findAll();
    }

    public void insertCsv(String fileName) {
        Campo c;
        Telefone t = new Telefone();
        Campanha p;
        String diretorioUpload = System.getProperty("user.dir") + "\\src\\main\\resources\\arquivos";
        List<Campanha> listaCampanhas = new ArrayList<>();
        Reader reader;
        {
            try {
                reader = Files.newBufferedReader(Paths.get(diretorioUpload + "\\" + fileName));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
                List<String[]> campanhas = csvReader.readAll();
                for (String[] campanha : campanhas) {
                    c = new Campo();
                    p = new Campanha();
                    p.setNome(campanha[0]);
                    p.setQtdTelefone(Integer.parseInt(campanha[1]));
                    Integer qtdTelefone = p.getQtdTelefone();
                    p.setTelefone((validacaoQtdTelefone(campanha, qtdTelefone, p)));
                    p.setCampos(verificarCampos(qtdTelefone + 2, campanha));
                    listaCampanhas.add(p);
                }
                insertCampanha(listaCampanhas);

            } catch (IOException | CsvException | ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Telefone> validacaoQtdTelefone(String[] pessoa, Integer qtdTelefone, Campanha p) throws ParseException {
        Telefone telefone = new Telefone();
        List<String> phone = new ArrayList<>(Arrays.asList(pessoa).subList(2, qtdTelefone + 2));
        List<String> novaListTelefone = new ArrayList<>();
        for (String tel : phone) {
            String telefoneFormatado = formatString(tel, "(##) #####-####");
            novaListTelefone.add(telefoneFormatado);
        }
        telefone.setPhones(novaListTelefone);
        p.setTelefone(Collections.singletonList(telefone));
        return Collections.singletonList(telefone);
    }
    public List<Campo> verificarCampos(Integer qtdCampos, String[] pessoa) {
        Campo p;
        List<Campo> campos = new ArrayList<>();
        Integer qtdTotal = Math.toIntExact(Arrays.stream(pessoa).count()) - 1;
        for (int a = qtdCampos; a <= qtdTotal; a++) {
            p = new Campo();
            p.setNome("campo-" + ((qtdTotal + 1)- a));
            p.setValor(pessoa[a]);
            campos.add(p);
        }
        return campos;
    }

    public List<Campanha> insertCampanha(List<Campanha> obj) {
        try {
            return repository.saveAll(obj);
        } catch (RuntimeException e) {
            throw new IllegalStateException(e);
        }

    }


    public static String formatString(String string, String mask)
            throws java.text.ParseException {
        javax.swing.text.MaskFormatter mf =
                new javax.swing.text.MaskFormatter(mask);
        mf.setValueContainsLiteralCharacters(false);

        return mf.valueToString(string);
    }


    public Campo insertCampo(Campo obj) {
        try {
            return repositoryCampo.save(obj);
        } catch (RuntimeException e) {
            throw new IllegalStateException(e);
        }
    }
}


//        List<Campanha> campanhas = LerAquivoCsv.lerCsv(filename);
//        System.out.println(campanhas);
//            assert campanhas != null;
//            for(Campanha camp : campanhas){
//                System.out.println(camp.getCampos());
////                System.out.println(camp.getTelefones());
//            }
//            repository.saveAll(campanhas);

//    }

//public Campanha insert(Campanha p){
//        return repository.saveAndFlush(p);
//        }


//    public long procurarId(){
//        return repository.findAll().stream().count();
//    }
//        }
