Para testar as alterações é preciso configurar as variáveis de ambiente `SCM_REPOSITORY` e `SCM_BRANCH` que está localizada no arquivo [docker-compose.yml](docker-compose.yml) com seu repositório.

Agora basta executar o comando abaixo para construir a imagem e subir o container:

```sh
./build-and-run.sh
```

**PS: O plugin scm-sync-configuration está com um bug na versão `0.0.10` com a imagem `jenkins:latest`. Se você instalar essa versão ela vai apresentar o seguinte erro: Ao entrar pela primeira vez o plugin irá sincronizar as configurações para o repositório normalmente, mas quando você remove o container e cria novamente ele vai se perder ao baixar as configurações e irá fazer commits errados sobreescrevendo o que você tinha configurado antes, por exemplo: remover autenticação.**
