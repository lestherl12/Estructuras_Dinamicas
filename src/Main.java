import java.util.Scanner;

class DoublyLinkedList<T> {

    static class Node<T> {
        T data;
        Node<T> prev, next;

        public Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    private Node<T> head, tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void insertAtBeginning(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void insertAtEnd(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void traverseForward() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void traverseBackward() {
        Node<T> current = tail;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.prev;
        }
        System.out.println();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node<T> searchByValue(T value) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(value)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public Node<T> searchByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Indice fuera de los limites");
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public void deleteFromPosition(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Indice invalido");
        }

        if (isEmpty()) {
            throw new IllegalStateException("lista vacia");
        }

        if (size == 1) {
            head = tail = null;
        } else if (index == 0) {
            head = head.next;
            head.prev = null;
        } else if (index == size - 1) {
            tail = tail.prev;
            tail.next = null;
        } else {
            Node<T> nodeToDelete = searchByIndex(index);
            nodeToDelete.prev.next = nodeToDelete.next;
            nodeToDelete.next.prev = nodeToDelete.prev;
        }
        size--;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoublyLinkedList<Integer> doublyList = new DoublyLinkedList<>();

        while (true) {
            System.out.println("Lista Doblemente Enlazada");
            System.out.println("1. Insertar al inicio");
            System.out.println("2. Insertar al final");
            System.out.println("3. Recorrer hacia adelante");
            System.out.println("4. Recorrer hacia atrás");
            System.out.println("5. Mostrar tamaño de la lista");
            System.out.println("6. Mostrar si la lista está vacía");
            System.out.println("7. Buscar elemento por valor");
            System.out.println("8. Buscar elemento por índice");
            System.out.println("9. Borrar un Elemento");
            System.out.println("10. Salir");

            System.out.print("Ingrese la opción: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el valor a insertar al inicio: ");
                    int valueToInsertAtBeginning = scanner.nextInt();
                    doublyList.insertAtBeginning(valueToInsertAtBeginning);
                    break;
                case 2:
                    System.out.print("Ingrese el valor a insertar al final: ");
                    int valueToInsertAtEnd = scanner.nextInt();
                    doublyList.insertAtEnd(valueToInsertAtEnd);
                    break;
                case 3:
                    System.out.println("Recorriendo hacia adelante:");
                    doublyList.traverseForward();
                    break;
                case 4:
                    System.out.println("Recorriendo hacia atrás:");
                    doublyList.traverseBackward();
                    break;
                case 5:
                    System.out.println("Tamaño de la lista: " + doublyList.size());
                    break;
                case 6:
                    System.out.println("La lista está vacía: " + doublyList.isEmpty());
                    break;
                case 7:
                    System.out.print("Ingrese el valor a buscar: ");
                    int valueToSearch = scanner.nextInt();
                    DoublyLinkedList.Node<Integer> foundNodeByValue = doublyList.searchByValue(valueToSearch);
                    if (foundNodeByValue != null) {
                        System.out.println("Elemento encontrado en el nodo: " + foundNodeByValue.data);
                    } else {
                        System.out.println("Elemento no encontrado");
                    }
                    break;
                case 8:
                    System.out.print("Ingrese el índice a buscar: ");
                    int indexToSearch = scanner.nextInt();
                    try {
                        DoublyLinkedList.Node<Integer> foundNodeByIndex = doublyList.searchByIndex(indexToSearch);
                        System.out.println("Elemento encontrado en el nodo: " + foundNodeByIndex.data);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Índice no válido");
                    }
                    break;
                case 9:
                    System.out.print("Ingrese el índice del elemento a borrar: ");
                    int indexToDelete = scanner.nextInt();
                    try {
                        doublyList.deleteFromPosition(indexToDelete);
                        System.out.println("Elemento borrado exitosamente");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Índice no válido");
                    }
                    break;
                case 10:
                    System.out.println("Saliendo del programa");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida, por favor ingrese una opción válida");
            }
        }
    }
}
