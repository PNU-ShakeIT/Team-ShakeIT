package com.example.shake.api;

import com.example.shake.dto.CalendarDto;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CalenderGook {

    static String url ="https://open.assembly.go.kr/portal/openapi/nhedurlwawoquyxwn?";
    // tag값의 정보를 가져오는 메소드
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    public static List<CalendarDto> getAPIList() throws ParserConfigurationException, IOException, SAXException {
        int page = 1;  // 페이지 초기값
        // 총 개수 가져오기
        List<CalendarDto> calendarDtos = new ArrayList<>();
        for (int i = 1; i <= page; i++) {
            try {
                // parsing할 url 지정(API 키 포함해서)
                String urlFull = url +"KEY=679a42edc23e42689b7f234817f46fc6"
                        + "&pIndex=" + page
                        + "&pSize=" + 1000;

                DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
                Document doc = dBuilder.parse(urlFull);

                // root tag
                doc.getDocumentElement().normalize();
                System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

                // 파싱할 tag
                NodeList nList = doc.getElementsByTagName("row");
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    CalendarDto calendarDto = new CalendarDto();
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;
                        //System.out.println(eElement.getTextContent());

                        String title = getTagValue("CONTENTS", eElement);
                        String convert = org.springframework.web.util.HtmlUtils.htmlUnescape(title).replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
                        if (convert.contains("-->")) {
                            convert = convert.split("-->")[1];
                        }
                        String date = getTagValue("SCHEDULEDATE", eElement);
                        String result = "";
                        for (int j = 0; j < date.length(); j++) {
                            result += date.charAt(j) + "";
                            if (j == 3 || j == 5) {
                                result += "-";
                            }
                        }
                        if (result.contains("2022")) {
                            calendarDto.setDate(result);
                            calendarDto.setTitle(convert);

                            calendarDto.setCode(4);
                            calendarDto.setTime(getTagValue("SCHEDULETIME", eElement));
                            calendarDtos.add(calendarDto);
                        }


                    }  // for end
                }  // if end

            } catch (Exception e) {
                e.printStackTrace();
            }  // try~catch end
        }
        return calendarDtos;
    }  // main end
    public static int totalCount() throws ParserConfigurationException, IOException, SAXException {
        int page = 1;
        String urlFull = url +
                "KEY=679a42edc23e42689b7f234817f46fc6"
                        + "&pIndex=" + page
                        + "&pSize=1";

        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
        Document doc = dBuilder.parse(urlFull);

        // root tag
        doc.getDocumentElement().normalize();

        // 국회의원 수가 있는 head 태그
        NodeList countList = doc.getElementsByTagName("head");
        // head 태그 안 총 수 출력
        int totalCount = Integer.parseInt(getTagValue("list_total_count", (Element) ((Node) countList.item(0))));
        System.out.println("총 데이터 수 : " + totalCount);
        return totalCount / 1000 + 1;
    }
}  // class end