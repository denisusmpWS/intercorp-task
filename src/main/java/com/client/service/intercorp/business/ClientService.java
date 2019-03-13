package com.client.service.intercorp.business;


import com.client.service.intercorp.model.ClientRq;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface ClientService {
	
	Maybe<ClientRq> saveNewClient(ClientRq rq);
	Single<ClientRq> listClientsKpi();
	Observable<ClientRq> listClientDateDie();

}
