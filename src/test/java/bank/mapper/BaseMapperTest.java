package bank.mapper;

import bank.dto.BaseDto;
import bank.entity.BaseEntity;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BaseMapperTest {
    BaseMapper<BaseDto, BaseEntity> mapper = new BaseMapper<BaseDto, BaseEntity>() {
        @Override
        public BaseDto toDto(BaseEntity baseEntity) {
            return BaseDto.builder().id(baseEntity.getId()).build();
        }

        @Override
        public BaseEntity toEntity(BaseDto baseDto) {
            return BaseEntity.builder().id(baseDto.getId()).build();
        }
    };

    @Test
    public void testToDtos() {
        final List<BaseDto> dtos = mapper.toDtos(Collections.singletonList(createFakeEntity()));

        assertNotNull(dtos);
        assertEquals(dtos.size(), 1);
    }

    @Test
    public void testToEntities() {
        final List<BaseEntity> entities = mapper.toEntities(Collections.singletonList(createFakeDto()));

        assertNotNull(entities);
        assertEquals(entities.size(), 1);
    }

    @Test
    public void testBuildDtoPage() {
        final List<BaseEntity> entities = mapper.toEntities(Collections.singletonList(createFakeDto()));

        assertNotNull(entities);
        assertEquals(entities.size(), 1);

        final Page<BaseEntity> entityPage = new PageImpl<>(entities);
        final Page<BaseDto> page = mapper.buildDtoPage(entityPage);

        assertNotNull(page);
        assertEquals(page.getTotalElements(), 1);
    }

    private BaseEntity createFakeEntity() {
        return BaseEntity.builder().id("123").build();
    }

    private BaseDto createFakeDto() {
        return BaseDto.builder().id("123").build();
    }

}