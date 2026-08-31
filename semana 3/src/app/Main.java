package app;

import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Lista general de pedidos que fueron despachados.
        ArrayList<Pedido> historialEntregas = new ArrayList<>();

        System.out.println("==================================");
        System.out.println("        SISTEMA SPEEDFAST");
        System.out.println("==================================");


        /*
         * CASO 1: Pedido de comida.
         * Asignación automática y despacho.
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
        comida.reservar();

        System.out.println("\nAsignando repartidor automáticamente...");
        comida.asignarRepartidor();

        System.out.println(
                "Tiempo estimado de entrega: "
                        + comida.calcularTiempoEntrega()
                        + " minutos."
        );

        System.out.println("\nDespachando pedido...");
        comida.despachar();

        // Se agrega al historial general porque fue despachado.
        historialEntregas.add(comida);

        comida.verHistorial();


        /*
         * CASO 2: Pedido de encomienda.
         * Asignación manual y despacho.
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
        encomienda.reservar();

        System.out.println("\nAsignando repartidor manualmente...");
        encomienda.asignarRepartidor("Daniel - Camioneta");

        System.out.println(
                "Tiempo estimado de entrega: "
                        + encomienda.calcularTiempoEntrega()
                        + " minutos."
        );

        System.out.println("\nDespachando pedido...");
        encomienda.despachar();

        // Se agrega al historial general porque fue despachado.
        historialEntregas.add(encomienda);

        encomienda.verHistorial();


        /*
         * CASO 3: Pedido express.
         * Asignación automática y cancelación.
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
        express.reservar();

        System.out.println("\nAsignando repartidor automáticamente...");
        express.asignarRepartidor();

        System.out.println(
                "Tiempo estimado de entrega: "
                        + express.calcularTiempoEntrega()
                        + " minutos."
        );

        System.out.println("\nEl cliente solicita cancelar el pedido.");
        express.cancelar();

        express.verHistorial();


        /*
         * HISTORIAL GENERAL DE ENTREGAS
         */

        System.out.println("\n==================================");
        System.out.println(" HISTORIAL DE ENTREGAS REALIZADAS");
        System.out.println("==================================");

        for (Pedido pedido : historialEntregas) {

            pedido.mostrarResumen();

            System.out.println("----------------------------------");
        }


        System.out.println("\n==================================");
        System.out.println("       FIN DE LA SIMULACION");
        System.out.println("==================================");
    }
}