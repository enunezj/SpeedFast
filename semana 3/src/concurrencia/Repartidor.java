package concurrencia;

import model.Pedido;

public class Repartidor implements Runnable {

    private final String nombre;
    private final ZonaDeCarga zonaDeCarga;


    public Repartidor(
            String nombre,
            ZonaDeCarga zonaDeCarga) {

        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }


    @Override
    public void run() {

        Pedido pedido;

        while ((pedido =
                zonaDeCarga.retirarPedido()) != null) {

            boolean entregaCompletada =
                    procesarEntrega(pedido);

            /*
             * Si el hilo fue interrumpido,
             * se termina completamente su ejecución.
             */
            if (!entregaCompletada) {

                return;
            }
        }
    }


    private boolean procesarEntrega(
            Pedido pedido) {

        String hiloInfo =
                "["
                        + Thread.currentThread().getName()
                        + " - "
                        + nombre
                        + "]";


        System.out.println(
                "\n"
                        + hiloInfo
                        + " Retiró pedido #"
                        + pedido.getId()
        );


        /*
         * Asignación polimórfica.
         */
        pedido.asignarRepartidor(
                nombre
        );


        /*
         * El pedido pasa de PENDIENTE
         * a EN_REPARTO.
         */
        pedido.despachar();


        System.out.println(
                hiloInfo
                        + " Estado actual: "
                        + pedido.getEstado()
        );


        System.out.println(
                hiloInfo
                        + " En camino a entregar pedido #"
                        + pedido.getId()
                        + " en "
                        + pedido.getDireccionEntrega()
                        + "..."
        );


        try {

            Thread.sleep(2000);

        } catch (InterruptedException e) {

            /*
             * Se restaura el estado de interrupción.
             */
            Thread.currentThread().interrupt();

            System.err.println(
                    hiloInfo
                            + " Interrupción durante la entrega "
                            + "del pedido #"
                            + pedido.getId()
            );

            return false;
        }


        pedido.setEstado(
                "ENTREGADO"
        );


        System.out.println(
                hiloInfo
                        + " Pedido #"
                        + pedido.getId()
                        + " ENTREGADO exitosamente."
        );


        return true;
    }
}