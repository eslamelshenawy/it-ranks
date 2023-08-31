package com.example.dbservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ServicesMenu {

	@Id
	private String SERV_ID;

	private String SECURITY_FORMULA;
	private String TYPE;
	private String LABEL;
	private Integer SERV_COUNT;
	private String ENABLED;
	private String ICON;


}
