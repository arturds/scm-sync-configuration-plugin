Para testar as alterações é preciso configurar as variáveis de ambiente `SCM_REPOSITORY` e `SCM_BRANCH` que está localizada no arquivo [docker-compose.yml](docker-compose.yml) com seu repositório.

**PS: A branch escolhida assim como o respositório devem existir**

Agora basta executar o comando abaixo para construir a imagem e subir o container:

```sh
./build-and-run.sh
```