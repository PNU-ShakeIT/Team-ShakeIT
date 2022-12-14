package com.example.shake.service;

import com.example.shake.dto.CongressOfMemberDto;
import com.example.shake.entity.Bill;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface APIService {
    public String  insertCongressOfMember(List<CongressOfMemberDto> members);
    public String  insertCalenderDate() throws ParserConfigurationException, SAXException, IOException;

    public String  insertPendingPetitions() throws ParserConfigurationException, SAXException, IOException;
    public String  insertProcessedPetitions() throws ParserConfigurationException, SAXException, IOException;
    public String  insertLegislativeStatus() throws ParserConfigurationException, SAXException, IOException;
    public String  insertBill() throws ParserConfigurationException, SAXException, IOException;
    public List<Bill> getBillNotAnnounceDt();
}
