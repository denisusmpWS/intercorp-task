package com.client.service.intercorp.expose;

import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.CodeFlow;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.client.service.intercorp.business.ClientService;
import com.client.service.intercorp.model.ClientRq;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping
@Api(value = "Client", tags = {"Client"})
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	
	@RequestMapping(value = "/creacliente", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Registrar un cliente",
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
		response = ClientRq.class,
		httpMethod = "POST",
		notes = "### Acerca del API \n " +
				"Servicio de registro de clientes\n" +
				"### URI de acceso al API\n" +
				"|Method | URL|\n" +
				"|POST|/creacliente\n" +
				"### ** Colocar solo los campos requeridos para el registro. ", tags = {"Client"})
	@ApiResponses({
		@ApiResponse(code = 201, message = "Datos correctos.", response = ClientRq.class),
		@ApiResponse(code = 400, message = "Datos incorrectos."),
		@ApiResponse(code = 500, message = "Ocurrio un error en el sistema.")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public Maybe<ClientRq> saveNewClient(@RequestBody ClientRq clientRq) {
		return service.saveNewClient(clientRq);
	}
	
	
	@RequestMapping(value = "/kpideclientes", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Obtener promedio de edades y desviacion estandar de los clientes",
	produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
		response = ClientRq.class,
		httpMethod = "GET",
		notes = "### Acerca del API \n " +
					"Servicio de calculo de KPI y Desviacion Estandar de las edades de los clientes.\n" +
					"### URI de acceso al API\n" +
					"|Method | URL|\n" +
					"|GET|/kpideclientes", tags = {"Client"})
	@ApiResponses({
		@ApiResponse(code = 200, message = "Datos correctos.", response = ClientRq.class),
		@ApiResponse(code = 400, message = "Datos incorrectos."),
		@ApiResponse(code = 500, message = "Ocurrio un error en el sistema.")
	})
	@ResponseStatus(HttpStatus.OK)
	public Single<ClientRq> listClientsKpi(){
		return service.listClientsKpi();
	}
	
	
	@RequestMapping(value = "/listclientes", method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE})
	@ApiOperation(value = "Obtener los datos de los clientes y fecha probable de mortalidad",
	produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
		response = ClientRq.class,
		httpMethod = "GET",
		notes = "### Acerca del API \n " +
					"Servicio de lista de datos de clientes junto a fecha de mortalidad.\n" +
					"### URI de acceso al API\n" +
					"|Method | URL|\n" +
					"|GET|/listclientes", tags = {"Client"})
	@ApiResponses({
		@ApiResponse(code = 200, message = "Datos correctos.", response = ClientRq.class),
		@ApiResponse(code = 400, message = "Datos incorrectos."),
		@ApiResponse(code = 500, message = "Ocurrio un error en el sistema.")
	})
	@ResponseStatus(HttpStatus.OK)
	public Observable<ClientRq> listClientDateDie() {
		return service.listClientDateDie()
				.doFinally(() -> System.out.println("Finish process list client.."));
	}

}
