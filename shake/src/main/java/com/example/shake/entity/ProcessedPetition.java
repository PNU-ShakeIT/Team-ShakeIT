package com.example.shake.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "processed_petition")
public class ProcessedPetition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(columnDefinition = "VARCHAR(45)")
    String num;
    @Column(columnDefinition = "VARCHAR(45)")
    String age;
    @Column(columnDefinition = "MEDIUMTEXT")
    String name;
    @Column(columnDefinition = "VARCHAR(45)")
    String proposer;
    @Column(columnDefinition = "VARCHAR(45)")
    String approver;
    @Column(columnDefinition = "VARCHAR(45)")
    String pro_dt;
    @Column(columnDefinition = "VARCHAR(45)")
    String pro_result;
    @Column(columnDefinition = "VARCHAR(45)")
    String curr_committee;
    @Column(columnDefinition = "MEDIUMTEXT")
    String url;
    @Column(columnDefinition = "MEDIUMTEXT")
    String bill_id;
    @Column(columnDefinition = "VARCHAR(45)")
    String committee_id;

}