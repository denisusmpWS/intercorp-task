package com.client.service.intercorp.util;

// import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.client.service.intercorp.model.ClientRq;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utilities {
	
	/**
	 * Method from generated id unique.
	 * @return String.class
	 */
	public static final String getGenerateUUID(){
		String uuid2 = UUID.randomUUID().toString().replace("-", "");
		return uuid2;
	}
	 
	/**
	 * Method for return date.
	 * @return String.class
	 */
	public static String getFormmatDate(){
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String res = dateFormat.format(date);
		return res;
	}

	/**
	 * Method for calculate year present.
	 * @param dateBirth
	 * @return Integer.class
	 * @throws ParseException
	 */
	public static Integer getCalculatorYear(String dateBirth) throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat validDate = new SimpleDateFormat("dd-MM-yyyy");
		
		return (Integer.parseInt(dateFormat.format(new Date()))
					-  Integer.parseInt(dateFormat.format(validDate.parse(dateBirth))));
	}

	
	/**
	 * Method for calculate Desviation Standard.
	 * @param cant
	 * @param list
	 * @return Double.class
	 */
	public static Double calculateStandardDeviation(List<ClientRq> list){
		
		Double media = 0.0;
		Double varianza = 0.0;
		Double desviation = 0.0;
		
		//Media
		Double[] numbers = new Double[list.size()];
		for(int i = 0; i < list.size(); i++) {
			numbers[i] = (double)list.get(i).getYear()/100;
		}
		
		double sum = 0;
		for(Double i: numbers){
			sum = sum + i;
		}
		
		media = sum / list.size();
		
		//Varianza
		double sum2;
		for (int i=0; i<list.size(); i++) {
			sum2 = Math.pow(numbers[i] - media, 2);
			varianza = varianza + sum2;
		}
		
		varianza = varianza / (list.size()-1);
		
		//Desviation
		desviation = Math.sqrt(varianza);
		Double prm = Math.rint(desviation*100)/100;
		log.info("Desviation value: " + prm);
		
		return prm;
	}
	

	/*
	public static void main(String []args) throws ParseException {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat validDate = new SimpleDateFormat("dd-MM-yyyy");
		Date p = validDate.parse("12-01-2010");
		//Date Present
		String res = dateFormat.format(date);
		System.out.println(res);
		//Date Result
		String res02 = dateFormat.format(p);
		System.out.println(res02);
		//Year
		log.info("Edad Actual: " + (Integer.parseInt(res) - Integer.parseInt(res02))+ " años.");
	}
	*/

}
