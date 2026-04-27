# GameZone Pro

## Descripción General
GameZone Pro es una aplicación desarrollada en Java utilizando Swing que simula un sistema completo de videojuegos. Incluye funcionalidades de tienda, álbum de cartas, torneos con concurrencia (Threads), gamificación y generación de reportes HTML.

El sistema está diseñado bajo principios de Programación Orientada a Objetos y estructuras de datos implementadas manualmente.



## Funcionalidades Principales

### Tienda de Videojuegos
- Visualización de catálogo desde archivo `catalogo.txt`
- Carrito de compras
- Validación de stock
- Registro de compras en `historial.txt`
- Actualización persistente del stock



### Álbum de Cartas Coleccionables
- Implementación de matriz ortogonal (4x6)
- Cartas con atributos:
  - Código
  - Nombre
  - Tipo
  - Rareza
  - ATK, DEF, PS
- Inserción automática en primera celda vacía
- Intercambio de cartas
- Filtros por tipo y rareza
- Persistencia en `album.txt`



### Eventos Especiales (Torneos)
- Lectura de torneos desde `torneos.txt`
- Sistema de cola implementado desde cero
- Simulación de venta de tickets con Threads
- Uso de `synchronized` para evitar condiciones de carrera
- Registro en `tickets_vendidos.txt`



### Gamificación
- Sistema de experiencia (XP)
- Niveles del jugador
- Logros desbloqueables
- Leaderboard dinámico basado en usuarios reales
- Persistencia del XP en `usuarios.txt`



### Reportes HTML
Generación automática de:

- Inventario
- Ventas
- Álbum
- Torneos

Los reportes se abren automáticamente en el navegador.



## Persistencia de Datos

Archivos utilizados:

- `usuarios.txt`
- `catalogo.txt`
- `historial.txt`
- `album.txt`
- `torneos.txt`
- `tickets_vendidos.txt`



## Ejecución del Programa

1. Abrir el proyecto en NetBeans
2. Ejecutar la clase principal `GameZonePro.java`
3. Registrar o iniciar sesión
4. Navegar por el sistema



## Tecnologías Utilizadas

- Java
- Swing (Interfaz gráfica)
- Programación orientada a objetos
- Archivos de texto
- Threads (concurrencia)





# Manual de Usuario – GameZone Pro

## Inicio de Sesión
El sistema permite:
- Registro de usuario
- Inicio de sesión

Cada usuario tiene su propio XP y progreso.



## Menú Principal
Desde aquí puedes acceder a:

- Tienda
- Álbum
- Eventos
- Gamificación
- Reportes
- Información del estudiante



## Tienda
Permite:
- Ver catálogo
- Agregar juegos al carrito
- Comprar juegos
- Ver historial

Cada compra:
- Reduce stock
- Se guarda en archivo


<img width="864" height="646" alt="image" src="https://github.com/user-attachments/assets/07869a64-f56d-410e-a490-3065c5a47520" />

## Álbum
Permite:
- Agregar cartas
- Ver cartas
- Intercambiar cartas
- Filtrar cartas

Las cartas tienen:
- Tipo
- Rareza
- Estadísticas

<img width="1049" height="759" alt="image" src="https://github.com/user-attachments/assets/f545847e-929e-49cb-b63a-69ba6c4bcac0" />



## Eventos
Permite:
- Ver torneos
- Inscribirse
- Comprar tickets

Incluye:
- Cola de espera
- Simulación con Threads
- Venta automática

  <img width="1071" height="729" alt="image" src="https://github.com/user-attachments/assets/329367b3-9f59-4c89-9732-b76a39ab261e" />




## Gamificación
Muestra:
- XP acumulado
- Nivel
- Logros
- Ranking

El XP se gana por:
- Login
- Compras
- Cartas
- Torneos

  <img width="886" height="674" alt="image" src="https://github.com/user-attachments/assets/e461e777-76fd-4e14-af06-488b369bb3b5" />




## Reportes
Permite generar:
- Inventario
- Ventas
- Álbum
- Torneos

