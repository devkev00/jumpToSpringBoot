package com.mysite.sbb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // strategy 옵션을 넣어야 해당 속성만 별도로 번호가 차례로 늘어나게 할 수 있음
    // 이 옵션이 없으면, GeneratedValue 어노테이션이 붙은 모든 속성의 번호를 차례로 생성함
    private int id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT") // 텍스트를 열 데이터로 넣을 때, 글자 수 제한 x
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
}
