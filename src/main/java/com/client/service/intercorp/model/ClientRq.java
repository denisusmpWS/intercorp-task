package com.client.service.intercorp.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(value = "ClienRq", description = "Class Request for Client.")
public class ClientRq {
	
	@ApiModelProperty(
		name = "id",
		value = "Codigo de Cliente",
		dataType = "String",
		example = "125112545sndjdsjfbdsfs4df4s",
		position = 1,
		required = false)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String id;
	
	@ApiModelProperty(
			name = "name",
			value = "Nombre de Cliente",
			dataType = "String",
			example = "Denis",
			position = 2,
			required = true)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String name;
	
	@ApiModelProperty(
			name = "lastname",
			value = "Apellido de Cliente",
			dataType = "String",
			example = "Vargas",
			position = 3,
			required = true)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String lastname;
	
	@ApiModelProperty(
			name = "year",
			value = "Edad del Cliente",
			dataType = "Integer",
			example = "25",
			position = 4,
			required = true)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Integer year;
	
	@ApiModelProperty(
			name = "dateBirth",
			value = "Fecha de Nacimiento",
			dataType = "Integer",
			example = "05-10-1990",
			position = 5,
			required = true)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String dateBirth;
	
	@ApiModelProperty(
			name = "clientKpi",
			value = "Datos de KPI",
			dataType = "String",
			position = 6,
			required = false)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private ClientKpi clientKpi;
	
	@ApiModelProperty(
			name = "dateDie",
			value = "Fecha de Fallecimiento",
			dataType = "String",
			example = "05-10-1995",
			position = 7,
			required = false)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String dateDie;
	
	public ClientRq(ClientKpi clientKpi) {
		super();
		this.clientKpi = clientKpi;
	}

	public ClientRq(String id, String name, String lastname, Integer year, String dateBirth) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.year = year;
		this.dateBirth = dateBirth;
	}

	public ClientRq(String id, String name, String lastname, Integer year, String dateBirth, String dateDie) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.year = year;
		this.dateBirth = dateBirth;
		this.dateDie = dateDie;
	}

	public ClientRq(){}
	
	
	

}
