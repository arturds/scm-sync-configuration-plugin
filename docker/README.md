Para testar as alterações é preciso configurar as variáveis de ambiente `SCM_REPOSITORY` e `SCM_BRANCH` que está localizada no arquivo [docker-compose.yml](docker-compose.yml).

**PS: A branch escolhida assim como o respositório devem pré-existir para que tudo funcione corretamente**
**PS: Você precisa buildar o projeto do plugin com maven para que o comando abaixo funcione corretamente**

Agora basta executar o comando abaixo para construir a imagem e subir o container:

```sh
./build-and-run.sh
```
