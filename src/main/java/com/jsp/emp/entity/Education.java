package com.jsp.emp.entity;

import com.jsp.emp.util.HighestQualification;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String qualification;
	private String university;
	private double percentage;
	private int completionYear;
	@Enumerated(EnumType.STRING)
	private HighestQualification highestQualification;
	
	@ManyToOne
	private Employee employee;
}
