package com.app.basic.domain.entity;

import com.app.basic.domain.type.ReplyStatus;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TBL_REPLY")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

//    @EqualsAndHashCode.Exclude
    private String replyContent;
    private String replyWriter;

    @Enumerated(EnumType.STRING)
    private ReplyStatus replyStatus;
}
