package com.example.test.api;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

public class IndexAPI {

    // tag값의 정보를 가져오는 메소드
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    public static void getAPIList() throws ParserConfigurationException, IOException, SAXException {
        int page = 1;  // 페이지 초기값
        // 총 개수 가져오기
        int totalCount = totalCount();
        try{
//            while(true){
                // parsing할 url 지정(API 키 포함해서)
                String url = "https://open.assembly.go.kr/portal/openapi/nubbgpxmawmzkclkc?"
                        +"KEY=679a42edc23e42689b7f234817f46fc6"
                        +"&pIndex="+page
                        +"&pSize="+totalCount;
//https://open.assembly.go.kr/portal/openapi/nubbgpxmawmzkclkc?KEY=679a42edc23e42689b7f234817f46fc6&pIndex=1&pSize=50

                DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
                Document doc = dBuilder.parse(url);

                // root tag
                doc.getDocumentElement().normalize();
                System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

                // 파싱할 tag
                NodeList nList = doc.getElementsByTagName("row");
                for(int temp = 0; temp < nList.getLength(); temp++){
                    Node nNode = nList.item(temp);
                    if(nNode.getNodeType() == Node.ELEMENT_NODE){

                        Element eElement = (Element) nNode;
                        System.out.println("######################");
                        //System.out.println(eElement.getTextContent());
                        System.out.println("의안ID  : " +getTagValue("DIV", eElement));
                        System.out.println("의원명  : " +getTagValue("CHM_PN", eElement));
                        System.out.println("재임기간 : " +getTagValue("CHM_APTM_YS", eElement));
                        System.out.println("몇 회차 재임  : " +getTagValue("UNIT_NM", eElement));
                    }  // for end
                }  // if end


                System.out.println("page number : "+page);
//                if(page > 2){
//                    break;
//                }
//                page += 1;
//            }  // while end

        } catch (Exception e){
            e.printStackTrace();
        }  // try~catch end
    }  // main end
    public static int totalCount() throws ParserConfigurationException, IOException, SAXException {
        int page = 1;
        String url = "https://open.assembly.go.kr/portal/openapi/nubbgpxmawmzkclkc?"
                +"KEY=679a42edc23e42689b7f234817f46fc6"
                +"&pIndex="+page
                +"&pSize=1";

        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
        Document doc = dBuilder.parse(url);

        // root tag
        doc.getDocumentElement().normalize();

        // 파싱할 tag
        NodeList countList = doc.getElementsByTagName("head");
        // 총 수 출력
        int totalCount = Integer.parseInt(getTagValue("list_total_count",(Element) ((Node)countList.item(0))));
        System.out.println("총 국회의원 수 : "+ totalCount);
        return totalCount;
    }
}  // class end

