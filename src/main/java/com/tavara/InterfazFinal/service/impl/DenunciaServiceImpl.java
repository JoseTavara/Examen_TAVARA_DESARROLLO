package com.tavara.InterfazFinal.service.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import com.tavara.InterfazFinal.entity.Denuncia;
import com.tavara.InterfazFinal.service.DenunciaService;
import com.tavara.InterfazFinal.repository.DenunciaRepository;

import com.tavara.InterfazFinal.exceptions.GeneralServiceException;
import com.tavara.InterfazFinal.exceptions.NoDataFoundException;
import com.tavara.InterfazFinal.exceptions.ValidateServiceException;
import com.tavara.InterfazFinal.validator.DenunciaValidator;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
public class DenunciaServiceImpl implements DenunciaService {
    @Autowired
    private DenunciaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Denuncia> findAll(Pageable page) {
        try {
            return repository.findAll(page).toList();
        } catch (NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Denuncia> findByDni(String dni, Pageable page) {
        try {
            return repository.findByDniContaining(dni, page);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Denuncia findById(int id) {
        try {
            Denuncia registro = repository.findById(id).orElseThrow(() -> new NoDataFoundException("NO existe ese registro con ese ID"));
            return registro;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Denuncia create(Denuncia denuncia) {
        try {
            DenunciaValidator.save(denuncia);
           
            Denuncia registro = repository.save(denuncia);
            return registro;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Denuncia update(Denuncia denuncia) {
        try {
            DenunciaValidator.save(denuncia);
            Denuncia registro = repository.findById(denuncia.getId()).orElseThrow(() -> new NoDataFoundException("NO existe ese registro con ese ID"));
            
            registro.setDni(denuncia.getDni());
            registro.setFecha(denuncia.getFecha());
            registro.setTitulo(denuncia.getTitulo());
            registro.setDireccion(denuncia.getDireccion());
            registro.setDescripcion(denuncia.getDescripcion());
            repository.save(registro);
            return registro;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            Denuncia registro = repository.findById(id).orElseThrow(() -> new NoDataFoundException("NO existe ese registro con ese ID"));
            repository.delete(registro);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }
}
