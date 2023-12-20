package com.tavara.InterfazFinal.validator;

import com.tavara.InterfazFinal.exceptions.ValidateServiceException;

import com.tavara.InterfazFinal.entity.Denuncia;


public class DenunciaValidator {
    public static void save(Denuncia denuncia) {
        if (denuncia.getDni() == null || denuncia.getDni().isEmpty()) {
            throw new ValidateServiceException("El DNI es Requerido");
        }
        if (denuncia.getDni().length() > 8) {
            throw new ValidateServiceException("El DNI es muy largo");
        }
        if (denuncia.getFecha() == null) {
            throw new ValidateServiceException("La fecha es Requerida");
        }
        if (denuncia.getTitulo() == null || denuncia.getTitulo().isEmpty()) {
            throw new ValidateServiceException("El título es Requerido");
        }
        if (denuncia.getTitulo().length() > 11) {
            throw new ValidateServiceException("El título es muy largo");
        }
        if (denuncia.getDireccion() == null || denuncia.getDireccion().isEmpty()) {
            throw new ValidateServiceException("La Dirección es Requerida");
        }
        if (denuncia.getDireccion().length() > 200) {
            throw new ValidateServiceException("La Dirección es muy larga");
        }
    }
}
