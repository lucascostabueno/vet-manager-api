ALTER TABLE employees ADD COLUMN tenant_id VARCHAR(255) NOT NULL;
ALTER TABLE profiles ADD COLUMN tenant_id VARCHAR(255) NOT NULL;
ALTER TABLE users ADD COLUMN tenant_id VARCHAR(255) NOT NULL;
ALTER TABLE refresh_tokens ADD COLUMN tenant_id VARCHAR(255) NOT NULL;

ALTER TABLE employees DROP CONSTRAINT uc_employees_cpf;
ALTER TABLE employees DROP CONSTRAINT uc_employees_email;
ALTER TABLE employees ADD CONSTRAINT uk_employees_tenant_cpf UNIQUE (tenant_id, cpf);
ALTER TABLE employees ADD CONSTRAINT uk_employees_tenant_email UNIQUE (tenant_id, email);

ALTER TABLE profiles DROP CONSTRAINT uc_profiles_name;
ALTER TABLE profiles ADD CONSTRAINT uk_profiles_tenant_name UNIQUE (tenant_id, name);

CREATE INDEX idx_employees_tenant_id ON employees (tenant_id, id);
CREATE INDEX idx_profiles_tenant_id ON profiles (tenant_id, id);
CREATE INDEX idx_users_tenant_id ON users (tenant_id, id);
CREATE INDEX idx_refresh_tokens_tenant_id ON refresh_tokens (tenant_id, id);