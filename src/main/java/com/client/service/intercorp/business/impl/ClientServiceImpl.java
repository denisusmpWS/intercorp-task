package com.client.service.intercorp.business.impl;

import com.client.service.intercorp.config.ApplicationProperties;
import com.client.service.intercorp.model.ClientKpi;
import com.client.service.intercorp.util.Utilities;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.operator.CircuitBreakerOperator;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.client.service.intercorp.business.ClientService;
import com.client.service.intercorp.model.ClientRq;

import io.reactivex.Observable;
import io.reactivex.Single;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

	private ApplicationProperties properties;
	private CircuitBreaker breaker;

	static final List<ClientRq> list = new ArrayList<>();

	@Autowired
	public ClientServiceImpl(ApplicationProperties properties, @Qualifier(value = "intercorp.default") CircuitBreaker breaker) {
		this.properties = properties;
		this.breaker = breaker;
	}

	@Override
	public Maybe<ClientRq> saveNewClient(ClientRq rq) {
		// TODO Auto-generated method stub
		return Single.just(rq)
				.filter(r -> (Utilities.getCalculatorYear(rq.getDateBirth()) == rq.getYear()))
				.map(res -> {
						ClientRq resp = new ClientRq();
						resp.setId(Utilities.getGenerateUUID());
						resp.setName(rq.getName());
						resp.setLastname(rq.getLastname());
						resp.setDateBirth(rq.getDateBirth());
						resp.setYear(rq.getYear());
						list.add(resp);
						log.info("List add: " + list.toString());
						return resp;
				})
				.doOnError(ex -> System.out.println(ex.getMessage() + "\n Ingrese una edad y año de nacimiento válidos."))
				.doFinally(() -> log.info("Finish process registry client.."))
				.subscribeOn(Schedulers.computation());
	}

	@Override
	public Single<ClientRq> listClientsKpi() {
		// TODO Auto-generated method stub
		return Single
				.just(list)
				.map(res -> {
					int sizeClient = res.size();
					int averageYear = 0;
					for(ClientRq q:res){
						averageYear +=  q.getYear();
					}
					log.info("# de Clients: " + sizeClient);
					log.info("Add the years: " + averageYear);
					
					ClientRq clientRq = new ClientRq();
					
					ClientKpi clientKpi = new ClientKpi();
					clientKpi.setAverage(((double)averageYear/100) / ((double)sizeClient/100));
					clientKpi.setStandDeviation(Utilities.calculateStandardDeviation(res));
					
					clientRq.setClientKpi(clientKpi);
					return clientRq;
					}
				)
				.lift(CircuitBreakerOperator.of(breaker))
				.doOnError(ex -> System.out.println(ex.getMessage() + "Ocurrio un error inesperado."))
				.doFinally(()-> log.info("Finish process calculate operations.."))
				.subscribeOn(Schedulers.computation());


	}

	@Override
	public Observable<ClientRq> listClientDateDie() {
		// TODO Auto-generated method stub
		return Observable.fromIterable(list)
					.map(res -> {
							return addDateDieForClient(res);
					})
					.lift(CircuitBreakerOperator.of(breaker))
					.zipWith(Observable.interval(500, TimeUnit.MILLISECONDS),
							(c,i) -> {log.info("Time extensions 0.5s:" + c.getId());
							return c;
					})
					.doOnError(ex -> log.info(ex.getMessage() + "Campo de fecha con formato invalido."))
					.doOnNext(res -> {log.info("client year die: " + res.getDateDie());})
					.subscribeOn(Schedulers.computation());
	}

	
	private ClientRq addDateDieForClient(ClientRq c) throws NumberFormatException, ParseException{
		
		ClientRq clientRq = new ClientRq();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat validDate = new SimpleDateFormat("dd-MM-yyyy");
		// Año de nacimiento
		Integer a =	Integer.parseInt(dateFormat.format(validDate.parse(c.getDateBirth())));
		a = a + 70;
		
		clientRq.setId(c.getId());
		clientRq.setName(c.getName());
		clientRq.setLastname(c.getLastname());
		clientRq.setYear(c.getYear());
		clientRq.setDateBirth(c.getDateBirth());
		clientRq.setDateDie(c.getDateBirth().substring(0,6) + a);
		
		return clientRq;
	}
	
	
	
	
}
