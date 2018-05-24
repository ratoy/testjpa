package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wuhao on 2017/12/26.
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IccidChangePK implements Serializable {
    @Column(name = "vin")
    String vin;
    @Column(name = "old_iccid")
    String oldIccid;
    @Column(name = "change_time")
    Date changeTime;
}
