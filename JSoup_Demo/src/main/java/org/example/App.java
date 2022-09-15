package org.example;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App
{
    public static void main( String[] args )
    {
        // Parsing lists
//        try {
//            // userAgent() not specified, so default one in use
//            // HttpConnection.DEFAULT_UA
//            Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Main_Page")
//                                .get();
//
//            Element title = doc.getElementById("mp-itn-h2");
//            assert title != null;
//            System.out.println(title.text());
//
//            Element parentDivForList = doc.getElementById("mp-itn");
//
//            assert parentDivForList != null;
//            for(Element list : parentDivForList.getAllElements()) {
//                if(list.tagName().equals("li")) {
//                    System.out.println(list.text());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            // a user agent is any software, acting on behalf of a user,
            // which "retrieves, renders and facilitates end-user interaction with Web content"
            // most commonly used agent: Mozilla
            // nothing to do with the Mozilla browser
            Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_countries_by_GDP_(nominal)")
                                .userAgent("Mozilla")
                                .get();
            // JSoup cannot parse tr without the table tag
            for (Element row :
                    doc.select("table[style*=text-align:right] tr")) {
                System.out.print(row.select("td:nth-of-type(1)").text() + " ");
                System.out.print(row.select("td:nth-of-type(2)").text() + " ");
                System.out.print(row.select("td:nth-of-type(3)").text() + " ");
                System.out.print(row.select("td:nth-of-type(4)").text() + " ");
                System.out.print(row.select("td:nth-of-type(5)").text() + " ");
                System.out.print(row.select("td:nth-of-type(6)").text() + " ");
                System.out.print(row.select("td:nth-of-type(7)").text() + " ");
                System.out.println(row.select("td:nth-of-type(8)").text());
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
