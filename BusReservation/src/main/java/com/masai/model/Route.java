package com.masai.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//pending 

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer routeId;
	
	@NotNull(message = "can't set as null")
	private String routeFrom;
	
	@NotNull(message = "can't set as null")
	private String routeTo;
	
	@NotNull(message = "can't set as null")
	private Integer distance;
	
	private List<Bus> bus = new ArrayList<>();
	
}