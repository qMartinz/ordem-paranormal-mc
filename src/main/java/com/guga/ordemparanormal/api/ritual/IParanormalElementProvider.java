package com.guga.ordemparanormal.api.ritual;

import java.util.function.Supplier;

public interface IParanormalElementProvider {
    ParanormalElement getParanormalElement();
    default Supplier<ParanormalElement> getParanormalElementSupplier() {
        return this::getParanormalElement;
    }
}
