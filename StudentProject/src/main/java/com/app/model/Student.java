package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="std_tab")
public class Student {
	@Id
	@GeneratedValue(generator = "std_seq_name")
	@SequenceGenerator(name = "std_seq_name", sequenceName = "stud_type_seq")
	
	@Column(name="std_id_col", length = 3, nullable = false)
	private Integer id;
	@Column(name="std_name_col", length = 30, nullable = false)
	private String name;
	@Column(name="std_dob_col" )
	private String dob;
	@Column(name="std_doj_col")
	private String doj;
		

}
