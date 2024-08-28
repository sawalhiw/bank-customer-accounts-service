package bank.controller;

import bank.controller.base.BaseController;
import bank.dto.AccountDto;
import bank.dto.CustomerDto;
import bank.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController extends BaseController {
    private final AccountService service;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam final Integer size,
                                     @RequestParam final Integer page) {
        return call(() -> service.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable final String id) {
        return call(() -> service.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCustomerById(@Valid @RequestBody final AccountDto dto,
                                                @PathVariable final String id) {
        return call(() -> service.updateById(dto, id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody final AccountDto dto) {
        return call(() -> service.create(dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable final String id) {
        return call(() -> service.deleteById(id));
    }
}
