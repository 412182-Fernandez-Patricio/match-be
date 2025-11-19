# GEMINI.md

# **Modelado, lógica y arquitectura de un juego de pelea 1 vs 1**

## **1. Objetivo general**

Desarrollar e implementar la arquitectura completa de un juego de peleas 1 vs 1, demostrando competencias en lógica de negocio (Angular) e infraestructura (Docker, Nginx).

## **2. Funcionalidad de la app (60 puntos)**

### **2.1 Flujo general del juego**
1. El usuario selecciona "inicio de juego".
2. El jugador físico (el que usa la app) elige un personaje entre los disponibles.
3. El oponente se selecciona de forma automática y aleatoria entre el resto de los personajes.
4. Toda la info de la partida debe quedar registrada en la base de datos.

### **2.2 Lógica de partida**
- Una partida es un enfrentamiento entre: el personaje elegido por el jugador y un oponente elegido al azar por el sistema.
- Para elegir al oponente, el sistema debe consumir el endpoint para obtener un personaje random.
- Al crear la partida, se deben guardar como mínimo:
  - El personaje del jugador
  - El personaje oponente
  - Los valores de dados obtenidos por cada uno
  - El puntaje final de cada personaje
  - El resultado de la partida (ganador)

### **2.3 Interfaz y flujo de pantallas (SPA)**
El frontend debe ser una SPA. La navegación entre pantallas es un requisito fundamental.

### Pantalla 1: selección / vista de personajes
- Acciones:  
  GET /api/characters  
  GET /api/characters/random  
- Info requerida: id, nombre, descripcion, ataque, defensa, velocidad, imagen_url.
- Angular obligatorios:  
  - Reactividad (signals o rxjs)  
  - Servicios (service)
- Angular sugeridos:  
  - HttpInterceptorFn  
  - injectionToken para URL base

### Pantalla 2: Tirada de dados (lógica de pelea)
- Acción: consumir GET /api/v1/dice (x2). Mostrar 6 imágenes de dados. Calcular TOTAL_DADOS.
- Obligatorios Angular:  
  - signal()  
  - computed() para TOTAL_ATRIBUTOS, TOTAL_DADOS y PUNTAJE_FINAL
- Sugeridos:  
  - effect() para navegar automáticamente

### Pantalla 3: Resultado de la partida
- Acción: mostrar GANADOR/PERDEDOR y los puntajes finales.  
- Angular obligatorios:  
  - router.navigate()  
  - canDeactivate

### Pantalla 4: Estadísticas
- Acción: consumir endpoint de match-be y mostrar tabla: personajes, partidas jugadas, victorias, derrotas.
- Obligatorios: reactividad (signals o rxjs)
- Sugerido: vistas diferidas (@defer)

### **2.4 Cálculos del ganador**
1. TOTAL_ATRIBUTOS = ataque + defensa + velocidad  
2. TOTAL_DADOS = dado1 + dado2 + dado3  
3. PUNTAJE_FINAL = TOTAL_ATRIBUTOS * TOTAL_DADOS  
Gana el personaje con mayor PUNTAJE_FINAL.

### **2.5 Registro y estadísticas**
- Cada partida debe guardarse en BD.
- match-be debe proveer endpoint de estadísticas.

## **3. Eje 2: Arquitectura y Microservicios**
Requisito: levantar todo con docker compose up.

### **3.1 Servicios provistos (Cátedra)**

#### Characters Service
- Puerto interno: 80  
- Endpoints:
  - GET /api/characters/{id}
  - GET /api/characters/random
  - GET /api/characters/

#### Random Service
- Puerto interno: 8080  
- GET /api/v1/dice (3 dados, usar 2 veces por pelea)

### **3.2 Componentes a construir (Alumno)**

#### Frontend
- Angular 19+ standalone  
- Dockerfile multietapa con Nginx

#### Backend (match-be)
- Lógica de negocio (crear partida, calcular, guardar, stats)  
- Prohibido H2  
- Dockerfile multietapa  
- Exposición solo mediante reverse proxy

#### Base de datos
- PostgreSQL o MySQL  
- characters-db y match-db

#### Nginx (reverse proxy obligatorio)
- nginx.conf desde cero
- Reglas:
  - Puerto 1583 → frontend (con try_files)
  - Puerto 5712 → API Gateway:
    - /api/characters → characters-be  
    - /api/v1/dice → random-be  
    - /api/match → match-be

### **3.3 Docker Compose**
Servicios obligatorios (7):
1. frontend  
2. match-be  
3. match-db  
4. characters-be  
5. characters-db  
6. random-be  
7. proxy-reverse  

Construcción:  
- characters-be y random-be → imágenes públicas  
- frontend y match-be → build local  

Orden de arranque:  
- Backends no deben iniciar hasta que sus BD estén listas.
