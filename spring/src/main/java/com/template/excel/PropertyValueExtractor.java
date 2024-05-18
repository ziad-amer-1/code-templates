package com.template.excel;

@FunctionalInterface
public interface PropertyValueExtractor<T> {
    Object[] extract(T item);
}
