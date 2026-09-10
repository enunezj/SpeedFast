package app;

import controller.ControladorDeEnvios;
import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;

public class Main {

    public static void main(String[] args) {

        ControladorDeEnvios controlador =
                new ControladorDeEnvios();


        System.out.println("==================================");
        System.out.println("        SISTEMA SPEEDFAST");
        System.out.println("==================================");


        /*
         * CASO 1
         * Pedido de comida con asignación automática.
         */

        System.out.println("\n----------------------------------");
        System.out.println("CASO 1 - PEDIDO DE COMIDA");
        System.out.println("----------------------------------");

        Pedido comida = new PedidoComida(
                101,
                "Ana Torres",
                "Av. Central 123",
                4
        );

        comida.mostrarResumen();

        System.out.println("\nReservando pedido...");
        controlador.reservarPedido(comida);

        System.out.println(
                "\nAsignando repartidor automáticamente..."
        );
        controlador.asignarRepartidor(comida);

        controlador.mostrarTiempoEstimado(comida);

        System.out.println("\nDespachando pedido...");
        controlador.despacharPedido(comida);

        controlador.mostrarHistorialPedido(comida);


        /*
         * CASO 2
         * Pedido de encomienda con asignación manual.
         */

        System.out.println("\n==================================");
        System.out.println("CASO 2 - PEDIDO DE ENCOMIENDA");
        System.out.println("==================================");

        Pedido encomienda = new PedidoEncomienda(
                102,
                "Luis Martinez",
                "Los Carrera 550",
                5
        );

        encomienda.mostrarResumen();

        System.out.println("\nReservando pedido...");
        controlador.reservarPedido(encomienda);

        System.out.println(
                "\nAsignando repartidor manualmente..."
        );

        controlador.asignarRepartidor(
                encomienda,
                "Daniel"
        );

        controlador.mostrarTiempoEstimado(encomienda);

        System.out.println("\nDespachando pedido...");
        controlador.despacharPedido(encomienda);

        controlador.mostrarHistorialPedido(encomienda);


        /*
         * CASO 3
         * Pedido express con asignación automática
         * y posterior cancelación.
         */

        System.out.println("\n==================================");
        System.out.println("CASO 3 - PEDIDO EXPRESS");
        System.out.println("==================================");

        Pedido express = new PedidoExpress(
                103,
                "Maria Silva",
                "Providencia 450",
                3
        );

        express.mostrarResumen(true);

        System.out.println("\nReservando pedido...");
        controlador.reservarPedido(express);

        System.out.println(
                "\nAsignando repartidor automáticamente..."
        );
        controlador.asignarRepartidor(express);

        controlador.mostrarTiempoEstimado(express);

        System.out.println(
                "\nEl cliente solicita cancelar el pedido."
        );
        controlador.cancelarPedido(express);

        controlador.mostrarHistorialPedido(express);


        /*
         * El historial general ahora es responsabilidad
         * del controlador.
         */

        controlador.mostrarHistorialEntregas();


        System.out.println("\n==================================");
        System.out.println("       FIN DE LA SIMULACION");
        System.out.println("==================================");
    }
}