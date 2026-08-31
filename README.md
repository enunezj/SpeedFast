# SpeedFast - Desarrollo Orientado a Objetos II

## Autor del proyecto

* **Nombre completo:** Emilio Nuñez Jara
* **Sección:** PRY2203
* **Carrera:** Analista Programador Computacional
* **Sede:** Campus Virtual

---

## Descripción general del sistema

Proyecto desarrollado en Java para la empresa de repartos **SpeedFast**.

El sistema permite gestionar distintos tipos de pedidos:

* Pedidos de comida
* Encomiendas
* Pedidos express

El proyecto aplica conceptos de Programación Orientada a Objetos, tales como:

* Encapsulamiento
* Herencia
* Polimorfismo
* Clases abstractas
* Interfaces
* Sobrecarga de métodos
* Sobrescritura de métodos
* Uso de colecciones dinámicas `ArrayList`

El objetivo del sistema es simular la gestión de pedidos de SpeedFast, permitiendo reservar pedidos, asignar repartidores, calcular tiempos estimados de entrega, despachar o cancelar pedidos y visualizar su historial.

Esta versión corresponde a la actividad de la **Semana 3**, titulada **“Diseñando un sistema orientado a objetos con clases abstractas, polimorfismo e interfaces”**.

---

## Funcionalidades del sistema

### Pedidos de comida

* Crear pedidos de comida.
* Reservar pedidos.
* Asignar repartidor automáticamente.
* Asignar repartidor manualmente.
* Calcular el tiempo estimado de entrega.
* Despachar pedidos.
* Cancelar pedidos.
* Consultar el historial.

### Pedidos de encomienda

* Crear pedidos de encomienda.
* Reservar pedidos.
* Asignar repartidor automáticamente.
* Asignar repartidor manualmente.
* Calcular el tiempo estimado de entrega.
* Despachar pedidos.
* Cancelar pedidos.
* Consultar el historial.

### Pedidos express

* Crear pedidos express.
* Reservar pedidos.
* Asignar repartidor automáticamente.
* Asignar repartidor manualmente.
* Calcular el tiempo estimado de entrega.
* Despachar pedidos.
* Cancelar pedidos.
* Consultar el historial.

### Historial de entregas

* Registrar los eventos realizados sobre cada pedido.
* Visualizar el historial individual de cada pedido.
* Almacenar pedidos despachados mediante una colección `ArrayList<Pedido>`.
* Mostrar los pedidos entregados desde una misma colección.

---

## Estructura general del proyecto

```plaintext
📁 src/
│
├── app/
│   └── Main.java
│
├── interfaces/
│   ├── Cancelable.java
│   ├── Despachable.java
│   └── Rastreable.java
│
└── model/
    ├── Pedido.java
    ├── PedidoComida.java
    ├── PedidoEncomienda.java
    └── PedidoExpress.java
```

---

## Clases principales

### Pedido

Representa la clase base abstracta del sistema.

Contiene información común para todos los tipos de pedidos:

* Código
* Cliente
* Dirección
* Distancia en kilómetros
* Repartidor
* Estado
* Historial

La clase `Pedido` implementa las interfaces:

* `Despachable`
* `Cancelable`
* `Rastreable`

Además, contiene comportamientos comunes como:

```java
reservar();
mostrarResumen();
asignarRepartidor(String nombre);
despachar();
cancelar();
verHistorial();
```

También declara los métodos abstractos:

```java
asignarRepartidor();
calcularTiempoEntrega();
```

Estos métodos son implementados de manera diferente por cada subclase.

### PedidoComida

Hereda de la clase `Pedido`.

Representa un pedido de comida y sobrescribe los métodos:

```java
asignarRepartidor();
calcularTiempoEntrega();
```

La asignación automática utiliza un repartidor apropiado para pedidos de comida y posee una regla específica para calcular el tiempo estimado de entrega.

### PedidoEncomienda

Hereda de la clase `Pedido`.

Representa el envío de una encomienda y sobrescribe los métodos:

```java
asignarRepartidor();
calcularTiempoEntrega();
```

Las encomiendas poseen su propia lógica de asignación de repartidor y cálculo del tiempo estimado.

### PedidoExpress

Hereda de la clase `Pedido`.

Representa una compra o pedido express.

Sobrescribe los métodos:

```java
asignarRepartidor();
calcularTiempoEntrega();
```

Este tipo de pedido utiliza una regla de entrega más rápida que los otros tipos.

---

## Interfaces

### Despachable

Define el comportamiento necesario para despachar un pedido.

Declara el método:

```java
void despachar();
```

### Cancelable

Define el comportamiento necesario para cancelar un pedido.

Declara el método:

```java
void cancelar();
```

### Rastreable

Define el comportamiento necesario para consultar el historial de un pedido.

Declara el método:

```java
void verHistorial();
```

Las tres interfaces son implementadas por la clase abstracta `Pedido`, por lo que sus comportamientos también son utilizados por las clases `PedidoComida`, `PedidoEncomienda` y `PedidoExpress`.

---

## Conceptos de Programación Orientada a Objetos aplicados

### Encapsulamiento

Los atributos principales de la clase `Pedido` se encuentran declarados como `private`.

Esto evita el acceso directo desde otras clases y permite controlar la forma en que se utiliza la información del pedido.

---

### Herencia

El sistema implementa la siguiente jerarquía:

```plaintext
Pedido
├── PedidoComida
├── PedidoEncomienda
└── PedidoExpress
```

Las tres subclases reutilizan los atributos y comportamientos definidos en `Pedido`.

Esto evita repetir código y facilita la creación de nuevos tipos de pedidos.

---

