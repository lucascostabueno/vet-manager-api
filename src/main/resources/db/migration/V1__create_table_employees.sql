CREATE TABLE employees (
   id UUID NOT NULL,
   name VARCHAR(255) NOT NULL,
   cpf VARCHAR(11) NOT NULL,
   email VARCHAR(255) NOT NULL,
   CONSTRAINT pk_employees PRIMARY KEY (id),
   CONSTRAINT uc_employees_cpf UNIQUE (cpf),
   CONSTRAINT uc_employees_email UNIQUE (email)
);