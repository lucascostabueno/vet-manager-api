CREATE TABLE permissions (
    id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_permissions PRIMARY KEY (id),
    CONSTRAINT uc_permissions_name UNIQUE (name)
);


CREATE TABLE profile_permissions (
    profile_id UUID NOT NULL,
    permission_id UUID NOT NULL,
    CONSTRAINT pk_profile_permissions PRIMARY KEY (profile_id, permission_id),
    CONSTRAINT fk_profile_permissions_on_profile FOREIGN KEY (profile_id) REFERENCES profiles (id) ON DELETE CASCADE,
    CONSTRAINT fk_profile_permissions_on_permission FOREIGN KEY (permission_id) REFERENCES permissions (id) ON DELETE CASCADE
);

ALTER TABLE users ADD COLUMN profile_id UUID NOT NULL;

ALTER TABLE users ADD CONSTRAINT fk_users_on_profile FOREIGN KEY (profile_id) REFERENCES profiles (id);