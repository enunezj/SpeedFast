package model;

// Representa el envío de una encomienda.
public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(
            int codigo,
            String cliente,
            String direccion,
            double distanciaKm) {

        super(codigo, cliente, direccion, distanciaKm);
    }


    // SOBRESCRITURA:
    // Las encomiendas utilizan automáticamente
    // un repartidor en camioneta.
    @Override
    public void asignarRepartidor() {

        String nombre = "Pedro - Camioneta";

        setRepartidor(nombre);

        registrarEvento(
                "Repartidor automático asignado: " + nombre
        );

        System.out.println(
                "Repartidor automático asignado: " + nombre
        );
    }


    // Regla específica para encomiendas.
    @Override
    public int calcularTiempoEntrega() {

        return 30 + (int) (getDistanciaKm() * 4);
    }
}