Se abren automáticamente en el navegador.

<img width="428" height="351" alt="image" src="https://github.com/user-attachments/assets/1084ebdd-7e39-4ea5-abfe-45acb788cb4e" />
<img width="1878" height="853" alt="image" src="https://github.com/user-attachments/assets/b75c8625-e75e-4195-a585-61af6eb97599" />





## Estudiante
Muestra información del desarrollador del sistema.

# Manual Técnico – GameZone Pro

## Arquitectura del Sistema

El proyecto está organizado en paquetes:

- `modelo`: clases principales del dominio
- `gui`: interfaces gráficas
- `estructuras`: estructuras de datos personalizadas
- `logica`: control de sesión y persistencia

<img width="472" height="439" alt="image" src="https://github.com/user-attachments/assets/638fd31c-7096-424f-bacd-33e737fab73f" />



## Clases Principales

### Usuario
Representa al usuario del sistema.

**Atributos:**
- nombre
- carne
- xp
- nivel

**Métodos:**
- otorgarXP()
- getNivel()
- getRango()



### Juego
Representa un videojuego.

**Atributos:**
- código
- nombre
- precio
- stock

**Métodos:**
- reducirStock()



### Carta
Representa una carta del álbum.

**Atributos:**
- tipo
- rareza
- ataque
- defensa
- ps


### Compra
Representa una compra realizada.

**Atributos:**
- lista de items
- total


## Estructuras de Datos

### ListaSimple
Lista enlazada implementada manualmente.

### Cola
Implementada desde cero para eventos.

### MallaOrtogonal
Matriz enlazada utilizada para el álbum.



## Concurrencia

Uso de Threads para:
- Simular taquillas
- Procesar tickets

Se utiliza:
- `synchronized`
- `Thread.sleep()`


## Persistencia

El sistema utiliza archivos de texto para almacenamiento permanente.


## Reportes

Generación de HTML con estilos CSS embebidos.

Se utilizan:
- FileWriter
- Desktop.browse()

# Manual Técnico – GameZone Pro

## Arquitectura del Sistema

El proyecto está organizado en paquetes:

- `modelo`: clases principales del dominio
- `gui`: interfaces gráficas
- `estructuras`: estructuras de datos personalizadas
- `logica`: control de sesión y persistencia


## Clases Principales

### Usuario
Representa al usuario del sistema.

**Atributos:**
- nombre
- carne
- xp
- nivel

**Métodos:**
- otorgarXP()
- getNivel()
- getRango()



### Juego
Representa un videojuego.

**Atributos:**
- código
- nombre
- precio
- stock

**Métodos:**
- reducirStock()



### Carta
Representa una carta del álbum.

**Atributos:**
- tipo
- rareza
- ataque
- defensa
- ps



### Compra
Representa una compra realizada.

**Atributos:**
- lista de items
- total



## Estructuras de Datos

### ListaSimple
Lista enlazada implementada manualmente.

### Cola
Implementada desde cero para eventos.

### MallaOrtogonal
Matriz enlazada utilizada para el álbum.


## Concurrencia

Uso de Threads para:
- Simular taquillas
- Procesar tickets

Se utiliza:
- `synchronized`
- `Thread.sleep()`


## Persistencia

El sistema utiliza archivos de texto para almacenamiento permanente.


## Reportes

Generación de HTML con estilos CSS embebidos.

Se utilizan:
- FileWriter
- Desktop.browse()

  # Diagrama de Clases

## Clases principales

- Usuario
- Juego
- Carta
- Compra
- ItemCarrito

## Estructuras

- ListaSimple → NodoSimple
- Cola → NodoCola
- MallaOrtogonal → NodoMatriz

## Relaciones

- Compra contiene ItemCarrito
- ItemCarrito contiene Juego
- MallaOrtogonal contiene Carta

- <img width="1849" height="902" alt="image" src="https://github.com/user-attachments/assets/a848c42a-c0fd-4c73-877a-093599dcdaee" />
