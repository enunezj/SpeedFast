# SpeedFast - Desarrollo Orientado a Objetos II

## Autor del proyecto

* **Nombre completo:** Emilio Nuñez Jara
* **Sección:** PRY2203
* **Carrera:** Analista Programador Computacional
* **Sede:** Campus Virtual

---

## Descripción general del sistema

Proyecto desarrollado en Java para la empresa de repartos **SpeedFast**.

Esta versión continúa el trabajo realizado en actividades anteriores y agrega una simulación de **programación concurrente**, donde varios repartidores trabajan en paralelo sobre una **zona de carga compartida**.

El objetivo principal es coordinar el retiro y entrega de pedidos utilizando `Thread`, `Runnable` y mecanismos de sincronización mediante `synchronized`, evitando que un mismo pedido pueda ser retirado por más de un repartidor.

El proyecto aplica conceptos de Programación Orientada a Objetos y concurrencia, entre ellos:

* Encapsulamiento.
* Herencia.
* Polimorfismo.
* Clases abstractas.
* Interfaces.
* Sobrecarga de métodos.
* Sobrescritura de métodos.
* Enumeraciones (`enum`).
* Colecciones `List` y `ArrayList`.
* Implementación de `Runnable`.
* Creación y ejecución de hilos mediante `Thread`.
* Sincronización mediante `synchronized`.
* Espera de finalización mediante `join()`.
* Simulación de tiempos de entrega mediante `Thread.sleep()`.

Esta versión corresponde a la actividad de la **Semana 5**, enfocada en la sincronización de procesos concurrentes para el caso SpeedFast.

---

## Funcionalidades del sistema

### Gestión de pedidos

El sistema permite trabajar con tres tipos de pedidos:

* `PedidoComida`.
* `PedidoEncomienda`.
* `PedidoExpress`.

Cada pedido posee un identificador, dirección de entrega y estado.

Además, se conservan funcionalidades desarrolladas anteriormente:

* Reservar pedidos.
* Asignar repartidores automáticamente.
* Asignar repartidores manualmente.
* Calcular tiempos estimados de entrega.
* Despachar pedidos.
* Cancelar pedidos.
* Consultar el historial individual.
* Mostrar información resumida del pedido.

### Estados del pedido

El enum `EstadoPedido` define los estados:

```java
PENDIENTE
EN_REPARTO
ENTREGADO
```

Durante la simulación concurrente, el flujo principal es:

```plaintext
PENDIENTE
   ↓
Retiro seguro desde ZonaDeCarga
   ↓
Asignación del repartidor
   ↓
despachar()
   ↓
EN_REPARTO
   ↓
Thread.sleep()
   ↓
ENTREGADO
```

### Zona de carga compartida

La clase `ZonaDeCarga` representa el recurso compartido por los repartidores.

Internamente utiliza:

```java
List<Pedido>
```

Los métodos de acceso al recurso compartido se encuentran protegidos con `synchronized`:

```java
public synchronized void agregarPedido(Pedido p)
public synchronized Pedido retirarPedido()
public synchronized boolean estaVacia()
```

Cuando un pedido es retirado, se elimina de la colección dentro del método sincronizado. De esta manera, otro hilo no puede obtener el mismo pedido y se evita el retiro duplicado.

### Repartidores concurrentes

La clase `Repartidor` implementa:

```java
Runnable
```

Cada repartidor trabaja de manera independiente, pero comparte la misma instancia de `ZonaDeCarga`.

En la simulación se utilizan tres repartidores:

* Carlos.
* Sofia.
* Pedro.

Cada repartidor:

1. Retira un pedido de `ZonaDeCarga`.
2. Ejecuta la asignación manual correspondiente mediante polimorfismo.
3. Despacha el pedido, cambiándolo a `EN_REPARTO`.
4. Simula el tiempo de entrega con `Thread.sleep(2000)`.
5. Cambia el estado a `ENTREGADO`.
6. Continúa retirando pedidos mientras existan pedidos disponibles.

### Control de interrupciones

Si un hilo es interrumpido durante `Thread.sleep()`, el sistema restaura su estado de interrupción mediante:

```java
Thread.currentThread().interrupt();
```

En ese caso, la entrega interrumpida no se marca como `ENTREGADO` y ese repartidor finaliza su ejecución.

### Historial general de entregas

La clase `ControladorDeEnvios` mantiene un historial general de los pedidos que finalizaron correctamente con estado `ENTREGADO`.

Esta responsabilidad se mantiene separada de `Main`, favoreciendo la organización y mantenibilidad del sistema.

---

## Estructura general del proyecto

La actividad actual se encuentra en la carpeta:

```plaintext
semana 5/
```

Su estructura es:

```plaintext
semana 5/
└── src/
    │
    ├── app/
    │   └── Main.java
    │
    ├── concurrencia/
    │   ├── Repartidor.java
    │   └── ZonaDeCarga.java
    │
    ├── controller/
    │   └── ControladorDeEnvios.java
    │
    ├── interfaces/
    │   ├── Cancelable.java
    │   ├── Despachable.java
    │   └── Rastreable.java
    │
    └── model/
        ├── EstadoPedido.java
        ├── Pedido.java
        ├── PedidoComida.java
        ├── PedidoEncomienda.java
        └── PedidoExpress.java
```

