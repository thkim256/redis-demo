               ━━━━━━━━━━━━━━━━━━━━━
                            REDIS DUMMY DATA
                                  생성

                              TaeHyung Kim
               ━━━━━━━━━━━━━━━━━━━━━


Table of Contents
─────────────────

1 서버구동 예시
2 서버호출 예시
.. 2.1 세션 데이터 자동 생성
.. 2.2 실제 로그인 방식 세션 데이터 생성


spring boot application의 세션 서버를 Redis로 연결하여 더미데이터를
생성한다.


1 서버구동 예시
═══════════════

  첨부된 redis-demo.jar 를 아래와 같이 구동한다.

  ┌────
  │ nohup java -jar -Dspring.session.timeout=60s -Dspring.redis.sentinel.nodes=10.20.200.102:32762,10.20.200.102:32423,10.20.200.102:30064 redis-demo.jar > redis-spring.log &
  └────


2 서버호출 예시
═══════════════

2.1 세션 데이터 자동 생성
────────────

  자동으로 더미데이터 생성

  ┌────
  │ for ((i=1;i<=1000;i++)); do curl localhost:8080/generate?dummySize=10; done
  └────


2.2 실제 로그인 방식 세션 데이터 생성
──────────────────

  ━━━━━━━━━━━━━━━━━
   id     pw       
  ─────────────────
   admin  password 
   user1  password 
  ━━━━━━━━━━━━━━━━━

  ┌────
  │ curl -i -X POST -F "username=admin" -F "password=password" http://localhost:8080/login
  └────
