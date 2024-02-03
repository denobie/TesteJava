package com.elotechdenobie.testejava.resources;

import com.elotechdenobie.testejava.dto.DebitoDTO;
import com.elotechdenobie.testejava.entities.Debito;
import com.elotechdenobie.testejava.services.DebitoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/debito")
@RequiredArgsConstructor
public class DebitoResource {
    private final DebitoService debitoService;

    @GetMapping("/{id}")
    public ResponseEntity<DebitoDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.debitoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<DebitoDTO>> findByAll(Pageable pageable){
        return ResponseEntity.ok(this.debitoService.findAll(pageable));
    }

    @GetMapping("/by-pessoa/{id}")
    public ResponseEntity<Page<DebitoDTO>> findAllByPessoa(Pageable pageable, @PathVariable Long id){
        return ResponseEntity.ok(this.debitoService.findAllByPessoa(pageable, id));
    }

    @GetMapping(value = "/by-datalancamento", params = {"dataInicial", "dataFinal"})
    public ResponseEntity<Page<DebitoDTO>> findAllByPeriodo(Pageable pageable,
                                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
                                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal){

        return ResponseEntity.ok(this.debitoService.findAllByPriodoDataLancamento(pageable, dataInicial, dataFinal));
    }
}
