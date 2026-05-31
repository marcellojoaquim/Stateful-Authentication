# Stateful Authentication Project

<p> 
Autenticação Stateful é comumente usada em aplicações, especialmente as que NÃO exigem muita escalabilidade.
</p>

- A sessão com estado é criada no lado do servidor backend e o ID de referência da sessão correspondente é enviado ao cliente.
- Usa um token opaco, ou seja, tokens em um formato proprietário, em que não é possível acessar informações do usuário e contém algum identificador para acessar informações persistidas em algum servidor de armazenamento.
- Para validar o token é preciso enviar uma requisição, normalmente HTTP, contendo o token para o servidor que o gerou.
- Token opaco é normalmente uma string simples que não possui significado legível.
- Como token opaco, por exemplo, podemos usar UUIDs para representar a sessão do usuário.
- A base de dados para armazenamento mais comumente usada é o Redis.
Desta forma, uma vez que o ID de referência é excluído no lado do servidor o cleinte nao conseguirá mais se autenticar, sendo necessário gerar um novo.

## Vantagens

- Lógica de implementação centralizada.
- Gestão de controle de acesso simplificados.
- Excelente para aplicações MVC, Monolíticas e processos internos.
- Maior complexidade para autenticação de clientes externos.
- Terceiros mal intencionados possuem maior dificuldade para esconder brechas de segurança.

## Desvantagens

- Potencial estresse da API responsável por realizar a validação do token.
- Maior dificuldade em relação a escalabilidade
- Maior dificuldade em propagar a autenticação entre microsserviços.

## Arquitetura
![Desenho da arquitetura](utils/stateful.png)