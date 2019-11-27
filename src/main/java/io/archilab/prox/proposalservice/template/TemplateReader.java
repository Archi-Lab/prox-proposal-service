package io.archilab.prox.proposalservice.template;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import org.springframework.core.io.ClassPathResource;

public class TemplateReader {

  private static TemplateReader instance = null;
  private String template;

  private TemplateReader() {

    // Import the the template from propositionTemplate.txt into proposalText
    ClassPathResource resource = new ClassPathResource("propositionTemplate.txt");
    String content;
    try {
      InputStream inputStream = resource.getInputStream();
      content = new Scanner(inputStream).useDelimiter("\\Z").next();

    } catch (IOException e) {
      // TODO Fehlerbehandlung, wenn propositionTemplate.txt nicht aufrufbar ist. Vielleicht ein
      // vereinfachtes Template einbauen?
      content = "";
    }

    this.template = content;
  }

  public static TemplateReader getInstance() {
    if (TemplateReader.instance == null) {
      TemplateReader.instance = new TemplateReader();
    }
    return TemplateReader.instance;
  }

  public String getTemplate() {
    return this.template;
  }
}
