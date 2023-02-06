package com.example.demo.analytics.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PGI_MS_Summarized")
public class PgiMsSummarized {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
private int	id;
@Column(name = "summarizedName")
private String summarizedName;



	
}
