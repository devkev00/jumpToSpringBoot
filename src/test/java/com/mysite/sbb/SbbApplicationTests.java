package com.mysite.sbb;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {

	@Autowired // 의존성 주입, QuestionRepository의 객체(구현체) 주입
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	@Transactional
	void testJpa() {
//		Question q1 = new Question();
//		q1.setSubject("sbb가 무엇인가요?");
//		q1.setContent("sbb에 대해 알고 싶습니다");
//		q1.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q1); // 첫 번째 Question 엔티티(객체) 저장
//
//		Question q2 = new Question();
//		q2.setSubject("스프링부트 모델 질문입니다");
//		q2.setContent("id는 자동으로 생성되나요?");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2); // 두 번째 Questions 엔티티(객체) 저장

//		List<Question> all = questionRepository.findAll();
//		assertEquals(2, all.size());
//
//		Question q = all.get(0);
//		assertEquals("sbb가 무엇인가요?", q.getSubject());

//		Optional<Question> oq = this.questionRepository.findById(1);
//		// id로 값을 조회할 때는 해당 객체가 있을 수도, 없을 수도 있기 때문에 리턴 타입을 Optional로 설정한다
//		if (oq.isPresent()) {
//			Question q = oq.get();
//			assertEquals("sbb가 무엇인가요?", q.getSubject());
//		}

//		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
//		assertEquals(1, q.getId());

//		Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해 알고 싶습니다");
//		assertEquals(1, q.getId());

//		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
//		Question q = qList.get(0);
//		assertEquals("sbb가 무엇인가요?", q.getSubject());

//		Optional<Question> oq = questionRepository.findById(1);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//		q.setSubject("수정된 제목");
//		this.questionRepository.save(q);

//		assertEquals(2, this.questionRepository.count());
//		Optional<Question> oq = questionRepository.findById(1);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//		this.questionRepository.delete(q);
//		assertEquals(1,this.questionRepository.count());

//		Optional<Question> oq = this.questionRepository.findById(2);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//
//		Answer a = new Answer();
//		a.setContent("네 자동으로 생성됩니다");
//		a.setQuestion(q);
//		a.setCreateDate(LocalDateTime.now());
//		this.answerRepository.save(a);

//		Optional<Answer> oa = this.answerRepository.findById(1);
//		assertTrue(oa.isPresent());
//		Answer a = oa.get();
//		assertEquals(2, a.getQuestion().getId());

		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get(); // test 환경에서는 여기서 DB 세션 종료

		List<Answer> answerList = q.getAnswerList();
		// 즉 여기서 LAZY 처리해주지 않으면 오류 발생
		// Transanctional 어노테이션: 메서드 종료 시까지 세션을 유지해줌

		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다", answerList.get(0).getContent());
	}
}
