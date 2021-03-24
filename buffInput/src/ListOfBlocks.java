public class ListOfBlocks {
    private Node head;
    private int size;
    private int capacity;

    public ListOfBlocks(int capacity){
        head = null;
        size = 0;
        this.capacity = capacity;
    }

    public class Node{
        private byte[] arrOfBytes;
        public Node next;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int getSize(){
        return size;
    }

    public boolean add(byte [] bytes){
        if (size < capacity) {
            Node n = new Node();
            n.arrOfBytes = bytes;
            n.next = head;
            head = n;
            size++;
            return true;
        } else{
            return false;
        }
    }

    public byte[] remove(){
        if (head != null) {
            Node n = head;
            head = head.next;
            size --;
            return n.arrOfBytes;
        }
        else {
            return null;
        }
    }
}