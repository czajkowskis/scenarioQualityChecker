package pl.poznan.put.checker.rest;

/***
 * Class representing single value response made by the API
 */
public record Response<T>(String name, T value) {}