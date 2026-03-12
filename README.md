## Dungeon Meshi Game

A temática para nosso jogo será o mangá "Dungeon Meshi".
Resumidamente: Um grupo de aventureiros desce uma masmorra com um sistema ecológico próprio para salvar um de seus companheiros, e fazem suas refeições com os monstros que lá habitam.

Por enquanto, o jogo tem apenas uma batalha simples com dois inimigos, um herói, e duas cartas (infinitamente reutilizáveis)

## Compilação

O jogo pode ser compilado seguindo as instruções que estão no doc da tarefa 01:
```
javac -d bin $(find src -name "*.java")
java -cp bin App
```
## Jogabilidade

No seu turno, você é mostrado o estado da batalha e as suas ações possiveis, além da sua energia. como mostrado:
```
===== Batalha =====
Rodada 1
Laios(20/20)
vs
Cogumelo Andarilho(8/8)
Cogumelo Andarilho(8/8)
===== --------- =====
======= Seu Turno ======
Energia: 2/2
Suas Cartas:
Espada: Causa 4 de Dano, Custa 1 de Energia
Escudo Pequeno: Concede 3 de Escudo, Custa 1 de Energia
===== Escolha uma ação ===== 
Energia: 2/2
(1) Usar Espada | Custo: 1
(2) Usar Escudo Pequeno | Custo: 1
(3) Encerrar turno
```

Nesse momento, você pode digitar um número para escolher sua ação. Em seguida, você recebe uma lista de alvos possíveis para sua ação:
```
===== Escolha um alvo =====
(1) Cogumelo Andarilho 8/8
(2) Cogumelo Andarilho 8/8
(3) Retornar ao menu de ação
```
E novamente digita um número para escolher sua ação.

Quando você esgota sua energia, seu turno acaba, e os inimigos vivos imediatamente te atacam.

A batalha continua até que ou você seja derrotado, ou derrote todos os inimigos.

