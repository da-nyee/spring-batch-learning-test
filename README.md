# spring-batch-learning-test
Spring Batch 학습 테스트를 위한 저장소

<br/>

## Notes
### 1.
- JobParameters에 사용할 수 있는 자료형은 <b>Long, Double, String, Date</b>이다.
- 아쉽게도 LocalDate, LocalDateTime은 지원하지 않아, String으로 받아 형변환을 해야 한다.

### 2.
- <b>Chunk Size</b>는 <b>한번에 처리되는 트랜잭션 단위</b>이다.
- <b>Page Size</b>는 <b>한번에 조회되는 데이터 양</b>이다.

<br/>

## References
### Read
- [1. Spring Batch 가이드 - 배치 어플리케이션이란?](https://jojoldu.tistory.com/324)
- [6. Spring Batch 가이드 - Chunk 지향 처리](https://jojoldu.tistory.com/331)
- [Spring Batch에서 영속성 컨텍스트 문제 (processor에서 lazyException 발생할때)](https://jojoldu.tistory.com/146)

### Write
- [2. Spring Batch 가이드 - Batch Job 실행해보기](https://jojoldu.tistory.com/325)
- [3. Spring Batch 가이드 - 메타테이블엿보기](https://jojoldu.tistory.com/326)
- [4. Spring Batch 가이드 - Spring Batch Job Flow](https://jojoldu.tistory.com/328)
- [5. Spring Batch 가이드 - Spring Batch Scope & Job Parameter](https://jojoldu.tistory.com/330)
