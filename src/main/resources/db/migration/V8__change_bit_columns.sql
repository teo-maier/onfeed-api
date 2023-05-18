ALTER TABLE `sessions`
    MODIFY `is_anonymous` bit(1) NOT NULL DEFAULT false;
ALTER TABLE `sessions`
    MODIFY `is_suggestion` bit(1) NOT NULL DEFAULT false;
ALTER TABLE `sessions`
    MODIFY `is_draft` bit(1) NOT NULL DEFAULT true;