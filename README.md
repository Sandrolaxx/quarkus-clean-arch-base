# Aplicação Quarkus com Clean Architeture

Abaixo, os conteúdos do readme estão dispostos da seguinte forma:
* 🏗 O que seria Clean Architeture?
* 📂 Estrutura das pastas
* 🗃 Implementação do Repo
* ✨ O que seria Quarkus?
* 🤔 Sobre o projeto?
* 👨‍💻 Executando aplicação em dev mode
* 📦 Empacotando e executando a aplicação
* 🌪 Criando um executável nativo
* 🔌 Extenções utilizadas

## 🏗 O que seria Clean Architeture?

Desde o início de sua jornada como dev, você deve estar ciente dos problemas que um código sujo traz, não em um primeiro momento, mas sim algum tempo depois, com bug's aleatórios e a dificuldade na manutenção.

O mesmo problema ocorre com uma arquitetura de software mal planejada, onde no início tudo parece normal e não ha impacto na produtividade, porém depois de alguns anos o código fica complexo, todo o acoplamento criado não permite os times serem ágeis e entregar funcionalidades em um tempo satisfatório, e quando adicionado uma feature na "região norte" do sistema o "região sul" é impactada e tal problema só é descoberto em produção, então é proposto criar todo o sistema do zero, e a cada 5/7 anos esse ciclo vai se repetir, e a verdadeira raiz do problema, a arquitetura ruim, nunca é considerada.

Fazendo uma analogia entre a construção de um software e a de uma casa, imagine todo o processo de desenvolvimento da residência, desde o projeto até a entrega das chaves. Ao projetar e construir uma casa, bons arquitetos e engenheiros devem confluir suas ideias, sempre respeitando regras específicas, para que, após a entrega do imóvel, qualquer tipo de alteração possa ser realizada da maneira mais prática possível.

Imagine que algum tempo depois do imóvel pronto, o proprietário resolve fazer alguma alteração física da residência, como, por exemplo, ampliar um quarto, acrescentar um novo banheiro e fazer uma nova janela. Dependendo de como a casa foi concebida na mesa do arquiteto, uma alteração pode se tornar mais dispendiosa, demorar mais tempo para ser concluída, ou até mesmo impossível.

Assim como o planejamento de uma casa deve ser responsável e seu produto final precisa ter flexibilidade para alterações ou ampliações, a criação de um software também deve favorecer a qualidade do código. Vale lembrar ainda que, assim como um arquiteto de uma casa, o desenvolvedor do programa é o responsável pela entrega do produto e provavelmente em possíveis upgrades futuros.

Estabelecer uma arquitetura limpa pode facilitar seu próprio trabalho a médio ou longo prazo.

