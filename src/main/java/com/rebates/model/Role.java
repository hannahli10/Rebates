package com.rebates.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role {
    public Role(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "allowed_resource")
    private String allowedResource;
    @Column(name = "allowed_read")
    private Boolean allowedRead;
    @Column(name = "allowed_create")
    private Boolean allowedCreate;
    @Column(name = "allowed_update")
    private Boolean allowedUpdate;
    @Column(name = "allowed_delete")
    private Boolean allowedDelete;


    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAllowedResource(String allowedResource) { this.allowedResource = allowedResource; }
    public void setAllowedRead(Boolean allowedRead) { this.allowedRead = allowedRead; }
    public void setAllowedCreate(Boolean allowedCreate) { this.allowedCreate = allowedCreate; }
    public void setAllowedUpdate(Boolean allowedUpdate) { this.allowedUpdate = allowedUpdate; }
    public void setAllowedDelete(Boolean allowedDelete){ this.allowedDelete = allowedDelete;}


    public Long getId() { return id; }
    public String getName() { return name; }
    public String getAllowedResource() { return allowedResource; }
    public Boolean getAllowedRead() { return allowedRead; }
    public Boolean getAllowedCreate() { return allowedCreate; }
    public Boolean getAllowedUpdate() { return allowedUpdate; }
    public Boolean getAllowedDelete(){ return allowedDelete; }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", allowedResource='" + allowedResource + '\'' +
                ", allowedRead=" + allowedRead +
                ", allowedCreate=" + allowedCreate +
                ", allowedUpdate=" + allowedUpdate +
                ", allowedDelete=" + allowedDelete+
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(name, role.name) &&
                Objects.equals(allowedResource, role.allowedResource) &&
                Objects.equals(allowedCreate, role.allowedCreate) &&
                Objects.equals(allowedRead, role.allowedRead) &&
                Objects.equals(allowedUpdate, role.allowedUpdate) &&
                Objects.equals(allowedDelete, role.allowedDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, allowedResource, allowedCreate, allowedRead, allowedUpdate, allowedDelete);

    }
}
