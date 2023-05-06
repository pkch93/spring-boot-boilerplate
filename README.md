# boilerplate

## install

`install.sh`로 보일러 플레이트 설치용 쉘 스크립트를 구성

```bash
 /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/pkch93/spring-boot-boilerplate/HEAD/install.sh)" 
```

위 명령으로 github에 올라가 있는 `install.sh`를 가져와 실행할 수 있다.

```
1) spring-boot-react-kts
2) spring-boot-kts
#?
```

실행하면 위와 같이 어떤 보일러 플레이트를 가져올 지 선택할 수 있다. 선택하면 해당 보일러 플레이트가 받아진다.

### 이름 변경하기

어떠한 이름을 주지 않으면 기본적으로 각각의 보일러 플레이트 이름으로 받아진다.

만약 이름을 바꾸고 싶다면 `install.sh` 실행시에 `-n` 옵션으로 이름을 줄 수 있다.

```bash
 /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/pkch93/spring-boot-boilerplate/HEAD/install.sh)" -n test
```

위와 같이 `-n` 옵션으로 `test`를 전달하면 선택한 보일러 플레이트가 `test`라는 이름으로 받아진다.

## description

- spring-boot-kts

kotlin + spring boot 용 보일러 플레이트

- spring-boot-react-kts

kotlin + spring boot와 react 결과물을 spring boot 서버로 서빙하도록 구성한 보일러 플레이트.

> Spring Boot로 SSR이 아닌 단순 결과물 HTML을 Spring Boot 서버로 내려줌. 때문에 React 페이지는 CSR로 동작함
