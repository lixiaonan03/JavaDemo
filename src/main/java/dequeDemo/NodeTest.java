package dequeDemo;

import static java.lang.Long.MAX_VALUE;

/**
  *  @author lixiaonan
  *  功能描述: 反转链接demo的
  *  时 间： 2022/9/9 10:28
  */
public class NodeTest {


    public static void main(String[] args) {
        ListNode nodeSta = new ListNode(1);    //创建首节点
        ListNode nextNode;                     //声明一个变量用来在移动过程中指向当前节点
        nextNode = nodeSta;                      //指向首节点

        //创建链表
        for(int i=2;i<11;i++){
            ListNode node = new ListNode(i);  //生成新的节点
            nextNode.next=node;               //把心节点连起来
            nextNode=nextNode.next;           //当前节点往后移动
        } //当for循环完成之后 nextNode指向最后一个节点，

        nextNode=nodeSta;//重新赋值让它指向首节点

        print(reverseList(nextNode));
//        print(nextNode);
    }

    //打印输出方法
    static void print(ListNode listNoed){
        //创建链表节点
        while(listNoed!=null){
            System.out.println("节点:"+listNoed.val);
            listNoed=listNoed.next;
        }
        System.out.println();
    }

    /**
     * 递归  反转链表的
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        //最小子问题，结束
        if (head == null || head.next == null) {
            return head;
        }
        System.out.println("开始拆==="+head.val);
        //递的过程，一次次拆解问题
        ListNode newHead = reverseList(head.next);
        System.out.println("开始反转的==="+head.val);
        //归的过程，反转
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 迭代的 反转链表的
     * @param head
     * @return
     */
    public static ListNode reverseListIterative(ListNode head) {
        ListNode prev = null; //前指针节点
        ListNode curr = head; //当前指针节点
        ListNode next;
        //每次循环，都将当前节点指向它前面的节点
        //然后当前节点和前节点后移
        while (curr != null) {
            //临时节点，暂存当前节点的下一节点，用于后移
            //因为接下来curr.next进行赋值后将会断链
            next= curr.next;
            //将当前节点指向它前面的节点
            curr.next = prev;
            //前指针后移
            prev = curr;
            //当前指针后移
            curr = next;
        }
        return prev;
    }

    /**
     * 定义链表
     * @param <E>
     */
    static class ListNode<E>{                //类名 ：Java类就是一种自定义的数据结构
        E val;                        //数据 ：节点数据
        ListNode<E> next;             //对象 ：引用下一个节点对象。在Java中没有指针的概念，Java中的引用和C语言的指针类似

        ListNode(E val){              //构造方法 ：构造方法和类名相同
            this.val=val;             //把接收的参数赋值给当前类的val变量
        }
    }
}



