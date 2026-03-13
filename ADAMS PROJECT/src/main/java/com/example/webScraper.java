package com.example;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class webScraper {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://news.ycombinator.com/").get();
            Elements articles = doc.select(".titleline > a");

            for (Element article : articles) {
                String title = article.text();
                String url = article.absUrl("href");
                System.out.println(title + " - " + url);
            }
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error fetching or parsing the webpage: " + e.getMessage());
        }
    }
}
