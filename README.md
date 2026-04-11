## Dungeon Meshi Game

A temática para nosso jogo será o mangá "Dungeon Meshi".
Resumidamente: Um grupo de aventureiros desce uma masmorra com um sistema ecológico próprio para salvar um de seus companheiros, e fazem suas refeições com os monstros que lá habitam.

Por enquanto, o jogo tem apenas uma batalha simples com dois inimigos, um herói, e duas cartas (infinitamente reutilizáveis)

## Compilação

O jogo pode ser compilado rodando o comando do gradle a partir da root do diretório:
```
./gradlew run
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

## Cartas

Há 9 tipos de cartas distintas atualmente no jogo:

**1. Espada**: Causa 3 de dano, custa 1 de energia.

**2. Machado do Senshi**: Causa 6 de dano, custa 2 de energia

**3. Escudo pequeno**: Concede 3 de escudo ao usuário. Custa 1 de de energia.

**4. Panela inoxídavel do Senshi**: Concede 7 de escudo ao usuário. Custa 2 de energia.

**5. Ferrão do escorpião**: Causa 1 de dano. Aplica Veneno (2) ao alvo. Custa 1 de energia.

**6. Antidoto de escorpião**: Aplica Veneno (-3) a um alido ou oponente (Isto é, cura 3 acumulos de veneno.)

**7. Frasco de Veneno**: Aplica Veneno (3) a um aliado ou oponente.

**8. Mandrágora no Vaso**: Causa 3 de dano a todos os inimigos, mas causa 3 de dano ao usuário. Custa 1 de energia.

**9. Força**: Aplica Força (3) em um aliado por um turno. Custa 1 de energia.

## Efeitos

Há alguns efeitos que podem ser aplicados em jogo.

**1. Veneno**: No final do turno do herói, o personagem com este efeito tomará dano equivalente a quantidade de acúmulos, e se curará de um acúmulo.

**2. Força**: Cartas que dão dano utilizadas por este personagem recebem um aumento de dano.

**3. Paralise**: No inicio do turno do herói, você deve descartar cartas equivalente ao número de acúmulos.


