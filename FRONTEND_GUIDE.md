# Guía para el Frontend - Flujo de Partida

Este documento explica cómo el frontend debe interactuar con el backend (`match-be`) para crear y jugar una partida.

## Arquitectura General

La lógica ha sido refactorizada. Ahora, el **frontend es responsable de orquestar la partida**, mientras que el backend solo se encarga de calcular el resultado y guardarlo.

Esto significa que el frontend debe:
1.  Obtener los datos de los personajes.
2.  Obtener las tiradas de los dados.
3.  Enviar toda esta información al backend en una única petición.

## Flujo Paso a Paso para el Frontend

1.  **Obtener Personaje del Jugador**: Cuando el usuario elija un personaje, obtené el objeto completo de ese personaje desde el servicio de `characters-be`.

2.  **Obtener Personaje Oponente**: Realizá una petición al endpoint de personaje aleatorio de `characters-be` (`GET /api/characters/random`) para obtener el oponente.

3.  **Obtener Dados del Jugador**: Hacé una petición al servicio de `random-be` (`GET /api/v1/dice`) para obtener las 3 tiradas de dados del jugador.

4.  **Obtener Dados del Oponente**: Volvé a hacer una petición a `random-be` (`GET /api/v1/dice`) para obtener las 3 tiradas de dados del oponente.

5.  **Enviar Petición al Backend (`match-be`)**: Una vez que tenés toda la información, debés construir un objeto JSON y enviarlo mediante una petición `POST` al endpoint `/match/play` del `match-be`.

## Estructura de la Petición `POST /match/play`

El body de la petición debe ser un JSON con la siguiente estructura:

```json
{
  "playerCharacter": {
    "id": 1,
    "nombre": "Personaje Jugador",
    "descripcion": "Descripción...",
    "ataque": 50,
    "defensa": 30,
    "velocidad": 20,
    "imagen_url": "http://example.com/img.png"
  },
  "opponentCharacter": {
    "id": 2,
    "nombre": "Personaje Oponente",
    "descripcion": "Descripción...",
    "ataque": 40,
    "defensa": 40,
    "velocidad": 20,
    "imagen_url": "http://example.com/img2.png"
  },
  "playerDices": [
    { "numero": 6, "imagen_url": "..." },
    { "numero": 4, "imagen_url": "..." },
    { "numero": 3, "imagen_url": "..." }
  ],
  "opponentDices": [
    { "numero": 5, "imagen_url": "..." },
    { "numero": 5, "imagen_url": "..." },
    { "numero": 2, "imagen_url": "..." }
  ]
}
```

### Campos:

-   `playerCharacter`: Objeto completo del personaje del jugador.
-   `opponentCharacter`: Objeto completo del personaje oponente.
-   `playerDices`: Array con los 3 objetos de dados del jugador.
-   `opponentDices`: Array con los 3 objetos de dados del oponente.

## Respuesta del Backend

El backend procesará la partida y responderá con un `HTTP 200 OK` y un JSON que representa el resultado de la partida guardada. El formato será el siguiente:

```json
{
  "id": 123,
  "playerCharacterId": 1,
  "opponentCharacterId": 2,
  "playerScore": 6000,
  "opponentScore": 5280,
  "winnerCharacterId": 1
}
```
