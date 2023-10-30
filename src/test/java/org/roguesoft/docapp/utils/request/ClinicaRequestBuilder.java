package org.roguesoft.docapp.utils.request;

import org.roguesoft.docapp.application.dto.ClinicaDTO;
import org.roguesoft.docapp.utils.RequestBuilder;

public class ClinicaRequestBuilder implements RequestBuilder<ClinicaDTO> {

    private static final String CONVENIO_ID = "IND-123456789-CNV";

    @Override
    public ClinicaDTO buildValid(String... params) {
        ClinicaDTO request = new ClinicaDTO();
        request.setNome(params[0]);
        request.setEmail(params[1]);
        request.setTelefone(params[2]);
        request.setConvenioId(CONVENIO_ID);
        return request;
    }
}
