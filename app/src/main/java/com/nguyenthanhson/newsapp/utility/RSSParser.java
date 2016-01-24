package com.nguyenthanhson.newsapp.utility;

import com.nguyenthanhson.newsapp.model.ArticleInfo;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by SON on 1/18/2016.
 */
public class RSSParser {
    public List<ArticleInfo> getListArticle(String link){
        try {
            URL url=new URL(link);
            SAXParserFactory factory=SAXParserFactory.newInstance();
            SAXParser parser=factory.newSAXParser();
            XMLReader reader=parser.getXMLReader();
            RSSHandler handler=new RSSHandler();
            reader.setContentHandler(handler);
            InputSource inputSource=new InputSource(url.openStream());
            reader.parse(inputSource);
            return handler.getListArticle();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
