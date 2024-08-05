package com.msproposta.mapper;

import com.msproposta.dto.PropostaRequestDto;
import com.msproposta.dto.PropostaResponseDto;
import com.msproposta.entities.Proposta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.text.NumberFormat;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PropostaMapper {

    PropostaMapper INSTANCE = Mappers.getMapper(PropostaMapper.class);

    @Mapping(target = "usuario.nome", source = "nome")
    @Mapping(target = "usuario.sobrenome", source = "sobrenome")
    @Mapping(target = "usuario.telefone", source = "telefone")
    @Mapping(target = "usuario.renda", source = "renda")
    @Mapping(target = "usuario.cpf", source = "cpf")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aprovada", ignore = true)
    @Mapping(target = "integrada", constant = "true")
    Proposta convertDtoToProposta(PropostaRequestDto propostaRequestDto);

    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "sobrenome", source = "usuario.sobrenome")
    @Mapping(target = "telefone", source = "usuario.telefone")
    @Mapping(target = "renda", source = "usuario.renda")
    @Mapping(target = "cpf", source = "usuario.cpf")
    @Mapping(target = "valorSolicitadoFmt", expression = "java(setValorSolicitadoFmt(proposta))")
    PropostaResponseDto convertEntityToDto(Proposta proposta);

    List<PropostaResponseDto> convertListEntitytoToListDto(Iterable<Proposta> propostas);

    default String setValorSolicitadoFmt(Proposta proposta) {
        Double valor = proposta.getValorSolicitado();
        return valor != null ? NumberFormat.getCurrencyInstance().format(valor) : null;
    }
}

