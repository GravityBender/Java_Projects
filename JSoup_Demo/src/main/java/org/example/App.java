package org.example;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App
{
    public static void main( String[] args )
    {
        try {
            Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Main_Page")
                                .get();
            
            Element title = doc.getElementById("mp-itn-h2");
            assert title != null;
            System.out.println(title.text());

            Element parentDivForList = doc.getElementById("mp-itn");

            assert parentDivForList != null;
            for(Element list : parentDivForList.getAllElements()) {
                if(list.tagName().equals("li")) {
                    System.out.println(list.text());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
