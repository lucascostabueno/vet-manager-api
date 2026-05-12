CREATE TABLE profiles (
    id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_profiles PRIMARY KEY (id),
    CONSTRAINT uc_profiles_name UNIQUE (name)
);



