package com.kpi.korolova.shop.util;

import java.util.Objects;

public class GenericPair<K, V> {
    private K p1;
    private K p2;

    public GenericPair() {
    }

    public GenericPair(K p1, K p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public K getP1() {
        return p1;
    }

    public void setP1(K p1) {
        this.p1 = p1;
    }

    public K getP2() {
        return p2;
    }

    public void setP2(K p2) {
        this.p2 = p2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericPair<?, ?> that = (GenericPair<?, ?>) o;
        return Objects.equals(p1, that.p1) &&
                Objects.equals(p2, that.p2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1, p2);
    }

    @Override
    public String toString() {
        return "GenericPair{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }
}
