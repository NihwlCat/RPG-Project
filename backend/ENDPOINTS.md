- **/login ?username= [POST]**
> Autenticação do usuário.

### 🟩 Endpoints /characters [GET]
- /characters/profile
> Retorna uma projeção da ficha mostrando apenas os dados principais do personagem de um usuário logado.

- /characters/profile/resources **?resource[sheet, history, items, spells]**
> Retorna uma projeção da ficha mostrando uma fatia do conjunto de acordo com o parâmetro especificado.

- /characters/master *[ROLE: MASTER]*
> Retorna todos os personagens com conteúdo resumido.

- /characters/master/{id} *[ROLE: MASTER]*
> Retorna informações do personagem de acordo com o ID.

- /characters/master/{id}/resources **?resource=[sheet, history, items, spells]** *[ROLE: MASTER]*
> Retorna uma projeção de um personagem dado o ID com o recurso especificado

### 🟦 Endpoints /characters [PATCH]
- /characters/profile **?resource=[seals, profile, items, spells, history, general, offensive, basic]**
> Atualiza uma parte específica da ficha do personagem do usuário logado de acordo com o parâmetro.

- /characters/master/{id}/resources **?resource=[seals, profile, items, spells, history, general, offensive, basic]** *[ROLE: MASTER]*
> Atualiza uma parte específica da ficha do personagem de acordo com o ID.

### 🟥 Endpoints /characters [DELETE]
- /characters/profile **?resource=[seals, spells, items]**
> Remove um item do inventário do personagem do usuário logado de acordo com o tipo.

- /characters/master/{id}/resources **?resource=[seals, spells, items]** *[ROLE: MASTER]*
> Remove um item do inventário do personagem de acordo com o ID.

- /characters/master/{id} *[ROLE: MASTER]*
> Delete um personagem do banco de dados.

### 🟨 Endpoints /characters [POST]
- /characters/master *[ROLE: MASTER]*
> Cria um personagem passando um nome no body.

### 🟩 Endpoints /players [GET]

- /players
> Lista todos os jogadores da mesa.

- /players/{id}
> Informações detalhadas de um jogador da mesa.

### 🟦 Endpoints /players [PATCH]

- /players
> Altera as anotações do jogador logado

- /players/{id} *[ROLE: MASTER]*
> Altera as anotações de um jogador dado o ID.

### 🟥 Endpoints /players [DELETE]

- /players/{id} *[ROLE: MASTER]*
> Remove um jogador do sistema.

### 🟨 Endpoints /players [POST]

- /players *[ROLE: MASTER]*
> Adiciona um novo jogador ao sistema.


### 🟨 Endpoints /interactions [POST]

- /interactions/bot **?personaId=**
> Realiza um teste da perícia informada no body que posteriormente será enviada para o Discord Bot.
> Caso não tenha valor no **personaId** será usado o personagem do usuário logado.

## JSON - Atalhos

- Body para atualizar **profile** [profile]
```json
{
    "profile": {
        "name": "Bill Cipher",
        "age": 0,
        "alignment": "Dimensão do caos",
        "status": "ALIVE"
    },
    "individuality": {
        "value": 87,
        "description": "Consegue conjurar o estranhagedom"
    },
    "basic": {
        "life": 8765,
        "maxLife": 9999,
        "magic": 8765,
        "maxMagic": 9999,
        "sanity": 40,
        "maxSanity": 78,
        "awakening": 23,
        "control": 34,
        "movement": 9,
        "vigor": 6,
        "defense": 12
    }
}
```
- Body para atualizar **feitiços** e **runas** [spells, seals]
```json
{
    "name": "Feitiço poderoso",
    "imgUrl": "imagem.com.br",
    "description": "Causa muito dano mesmo."
}
```
- Body para atualizar **itens** [items]
```json
{
    "id": 6,
    "type": "MELEE_WEAPON",
    "description": "Uma espada pequena."
}
```
- Body para atualizar **ficha** [offensive, general, basic]
> É possível passar somente o atributo e o novo valor para atualizar.

```json
{
    "DESTREZA": 33
}
```
- Body para criar player
```json
{
    "id":"XXX#1234",
    "name":"Miguel",
    "role":"JOGADOR"
}
```
- Body para alterar anotações
```json
{
    "annotations": "Lavender fields"
}
```
