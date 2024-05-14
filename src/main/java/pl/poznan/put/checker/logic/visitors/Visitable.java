package pl.poznan.put.checker.logic.visitors;

public interface Visitable {
    void accept(Visitor visitor);
}
