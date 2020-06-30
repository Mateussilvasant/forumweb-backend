package br.com.mateussilvasant.forumweb.api.utils;

import java.util.function.Function;

import org.modelmapper.ExpressionMap;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.data.domain.Page;

public class DTOConverter<D, E> {

  private ModelMapper mapper;

  private Class<E> typeEntity;
  private Class<D> typeDTO;

  public DTOConverter(Class<E> typeEntity, Class<D> typeDTO) {
    this.typeEntity = typeEntity;
    this.typeDTO = typeDTO;
  }

  public D converterToDTO(Object entity, ExpressionMap<E, D> eMap) {

    mapper = new ModelMapper();

    if (eMap != null) {
      mapper.typeMap(typeEntity, typeDTO).addMappings(eMap);
    }

    return convertEntitiy(entity);
  }

  private D convertEntitiy(Object entity) {
    return mapper.map(entity, typeDTO);
  }

  public Page<D> converterAllToDTO(Page<E> entities, Function<D, D> servicesDTO, PropertyMap<E, D> eMap) {

    mapper = new ModelMapper();

    if (eMap != null) {
      mapper.typeMap(typeEntity, typeDTO).addMappings(eMap);
    }

    Page<D> dtos = entities.map(new Function<E, D>() {

      @Override
      public D apply(E t) {
        D dto = convertEntitiy(t);
        D dtoModifield = servicesDTO.apply(dto);
        return dtoModifield;
      }

    });

    return dtos;
  }

}