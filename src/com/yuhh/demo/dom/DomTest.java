package com.yuhh.demo.dom;

import org.junit.jupiter.api.Test;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;


/**
 * 获取一个document对象
 */
public class DomTest {

    private static File f = new File("./resources/contacts.xml");
    private static Document doc;

    static {
        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void transformTo() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source source = new DOMSource(doc);
        Result result = new StreamResult(f);
        transformer.transform(source, result);
    }

    @Test
    public void domTest() throws Exception {
        //1.获取你要操作的xml文件对象
        //2.获取DocumentBuilderFactroy对象
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        //3.获取DocumentBuilder对象
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        //4.获取Documen对象
        Document doc = builder.parse(f);
        System.out.println(doc);
    }

    //    需求：
//      1、得到某个具体的文本节点的内容:取出第二个联系人的名字
    @Test
    public void test1() throws Exception {
        Element root = doc.getDocumentElement();
        NodeList elements = root.getElementsByTagName("linkman");
        for (int i = 0; i < elements.getLength(); i++) {
            System.out.println(elements.item(i));
        }

        Element linkman = (Element) elements.item(1);
        Node name = linkman.getElementsByTagName("name").item(0);
        String textContent = name.getTextContent();
        System.out.println(textContent);
    }

    //      2、修改某个元素节点的主体内容：把第一个联系人的邮箱改掉
    @Test
    public void test2() throws Exception {
        Element root = doc.getDocumentElement();
        Element linkman = (Element) root.getElementsByTagName("linkman").item(0);
        Node email = linkman.getElementsByTagName("email").item(0);
        email.setTextContent("hhandgg@outlook.com");

        transformTo();
        return;
    }


    //      3、向指定元素节点中增加子元素节点：增加一个联系人信息
    @Test
    public void test3() throws Exception {
        Element root = doc.getDocumentElement();
        //创建一个新的联系人节点
        Element linkman = doc.createElement("linkman");
        //创建联系人节点的四个元素
        Element nameEL = doc.createElement("name");
        Element emailEL = doc.createElement("email");
        Element addressEL = doc.createElement("address");
        Element groupEL = doc.createElement("group");
        //设置四个元素的属性
        nameEL.setTextContent("郭芙蓉");
        emailEL.setTextContent("wtuiawitfk@163.com");
        addressEL.setTextContent("四川");
        groupEL.setTextContent("华山派");
        //把四个元素作为linkman的子元素
        linkman.appendChild(nameEL);
        linkman.appendChild(emailEL);
        linkman.appendChild(addressEL);
        linkman.appendChild(groupEL);
        //把linkman作为根元素的一个节点
        root.appendChild(linkman);
        //同步到文件
        transformTo();
    }

    //      4、操作XML文件属性：设置/获取联系人的id属性
    @Test
    public void test4() throws Exception{
        Element root = doc.getDocumentElement();
        NodeList linkmans = root.getElementsByTagName("linkman");
        Element linkman = (Element) root.getElementsByTagName("linkman").item(2);
        linkman.setAttribute("id","3");
        transformTo();
        for (int i = 0; i < linkmans.getLength(); i++) {
            String id = ((Element) linkmans.item(i)).getAttribute("id");
            System.out.println(id);
        }
    }

    //      5、删除指定元素节点：删除第三个联系人信息
    @Test
    public void test5() throws TransformerException {
        Element root = doc.getDocumentElement();
        Node linkman3 = root.getElementsByTagName("linkman").item(2);
        root.removeChild(linkman3);
        transformTo();
    }

    //      6、在内存中创建一个Document对象。
    @Test
    public void test6() throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.newDocument();
        Element contactsEL = doc.createElement("contacts");
        doc.appendChild(contactsEL);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Source source = new DOMSource(doc);
        File f = new File(("./resources/contacts2.xml"));
        Result result = new StreamResult(f);
        transformer.transform(source, result);
    }
}