La carpeta `semana 3/` conserva la versión correspondiente a la actividad anterior del proyecto.

---

## Clases principales

### Pedido

`Pedido` es la clase abstracta base del sistema.

Sus principales atributos son:

```plaintext
id
direccionEntrega
estado
cliente
distanciaKm
repartidor
reservado
cancelado
historial
```

Implementa las interfaces:

```plaintext
Despachable
Cancelable
Rastreable
```

Además, dispone de dos formas de asignar un repartidor:

```java
asignarRepartidor()
asignarRepartidor(String nombre)
```

La primera representa la asignación automática y la segunda la asignación manual.

También mantiene dos versiones de:

```java
mostrarResumen()
mostrarResumen(boolean mostrarTiempo)
```

El método:

```java
setEstado(String nuevoEstado)
```

convierte el texto recibido al enum `EstadoPedido` y actualiza el estado del pedido.

### PedidoComida

Hereda de `Pedido` e implementa su propia lógica para:

```java
asignarRepartidor()
asignarRepartidor(String nombre)
calcularTiempoEntrega()
```

### PedidoEncomienda

Hereda de `Pedido` y sobrescribe las dos formas de asignación de repartidor y su regla de cálculo del tiempo estimado.

### PedidoExpress

Hereda de `Pedido` y sobrescribe las dos formas de asignación y su cálculo del tiempo estimado.

### EstadoPedido

Enum utilizado para controlar los estados válidos:

```java
PENDIENTE
EN_REPARTO
ENTREGADO
```

### ZonaDeCarga

Representa el recurso compartido de la simulación concurrente.

Almacena:

```java
List<Pedido> pedidosPendientes
```

Sus operaciones principales son:

```java
agregarPedido(Pedido p)
retirarPedido()
estaVacia()
```

Las tres están sincronizadas para controlar el acceso concurrente.

### Repartidor

Implementa:

```java
Runnable
```

Posee los atributos:

```plaintext
nombre
zonaDeCarga
```

Su método `run()` retira pedidos mientras existan elementos disponibles.

La lógica individual de entrega se encuentra en:

```java
procesarEntrega(Pedido pedido)
```

### ControladorDeEnvios

Mantiene:

```java
List<Pedido> historialEntregas
```

y se encarga de:

```java
registrarEntregas(List<Pedido> pedidos)
mostrarHistorialEntregas()
```

### Main

La clase `Main` coordina la simulación.

Sus responsabilidades principales son:

* Instanciar `ZonaDeCarga`.
* Crear cinco pedidos.
* Agregar los cinco pedidos a la zona compartida.
* Crear tres objetos `Repartidor`.
* Crear tres objetos `Thread`.
* Iniciar los tres hilos mediante `start()`.
* Esperar su finalización mediante `join()`.
* Registrar las entregas realizadas.
* Comprobar que todos los pedidos terminen en `ENTREGADO`.
* Mostrar el mensaje final solicitado.

Además, conserva una prueba breve de polimorfismo para comprobar la asignación automática y manual mediante referencias de tipo `Pedido`.

---

## Interfaces

### Despachable

Define:

```java
void despachar();
```

`Pedido` implementa este comportamiento y cambia el estado a `EN_REPARTO` cuando las condiciones permiten el despacho.

### Cancelable

Define:

```java
void cancelar();
```

Permite cancelar un pedido cuando su estado lo permite.

### Rastreable

Define:

```java
void verHistorial();
```

Permite consultar los eventos registrados durante el ciclo de vida de cada pedido.

---

## Programación concurrente aplicada

### Runnable

`Repartidor` implementa `Runnable`:

```java
public class Repartidor implements Runnable
```

Esto permite ejecutar cada repartidor mediante un hilo independiente.

### Thread

En `Main` se crean tres hilos:

```java
Thread hilo1 = new Thread(repartidor1, "Hilo-Carlos");
Thread hilo2 = new Thread(repartidor2, "Hilo-Sofia");
Thread hilo3 = new Thread(repartidor3, "Hilo-Pedro");
```

Los tres comienzan su ejecución mediante:

```java
hilo1.start();
hilo2.start();
hilo3.start();
```

### Sincronización

Los tres repartidores comparten una única instancia de `ZonaDeCarga`.

El acceso a la colección de pedidos se protege mediante:

```java
synchronized
```

Esto permite que solo un hilo a la vez ejecute la operación crítica de retiro.

El pedido seleccionado se elimina de `pedidosPendientes` antes de liberar el bloqueo, evitando que otro repartidor pueda retirar el mismo objeto.

### Thread.sleep()

Cada repartidor simula el tiempo de entrega mediante:

```java
Thread.sleep(2000);
```

Mientras un repartidor espera, los demás hilos pueden continuar procesando otros pedidos.

### Thread.join()

Después de iniciar los hilos, `Main` utiliza:

```java
hilo1.join();
hilo2.join();
hilo3.join();
```

Esto impide mostrar el resultado final antes de que los tres repartidores hayan terminado.

