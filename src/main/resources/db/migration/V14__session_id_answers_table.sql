START TRANSACTION ;
ALTER TABLE answers
    ADD session_id bigint NOT NULL;
ALTER TABLE answers ADD CONSTRAINT fk_session_id FOREIGN KEY (session_id) REFERENCES sessions(id);
ALTER TABLE answers ADD UNIQUE KEY question_employee_session_unique(question_id, employee_id, session_id);
COMMIT;