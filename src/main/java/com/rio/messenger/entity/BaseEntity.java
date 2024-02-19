package com.groww.stocks.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.groww.stocks.common.constants.TimeFormatsConstant.STOCKS_ORDER_TIME_FORMAT_S;


@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Audited
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreatedDate
  @JsonFormat(pattern = STOCKS_ORDER_TIME_FORMAT_S, shape = JsonFormat.Shape.STRING)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @JsonFormat(pattern = STOCKS_ORDER_TIME_FORMAT_S, shape = JsonFormat.Shape.STRING)
  private LocalDateTime updatedAt;

  @Version
  private Long version;
}
