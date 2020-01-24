package br.com.acqio.transaction.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.acqio.transaction.model.Payload;
import br.com.acqio.transaction.model.enums.CardApplication;
import br.com.acqio.transaction.model.enums.PaymentStatus;
import br.com.acqio.transaction.repository.PayloadRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePayloadForm {

	private Long id;
	private String date;
	private String time;
	private BigDecimal value;
	private CardApplication cardApplication;
	private PaymentStatus status;

	public Payload update(PayloadRepository payloadRepository) {
		Payload payload = payloadRepository.getOne(this.id);

		LocalDate dateF = LocalDate.parse(this.date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDateTime timeF = LocalDateTime.parse(this.date.concat(" " + this.time),
				DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

		payload.setDate(dateF);
		payload.setTime(timeF);
		payload.setValue(this.value);
		payload.setCardApplication(this.cardApplication);
		payload.setStatus(this.status);
		return payload;
	}

}
