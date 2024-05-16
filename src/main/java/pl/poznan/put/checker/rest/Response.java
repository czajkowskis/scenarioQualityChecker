package pl.poznan.put.checker.rest;

public record Response<T>(String name, T value) {}