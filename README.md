# JVegred
## como compilar e executar
### 1) Usando apache ant
va ao diretório raiz e
execute o comando "ant run" e o programa vai compilar e executar.

"ant jar" compila e empacota o programa em um .jar no diretório raiz.
### 2) Sem apache ant
ir ao diretorio src e executar o comando
"javac src/JVegredApp.java" compila o programa

o comando "java src.JVegredApp" ainda no diretório src/ executa o programa
## Como usar
e - nova elipse.

t - novo triangulo.

r - novo retangulo.

s - novo texto. <- Removido

delete - deleta figuras em foco

control + r - liga / desliga o modo resize. (padrão desligado)
Com o modo resize desligado o usuario move as figuras.

Mover a roda do mouse altera uma propriedade das figuras em foco:

f - selecionar cor de fundo (padrão)

p - selecionar grossura de contorno

o - selecionar cor do contorno

### Novidades
h - novo hexágono.

control + s - Salva o estado atual do canvas no arquivo canvas.bin no diretório de execução.

control + o - Lê o o arquivo canvas.bin do diretório de execução e restaura o estado do canvas.

control + c - Copia a seleção atual de figuras.

control + v - Cola a ultima seleção copiada.

O usuário pode criar figuras por uma toolbox no canto superior esquerdo. É só clicar com o mouse na figura desejada.