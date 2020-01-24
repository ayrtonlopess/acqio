package br.com.acqio.transaction.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.acqio.transaction.model.enums.CardApplication;
import br.com.acqio.transaction.model.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payload {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate date;
	private LocalDateTime time;
	private BigDecimal value;

	@Enumerated(EnumType.STRING)
	private CardApplication cardApplication;

	@Enumerated(EnumType.STRING)
	private PaymentStatus status;

	public Payload() {
		super();
	}

	public Payload(LocalDate date, LocalDateTime time, BigDecimal value, CardApplication cardApplication,
			PaymentStatus status) {
		super();
		this.date = date;
		this.time = time;
		this.value = value;
		this.cardApplication = cardApplication;
		this.status = status;
	}

}
