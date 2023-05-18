package com.project.onfeedapi.mapper;

import com.project.onfeedapi.dto.SessionRecipientDTO;
import com.project.onfeedapi.model.SessionRecipient;

public class SessionRecipientMapper {
    public static SessionRecipientDTO convertToDTO(SessionRecipient sessionRecipient) {
        return SessionRecipientDTO.builder()
                .id(sessionRecipient.getId())
                .employee(EmployeeMapper.convertToDTO(sessionRecipient.getEmployee()))
                .isCompleted(sessionRecipient.isCompleted())
                .build();
    }

    public static SessionRecipient convertToModel(SessionRecipientDTO sessionRecipientDTO) {
        return SessionRecipient.builder()
                .id(sessionRecipientDTO.getId())
                .employee(EmployeeMapper.convertToModel(sessionRecipientDTO.getEmployee()))
                .isCompleted(sessionRecipientDTO.isCompleted())
                .build();
    }
}
