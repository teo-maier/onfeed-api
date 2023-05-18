package com.project.onfeedapi.service;

import com.project.onfeedapi.dto.SessionDTO;
import com.project.onfeedapi.dto.SessionRecipientDTO;
import com.project.onfeedapi.dto.SessionResultsDTO;
import com.project.onfeedapi.mapper.SessionRecipientMapper;
import com.project.onfeedapi.model.Session;
import com.project.onfeedapi.model.SessionRecipient;
import com.project.onfeedapi.repository.SessionRecipientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SessionRecipientService {

    private final SessionRecipientsRepository sessionRecipientsRepository;

    public List<SessionRecipientDTO> getAllBySessionId(Long sessionId) {
        return sessionRecipientsRepository.getAllBySessionId(sessionId).stream().map(SessionRecipientMapper::convertToDTO).toList();
    }

    public List<SessionRecipientDTO> getAllByEmployeeId(Long employeeId) {
        return sessionRecipientsRepository.getAllByEmployeeId(employeeId).stream().map(SessionRecipientMapper::convertToDTO).toList();
    }

    public SessionResultsDTO getSessionResultsBySessionId(Long sessionId) {
        List<SessionRecipientDTO> sessionRecipientDTOS = getAllBySessionId(sessionId);
        int totalRecipients = sessionRecipientDTOS.size();
        int answered = sessionRecipientsRepository.getNumberOfAnswered(sessionId);
        int notAnswered = totalRecipients - answered;
        return SessionResultsDTO.builder()
                .answered(answered)
                .notAnswered(notAnswered)
                .total(totalRecipients)
                .build();
    }

    public void setSessionToSessionRecipient(SessionDTO sessionDTO, Session session) {
        List<SessionRecipient> sessionRecipientList = sessionDTO.getSessionRecipients().stream().map(SessionRecipientMapper::convertToModel).toList();
        for (SessionRecipient sessionRecipient : sessionRecipientList) {
            session.addSessionRecipient(sessionRecipient);
        }
    }
}
