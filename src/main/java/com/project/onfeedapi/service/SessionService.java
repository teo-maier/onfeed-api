package com.project.onfeedapi.service;

import com.project.onfeedapi.dto.SessionDTO;
import com.project.onfeedapi.dto.exception.ExceptionDTO;
import com.project.onfeedapi.mapper.FormMapper;
import com.project.onfeedapi.mapper.SessionMapper;
import com.project.onfeedapi.mapper.SessionRecipientMapper;
import com.project.onfeedapi.model.*;
import com.project.onfeedapi.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final FormService formService;
    private final EmployeeService employeeService;


    public List<SessionDTO> getAll() {
        return sessionRepository.findAll().stream().map(SessionMapper::convertToDTO).toList();
    }

    public List<SessionDTO> getAllByCreator(Long creatorId) {
        return sessionRepository.getAllSessions(creatorId).stream().map(SessionMapper::convertToDTO).toList();
    }

    public List<SessionDTO> getAllNotCompletedByEmployeeId(Long employeeId) {
        return sessionRepository.getAllNotCompletedByEmployeeId(employeeId).stream().map(SessionMapper::convertToDTO).toList();
    }

    public List<SessionDTO> getAllCompletedByEmployeeId(Long employeeId) {
        return sessionRepository.getAllCompletedByEmployeeId(employeeId).stream().map(SessionMapper::convertToDTO).toList();
    }

    public List<SessionDTO> getDrafts(Long employeeId) {
        return sessionRepository.getAllDrafts(employeeId).stream().map(SessionMapper::convertToDTO).toList();
    }

    public SessionDTO getById(Long sessionId) {
        return SessionMapper.convertToDTO(sessionRepository.findById(sessionId).orElseThrow(() ->
                new ExceptionDTO("Could not find session by id", HttpStatus.NOT_FOUND)));
    }

    public SessionDTO create(SessionDTO sessionDTO) {
        if (Objects.nonNull(sessionDTO.getForm())) {
            formService.existsById(sessionDTO.getForm().getId());
        }
        if (Objects.nonNull(sessionDTO.getCreator())) {
            employeeService.existsById(sessionDTO.getCreator().getId());
        }
        Session session = SessionMapper.convertToModel(sessionDTO);
        if (!sessionDTO.getSessionRecipients().isEmpty()) {
            setSessionToSessionRecipient(sessionDTO, session);
        }
        sessionRepository.save(session);
        return SessionMapper.convertToDTO(sessionRepository.save(session));
    }

    public void setSessionToSessionRecipient(SessionDTO sessionDTO, Session session) {
        List<SessionRecipient> sessionRecipientList = sessionDTO.getSessionRecipients().stream().map(SessionRecipientMapper::convertToModel).toList();
        for (SessionRecipient sessionRecipient : sessionRecipientList) {
            session.addSessionRecipient(sessionRecipient);
        }
    }

    public SessionDTO edit(Long sessionDTO, SessionDTO editedSession) {
        SessionDTO session = getById(sessionDTO);
        setFields(editedSession, session);
        Session sessionModel = SessionMapper.convertToModel(session);
        sessionModel.setSessionRecipients(editedSession.getSessionRecipients().stream().map(SessionRecipientMapper::convertToModel).toList());
        for (SessionRecipient sessionRecipient : sessionModel.getSessionRecipients()) {
            sessionRecipient.setSession(sessionModel);
        }
        sessionRepository.save(sessionModel);
        return session;
    }

    private static void setFields(SessionDTO editedSession, SessionDTO session) {
        session.setTitle(editedSession.getTitle());
        session.setDescription(editedSession.getDescription());
        session.setSessionRecipients(editedSession.getSessionRecipients());
        session.setAnonymous(editedSession.isAnonymous());
        session.setSuggestion(editedSession.isSuggestion());
        session.setDraft(editedSession.isDraft());
        session.setForm(editedSession.getForm());
        session.setCreator(editedSession.getCreator());
    }

    public void delete(Long sessionId) {
        if (sessionRepository.existsById(sessionId)) {
            sessionRepository.deleteById(sessionId);
        } else {
            throw new ExceptionDTO("Could not find session by id", HttpStatus.NOT_FOUND);
        }
    }
}
