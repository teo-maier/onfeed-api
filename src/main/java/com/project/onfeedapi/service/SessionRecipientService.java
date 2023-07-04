package com.project.onfeedapi.service;

import com.project.onfeedapi.dto.EmployeeDTO;
import com.project.onfeedapi.dto.SessionDTO;
import com.project.onfeedapi.dto.SessionRecipientDTO;
import com.project.onfeedapi.dto.SessionResultsDTO;
import com.project.onfeedapi.dto.exception.ExceptionDTO;
import com.project.onfeedapi.mapper.EmployeeMapper;
import com.project.onfeedapi.mapper.SessionMapper;
import com.project.onfeedapi.mapper.SessionRecipientMapper;
import com.project.onfeedapi.model.Employee;
import com.project.onfeedapi.model.Session;
import com.project.onfeedapi.model.SessionRecipient;
import com.project.onfeedapi.repository.SessionRecipientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class SessionRecipientService {

    private final SessionRecipientsRepository sessionRecipientsRepository;

    private final SessionService sessionService;

    private final EmployeeService employeeService;

    public List<SessionRecipientDTO> getAllBySessionId(Long sessionId) {
        return sessionRecipientsRepository.getAllBySessionIdOrderByUpdatedAt(sessionId).stream().map(SessionRecipientMapper::convertToDTO).toList();
    }

    public List<SessionRecipientDTO> getAllByEmployeeId(Long employeeId) {
        return sessionRecipientsRepository.getAllByEmployeeId(employeeId).stream().map(SessionRecipientMapper::convertToDTO).toList();
    }

    public SessionRecipientDTO getById(Long recipientId) {
        return SessionRecipientMapper.convertToDTO(sessionRecipientsRepository.findById(recipientId)
                .orElseThrow(() -> new ExceptionDTO("Could not find session recipient by id")));
    }

    public SessionRecipientDTO getByEmployeeIdAndSessionId(Long employeeId, Long sessionId) {
        SessionRecipient sessionRecipient = getSessionRecipientByEmployeeAndSession(sessionId, employeeId);
        SessionRecipientDTO sessionRecipientDTO = SessionRecipientMapper.convertToDTO(sessionRecipient);
        if (Objects.isNull(sessionRecipientDTO)) {
            throw new ExceptionDTO("Could not find session recipient by id");
        }
        return sessionRecipientDTO;
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

    public void setCompleted(Long sessionId, Long employeeId) {
        SessionRecipient sessionRecipient = getSessionRecipientByEmployeeAndSession(sessionId, employeeId);
        sessionRecipient.setCompleted(true);
        sessionRecipientsRepository.save(sessionRecipient);
    }

    private SessionRecipient getSessionRecipientByEmployeeAndSession(Long sessionId, Long employeeId) {
        Session session = SessionMapper.convertToModel(sessionService.getById(sessionId));
        Employee employee = EmployeeMapper.convertToModel(employeeService.getEmployee(employeeId));
        return sessionRecipientsRepository.getBySessionAndEmployee(session, employee);
    }

    public SessionRecipientDTO edit(Long recipientId, SessionRecipientDTO sessionRecipientDTO) {
        SessionRecipientDTO recipientById = getById(recipientId);

        SessionRecipient sessionRecipient = SessionRecipientMapper.convertToModel(recipientById);

        SessionDTO sessionDTO = sessionService.getById(sessionRecipientDTO.getSession().getId());
        sessionRecipient.setSession(SessionMapper.convertToModel(sessionDTO));
        EmployeeDTO employeeDTO = employeeService.getEmployee(sessionRecipientDTO.getEmployee().getId());
        sessionRecipient.setEmployee(EmployeeMapper.convertToModel(employeeDTO));

        sessionRecipient.setCompleted(sessionRecipientDTO.isCompleted());
        sessionRecipient.setReviewed(sessionRecipientDTO.isReviewed());
        sessionRecipient.setCreatedAt(sessionRecipientDTO.getCreatedAt());
        sessionRecipient.setUpdatedAt(sessionRecipientDTO.getUpdatedAt());

        sessionRecipientsRepository.save(sessionRecipient);
        return sessionRecipientDTO;
    }
}
