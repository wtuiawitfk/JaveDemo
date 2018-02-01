package com.yuhh.demo.dom;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


/**
 * 获取一个document对象
 */
public class DomTest {

    @Test
    public void domTest() throws Exception {
        //1.获取你要操作的xml文件对象
        File f = new File("./resources/contacts.xml");
        //2.获取DocumentBuilderFactroy对象
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        //3.获取DocumentBuilder对象
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        //4.获取Documen对象
        Document doc = builder.parse(f);
        System.out.println(doc);
    }
}
