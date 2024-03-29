package suan;

import java.util.HashMap;

/**
  *  @author lixiaonan
  *  功能描述: 自己通过算法实现lru
  *  时 间： 2022/9/13 19:13
  */
public class LruCache1 {
    private class Node{
        Node prev;
        Node next;
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> hs = new HashMap<>();
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1); //队尾的

    public LruCache1(int capacity) {
        this.capacity = capacity;
        tail.prev = head;
        head.next = tail;
    }
    public int get(int key) {
        if( !hs.containsKey(key)) {
            //key找不到
            return -1;
        }

        // remove current
        Node current = hs.get(key);
        current.prev.next = current.next;
        current.next.prev = current.prev;

        // 每次get，使用次数+1，最近使用，放于尾部
        move_to_tail(current);

        return hs.get(key).value;
    }

    public void set(int key, int value) {			//数据放入缓存
        // get 这个方法会把key挪到最末端，因此，不需要再调用 move_to_tail
        if (get(key) != -1) {
            hs.get(key).value = value;
            return;
        }

        if (hs.size() == capacity) {		//超出缓存上限
            hs.remove(head.next.key);		//删除头部数据
            head.next = head.next.next;
            head.next.prev = head;
        }

        Node insert = new Node(key, value);		//新建节点
        hs.put(key, insert);
        move_to_tail(insert);					//放于尾部
    }

    /**
     * 移动数据至尾部
     * @param current
     */
    private void move_to_tail(Node current) {
        current.prev = tail.prev;
        tail.prev = current;
        current.prev.next = current;
        current.next = tail;
    }
}
