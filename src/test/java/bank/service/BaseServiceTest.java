package bank.service;

import bank.dto.BaseDto;
import bank.entity.BaseEntity;
import bank.mapper.BaseMapper;
import bank.repository.BaseRepository;
import bank.service.implementation.BaseServiceImpl;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringJUnitConfig
public abstract class BaseServiceTest<ENTITY extends BaseEntity, DTO extends BaseDto> {
    private BaseMapper<DTO, ENTITY> mapper;
    private BaseRepository<ENTITY> repository;

    private BaseServiceImpl<ENTITY, DTO> service;

    protected abstract ENTITY createEntity();

    protected abstract DTO createDto();

    public void setup(BaseMapper<DTO, ENTITY> mapper,
                      BaseRepository<ENTITY> repository,
                      BaseServiceImpl<ENTITY, DTO> service) {
        this.mapper = mapper;
        this.repository = repository;
        this.service = service;
    }

    public void testFindAll() {
        Page<ENTITY> entityPage = new PageImpl<>(List.of(createEntity()));
        Page<DTO> dtoPage = new PageImpl<>(List.of(createDto()));
        when(repository.findAll(any(Pageable.class))).thenReturn(entityPage);
        when(mapper.buildDtoPage(any())).thenReturn(dtoPage);

        Page<DTO> result = service.findAll(Pageable.unpaged());

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    public void testFindById() {
        ENTITY entity = createEntity();
        when(repository.findById(any(String.class))).thenReturn(Optional.of(entity));
        when(mapper.toDto(any())).thenReturn(createDto());

        DTO result = service.findById(createEntity().getId());

        assertNotNull(result);
    }

    public void testFindByIdWithoutPreparingRepositoryCalls() {
        ENTITY entity = createEntity();
        when(mapper.toDto(any())).thenReturn(createDto());

        DTO result = service.findById(createEntity().getId());

        assertNotNull(result);
    }

    public void testCreate() {
        DTO dto = createDto();
        ENTITY entity = createEntity();
        when(mapper.toEntity(any())).thenReturn(entity);
        when(repository.save(any())).thenReturn(entity);
        when(mapper.toDto(any())).thenReturn(dto);

        DTO result = service.create(dto);

        assertNotNull(result);
    }

    public void testUpdate() {
        DTO dto = createDto();
        ENTITY entity = createEntity();
        when(mapper.toEntity(any())).thenReturn(entity);
        when(repository.save(any())).thenReturn(entity);
        when(mapper.toDto(any())).thenReturn(dto);

        DTO result = service.updateById(dto, createEntity().getId());

        assertNotNull(result);
    }

    public void testDeleteById() {
        ENTITY entity = createEntity();
        when(repository.findById(any(String.class))).thenReturn(Optional.of(entity));
        when(mapper.toDto(any())).thenReturn(createDto());

        DTO result = service.deleteById(createEntity().getId());

        assertNotNull(result);
        Mockito.verify(repository).deleteById(createEntity().getId());
    }
}
