package bank.mapper;

import org.mapstruct.AnnotateWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@AnnotateWith(GeneratedMapper.class)
public abstract class  BaseMapper<DTO, ENTITY> {
    public abstract DTO toDto(final ENTITY entity);

    public abstract ENTITY toEntity(final DTO dto);


    public List<DTO> toDtos(final List<ENTITY> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<ENTITY> toEntities(final List<DTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public Page<DTO> buildDtoPage(final Page<ENTITY> entities) {
        return new PageImpl<>(toDtos(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }
}
