package org.iine.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HtmlParser {

	private URL url;
	private String title = "";

	public HtmlParser(String uri) throws Exception{
		url = new URL(uri);
		read();
	}
	
	
	
	private void read() throws Exception{
      InputStreamReader inReader = new InputStreamReader(url.openStream());
      BufferedReader reader = new BufferedReader(inReader);
      String line;

      while ((line = reader.readLine()) != null) {
          int i0 = line.indexOf("<title>");
          if (i0 > 0) {
        	  this.setTitle(line.substring(i0));
        	  break;
          }
          
      }
      reader.close();

	}

	
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	
		
	
}
