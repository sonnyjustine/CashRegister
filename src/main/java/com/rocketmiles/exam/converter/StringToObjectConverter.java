package com.rocketmiles.exam.converter;

/**
 * Blueprint for converting String to object type T
 *
 * @author sonny
 * @param <T> Resulting object type
 */
interface StringToObjectConverter<T>
{
    T convert(String s);
}

