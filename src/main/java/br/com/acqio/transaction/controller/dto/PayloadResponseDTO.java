package br.com.acqio.transaction.controller.dto;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import br.com.acqio.transaction.model.Payload;
import br.com.acqio.transaction.model.enums.CardApplication;
import br.com.acqio.transaction.model.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayloadResponseDTO {

	private Long id;
	private String date;
	private String time;
	private BigDecimal value;
	private CardApplication cardApplication;
	private PaymentStatus status;

	public PayloadResponseDTO(Payload payload) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		this.id = payload.getId();
		this.date = formatter.format(payload.getDate());

		formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

		this.time = formatter.format(payload.getTime());
		this.value = payload.getValue();
		this.cardApplication = payload.getCardApplication();
		this.status = payload.getStatus();
	}

	public static List<PayloadResponseDTO> convert(List<Payload> payloads) {
		return payloads.stream().map(PayloadResponseDTO::new).collect(Collectors.toList());
	}

}
