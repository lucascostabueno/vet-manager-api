ALTER TABLE users ADD COLUMN employee_id UUID NOT NULL;

ALTER TABLE users ADD CONSTRAINT uc_users_employee_id UNIQUE (employee_id);

ALTER TABLE users ADD CONSTRAINT fk_users_on_employee FOREIGN KEY (employee_id) REFERENCES employees (id);