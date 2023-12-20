package com.tavara.InterfazFinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tavara.InterfazFinal.entity.Denuncia;

import com.tavara.InterfazFinal.service.DenunciaService;
import com.tavara.InterfazFinal.utils.WrapperResponse;
import com.tavara.InterfazFinal.dto.DenunciaDTO;
import com.tavara.InterfazFinal.converter.DenunciaConverter;


@RestController
@RequestMapping("/denuncias")
public class DenunciaController {
    @Autowired
    private DenunciaService service;

    @Autowired
    private DenunciaConverter converter;

    @GetMapping()
    public ResponseEntity<List<DenunciaDTO>> findAll(
            @RequestParam(value = "dni", required = false, defaultValue = "") String dni,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Denuncia> denuncias;
        if (dni == null) {
            denuncias = service.findAll(page);
        } else {
            denuncias = service.findByDni(dni, page);
        }
        List<DenunciaDTO> denunciasDTO = converter.fromEntity(denuncias);
        return new WrapperResponse(true, "success", denunciasDTO).createRespose(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WrapperResponse<DenunciaDTO>> findById(@PathVariable("id") int id) {
        Denuncia denuncia = service.findById(id);
        DenunciaDTO denunciaDTO = converter.fromEntity(denuncia);
        return new WrapperResponse<DenunciaDTO>(true, "success", denunciaDTO).createRespose(HttpStatus.OK);
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<WrapperResponse<List<DenunciaDTO>>> findByDni(@PathVariable(name = "dni") String dni) {
        List<Denuncia> denuncias = service.findByDni(dni, null);

        if (denuncias.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new WrapperResponse<>(false, "No se encontraron denuncias para el DNI: " + dni, null));
        }

        List<DenunciaDTO> denunciasDTO = converter.fromEntity(denuncias);
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new WrapperResponse<>(true, "success", denunciasDTO));
    }
    
    @PostMapping
    public ResponseEntity<DenunciaDTO> create(@RequestBody DenunciaDTO denunciaDTO) {
        Denuncia registro = service.create(converter.fromDTO(denunciaDTO));
        DenunciaDTO registroDTO = converter.fromEntity(registro);
        return new WrapperResponse(true, "success", registroDTO).createRespose(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DenunciaDTO> update(@PathVariable("id") int id, @RequestBody DenunciaDTO denunciaDTO) {
        Denuncia registro = service.update(converter.fromDTO(denunciaDTO));
        DenunciaDTO registroDTO = converter.fromEntity(registro);
        return new WrapperResponse(true, "success", registroDTO).createRespose(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DenunciaDTO> delete(@PathVariable("id") int id) {
        service.delete(id);
        return new WrapperResponse(true, "success", null).createRespose(HttpStatus.OK);
    }
}
