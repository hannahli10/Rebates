package com.rebates.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "providers")
public class Provider {
    public Provider(){}
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;   //id
    @Column(name = "name")
    private String name;


    //    @OneToMany(mappedBy = "provider",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "provider",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Rebate> rebates;


    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Set<Rebate> getRebates() { // this method to avoid throw exception of rebates is null.
        if (rebates == null)
            rebates = new HashSet<Rebate>();
        return rebates;
    }

    public void setRebates(Set<Rebate> rebates) {
        this.rebates = rebates;
    }

        public void setId ( long id){
            this.id = id;
        }
        public void setName (String name){
            this.name = name;
        }


        @Override
        public String toString () {
            return "Provider{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }



