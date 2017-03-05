package com.protry;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;

/**
 * Created by Administrator on 2017/3/5 0005.
 */
public class MyHandler extends DefaultHandler {

    //使用栈这个数据结构来保存 // 不一定是好的结构 建议用ArrayDeque
    private Stack<String> stack = new Stack<String>();

    @Override
    public void startDocument() throws SAXException {
        System.out.println("start document -> parse begin");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("end document -> parse finished");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("start element-------------");
        System.out.println("     localName: "+ localName);
        System.out.println("     qName: "+ qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("end element-----------");
        System.out.println("    localName: " + localName);
        System.out.println("    qName: " + qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println("characters-----------");
        System.out.println("    ch: " + ch);
        System.out.println("    start: " + start);
        System.out.println("    length: " + length);
    }
}
