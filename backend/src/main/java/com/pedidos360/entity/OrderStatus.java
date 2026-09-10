package com.pedidos360.entity;
public enum OrderStatus {
    PENDING("Pendiente"),
    CONFIRMED("Confirmado"),
    SHIPPED("Enviado"),
    DELIVERED("Entregado"),
    CANCELLED("Cancelado");
    private final String displayName;
    OrderStatus(String displayName) { this.displayName = displayName; }
    public String getDisplayName() { return displayName; }
}
