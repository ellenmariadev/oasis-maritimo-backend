# Sistema de Gerenciamento para Oceanário - Oásis Marítimo

O Sistema de Gerenciamento para Oceanário, também conhecido como Oásis Marítimo, é uma plataforma dedicada ao cuidado, conservação e pesquisa das espécies marinhas em oceanários. Desenvolvido com o objetivo de simplificar e otimizar as operações diárias em ambientes marinhos, o Oásis Marítimo oferece uma variedade de funcionalidades para profissionais envolvidos no cuidado e monitoramento dos animais.


<kbd> Use **[Insomnia](https://github.com/ellenmariadev/oncar-backend/blob/main/data/insomnia.json)** para acessar as rotas.</kbd>

![Static Badge](https://img.shields.io/badge/Java-52b0d4)
![Static Badge](https://img.shields.io/badge/SpringBoot-52b0d4)
![Static Badge](https://img.shields.io/badge/PostgreSQL-52b0d4)
![Static Badge](https://img.shields.io/badge/Heroku-52b0d4)
![Static Badge](https://img.shields.io/badge/H2%20Database-52b0d4)
![Static Badge](https://img.shields.io/badge/AWS-52b0d4)
![Static Badge](https://img.shields.io/badge/Spring%20Security%20JWT-52b0d4)

 ### 🌐 DEPLOY 
 
https://oasis-maritimo-afe4c920a559.herokuapp.com/api/v1

## 📑 Documentação

 | Route | Description |
 | ----- | ----------- |
 | <kbd> /api-docs </kbd> |[Documentação JSON](https://oasis-maritimo-afe4c920a559.herokuapp.com/api-docs)|
 | <kbd> /swagger.html </kbd>  | [Documentação Swagger](https://oasis-maritimo-afe4c920a559.herokuapp.com/swagger-ui/index.html) |

## Instalação e Uso

1. Clone este repositório em sua máquina local.
```java
git clone <https://github.com/ellenmariadev/oasis-maritimo.git>
```
2. Instale as dependências necessárias.
```java
mvn clean install
```
3. Rode a aplicação
```java
mvn spring-boot:run // O comando específico para iniciar o servidor pode variar dependendo da configuração do projeto.
```

4. **Acesse a plataforma por meio de um navegador da web:**

Abra um navegador e navegue até o endereço local onde o servidor está sendo executado `http://localhost:8080`.

 ### ○ Variáveis de ambiente
```java
  DATABASE_HOST=
  DATABASE_PORT=
  DATABASE_NAME=
  DATABASE_USERNAME=
  DATABASE_PASSWORD=

# Hospedar as imagens na AWS

  AWS_ACCESS_KEY_ID=
  AWS_ACCESS_KEY_SECRET=
  AWS_REGION=
  AWS_BUCKET_NAME= 
```

## Contribuição

Contribuições são bem-vindas! Se você deseja melhorar o Oásis Marítimo, siga estas etapas:

1. Fork este repositório.
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`).
3. Faça commit de suas alterações (`git commit -am 'Adicionar nova feature'`).
4. Faça push para a branch (`git push origin feature/nova-feature`).
5. Abra um pull request.

## Licença

Este projeto é licenciado sob a [Licença MIT](https://opensource.org/licenses/MIT) - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---
