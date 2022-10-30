package com.qabound.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Quote {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String quote;
	private String source;
	@Builder.Default private long date = Instant.now().getEpochSecond();
}
