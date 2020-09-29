package com.metricalab.saying.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import com.metricalab.saying.converter.SayingConverter;
import com.metricalab.saying.pojo.InMemorySaying;
import com.metricalab.saying.pojo.SayingDTO;
import com.metricalab.saying.service.ISayingService;
import com.metricalab.saying.service.SayingDataBaseServiceImpl;
import com.metricalab.saying.service.SayingInMemoryServiceImpl;
import com.metricalab.saying.utils.ConstantsData;

@Configuration
public class PersistentContext {

	@Bean
	public InMemorySaying buildSaying() {

		final List<SayingDTO> listsaying = new ArrayList<>();
		listsaying.add(new SayingDTO((long) 1, "A caballo regalado no le mires el diente", 3,
				ConstantsData.SPRING_BOOT_MEMORY));
		listsaying.add(new SayingDTO((long) 2, "A falta de pan, buenas son tortas", 4,
				ConstantsData.SPRING_BOOT_MEMORY));
		listsaying.add(new SayingDTO((long) 3, "A la arrogancia en el pedir, la virtud del no dar...", 12,
				ConstantsData.SPRING_BOOT_MEMORY));
		listsaying
				.add(new SayingDTO((long) 4, "A la tercera va la vencida", 3, ConstantsData.SPRING_BOOT_MEMORY));
		listsaying.add(
				new SayingDTO((long) 5, "A palabras necias, oídos sordos", 5, ConstantsData.SPRING_BOOT_MEMORY));
		listsaying.add(
				new SayingDTO((long) 6, "Al pan, pan, y al vino, vino", 11, ConstantsData.SPRING_BOOT_MEMORY));
		listsaying.add(new SayingDTO((long) 7, "Al perro flaco, todo se le vuelven pulgas", 1,
				ConstantsData.SPRING_BOOT_MEMORY));
		listsaying.add(new SayingDTO((long) 8, "Al que Dios se la dé, San Pedro se la bendiga", 9,
				ConstantsData.SPRING_BOOT_MEMORY));
		listsaying.add(new SayingDTO((long) 9, "Antes se coge al mentiroso que al cojo", 8,
				ConstantsData.SPRING_BOOT_MEMORY));
		listsaying.add(
				new SayingDTO((long) 10, "Burro grande, ande o no ande", 2, ConstantsData.SPRING_BOOT_MEMORY));

		final InMemorySaying inMemorySaying = new InMemorySaying();
		inMemorySaying.setSaying(listsaying);

		return inMemorySaying;
	}

	@Primary
	@Bean
	public ISayingService getServiceImpl(final Environment env) {
		final String serviceType = env.getProperty("metrica.data.service");
		return "inMemory".equals(serviceType) ? new SayingInMemoryServiceImpl() : new SayingDataBaseServiceImpl();
	}

	@Bean
	public SayingConverter getConverter() {
		return new SayingConverter();
	}

}
