### 요구사항
- 회원은 여러번 주문할 수 있다. (일대다)
- 주문할 때 여러 상품을 선책할 수 있다.
- 반대로 같은 상품도 여러번 주문될 수 있다.
- 주문 상품이라는 모델을 만들어서 다대다 관계를 일대다, 다대일로 풀어내야한다.

---
### Timestamp

- **2022-08-24, 연관관계는 아직 설정하지 않음**
  - 현재 방식은 객체 설계를 테이블 설계에 맞춘 방식
  - 테이블의 외래키를 객체에 그대로 가져옴
  - 객체 그래프 탐색이 불가능하다.
    - em.find() -> getId() -> em.find() 
  - 참조가 없으므로 UML도 잘못됨