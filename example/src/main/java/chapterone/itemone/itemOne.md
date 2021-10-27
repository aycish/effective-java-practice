### 찾아봤던 내용들

1. [팩토리 메서드 패턴](https://github.com/aycish/effective-java-practice/blob/Chapter1/example/src/main/java/chapterone/itemone/factory/factoryMethod.md) - 본문에서 사용되는 팩터리 메서드와 유사하지만, 동일한 것은 아님
2. [템플릿 메서드 패턴](https://github.com/aycish/effective-java-practice/blob/Chapter1/example/src/main/java/chapterone/itemone/template/templateMethod.md)
3. [플라이 웨이트](https://github.com/aycish/effective-java-practice/blob/Chapter1/example/src/main/java/chapterone/itemone/flyweight/flyWeight.md)

---

클라이언트가 클래스의 인스턴스를 얻는 수단은 public 생성자지만, 본 책에서는 정적 팩터리 메서드를 활용하여 인스턴스를 얻는 것을 권장하고 있다.

추천하는 이유로 5가지의 장점을 말하고 있는데, 이를 정리해보자.

## 장점

### 첫 번째, 이름을 가질 수 있다.

- 명확한 이름을 가진 생성자를 정의할 수 있어, 가독성을 높일 수 있다.

### 두 번째, 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다.

- 객체 생성 비용이 큰 경우, 생성이 자주 요청된다면 플라이 웨이트 기법과 비슷하게 구현하여 해결할 수 있다.
- 이를 인스턴스 통제 클래스라고 하는데, 인스턴스 통제 클래스를 통해 싱글턴으로 만들거나 인스턴스화 불가로 만들 수 있다.

### 세 번째, 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.

- 정적 팩터리 메서드를 통해 반환할 객체를 선택할 수 있으므로, 상황에 따라 하위 타입의 객체 또한 반환할 수 있다. → 유연성 증가
- 그에 따라, 구현 클래스를 공개하지 않아도 되며, API를 작게 유지할 수 있게 된다.

### 네 번째, 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.

- 이를 통해, 클라이언트는 팩터리가 건네주는 객체가 어떤 클래스의 인스턴스인지 알 수도 없고, 알 필요도 없다.
- 단지 반환받는 클래스가 하위 클래스이기만 하면 된다.

### 다섯 번째, 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.

- 이를 통해, 서비스 제공자 프레임워크를 만들 수 있게된다. (예시 JDBC)
- 서비스 제공자 프레임 워크란?
    - 구성
        - 서비스 인터페이스 : 구현체의 동작 정의
        - 제공자 등록 API : 제공자가 구현체를 등록할 때 사용
        - 서비스 접근 API : 클라이언트가 서비스의 인스턴스를 얻을 때 사용
        - (서비스 제공자 인터페이스) : 서비스 인터페이스의 인스턴스를 생성하는 팩터리 객체 → 만약 이를 사용하지 않는다면 리플렉션을 사용해야한다.

## 단점

### 첫 번째, 상속을 하기 위해선 public | protected 생성자가 필요하니 정적 팩터리 메서드만 제공하면 하위 클래스를 만들 수 없다.

- 이 제약은 상속보다 컴포지션을 사용하도록 유도한다.
- 어떻게 보면 컴포지션을 사용하기를 유도하기 때문에 장점일 수도 있다.

### 두 번째, 정적 팩터리 메서드는 프로그래머가 찾기 어렵다.

- 생성자처럼 API가 설명에 명확히 드러나지 않으니, 사용자는 정적 팩터리 메서드 방식 클래스를 인스턴스화할 방법을 알아내야 한다.
- 따라서 API 문서를 잘 작성해놓고 메서드 이름 또한 규약을 따라 짓는 방식을 취해 문제를 해결해야 한다.
    - 규약 예시
        - from : 매개변수를 하나 받아서 해당 타입의 인스턴스를 반환하는 형변환 메서드

            ```
            예) Date d = Date.from(instant);
            ```

        - of : 여러 매개변수를 받아 적합한 타입의 인스턴스를 반환하는 집계 메서드

            ```
            예) Set<Rank> faceCards = Enum,Set.of(JACK, QUEEN, KING);
            ```

        - valueOf : from 과 of의 더 자세한 버전

            ```
            예) BingInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
            ```

        - instance | getInstance : 매개변수로 명시한 인스턴스를 반환하지만, 같은 인스턴스임을 보장하지는 않는다.

            ```
            예) StackWalker luke = StackWalker.getInstance(options);
            ```

        - create | newInstance : instance 혹은 getInstance와 같지만, 매번 새로운 인스턴스를 생성해 반환함을 보장한다.

            ```
            예) Object newArray = Array.newInstance(classObject, arrayLen);
            ```

        - getType : getInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 쓴다. "Type"은 팩터리 메서드가 반환할 객체의 타입이다.

            ```
            예) FileStore fs = Files.getFileStore(path);
            ```

        - newType : newInstance와 같으나, 생성할 클래스가 아닌, 다른 클래스에 펙터리 메서드를 정의할 때 쓴다. "Type"은 팩터리 메서드가 반환할 객체의 타입이다.

            ```
            예) BufferedReader br = Files.newBufferedReader(path);
            ```

        - type : getType과 newType의 간결한 버전

            ```
            예) List<Complaint> litany = Coolections.list(legacyLitany);
            ```

## 정리

- 정적 팩터리 메서드와 public 생성자는 각자의 쓰임새가 존재하므로 상대적인 장단점을 이해해야한다.
- 정적 팩터리 메서드를 사용하는게 유리한 경우가 빈번하므로 public 생성자를 제공하던 습관이 있다면 고치자.
