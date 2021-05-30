# JVegred
## como compilar e executar
### 1) Usando apache ant
va ao diretório raiz e
execute o comando "ant run" e o programa vai compilar e executar.
"ant jar" compila e empacota o programa em build/jar
### 2) Sem apache ant
ir ao diretorio src e executar o comando
javac src/JVegredApp.java (compila)

java src.JVegredApp (executa)
## Como usar
e - nova elipse.

t - novo triangulo.

r - novo retangulo.

s - novo texto.

delete - deleta figuras em foco

control + r - liga / desliga o modo resize. (padrão desligado)

Com o modo resize desligado o usuario move as figuras. Caso contrário o usuário redimensiona a figura.
OBS: caso o modo resize esteja desligado, é necessário selecionar a figura primeiro para depois move-la arrastando com o mouse.

f - selecionar cor de fundo (padrão)

p - selecionar grossura de contorno

o - selecionar cor do contorno

Mover roda do mouse altera o atributo selecionado das figuras em foco