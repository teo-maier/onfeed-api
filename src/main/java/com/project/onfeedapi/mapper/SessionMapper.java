package com.project.onfeedapi.mapper;

import com.project.onfeedapi.dto.SessionDTO;
import com.project.onfeedapi.model.Session;

import java.util.ArrayList;

public class SessionMapper {

    public static SessionDTO convertToDTO(Session session) {
        SessionDTO sessionDTO = SessionDTO.builder()
                .id(session.getId())
                .title(session.getTitle())
                .description(session.getDescription())
                .isAnonymous(session.isAnonymous())
                .isSuggestion(session.isSuggestion())
                .isDraft(session.isDraft())
                .createdAt(session.getCreatedAt())
                .updatedAt(session.getUpdatedAt())
                .build();
        if (session.getCreator() != null) {
            sessionDTO.setCreator(EmployeeMapper.convertToDTO(session.getCreator()));
        }
        if (session.getForm() != null) {
            sessionDTO.setForm(FormMapper.convertToDTO(session.getForm()));
        }
        return sessionDTO;
    }

    public static Session convertToModel(SessionDTO sessionDTO) {
        Session session = Session.builder()
                .id(sessionDTO.getId())
                .title(sessionDTO.getTitle())
                .description(sessionDTO.getDescription())
                .sessionRecipients(new ArrayList<>())
                .isAnonymous(sessionDTO.isAnonymous())
                .isSuggestion(sessionDTO.isSuggestion())
                .isDraft(sessionDTO.isDraft())
                .build();
        if (sessionDTO.getCreator() != null) {
            session.setCreator(EmployeeMapper.convertToModel(sessionDTO.getCreator()));
        }
        if (sessionDTO.getForm() != null) {
            session.setForm(FormMapper.convertToModel(sessionDTO.getForm()));
        }
        return session;
    }
}
