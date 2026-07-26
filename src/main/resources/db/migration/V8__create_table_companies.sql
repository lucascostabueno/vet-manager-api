CREATE TABLE companies (
    id UUID NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    company VARCHAR(100) NOT NULL,
    corporate_name VARCHAR(100) NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE,
    created_by UUID,
    updated_by UUID,

    CONSTRAINT pk_companies PRIMARY KEY (id),
    CONSTRAINT uc_companies_cnpj UNIQUE (cnpj),
    CONSTRAINT fk_companies_created_by FOREIGN KEY (created_by) REFERENCES users (id),
    CONSTRAINT fk_companies_updated_by FOREIGN KEY (updated_by) REFERENCES users (id)
);

CREATE TABLE company_contacts (
    company_id UUID NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    instagram VARCHAR(50),

    CONSTRAINT pk_company_contacts PRIMARY KEY (company_id),
    CONSTRAINT uc_company_contacts_email UNIQUE (email),
    CONSTRAINT fk_company_contacts_company FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE
);

CREATE TABLE company_addresses (
    company_id UUID NOT NULL,
    zip_code VARCHAR(9) NOT NULL,
    address VARCHAR(100) NOT NULL,
    number VARCHAR(10) NOT NULL,
    complement TEXT,
    neighborhood VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(2) NOT NULL,

    CONSTRAINT pk_company_addresses PRIMARY KEY (company_id),
    CONSTRAINT fk_company_addresses_company FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE
);

INSERT INTO permissions (id, name) VALUES
    ('3c9d1e2f-3a4b-5c6d-7e8f-9a0b1c2d3e4f', 'COMPANY_VIEW'),
    ('4d0e1f2a-3b4c-5d6e-7f8a-9b0c1d2e3f4a', 'COMPANY_CREATE'),
    ('5e1f2a3b-4c5d-6e7f-8a9b-0c1d2e3f4a5b', 'COMPANY_UPDATE'),
    ('6f2a3b4c-5d6e-7f8a-9b0c-1d2e3f4a5b6c', 'COMPANY_DELETE');