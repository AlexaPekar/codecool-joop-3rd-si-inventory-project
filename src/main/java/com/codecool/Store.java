package com.codecool;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class Store implements StorageCapable {

    List<Product> products = new ArrayList<Product>();
    String fileName;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public List<Product> getAllProduct() {
        return products;
    }


    protected abstract void storeProduct(Product product);


    protected Product createProduct(String type, String name, int price, int size) {
        if (type.equals("CD")) {
            CDProduct newCd = new CDProduct(name, price, size);
            return newCd;

        } else {
            BookProduct newBook = new BookProduct(name, price, size);
            return newBook;
        }
    }


    public void storeCDProducts(String name, int price, int tracks) {
        Product cd = createProduct("CD", name, price, tracks);
        store(cd);
    }


    public void storeBookProduct(String name, int price, int pages) {
        Product book = createProduct("book", name, price, pages);
        store(book);
    }

    private void saveToXml() {

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(this.fileName));

            Element store = document.createElement("Store");
            document.appendChild(store);


            for (int i = 0; i < getAllProduct().size(); i++) {
                if (getAllProduct().get(i) instanceof CDProduct) {
                    Element productElem = document.createElement("Product");
                    store.appendChild(productElem);
                    Attr attr = document.createAttribute("type");
                    attr.setValue("cd");
                    productElem.setAttributeNode(attr);

                    Element name = document.createElement("name");
                    name.appendChild(document.createTextNode(getAllProduct().get(i).getName()));
                    productElem.appendChild(name);

                    Element price = document.createElement("price");
                    price.appendChild(document.createTextNode(Integer.toString(getAllProduct().get(i).getPrice())));
                    productElem.appendChild(price);

                    Element tracks = document.createElement("tracks");
                    tracks.appendChild(document.createTextNode(Integer.toString(((CDProduct) getAllProduct().get(i)).getNumOfTrack())));
                    productElem.appendChild(tracks);

                    transformer.transform(source, result);

                } else if (getAllProduct().get(i) instanceof BookProduct) {
                    Element productElem = document.createElement("Product");
                    store.appendChild(productElem);
                    Attr attr = document.createAttribute("type");
                    attr.setValue("book");
                    productElem.setAttributeNode(attr);

                    Element name = document.createElement("name");
                    name.appendChild(document.createTextNode(getAllProduct().get(i).getName()));
                    productElem.appendChild(name);

                    Element price = document.createElement("price");
                    price.appendChild(document.createTextNode(Integer.toString(getAllProduct().get(i).getPrice())));
                    productElem.appendChild(price);

                    Element pages = document.createElement("pages");
                    pages.appendChild(document.createTextNode(Integer.toString(((BookProduct) getAllProduct().get(i)).getNumOfPages())));
                    productElem.appendChild(pages);

                    transformer.transform(source, result);
                }
            }
        } catch (ParserConfigurationException parserConfEx) {
            parserConfEx.printStackTrace();
        } catch (TransformerException transformEx) {
            transformEx.printStackTrace();
        }
    }


    public List<Product> loadProducts() {
        try {
            File xmlFile = new File(fileName);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(xmlFile);

            document.getDocumentElement().normalize();
            System.out.println("Root element :" + document.getDocumentElement().getNodeName());
            NodeList nodeList = document.getElementsByTagName("Product");
            //System.out.println("----------------------------");
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node nNode = nodeList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (eElement.getAttribute("type").equals("cd")) {
                        Product product = new CDProduct(eElement.getElementsByTagName("name").item(0).getTextContent(),
                                Integer.parseInt(eElement.getElementsByTagName("price").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("tracks").item(0).getTextContent()));
                        products.add(product);
                    } else if (eElement.getAttribute("type").equals("book")) {
                        Product product = new BookProduct(eElement.getElementsByTagName("name").item(0).getTextContent(),
                                Integer.parseInt(eElement.getElementsByTagName("price").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("pages").item(0).getTextContent()));
                        products.add(product);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public void store(Product product) {
        storeProduct(product);
        saveToXml();
    }
}
