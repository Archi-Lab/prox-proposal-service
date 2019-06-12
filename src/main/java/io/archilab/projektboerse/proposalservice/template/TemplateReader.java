package io.archilab.projektboerse.proposalservice.template;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class TemplateReader {
    private static TemplateReader instance = null;
    private String template;

    private TemplateReader(){

        //Import the the template from propositionTemplate.txt into proposalText
        ClassPathResource resource = new ClassPathResource("propositionTemplate.txt");
        String content;
        try {
            InputStream inputStream = resource.getInputStream();
            content = new Scanner(inputStream).useDelimiter("\\Z").next();

        } catch (IOException e) {
            //TODO Fehlerbehandlung, wenn propositionTemplate.txt nicht aufrufbar ist. Vielleicht ein vereinfachtes Template einbauen?
            content = "";
        }

        template = content;
    }

    public static TemplateReader getInstance(){
        if (instance == null) instance = new TemplateReader();
        return instance;
    }

    public String getTemplate() {
        return template;
    }
}
