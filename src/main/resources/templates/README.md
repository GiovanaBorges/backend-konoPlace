## KonoPlace documentação

KonoPlace é uma plataforma de reserva de lugares do escritório da First tech . O KonoPlace permite a reserva de lugares por data.

### Problema encontrado

A quantidade de mesas nos escritórios é desigual a quantidade de funcionários da First tech . O Konoplace oferece uma solução de plataforma online onde é possível reservar seu lugar , evitando assim que o escritório fique cheio ou que pessoas não tenham lugar para ficar.

### Rotas

Rotas para o Usuário
GET "user/login" Usuário logar na conta

POST "user/" Cadastro de usuário

DELETE "user/id_user" Deletar usuário

PUT "user/id_user" Editar usuário

Rotas para Reserva
GET "/reserva" Verificar todas as reservas

GET "/reserva:data" Verificar todas as reservas por data

POST "/reserva" Registrar reservar na tabela de reservas

PUT "/reserva:id_reserva" Editar informação reserva

DELETE "/reserva:id_reserva" Deletar reserva

Rotas para Mesa
GET "/mesa" Verificar todas as mesas Registradas no banco

GET "/mesa/name/:name_mesa" Verificar mesa por nome

GET "/mesa/:id_mesa" Verificar mesa por ID

POST "/mesa" Registrar mesa

PUT "/place:id_place" Editar informação sobre mesa

DELETE" /place:id_place" Deletar informação sobre mesa

### Modelagem banco:

tabela usuário:
idUsuario

tabela reserva:
idReserva
data
fk usuário
fk mesa

tabela mesa:
idMesa

### Telas disponíveis :
link do protótipo no figma
https://www.figma.com/file/QqthVkxqgY3IbtQL2atunb/Prototype-Kono?node-id=0%3A1



### Paleta de cores ? , fontes , protótipo

Fonte : Poppins

Paleta de cores :
    white:#FFFFFF // cor letra
    dark-100:#1C1C1C; // cor alternativa fundo
    bg-dark:#0D0D0D; // cor de fundo
    gray-50:#262626; // cor letra
    gray-30:#302C2C; // cor letra 
    red:#ff8c00; // cor buttons
    green:#14760B; // cor buttons

### Tecnologias usadas :
Frontend:
Html
Css
Javascript
Bootstrap

Backend:
Java
Spring boot
Mysql
Spring validator
Spring 
Spring security

### features futuras

Futuras atualizações do projeto