package com.metricalab.saying.converter;

import java.util.Date;

import com.metricalab.saying.entity.Saying;
import com.metricalab.saying.pojo.SayingDTO;

public class SayingConverter implements GenericConverter<Saying, SayingDTO> {

	@Override
	public SayingDTO apply(final Saying input) {
		return new SayingDTO(input.getId(), input.getText(), input.getQuality(), input.getOrigin());
	}

	public Saying apply(final SayingDTO output) {
		return new Saying(output.getText(), output.getQuality(), new Date(), output.getOrigin());
	}

}
