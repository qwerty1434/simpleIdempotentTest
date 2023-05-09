# simpleIdempotentTest
간단한 시나리오로 멱등성에 대해 알아보는 코드입니다. </br>
예제 및 멱등성에 대한 상세한 설명은 <a href="https://velog.io/@qwerty1434/%ED%86%A0%EC%8A%A4%ED%8E%98%EC%9D%B4%EB%A8%BC%EC%B8%A0-%EB%A9%B1%EB%93%B1%EC%84%B1%EC%9D%B4-%EB%AD%94%EA%B0%80%EC%9A%94">블로그</a>에 기록되어 있습니다.
## 설명
- POST요청으로 멱등한 API와 멱등하지 않은 API에 요청했을 때의 차이점을 확인할 수 있습니다.
- 메모리에서 Map자료구조를 이용한 간단한 예제와 Redis를 활용한 조금 더 일반적인 예제 두 가지가 있습니다.
## 사용
- `localhost:8080/nonIdempotent`, `localhost:8080/redis/nonIdempotent`에 Body에 `{ "id": 1 }`를 담아 POST요청을 하면 매번 요청이 처리되어 증가된 값을 반환받습니다.
- localhost:8080/idempotent, localhost:8080/redis/idempotent에 Body에 `{ "id": 1 }`를, Header에 `{idempotentId: abc}`를 담아 POST요청을 하면 3초동안 같은 요청은 처리되지 않습니다.

