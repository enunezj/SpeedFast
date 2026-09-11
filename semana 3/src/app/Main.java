package app;

import concurrencia.Repartidor;
import concurrencia.ZonaDeCarga;
import controller.ControladorDeEnvios;
import model.EstadoPedido;
import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println(
                "======================================"
        );
        System.out.println(
                " SISTEMA SPEEDFAST"
        );
        System.out.println(
                "======================================\n"
        );

        /*
         * Se conserva una prueba breve
         * del polimorfismo trabajado anteriormente.
         */
        probarPolimorfismo();

        ControladorDeEnvios controlador =
                new ControladorDeEnvios();

        /*
         * Recurso compartido por
         * todos los repartidores.
         */
        ZonaDeCarga zonaDeCarga =
                new ZonaDeCarga();

        /*
         * Se crean cinco pedidos.
         */
        List<Pedido> pedidos =
                crearPedidos();

        System.out.println();

        /*
         * Los cinco pedidos llegan
         * a la misma zona de carga.
         */
        for (Pedido pedido : pedidos) {
            zonaDeCarga.agregarPedido(
                    pedido
            );
        }

        /*
         * Los tres repartidores comparten
         * la misma instancia de ZonaDeCarga.
         */
        Repartidor repartidor1 =
                new Repartidor(
                        "Carlos",
                        zonaDeCarga
                );

        Repartidor repartidor2 =
                new Repartidor(
                        "Sofia",
                        zonaDeCarga
                );

        Repartidor repartidor3 =
                new Repartidor(
                        "Pedro",
                        zonaDeCarga
                );

        /*
         * Cada repartidor se ejecuta
         * mediante un hilo independiente.
         */
        Thread hilo1 =
                new Thread(
                        repartidor1,
                        "Hilo-Carlos"
                );

        Thread hilo2 =
                new Thread(
                        repartidor2,
                        "Hilo-Sofia"
                );

        Thread hilo3 =
                new Thread(
                        repartidor3,
                        "Hilo-Pedro"
                );

        System.out.println(
                "\n======================================"
        );
        System.out.println(
                " INICIO DE LAS ENTREGAS"
        );
        System.out.println(
                "======================================"
        );

        /*
         * Los tres hilos comienzan
         * su ejecución concurrente.
         */
        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {

            /*
             * Main espera a que los tres
             * hilos finalicen su trabajo.
             */
            hilo1.join();
            hilo2.join();
            hilo3.join();

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println(
                    "El proceso principal fue interrumpido."
            );

            return;
        }

        /*
         * El controlador registra únicamente
         * los pedidos efectivamente entregados.
         */
        controlador.registrarEntregas(
                pedidos
        );

        if (zonaDeCarga.estaVacia()) {

            System.out.println(
                    "\n[Zona de carga vacía]"
            );
        }

        controlador.mostrarHistorialEntregas();

        /*
         * Se comprueba el resultado antes
         * de mostrar el mensaje final.
         */
        if (todosLosPedidosEntregados(pedidos)) {

            System.out.println(
                    "\n======================================"
            );
            System.out.println(
                    "Todos los pedidos han sido entregados correctamente"
            );
            System.out.println(
                    "======================================"
            );

        } else {

            System.out.println(
                    "\n======================================"
            );
            System.out.println(
                    "Existen pedidos que no pudieron ser entregados."
            );
            System.out.println(
                    "======================================"
            );
        }
    }

    private static List<Pedido> crearPedidos() {

        List<Pedido> pedidos =
                new ArrayList<>();

        pedidos.add(
                new PedidoComida(
                        1,
                        "Santiago Centro"
                )
        );

        pedidos.add(
                new PedidoEncomienda(
                        2,
                        "Providencia"
                )
        );

        pedidos.add(
                new PedidoExpress(
                        3,
                        "Ñuñoa"
                )
        );

        pedidos.add(
                new PedidoComida(
                        4,
                        "Recoleta"
                )
        );

        pedidos.add(
                new PedidoEncomienda(
                        5,
                        "Las Condes"
                )
        );

        return pedidos;
    }

    private static boolean todosLosPedidosEntregados(
            List<Pedido> pedidos) {

        for (Pedido pedido : pedidos) {

            if (pedido.getEstado()
                    != EstadoPedido.ENTREGADO) {

                return false;
            }
        }

        return true;
    }

    private static void probarPolimorfismo() {

        System.out.println(
                "--- VALIDACIÓN DE POLIMORFISMO ---"
        );

        /*
         * Ambas referencias son Pedidos,
         * aunque los objetos reales son diferentes.
         */
        Pedido pruebaAutomatica =
                new PedidoComida(
                        900,
                        "Cliente prueba",
                        "Prueba automática",
                        2
                );

        Pedido pruebaManual =
                new PedidoEncomienda(
                        901,
                        "Cliente prueba",
                        "Prueba manual",
                        2
                );

        System.out.println(
                "\nAsignación automática:"
        );

        pruebaAutomatica.reservar();

        pruebaAutomatica
                .asignarRepartidor();

        pruebaAutomatica
                .mostrarResumen();

        System.out.println(
                "\nAsignación manual:"
        );

        pruebaManual.reservar();

        pruebaManual
                .asignarRepartidor(
                        "Carlos"
                );

        pruebaManual
                .mostrarResumen(true);

        pruebaManual.cancelar();

        pruebaManual.verHistorial();

        System.out.println(
                "-----------------------------------\n"
        );
    }
}