### Manejo de interrupciones

Si `Thread.sleep()` es interrumpido, se restaura el estado de interrupción mediante:

```java
Thread.currentThread().interrupt();
```

y el hilo afectado finaliza su ejecución.

---

## Conceptos de Programación Orientada a Objetos aplicados

### Encapsulamiento

Los atributos principales del sistema se mantienen encapsulados y se accede a ellos mediante operaciones controladas.

### Herencia

La jerarquía principal es:

```plaintext
Pedido
├── PedidoComida
├── PedidoEncomienda
└── PedidoExpress
```

### Abstracción

`Pedido` está declarado como clase abstracta y define:

```java
asignarRepartidor()
asignarRepartidor(String nombre)
calcularTiempoEntrega()
```

Las subclases deben proporcionar sus propias implementaciones.

### Polimorfismo

El sistema utiliza referencias de tipo:

```java
Pedido
```

aunque los objetos reales puedan ser:

```plaintext
PedidoComida
PedidoEncomienda
PedidoExpress
```

Durante la simulación concurrente, `Repartidor` ejecuta:

```java
pedido.asignarRepartidor(nombre);
```

Java selecciona la implementación correspondiente al tipo real del objeto.

Además, `Main` conserva una prueba para comparar la asignación automática y manual mediante referencias de tipo `Pedido`.

### Sobrescritura

Las subclases sobrescriben:

```java
asignarRepartidor()
asignarRepartidor(String nombre)
calcularTiempoEntrega()
```

### Sobrecarga

El sistema mantiene sobrecarga en:

```java
asignarRepartidor()
asignarRepartidor(String nombre)
```

y:

```java
mostrarResumen()
mostrarResumen(boolean mostrarTiempo)
```

También existen constructores sobrecargados en los tipos de pedido.

---

## Control de condiciones de carrera

El recurso compartido principal es:

```java
List<Pedido> pedidosPendientes
```

perteneciente a `ZonaDeCarga`.

El retiro se realiza mediante:

```java
public synchronized Pedido retirarPedido()
```

El pedido seleccionado se elimina dentro de la sección sincronizada antes de ser devuelto.

De esta forma, cuando otro hilo ingresa al método, ese pedido ya no se encuentra disponible.

Esto evita el retiro duplicado y garantiza que cada pedido sea atendido por un único repartidor.

---

## Simulación del sistema

La simulación utiliza cinco pedidos:

```plaintext
Pedido #1 → Santiago Centro
Pedido #2 → Providencia
Pedido #3 → Ñuñoa
Pedido #4 → Recoleta
Pedido #5 → Las Condes
```

y tres repartidores:

```plaintext
Carlos
Sofia
Pedro
```

Los tres trabajan sobre la misma `ZonaDeCarga`.

El orden de retiro y entrega puede variar entre ejecuciones debido a la naturaleza concurrente del programa.

Al finalizar, `Main` comprueba que todos los pedidos tengan estado:

```java
EstadoPedido.ENTREGADO
```

Si todos fueron procesados correctamente, se muestra:

```plaintext
Todos los pedidos han sido entregados correctamente
```

---

## Escalabilidad, reutilización y mantenibilidad

Las responsabilidades principales quedan distribuidas de la siguiente forma:

```plaintext
Pedido                → datos y comportamiento del pedido
Subclases de Pedido   → reglas específicas por tipo
ZonaDeCarga           → protección del recurso compartido
Repartidor            → procesamiento concurrente
ControladorDeEnvios   → historial general de entregas
Main                  → coordinación de la simulación
```

La jerarquía permite incorporar nuevos tipos de pedidos y la zona compartida permite agregar más repartidores sin modificar la lógica básica de retiro seguro.

---

## Instrucciones para clonar y ejecutar el proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/enunezj/SpeedFast.git
```

### 2. Abrir el proyecto

Abrir la carpeta del repositorio con **IntelliJ IDEA**.

La actividad actual se encuentra en:

```plaintext
semana 5
```

### 3. Ejecutar el sistema

Ejecutar:

```plaintext
semana 5/src/app/Main.java
```

o ejecutar la clase:

```java
app.Main
```

---

## Resultado esperado

Durante la ejecución se observará:

```plaintext
1. Prueba breve de polimorfismo.
2. Inicialización de ZonaDeCarga.
3. Registro de cinco pedidos.
4. Inicio de tres hilos concurrentes.
5. Retiro seguro de pedidos.
6. Cambio de estado a EN_REPARTO.
7. Simulación del tiempo de entrega.
8. Cambio a ENTREGADO.
9. Visualización del historial general.
10. Confirmación de que todos los pedidos fueron entregados.
```

Debido a la ejecución concurrente, Carlos, Sofia y Pedro pueden aparecer en diferente orden entre una ejecución y otra.

---

## Tecnologías utilizadas

* Java.
* IntelliJ IDEA.
* Git.
* GitHub.
* Programación orientada a objetos.
* Programación concurrente mediante `Thread` y `Runnable`.

---

## Repositorio GitHub

https://github.com/enunezj/SpeedFast

---

## Fecha de actualización

11/09/2026
