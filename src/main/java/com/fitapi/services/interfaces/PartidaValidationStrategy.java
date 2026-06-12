package com.fitapi.services.interfaces;

import com.fitapi.dtos.request.PartidaRequestDTO;

public interface PartidaValidationStrategy {

    void validate(PartidaRequestDTO partidaRequest);
}
