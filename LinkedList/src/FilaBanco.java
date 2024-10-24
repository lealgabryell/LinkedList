import java.util.HashSet;
import java.util.NoSuchElementException;

public class FilaBanco implements FilaBancoInterface {
    Node head;
    Node tail;
    int size;

    public FilaBanco(Node head, Node tail, int size) {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void addLast(Aluno aluno) {
        Node newNode = new Node(aluno);
        if (isEmpty()) {
            this.head = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
        }
        this.tail = newNode;
        size += 1;
    }

    @Override
    public String popFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Aluno alunoRemovido = this.head.aluno;

        this.head = this.head.next;
        if (this.head != null) {
            this.head.prev = null;
        }
        size -= 1;
        return alunoRemovido.getNome() + " foi atendido. O proximo da fila é: " + head.next.aluno.getNome();
    }

    @Override
    public void print() {
        if (isEmpty()) {
            System.out.println("A fila está vazia.");
            return;
        }

        Node aux = this.head;

        while (aux != null) {
            System.out.println(aux.aluno.getNome());
            aux = aux.next;
        }
    }

    @Override
    public void printReverse() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node aux = this.head;
        Node temp = null;
        while (aux != null) {
            temp = aux.next;
            aux.next = aux.prev; // null
            aux.prev = temp; // pablo = next
            aux = temp; // pablo
        }
        this.head = this.tail;
        this.tail = temp;
        print();
    }

    @Override
    public void popLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (this.head.next == null) {
            this.head = null;
            this.tail = null;
        } else {
            this.tail = this.tail.prev;
            this.tail.next = null;
        }
        size -= 1;
    }

    @Override
    public void pop(Aluno aluno) {
        if (isEmpty()) {
            System.out.println("Ta vazia corno");
            return;
        }
        Node aux = this.head;
        for (int i = 0; i <= this.size - 1; i++) {
            if (aux.aluno != null && aux.aluno.equals(aluno)) {
                if (aux.next == null) {
                    popLast();
                    break;
                }
                if (aux == this.head) {
                    popFirst();
                    break;
                }
                aux.next.prev = aux.prev;
                aux.prev.next = aux.next;
                size -= 1;
                break;
            }
            aux = aux.next;
        }
    }

    @Override
    public void removeDuplicates() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node aux = this.head;
        HashSet<Aluno> hash = new HashSet<>();
        while (aux != null) {
            if (hash.contains(aux.aluno)) {
                pop(aux.aluno);
            } else {
                hash.add(aux.aluno);
            }
            aux = aux.next;
        }
    }

    @Override
    public Aluno getByIndex(int index) {
        if (isEmpty()) {
            System.out.println("Ta vazia corno!");
        }
        Node aux = this.head;
        for (int i = 0; i < index; i++) {
            aux = aux.next;
        }
        return aux.aluno;
    }

    @Override
    public boolean isPalindromo(FilaBanco fila) {
        boolean palindromo = true;

        if (isEmpty()) {
            palindromo = false;
            return palindromo;
        }

        for (int i = 0, j = this.size - 1; i < j; i++, j--) {
            if (fila.getByIndex(i) != fila.getByIndex(j)) {
                palindromo = false;
                break;
            }
        }
        return palindromo;
    }

    @Override
    public void concatenar(FilaBanco fila1, FilaBanco fila2){
        FilaBanco filaConcatenada = new FilaBanco(null, null, 0);
        Node aux1 = fila1.head;
        Node aux2 = fila2.head;

        for(int i = 0; i < fila1.size; i++){
            filaConcatenada.addLast(aux1.aluno);
            aux1 = aux1.next;
        }
        for(int i = 0; i < fila2.size; i++){
            filaConcatenada.addLast(aux2.aluno);
            aux2 = aux2.next;
        }
        filaConcatenada.print();
    }
}
