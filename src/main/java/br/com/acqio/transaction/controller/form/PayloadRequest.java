package br.com.acqio.transaction.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.acqio.transaction.model.Payload;
import br.com.acqio.transaction.model.enums.CardApplication;
import br.com.acqio.transaction.model.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayloadRequest {

	private String date;
	private String time;
	private BigDecimal value;
	private CardApplication cardApplication;
	private PaymentStatus status;

	public Payload convert() {
		LocalDate dateF = LocalDate.parse(this.date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDateTime timeF = LocalDateTime.parse(this.date.concat(" " + this.time),
				DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		return new Payload(dateF, timeF, this.value, this.cardApplication, this.status);
	}

}
