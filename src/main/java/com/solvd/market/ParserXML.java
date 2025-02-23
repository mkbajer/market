package com.solvd.market;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ParserXML {
    public static void main(String[] args) {
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            File xmlFile = new File("d:/tools/intelij-workspace/market/src/main/resources/structures/market.xml");
            Document document = builder.parse(xmlFile);

            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();
            System.out.println("Root element: " + root.getNodeName());

            NodeList users = document.getElementsByTagName("user");
            for (int i = 0; i < users.getLength(); i++) {
                Node userNode = users.item(i);
                if (userNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element userElement = (Element) userNode;
                    System.out.println("User ID: " + userElement.getElementsByTagName("id").item(0).getTextContent());
                    System.out.println("Name: " + userElement.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Surname: " + userElement.getElementsByTagName("surname").item(0).getTextContent());
                    System.out.println("Email: " + userElement.getElementsByTagName("email").item(0).getTextContent());
                    System.out.println("Phone: " + userElement.getElementsByTagName("phone").item(0).getTextContent());
                    System.out.println("Type: " + userElement.getElementsByTagName("type").item(0).getTextContent());
                    System.out.println("Active: " + userElement.getElementsByTagName("active").item(0).getTextContent());

                    NodeList carts = userElement.getElementsByTagName("cart");
                    for (int j = 0; j < carts.getLength(); j++) {
                        Node cartNode = carts.item(j);
                        if (cartNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element cartElement = (Element) cartNode;
                            System.out.println("  Cart ID: " + cartElement.getElementsByTagName("id").item(0).getTextContent());

                            NodeList products = cartElement.getElementsByTagName("product");
                            for (int k = 0; k < products.getLength(); k++) {
                                Node productNode = products.item(k);
                                if (productNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element productElement = (Element) productNode;
                                    System.out.println("    Product ID: " + productElement.getElementsByTagName("id").item(0).getTextContent());
                                    System.out.println("    Name: " + productElement.getElementsByTagName("name").item(0).getTextContent());
                                    System.out.println("    Price: " + productElement.getElementsByTagName("price").item(0).getTextContent());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}