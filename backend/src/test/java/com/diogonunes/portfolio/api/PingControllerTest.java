package com.diogonunes.portfolio.api;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PingController.class)
@Import(PingControllerTest.TestConfig.class)
class PingControllerTest {

	private static final Instant BUILD_TIME = Instant.parse("2026-08-13T10:00:00Z");
	private static final Instant NOW = Instant.parse("2026-08-13T10:05:00Z");

	@Autowired
	MockMvc mvc;

	@Test
	void pingReturnsExpectedJson() throws Exception {
		mvc.perform(get("/api/v1/ping"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.service").value("portfolio-api"))
				.andExpect(jsonPath("$.version").value("0.0.1-SNAPSHOT"))
				.andExpect(jsonPath("$.buildTime").value(BUILD_TIME.toString()))
				.andExpect(jsonPath("$.timestamp").value(NOW.toString()));
	}

	@TestConfiguration
	static class TestConfig {

		@Bean
		BuildProperties buildProperties() {
			Properties properties = new Properties();
			properties.setProperty("version", "0.0.1-SNAPSHOT");
			properties.setProperty("time", BUILD_TIME.toString());
			return new BuildProperties(properties);
		}

		@Bean
		Clock clock() {
			return Clock.fixed(NOW, ZoneOffset.UTC);
		}

	}

}
