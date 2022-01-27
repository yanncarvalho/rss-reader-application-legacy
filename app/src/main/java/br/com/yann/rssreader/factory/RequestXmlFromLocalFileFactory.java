package br.com.yann.rssreader.factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import br.com.yann.rssreader.factory.interfaces.RequestXmlInterface;


public class RequestXmlFromLocalFileFactory implements RequestXmlInterface {


  private String xml="";

  @Override
  public String getXml(String uri) throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader(uri));
    String aux;

    while ((aux = reader.readLine()) != null) {
      xml = xml.concat(aux);
    }

    reader.close(); 
    return this.xml;
  }
}