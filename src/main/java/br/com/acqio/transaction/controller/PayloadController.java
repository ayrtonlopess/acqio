package br.com.acqio.transaction.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.acqio.transaction.controller.dto.PayloadResponseDTO;
import br.com.acqio.transaction.controller.form.PayloadRequest;
import br.com.acqio.transaction.controller.form.UpdatePayloadForm;
import br.com.acqio.transaction.model.Payload;
import br.com.acqio.transaction.repository.PayloadRepository;

@RestController
@RequestMapping("/transaction")
public class PayloadController {

	@Autowired
	PayloadRepository payloadRepository;

	@PostMapping(produces = "application/json", consumes = "application/json")
	@Transactional
	public ResponseEntity<PayloadResponseDTO> save(@RequestBody PayloadRequest form, UriComponentsBuilder uriBuilder) {
		Payload payload = form.convert();
		payloadRepository.save(payload);
		URI uri = uriBuilder.path("/transaction/{id}").buildAndExpand(payload.getId()).toUri();
		return ResponseEntity.created(uri).body(new PayloadResponseDTO(payload));
	}

	@GetMapping(value = "/all", produces = "application/json")
	public List<PayloadResponseDTO> list() {
		return PayloadResponseDTO.convert(payloadRepository.findAll());
	}

	@PutMapping(produces = "application/json", consumes = "application/json")
	@Transactional
	public ResponseEntity<PayloadResponseDTO> atualizar(@RequestBody UpdatePayloadForm form) {
		Optional<Payload> payload = payloadRepository.findById(form.getId());
		if (payload.isPresent()) {
			Payload payloadNew = form.update(payloadRepository);
			return ResponseEntity.ok(new PayloadResponseDTO(payloadNew));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
