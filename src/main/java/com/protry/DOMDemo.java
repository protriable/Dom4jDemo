package com.protry;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/3/5 0005.
 * DOM解析器
 * 将整个XML文档转化为DOM树存放在内存中
 * 对内存需求比较高。比较耗时。
 */
public class DOMDemo {

    public static void main(String[] args) {
        //step 1:获得DOM解析器工厂
        //工厂的作用是创建具体的解析器
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            //step 2:获得具体的dom解析器
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            /**
             * builder.parse() 方法将给定的内容解析为xml文档，并且返回一个新的DOM Document对象
             */
            // step 3:解析一个xml文档，获得Document对象
            Document document = builder.parse(new File("D:/Demo/Dom4jDemo/src/main/resources/books.xml"));

            printNode(document, 0);

            //获取文档的根元素，复制给rootElement变量
            Element rootElement = document.getDocumentElement();
            //获取根元素的count属性
            int count = Integer.parseInt(rootElement.getAttribute("count"));
            String str = rootElement.getAttribute("xmlns");
            System.out.println(str);
            System.out.println("There are " + count + " books , they are ");

            //获取rootElement的所有子节点(不包括属性节点),返回一个NodeList对象
            NodeList childNodes = rootElement.getChildNodes();
            for (int i=0; i < childNodes.getLength() ; i++) {
                //获取childNodes的第i个节点
                Node childNode = childNodes.item(i);
                //判断childNode是不是一个元素节点，并且它的nodeName值为book
                if (childNode.getNodeType() == Node.ELEMENT_NODE
                        && childNode.getNodeName().equals("book")) {
                    //若是，则获取childNode的所有子节点，返回一个NodeList对象
                    NodeList childNodes2 = childNode.getChildNodes();
                    //判断childNode_2是不是一个元素节点，并且它的nodeName值为name
                    for(int j = 0;j < childNodes2.getLength();j++){
                        //获取childNodes_2的第j个节点
                        Node childNode_2 = childNodes2.item(j);
                        //判断childNode_2是不是一个元素节点，并且它的 nodeName 值为name
                        if(childNode_2.getNodeType() == Node.ELEMENT_NODE
                                && childNode_2.getNodeName().equals("name")){
                            //若是，则获取childNode_2的所有子节点（不包括属性节点），返回一个NodeList对象
                            NodeList childNodes_3 = childNode_2.getChildNodes();
                            for(int k = 0;k < childNodes_3.getLength();k++){
                                //获取childNodes_3的第k个节点
                                Node childNode_3 = childNodes_3.item(k);
                                //判断childNodes_3是不是一个文本节点
                                if(childNode_3.getNodeType() == Node.TEXT_NODE){
                                    //若是，则打印输出这个文本节点的nodeValue
                                    System.out.println("  <<"+childNode_3.getNodeValue()+">>");
                                }
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印 DOM 节点
     * 输出格式为：
     *     nodeType(nodeName,nodeValue)
     *         "ATTRIBUTE"(attributeName=attributeValue)
     *         ...
     *         childNodeType[childNodeName,childNodeValue]
     *         ...
     */
    public static void printNode(Node node,int count) {
        if (node != null) {
            String tmp = "";
            for(int i = 0 ; i < count ; i++) tmp += "  ";
            //获取node节点的节点类型，赋值给nodeType变量
            int nodeType = node.getNodeType();
            switch (nodeType) {
                case Node.ATTRIBUTE_NODE: tmp += "ATTRIBUTE";break;
                case Node.CDATA_SECTION_NODE: tmp += "CDATA_SECTION";break;
                case Node.COMMENT_NODE:tmp += "COMMENT";break;
                case Node.DOCUMENT_FRAGMENT_NODE:tmp += "DOCUMENT_FRAGMENT";break;
                case Node.DOCUMENT_NODE:tmp += "DOCUMENT";break;
                case Node.DOCUMENT_TYPE_NODE:tmp += "DOCUMENT_TYPE";break;
                case Node.ELEMENT_NODE:tmp += "ELEMENT";break;
                case Node.ENTITY_NODE:tmp += "ENTITY";break;
                case Node.ENTITY_REFERENCE_NODE:tmp += "ENTITY_REFERENCE";break;
                case Node.NOTATION_NODE:tmp += "NOTATION";break;
                case Node.PROCESSING_INSTRUCTION_NODE:tmp += "PROCESSING_INSTRUCTION";break;
                case Node.TEXT_NODE:tmp += "TEXT";break;
                default:return;//invalid node type.
            }

            System.out.println(tmp+" ("+node.getNodeName()+","+node.getNodeValue()+")");
            /**
             * node.getAttributes()方法返回
             * 包含node节点的属性的 NamedNodeMap（如果它是 Element）
             */
            NamedNodeMap attrs = node.getAttributes();
            if(attrs != null)
                for(int i = 0 ; i < attrs.getLength() ; i++){
                    printNode(attrs.item(i),count+1);
                }
            /**
             * node.getChildNodes()方法返回
             * 包含node节点的所有子节点的 NodeList。
             */
            NodeList childNodes = node.getChildNodes();
            for(int i = 0 ; i < childNodes.getLength() ; i++){
                printNode(childNodes.item(i),count+1);
            }
        }
    }
}
