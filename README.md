## Mybatis 적용하기 


### mybatis란??
MyBatis는 SQL 쿼리와 객체 간의 매핑을 중심으로 하는 데이터 매핑 프레임워크입니다. MyBatis는 개발자가 SQL을 직접 작성하게 하면서, 그 SQL을 자바 객체와 연결해주는 역할을 합니다. 이는 개발자가 SQL을 통해 데이터베이스를 더 세밀하게 제어할 수 있게 해주며, 복잡한 쿼리나 최적화된 쿼리를 필요로 하는 상황에서 유용하게 사용될 수 있습니다.
> 자바 코드랑 sql을 분리해준다. ->각각 관리 가능! <br> 데이터베이스의 결과를 자바객체에 자동으로 매핑해준다.

<Br>
<details><summary>필요한 의존성 
</summary>

*  org.mybatis 
<br>
* mybatis-spring
</details>


##  필요한 코드 
- 1. mybatis-config파일 만들기 
- 2. DataSource설정 먼저하기!(기본)
- 3. SqlSessionFactoryBean만들기 (아래코드 참조)

```
  <!-- SqlSessionFactoryBean 설정: MyBatis와 Spring 연동 모듈의 핵심으로, SqlSessionFactory를 생성합니다. -->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <!-- 데이터베이스 연결을 위한 DataSource 설정: 연결 정보가 담긴 dataSource 빈을 참조합니다. -->
    <property name="dataSource" ref="dataSource" />
    <!-- MyBatis 전역 설정 파일 위치 지정: MyBatis의 동작 방식, 매핑 규칙 등을 설정합니다. -->
    <property name="configLocation" value="classpath:mybatis-config.xml" />
    <!-- SQL 매핑 파일 위치 설정: SQL 문이 정의된 XML 파일의 위치를 지정합니다. 'classpath:sql/SQL.*.xml' 패턴으로 모든 SQL XML 파일을 인식합니다. -->
    <property name="mapperLocations" value="classpath:sql/SQL.*.xml" />
    <!-- 트랜잭션 관리 설정: Spring이 관리하는 트랜잭션으로 MyBatis 동작을 통합하기 위한 설정입니다. -->
    <property name="transactionFactory">
        <bean class="org.mybatis.spring.transaction.SpringManagedTransactionFactory" />
    </property>
</bean>

<!-- Mapper 인터페이스 스캔 설정: MyBatis Mapper 인터페이스를 자동으로 스캔하여 Spring 빈으로 등록합니다. 지정된 패키지 아래의 모든 인터페이스가 대상이 됩니다. -->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <!-- 스캔할 basePackage 설정: DAO(데이터 접근 객체)로 사용될 인터페이스가 위치한 패키지를 지정합니다. -->
    <property name="basePackage" value="com.feb.jdbc.dao" />
</bean>
<!-- 트랜잭션 매니저 설정: 데이터베이스 트랜잭션을 관리하기 위한 빈을 설정합니다. Spring의 트랜잭션 관리 기능을 활용하여 데이터 일관성을 유지합니다. -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <!-- DataSourceTransactionManager에 dataSource 설정: 데이터 소스 빈을 참조하여 트랜잭션 관리에 사용합니다. -->
    <property name="dataSource" ref="dataSource" />
</bean>

```

<br>

### mapping기능에 대한 추가설명 

<details><summary>mapping기능
</summary>


MyBatis는 데이터베이스의 결과를 자바 객체에 자동으로 매핑해주는 기능을 제공합니다. 이 기능은 SQL 쿼리의 실행 결과를 자바의 POJO(Plain Old Java Object)에 직접 매핑하여, 데이터베이스 작업 결과를 객체 지향적인 방식으로 쉽게 다룰 수 있게 해줍니다. MyBatis의 자동 매핑 기능은 크게 두 가지 방식으로 나뉩니다: 기본적인 자동 매핑과 고급 매핑 기능입니다.

기본적인 자동 매핑
MyBatis는 실행된 SQL 쿼리의 결과 컬럼 이름과 자바 객체의 필드 이름이 같을 경우, 자동으로 결과를 해당 객체의 필드에 매핑합니다. 이 때, MyBatis는 컬럼 이름과 필드 이름 사이의 대소문자 구분을 무시하며, 자동으로 매핑을 수행합니다.

Simple: 단순한 매핑으로, 결과 컬럼 이름과 객체 필드 이름이 정확히 일치할 때 자동으로 매핑합니다.
고급 매핑 기능
복잡한 쿼리 결과나 조인을 포함하는 쿼리 결과를 매핑할 때는, MyBatis의 고급 매핑 기능을 사용할 수 있습니다.

ResultMap: ResultMap은 SQL 쿼리의 결과와 자바 객체 필드 사이의 매핑 규칙을 정의합니다. 복잡한 조인 쿼리의 결과나, 컬럼 이름과 자바 객체 필드 이름이 다른 경우에 사용됩니다. ResultMap을 사용하면, 매우 세밀한 수준의 매핑 설정이 가능하며, 복잡한 계층 구조의 객체 매핑도 구현할 수 있습니다.
사용 예제
기본적인 자동 매핑을 사용하는 경우, 별도의 설정 없이 MyBatis 설정 파일이나 매핑 XML 파일에서 SQL 쿼리를 정의하면 됩니다. 컬럼 이름과 객체 필드 이름이 일치한다면 자동으로 매핑됩니다.

고급 매핑 기능을 사용하는 경우, ResultMap을 XML 매핑 파일에 정의하여 사용합니다:


```
<resultMap id="exampleResultMap" type="ExampleClass">
    <result property="field1" column="column1"/>
    <result property="field2" column="column2"/>
    ...
</resultMap>
여기서 type은 매핑될 객체의 타입을 나타내고, property와 column은 각각 객체의 필드 이름과 데이터베이스 컬럼 이름을 나타냅니다.

MyBatis의 자동 매핑 기능을 통해, 개발자는 SQL 쿼리의 결과를 객체에 매핑하는 과정을 간소화할 수 있으며, 데이터베이스 작업을 보다 객체 지향적으로 처리할 수 있게 됩니다.
```


</details>



