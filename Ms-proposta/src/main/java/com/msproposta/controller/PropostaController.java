package com.msproposta.controller;

import com.msproposta.dto.PropostaRequestDto;
import com.msproposta.dto.PropostaResponseDto;
import com.msproposta.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    @Autowired
    private PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto requestDto) {
        PropostaResponseDto response = propostaService.criar(requestDto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri()).body(response);
    }

        @GetMapping
        public ResponseEntity<List<PropostaResponseDto>> obterProposta () {
            return ResponseEntity.ok(propostaService.obterProposta());
        }
}
