package br.com.mateussilvasant.forumweb.api.utils;

import org.modelmapper.ExpressionMap;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component("dto_converter")
public class DTOConverter<D, E> {

    private final ModelMapper mapper = new ModelMapper();

   public D converterToDTO(E entity,Class<E> typeEntity,Class<D> typeDTO, ExpressionMap<E,D> eMap){

     if(eMap != null){
          mapper.typeMap(typeEntity, typeDTO).addMappings(eMap);
     }

     return mapper.map(entity, typeDTO);
   }
    

}