### Abstracción

La clase `Pedido` se encuentra declarada como abstracta:

```java
public abstract class Pedido
```

Esta clase concentra los atributos y comportamientos comunes de todos los pedidos.

Además, declara métodos abstractos:

```java
public abstract void asignarRepartidor();

public abstract int calcularTiempoEntrega();
```

Cada tipo de pedido debe proporcionar su propia implementación de estos métodos.

---

### Polimorfismo

El sistema permite trabajar con diferentes tipos de pedidos utilizando referencias de tipo `Pedido`.

Ejemplo:

```java
Pedido comida = new PedidoComida(...);
Pedido encomienda = new PedidoEncomienda(...);
Pedido express = new PedidoExpress(...);
```

Aunque las variables son de tipo `Pedido`, cada objeto ejecuta su propia implementación de los métodos sobrescritos.

---

### Sobrescritura de métodos

Las clases:

* `PedidoComida`
* `PedidoEncomienda`
* `PedidoExpress`

sobrescriben los métodos:

```java
asignarRepartidor();
calcularTiempoEntrega();
```

Esto permite que cada tipo de pedido tenga una lógica particular.

---

### Sobrecarga de métodos

El sistema utiliza sobrecarga mediante diferentes versiones de un mismo método.

Para la asignación de repartidores se utilizan:

```java
asignarRepartidor();
```

y:

```java
asignarRepartidor(String nombre);
```

La primera versión permite realizar una asignación automática y la segunda permite realizar una asignación manual.

También se utilizan:

```java
mostrarResumen();
```

y:

```java
mostrarResumen(boolean mostrarTiempo);
```

Esto permite mostrar la información básica del pedido o incluir también el tiempo estimado de entrega.

---

### Interfaces

Las interfaces `Despachable`, `Cancelable` y `Rastreable` permiten separar responsabilidades específicas dentro del sistema.

Cada interfaz representa una capacidad diferente:

```plaintext
Despachable → Despachar pedidos
Cancelable  → Cancelar pedidos
Rastreable  → Consultar historial
```

Esto favorece el bajo acoplamiento y permite mantener organizada la lógica del sistema.

---

### Uso de ArrayList

El sistema utiliza `ArrayList` para almacenar información dinámica.

Cada pedido posee un historial:

```java
ArrayList<String>
```

donde se registran eventos como:

* Pedido reservado.
* Repartidor asignado.
* Pedido despachado.
* Pedido cancelado.

También se utiliza una colección:

```java
ArrayList<Pedido>
```

para almacenar los pedidos que han sido despachados.

---

## Escalabilidad, reutilización y mantenibilidad

### Escalabilidad

La estructura permite agregar nuevos tipos de pedidos sin modificar significativamente las clases existentes.

Por ejemplo, podría agregarse:

```java
public class PedidoFarmacia extends Pedido
```

implementando únicamente su propia lógica para asignar repartidores y calcular el tiempo de entrega.

### Reutilización

La clase abstracta `Pedido` concentra atributos y comportamientos comunes.

Las clases `PedidoComida`, `PedidoEncomienda` y `PedidoExpress` reutilizan estos elementos mediante herencia, evitando duplicar código.

### Mantenibilidad

La separación del sistema en clases e interfaces permite que cada componente tenga una responsabilidad clara.

Las interfaces desacoplan funcionalidades como el despacho, la cancelación y el rastreo, mientras que cada subclase se encarga principalmente de las reglas específicas de su tipo de pedido.

Esto facilita realizar cambios en el sistema sin afectar innecesariamente a otros componentes.

---

## Simulación del sistema

La clase `Main` ejecuta tres casos diferentes para demostrar el funcionamiento del sistema.

### Caso 1 - Pedido de comida

```plaintext
Crear pedido
→ Reservar pedido
→ Asignar repartidor automáticamente
→ Calcular tiempo estimado
→ Despachar pedido
→ Mostrar historial
```

### Caso 2 - Pedido de encomienda

```plaintext
Crear pedido
→ Reservar pedido
→ Asignar repartidor manualmente
→ Calcular tiempo estimado
→ Despachar pedido
→ Mostrar historial
```

### Caso 3 - Pedido express

```plaintext
Crear pedido
→ Reservar pedido
→ Asignar repartidor automáticamente
→ Calcular tiempo estimado
→ Cancelar pedido
→ Mostrar historial
```

Estos casos permiten comprobar el comportamiento diferente de cada tipo de pedido.

---

## Instrucciones para clonar y ejecutar el proyecto

### 1. Clonar el repositorio desde GitHub

```bash
git clone https://github.com/enunezj/SpeedFast.git
```

### 2. Abrir el proyecto

Abrir la carpeta:

```plaintext
semana 3
```

utilizando **IntelliJ IDEA**.

### 3. Ejecutar el sistema

Ejecutar la clase principal:

```plaintext
src/app/Main.java
```

o ejecutar directamente:

```java
app.Main
```

---

## Ejecución del sistema

Al ejecutar el programa se mostrarán por consola tres casos diferentes:

```plaintext
CASO 1 - PEDIDO DE COMIDA
CASO 2 - PEDIDO DE ENCOMIENDA
CASO 3 - PEDIDO EXPRESS
```

Durante la simulación se mostrarán las reservas, asignaciones de repartidores, tiempos estimados, despachos, cancelaciones e historiales correspondientes.

Al finalizar también se visualizará el historial de entregas realizadas.

---

## Tecnologías utilizadas

* Java
* IntelliJ IDEA
* Git
* GitHub

---

## Repositorio GitHub

https://github.com/enunezj/SpeedFast

---

## Fecha de entrega

31/08/2026
