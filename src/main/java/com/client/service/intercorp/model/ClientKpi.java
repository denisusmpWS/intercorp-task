package com.client.service.intercorp.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ClientKpi{

	@ApiModelProperty(
			name = "average",
			value = "Promedio de Edades de los clientes",
			dataType = "Dpuble",
			example = "25.63",
			position = 1,
			required = false)
    private Double average;
	
	@ApiModelProperty(
			name = "standDeviation",
			value = "Desviacion Estandar de las Edades",
			dataType = "Double",
			example = "15.06",
			position = 2,
			required = false)
    private Double standDeviation;

}
