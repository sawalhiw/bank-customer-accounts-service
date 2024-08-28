package bank.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<DTO> {
    Page<DTO> findAll(Pageable pageable);

    DTO findById(String id);

    DTO deleteById(String id);

    DTO updateById(DTO dto, String id);

    DTO create(DTO dto);
}
