package com.example.shake.controller;

import com.example.shake.api.*;
import com.example.shake.dto.CalendarDto;
import com.example.shake.dto.CongressOfMemberDto;
import com.example.shake.dto.PendingPetitionDto;
import com.example.shake.entity.Calendar;
import com.example.shake.entity.CongressOfMember;
import com.example.shake.entity.TestEntity;
import com.example.shake.repository.CalenderRepository;
import com.example.shake.repository.CongressOfMemberRepository;
import com.example.shake.repository.TestRepository;
import com.example.shake.service.APIService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class HelloController {
    final TestRepository testRepository;
    final APIService apiService;
    final CongressOfMemberRepository congressOfMemberRepository;
    final CalenderRepository calenderRepository;
    // CongressMember ##############################################
    @GetMapping("/insertCongressMemberencodemeomd")
    public String insertMember() throws ParserConfigurationException, IOException, SAXException {
        List<CongressOfMemberDto> congressOfMemberDtos = MemberOfCongressAPI.getAPIList();
        return apiService.insertCongressOfMember(congressOfMemberDtos);
    }

    @RequestMapping(value = "/getCongressMember", produces = "application/json; charset=utf8")
    public List<CongressOfMember> getMember()  {
        return congressOfMemberRepository.findAll();
    }
    // CongressMember ##############################################
    // Calendar ##############################################
    @RequestMapping(value = "/getCalendarBon", produces = "application/json; charset=utf8")
    public void getCalendarBon() throws ParserConfigurationException, IOException, SAXException {

        List<CalendarDto> calendarDtos = CalenderSemina.getAPIList();
//        List<CalendarDto> calendarDtos = CalenderBon.getAPIList();
//        List<CalendarDto> calendarDtos = CalenderWee.getAPIList();
//        List<CalendarDto> calendarDtos = CalenderGook.getAPIList();
//        List<CalendarDto> calendarDtos = CalenderGong.getAPIList();

        calendarDtos.stream().forEach(System.out::println);

    }
    @RequestMapping(value = "/insertCalendar2y344t56uy55ergr4u5y", produces = "application/json; charset=utf8")
    public String insertCalendar() throws ParserConfigurationException, IOException, SAXException {
        return apiService.insertCalenderDate();
    }
    @RequestMapping(value = "/getCalendar", produces = "application/json; charset=utf8")
    public List<Calendar> getCalendar() throws ParserConfigurationException, IOException, SAXException {
        return calenderRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }
    @RequestMapping(value = "/getCalendarByCode", produces = "application/json; charset=utf8")
    public List<Calendar> getCalendarByCode() throws ParserConfigurationException, IOException, SAXException {
        return calenderRepository.findAll(Sort.by(Sort.Direction.ASC, "code"));
    }
    // Calendar ##############################################
    @GetMapping("/getPendingPetition")
    public void getPetitions() throws ParserConfigurationException, IOException, SAXException {
        List<PendingPetitionDto> pendingPetitionDtos = PendingPetitionAPI.getAPIList();
        pendingPetitionDtos.stream().forEach(System.out::println);
    }
    @GetMapping("/insertPendingPetition45725453")
    public void insertPendingPetitions() throws ParserConfigurationException, IOException, SAXException {

    }
}
