public interface FilaBancoInterface {
    public boolean isEmpty();
    public void addLast(Aluno aluno);
    public String popFirst();
    public void print();
    public void printReverse();
    public void pop(Aluno aluno);
    public void popLast();
    public void removeDuplicates();
    public boolean isPalindromo(FilaBanco fila);
    public Aluno getByIndex(int index);
    public void concatenar(FilaBanco fila1, FilaBanco fila2);
}
