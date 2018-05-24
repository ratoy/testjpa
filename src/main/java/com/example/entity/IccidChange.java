package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wuhao on 2017/12/26.
 */
@Entity
@Table(name = "iccid_change")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IccidChange {
    @EmbeddedId
    IccidChangePK id;
    @Column(name = "new_iccid")
    String newIccid;
}
