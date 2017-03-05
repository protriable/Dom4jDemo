package com.protry;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/3/5 0005.
 * SAX解析XML
 */
public class SAXDemo {

    public static void main(String[] args) {
        //step 1: 获得SAX解析器工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            //step 2: 获得SAX解析器实例
            SAXParser parser = factory.newSAXParser();
            //step 3: 开始进行解析
            // 传入待解析的文档的处理器
            parser.parse(new File("D:/Demo/Dom4jDemo/src/main/resources/books.xml"), new MyHandler());

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
