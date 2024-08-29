package bank.service.implementation;

import bank.dto.BaseDto;
import bank.dto.FeatureInfoDto;
import bank.entity.BaseEntity;
import bank.exception.NotFoundException;
import bank.exception.ValidationException;
import bank.mapper.BaseMapper;
import bank.repository.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public abstract class BaseServiceImpl<ENTITY extends BaseEntity, DTO extends BaseDto> {

    private static final Logger logger = LogManager.getLogger(BaseServiceImpl.class);

    private final BaseRepository<ENTITY> repository;
    private final BaseMapper<DTO, ENTITY> mapper;

    protected abstract FeatureInfoDto featureInfo();

    protected abstract void validate(DTO dto);

    public Page<DTO> findAll(final Pageable pageable) {
        logger.debug("Finding all {} with pageable: {}", featureInfo().getPlural(), pageable);
        Page<DTO> result = mapper.buildDtoPage(repository.findAll(pageable));
        logger.debug("Found {}: {}", result.getTotalElements(), featureInfo().getPlural());
        return result;
    }

    public DTO findById(final String id) {
        logger.debug("Finding {} by ID: {}", featureInfo().getSingle(), id);
        ENTITY entity = repository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("%s with ID [%s] does not exist.",featureInfo().getSingle(), id);
                    logger.error(message);
                    return new NotFoundException(message);
                });
        DTO dto = mapper.toDto(entity);
        logger.debug("Found {}: {}", featureInfo().getSingle(), dto);
        return dto;
    }

    public DTO create(final DTO dto) {
        logger.info("Validating {}:  {}",featureInfo().getSingle(), dto);
        validate(dto);
        if (repository.existsById(dto.getId())) {
            throw new ValidationException(String.format("%s already exists", featureInfo().getSingle()));
        }
        ENTITY entity = mapper.toEntity(dto);
        DTO result = mapper.toDto(repository.save(entity));
        logger.info("Created {} with ID: {}", featureInfo().getSingle(), dto.getId());
        return result;
    }

    public DTO updateById(final DTO dto, final String id) {
        validateIfEntityExists(id);
        logger.info("Validating {}: {}", featureInfo().getSingle(), dto);
        validate(dto);
        dto.setId(id);
        ENTITY entity = mapper.toEntity(dto);
        DTO result = mapper.toDto(repository.save(entity));
        logger.info("Updated {} with ID: {}", featureInfo().getSingle(), id);
        return result;
    }

    public DTO deleteById(final String id) {
        DTO dto = this.findById(id);
        repository.deleteById(id);
        logger.info("Deleted {} with ID: {}", featureInfo().getSingle(), id);
        return dto;
    }

    public void validateIfEntityExists(final String id) {
        logger.debug("Validating existence of {} with ID: {}", featureInfo().getSingle(), id);
        if (!repository.existsById(id)) {
            String message = String.format("%s with ID [%s] does not exist.", featureInfo().getSingle(), id);
            logger.error(message);
            throw new NotFoundException(message);
        }
        logger.debug("{} with ID: {} exists", featureInfo().getSingle(), id);
    }
}