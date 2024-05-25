package org.example.labo08;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CafeteriaController {
    private float precioPapas = 1.25F;
    private float precioCarne = 2.25F;
    private float precioPollo = 1.75F;
    private float precioVegetales = 0.75F;

    @FXML
    private Label precioTotal;
    @FXML
    private Label subtotalPapas;
    @FXML
    private Label subtotalCarne;
    @FXML
    private Label subtotalPollo;
    @FXML
    private Label subtotalVegetales;
    @FXML
    private Spinner cantidadPapas;
    @FXML
    private Spinner cantidadCarne;
    @FXML
    private Spinner cantidadPollo;
    @FXML
    private Spinner cantidadVegetales;
    @FXML
    private RadioButton empleadoCheck;
    @FXML
    private RadioButton estudianteCheck;
    @FXML
    private RadioButton efectivoCheck;
    @FXML
    private RadioButton tarjetaCheck;
    @FXML
    private TextField inputNombre;
    @FXML
    private Button botonComprar;
    @FXML
    private Button botonLimpiar;

    @FXML
    protected void onClickBotonComprar() {
        if(validarNombre()&&validarArticulos()&&validarTipoCliente()&&validarMetodoPago()){
            // Confirmar compra
            float subtotal = Float.parseFloat(subtotalPapas.getText().substring(1))+Float.parseFloat(subtotalVegetales.getText().substring(1))+Float.parseFloat(subtotalCarne.getText().substring(1))+Float.parseFloat(subtotalPollo.getText().substring(1));
            float descuento = 0.00F;
            String metodoPago;
            if(empleadoCheck.isSelected()&&!estudianteCheck.isSelected()){
                descuento = (float) (subtotal*0.1);
            }
            if(efectivoCheck.isSelected()){
                metodoPago = "Efectivo";
            } else {
                metodoPago = "Tarjeta";
            }
            Alert confirmacionCompra = new Alert(Alert.AlertType.INFORMATION);
            confirmacionCompra.setHeaderText(null);
            confirmacionCompra.setTitle("Cafetería UCA");
            confirmacionCompra.setContentText("Bienvenido "+inputNombre.getText()+"\nSubtotal: $"+(subtotal)+"\nDescuento: $"+(descuento)+"\nTotal: $"+(subtotal-descuento)+"\nForma de pago: "+metodoPago+"\nGracias por su compra!");
            confirmacionCompra.showAndWait();
        }
    }
    @FXML
    protected boolean validarNombre(){
        // Validar nombre
        if(inputNombre.getText().equals("")||inputNombre.getText().length()<=6) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText(null);
            alerta.setTitle("Cafetería UCA");
            alerta.setContentText("Debe digitar su nombre.");
            alerta.showAndWait();
            return false;
        } else return true;
    }
    protected boolean validarArticulos(){
        // Validar que tenga artículos seleccionados
        if((Float.parseFloat(subtotalPapas.getText().substring(1))==0.00)&&(Float.parseFloat(subtotalCarne.getText().substring(1))==0.00)&&(Float.parseFloat(subtotalVegetales.getText().substring(1))==0.00)&&(Float.parseFloat(subtotalPollo.getText().substring(1))==0.00)) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Cafetería UCA");
            alerta.setContentText("Debe seleccionar al menos un artículo para comprar.");
            alerta.showAndWait();
            return false;
        } else return true;
    }
    @FXML
    protected boolean validarTipoCliente(){
        // Validar selección de tipo de cliente
        if(!estudianteCheck.isSelected()&&!empleadoCheck.isSelected()){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Cafetería UCA");
            alerta.setContentText("Debe seccionar un tipo de cliente.");
            alerta.showAndWait();
            return false;
        } else return true;
    }
    @FXML
    protected boolean validarMetodoPago(){
        // Validar selección de método de pago
        if(!efectivoCheck.isSelected()&&!tarjetaCheck.isSelected()){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Cafetería UCA");
            alerta.setContentText("Debe seccionar un método de pago.");
            alerta.showAndWait();
            return false;
        } else return true;
    }
    @FXML
    protected void onClickBotonLimpiar() {
        inputNombre.setText("");
        empleadoCheck.setSelected(false);
        estudianteCheck.setSelected(false);
        efectivoCheck.setSelected(false);
        tarjetaCheck.setSelected(false);
        cantidadPollo.getValueFactory().setValue(0);
        cantidadPapas.getValueFactory().setValue(0);
        cantidadVegetales.getValueFactory().setValue(0);
        cantidadCarne.getValueFactory().setValue(0);
        subtotalPapas.setText("$0.00");
        subtotalVegetales.setText("$0.00");
        subtotalCarne.setText("$0.00");
        subtotalPollo.setText("$0.00");
        precioTotal.setText("Total: $0.00");
    }
    @FXML
    protected void onPolloAumento(){
        int cantPollo = (int) cantidadPollo.getValue();
        float totalPollo = precioPollo*cantPollo;
        subtotalPollo.setText("$"+totalPollo);
        actualizarTotalTemporal();
    }
    @FXML
    protected void onCarneAumento(){
        int cantCarne = (int) cantidadCarne.getValue();
        float totalCarne = precioCarne*cantCarne;
        subtotalCarne.setText("$"+totalCarne);
        actualizarTotalTemporal();
    }
    @FXML
    protected void onVegetalesAumento(){
        int cantVegetales = (int) cantidadVegetales.getValue();
        float totalVegetales = precioVegetales*cantVegetales;
        subtotalVegetales.setText("$"+totalVegetales);
        actualizarTotalTemporal();
    }
    @FXML
    protected void onPapasAumento(){
        int cantPapas = (int) cantidadPapas.getValue();
        float totalPapas = precioPapas*cantPapas;
        subtotalPapas.setText("$"+totalPapas);
        actualizarTotalTemporal();
    }
    @FXML
    protected void actualizarTotalTemporal(){
        float sumaTotal = 0.00F;
        int cantPollo = (int) cantidadPollo.getValue();
        float totalPollo = precioPollo*cantPollo;
        int cantCarne = (int) cantidadCarne.getValue();
        float totalCarne = precioCarne*cantCarne;
        int cantVegetales = (int) cantidadVegetales.getValue();
        float totalVegetales = precioVegetales*cantVegetales;
        int cantPapas = (int) cantidadPapas.getValue();
        float totalPapas = precioPapas*cantPapas;
        sumaTotal = totalPollo+totalCarne+totalVegetales+totalPapas;
        if((empleadoCheck.isSelected())&&(!estudianteCheck.isSelected())){
            sumaTotal *= 0.9;
            precioTotal.setText("Total: $"+sumaTotal+" (10% descuento aplicado)");
        } else {
            precioTotal.setText("Total: $"+sumaTotal);
        }

    }
    @FXML
    protected void onEmpleadoCheck(){
        actualizarTotalTemporal();
        if(estudianteCheck.isSelected()){
            estudianteCheck.setSelected(false);
            actualizarTotalTemporal();
        }
    }
    @FXML
    protected void onEstudianteCheck(){
        actualizarTotalTemporal();
        if(empleadoCheck.isSelected()){
            empleadoCheck.setSelected(false);
            actualizarTotalTemporal();
        }
    }
    @FXML
    protected void onEfectivoCheck(){
        actualizarTotalTemporal();
        if(tarjetaCheck.isSelected()){
            tarjetaCheck.setSelected(false);
        }
    }
    @FXML
    protected void onTarjetaCheck(){
        actualizarTotalTemporal();
        if(efectivoCheck.isSelected()){
            efectivoCheck.setSelected(false);
        }
    }
}