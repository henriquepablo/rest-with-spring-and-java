package br.com.lacerda.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {
	
	//essa clase tem como função converter VO em entidades
	// e conveter entidade em VO (value Object)
	
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	// o método recebe a origem (O) do dado e o destino (D)
	// ele conveter um "dado" para "outro" 
	public static <O, D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}
	
	// esse método converte uma lista
	public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
		List<D> destinationObjects = new ArrayList<D>();
		
		for (O o: origin) {
			destinationObjects.add(mapper.map(o, destination));
		}
		return destinationObjects;
	}
	
}