Para mais sobre [Arquitetura Limpa](https://www.cedrotech.com/blog/arquitetura-limpa).

Durante as últimas décadas, viu-se a evolução de um apanhado de ideias importantes sobre Arquitetura de Software, incluindo: a Arquitetura Hexagonal (também conhecida como Ports and Adapters) de Alistair Cockburn, DCI de James Coplien e Trygve Reenskaug, BCE de Ivar Jacobson, entre outros.([TheWiseDev](https://thewisedev.com.br/))

Durante as últimas décadas, viu-se a evolução de um apanhado de ideias importantes sobre Arquitetura de Software, incluindo: a Arquitetura Hexagonal (também conhecida como Ports and Adapters) de Alistair Cockburn, DCI de James Coplien e Trygve Reenskaug, BCE de Ivar Jacobson, entre outros.([TheWiseDev](https://thewisedev.com.br/))

![Clean Architeture](https://thewisedev.com.br/1a851f5f4c6168cd8b072ca72b4d9fe4.svg) - Fonte - TheWiseDev

---

## 📂 Estrutura das pastas

```
src/
└── main/
    ├── docker/
    └── java/
        └── com/
            └── aktie/
                ├── domain/
                │   ├── entities/
                │   │   ├── dto/
                │   │   ├── enums/
                │   │   ├── mappers/
                │   │   ├── vo/
                │   │   └── UserBO.java
                │   ├── repositories/
                │   │   └── IUserRepository.java
                │   └── usecases/
                │       ├── CreateUser.java
                │       ├── DisableUser.java
                │       ├── FindUserBy.java
                │       ├── ListUsersBy.java
                │       └── UpdateUserInfo.java
                ├── utils/
                │   ├── exception/
                │   │   ├── AktieException.java
                │   │   ├── AktieExceptionResponseDto.java
                │   │   └── ErrorResponseExceptionMapper.java
                │   ├── DateUtil.java
                │   ├── EnumUtil.java
                │   ├── ListUtil.java
                │   ├── NumericUtil.java
                │   ├── StringUtil.java
                │   └── Utils.java
                infra/
                └── database/
                    ├── mongo/
                    │   ├── mappers/
                    │   │   └── MongoUserMapper.java
                    │   ├── model/
                    │   │   └── MongoUser.java
                    │   └── repositories/
                    │       └── MongoUserRepository.java
                    └── postgres/
                        ├── mappers/
                        │   └── PgUserMapper.java
                        ├── model/
                        │   └── PgUser.java
                        └── repositories/
                            └── PgUserRepository.java
                presentation/
                └── controllers/
                    └── UserController.java
                services/
                ├── AbstractService.java
                ├── DbFactory.java
                └── UserService.java
resources/
├── META-INF
│    └── resources
│        └── index.html
└── application.properties
test/
└── java/
    └── com/
        └── aktie/
            ├── integration/
            │   └── config/
            │       ├── AktieTestLifeCycleManager.java
            │       └── UserResourceTest.java
            │
            └── unit/
                ├── domain/
                │   ├── bo/
                │   │   └── UserBOTests.java
                │   └── usecase/
                │       └── UserUseCases.java
                │
                └── infra/
                    └── InMemoryUserRepository.java
```

### Resumo das principais pastas e responsabilidades:

- `domain/`: Camada de domínio, conforme a arquitetura limpa.
    - `entities/`: Contém objetos de negócio (como `UserBO`) e estruturas auxiliares (`dto`, `enums`, `mappers`, `vo`).
    - `repositories/`: Interfaces de abstração para os repositórios.
    - `usecases/`: Casos de uso que orquestram as regras de negócio da aplicação.
- `utils/`: Utilitários genéricos.
  - `exception/`: Estrutura de tratamento e mapeamento de exceções customizadas.
- `infra/database/mongo` e `postgres`: Implementações específicas para persistência (MongoDB e PostgreSQL), contendo:
    - `mappers/`: Conversão entre entidades de domínio e modelos de banco.
    - `model/`: Representações das entidades persistidas nos bancos.
    - `repositories/`: Implementações dos repositórios para cada tecnologia.
- `presentation/controllers`: Contém o controlador UserController.java, responsável por expor os endpoints HTTP.
- `services/`:
    - `AbstractService.java`: Provavelmente define uma base comum para serviços.
    - `DbFactory.java`: Responsável por decidir entre Mongo/Postgres.
    - `UserService.java`: Contém lógica de orquestração relacionada a usuários.

---

## 🗃 Implementação do Repo

Seguindo a teoria neste repositório temos uma implementação com algumas pequenas alterações no modelo original.

![sandrolaxCleanArch](https://github.com/Sandrolaxx/solid-examples/assets/61207420/c01d859d-0f69-46e7-87bf-eabdf444012d)

---

## ✨ O que seria Quarkus?

Quarkus é um framework Java nativo em Kubernetes e de stack completo que foi desenvolvido para máquinas virtuais Java (JVMs) e compilação nativa. Ele otimiza essa linguagem especificamente para containers, fazendo com que essa tecnologia seja uma plataforma eficaz para ambientes [serverless](https://www.redhat.com/pt-br/topics/cloud-native-apps/what-is-serverless), de [nuvem](https://www.redhat.com/pt-br/topics/cloud) e [Kubernetes](https://www.redhat.com/pt-br/topics/containers/what-is-kubernetes).

Benefícios do framework:

- Criado por dev's para dev's
- Soporte a [GraalVM](https://www.oracle.com/br/java/graalvm/what-is-graalvm/)
- [Container First](https://quarkus.io/container-first/)
- Possibilidade de desenvolver com código [imperativo e reativo](https://quarkus.io/continuum/)

Se você deseja saber mais sobre Quarkus, visite o site oficial deles: https://quarkus.io/ .

---

## 🤔 Sobre o projeto?

Consiste em uma API(CRUD de usuário) onde é possível passar no parâmetro da requisição qual implementação de banco de dados será utilizada, não parece algo muito corriqueiro, poderíamos ter uma lógica de negócio mais avançada aqui, porém foi implementado desta forma para facilitar a compreensão de uma das ideias principais por trás da arquitetura limpa, o alto nível de desacoplamento.

Temos duas opções de implementação que podem ser passadas no header, `POSTGRES`, onde irá realizar a persistência no banco de dados relacional PostgreSQL, e a outra opção `MONGO`, onde será realizada a persistência no banco de dados NoSql MongoDB.

---

## 👨‍💻 Executando aplicação em dev mode

Necessário executar o docker-compose localizado na raiz do projeto, ele irá subir os containers dos bancos de dados.

```
docker-compose up
```

Você pode executar sua aplicação em Dev Mode, este que habilita o hot reload, utilizando o comando:

```shell script
./mvnw quarkus:dev
```

> **_NOTA:_** Quarkus possui uma Dev UI, que está disponível somente em Dev Mode no caminho http://localhost:8080/q/dev/.

---

## 📦 Empacotando e executando a aplicação

Para empacotar:

```shell script
./mvnw package
```

Isso irá produzir o arquivo `quarkus-run.jar` no diretório `target/quarkus-app/`.

Isso não é um _über-jar_.

Caso você queira empacotar como _über-jar_, execute o comando a seguir:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

Para executar o .jar empacotado convencionalmente:

```shell script
java -jar target/quarkus-app/quarkus-run.jar
```

Para executar o .jar empacotado como _über-jar_:

```shell script
java -jar target/*-runner.jar
```

---

## 🌪 Criando um executável nativo

Você pode empacotar uma imagem nativa com:

```shell script
./mvnw package -Pnative
```

Caso você não tenha a GraalVM instalada, você pode empacotar uma imagem nativa em um container:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

Você pode executar sua imagem nativa com:

```
./target/quarkus-clean-arch-base-1.0.0-SNAPSHOT-runner
```

Caso você queira saber mais sobre como buildar imagens nativas, consulte: https://quarkus.io/guides/maven-tooling.

---

## 🔌 Extenções utilizadas

- REST Client Classic ([guia](https://quarkus.io/guides/rest-client)): Call REST services
- RESTEasy Classic JSON-B ([guia](https://quarkus.io/guides/rest-json)): JSON-B serialization support for RESTEasy Classic
- Hibernate With Panache([guia](https://quarkus.io/guides/hibernate-reactive-panache)) - Simplified Hibernate ORM
- MongoDB With Panache([guia](https://quarkus.io/guides/mongodb-panache)) - Simplified MongoDB with Panache
