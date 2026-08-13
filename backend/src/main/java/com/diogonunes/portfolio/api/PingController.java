package com.diogonunes.portfolio.api;

import java.time.Clock;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PingController {

	private final BuildProperties buildProperties;
	private final Clock clock;

	@Autowired
	public PingController(BuildProperties buildProperties, Clock clock) {
		this.buildProperties = buildProperties;
		this.clock = clock;
	}

	@GetMapping("/ping")
	public PingResponse ping() {
		return new PingResponse(
				"portfolio-api",
				buildProperties.getVersion(),
				buildProperties.getTime().toString(),
				Instant.now(clock).toString());
	}

}
