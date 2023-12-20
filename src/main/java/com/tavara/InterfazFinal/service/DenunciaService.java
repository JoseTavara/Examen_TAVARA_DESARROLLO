package com.tavara.InterfazFinal.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import com.tavara.InterfazFinal.entity.Denuncia;

public interface DenunciaService {
    List<Denuncia> findAll(Pageable page);
    List<Denuncia> findByDni(String dni, Pageable page);
    Denuncia findById(int id);
    Denuncia create(Denuncia denuncia);
    Denuncia update(Denuncia denuncia);
    void delete(int id);
}
