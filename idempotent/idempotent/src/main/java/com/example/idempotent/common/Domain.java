package com.example.idempotent.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    public Integer cnt;

    public void addCnt(){
        this.cnt += 10000;
    }
}
