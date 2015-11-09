/**
 * Created by dhyey on 23-09-2015.
 */
public class LinkedList {
    Node head;

    //constructor to create an empty LL
    public LinkedList(){
        head = null;
    }

    //to insert a node there are two options:
    //a. is to add at the end
    //b. is to add at the beginning
    public void append(int data){
        //if there is no element in LL
        if(head == null) {
            head = new Node(data);
            return;
        }
        //else traverse to the end of the LL and append
        Node temp = head;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = new Node(data);
    }

    public void add(int data){
        //if ll is empty then return
        if(head == null){
            head = new Node(data);
            return;
        }
        //else make a new node and make it head
        Node new_node = new Node(data);
        new_node.next = head;
        head = new_node;
        return;
    }

    //we can also add after a given node
    public void addAfter(Node given, int data){
        if(given==null)
            return;
        Node new_node = new Node(data);
        new_node.next = given.next;
        given.next = new_node;
        return;
    }

    //delete a node with a given value from the LL
    public void delete(int key){
        //LL already empty so cannot delete
        if(head==null)
            return;

        //if head is the one we want to delete
        if(head.data == key){
            head = head.next;
            return;
        }
        Node temp = head;
        Node prev = head;
        while(temp!=null && temp.data!=key){
            prev = temp;
            temp = temp.next;
        }
        if(temp == null) {
            System.out.println("In delete: cannot find the node with key:" + key);
            return;
        }
        prev.next = temp.next;
        return;
    }

    //given pointer to node delete that node
    //the node cannot be end of ll
    public void deleteThis(Node given){
        given.data = given.next.data;
        given.next = given.next.next;
    }

    public String toString(){
        Node temp = head;
        String s = "";
        while(temp!=null){
            s = s + "  " + temp.data;
            temp = temp.next;
        }
        return s;
    }

    //find the length of ll in recursive manner
    public static int length_recursive(Node head){
        if(head == null)
            return 0;
        return 1 + length_recursive(head.next);
    }

    //find the length of ll in iterative manner
    public static int length_iterative(Node head){
        int count = 0;
        Node temp = head;
        while(temp!=null){
            temp = temp.next;
            count++;
        }
        return count;
    }

    //search ll for element x in recursive manner
    public static boolean search_recursive(Node head, int x){
        if(head==null)
            return false;
        if(head.data==x)
            return true;
        return search_recursive(head.next, x);
    }

    //search ll for element x in iterative manner
    public static boolean search_iterative(Node head, int x){
        if(head==null)
            return false;
        Node temp = head;
        while(head!=null){
            if(head.data==x)
                return true;
            head = head.next;
        }
        return false;
    }

    //given two values x and y, swap the nodes for given values
    //without swapping the data
    public void swapNodes(int x, int y){
        if(x==y)    return;
        //search for x and y
        Node currx, curry, prevx=null, prevy=null;
        currx = head;
        curry = head;
        while(currx!=null && currx.data!=x){
            prevx = currx;
            currx = currx.next;
        }
        while(curry!=null && curry.data!=y){
            prevy = curry;
            curry = curry.next;
        }
        //if either not present
        if(currx==null || curry==null){
            return;
        }
        if(prevx !=null)
            prevx.next = curry;
        else
            head = curry;

        if(prevy!=null)
            prevy.next = currx;
        else
            head = currx;

        Node temp = curry.next;
        curry.next = currx.next;
        currx.next = temp;
        return;
    }

    //get nth node in ll
    public int getNth(int n){
        Node temp = head;
        if(temp == null)
            return Integer.MIN_VALUE;
        for(int i=1; i<n; i++){
            if(temp == null)
                return Integer.MIN_VALUE;
            temp = temp.next;
        }
        return temp.data;
    }

    //print the middle of given list
    public void printMiddle(){
        if(head == null) {
            System.out.println("Empty list no middle element found");
            return;
        }
        Node slow = head;
        Node fast = head;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        System.out.println("Middle element value is "+slow.data);
    }

    //print the nth element from the end of ll
    public void printNthFromEnd(int n){
        if(head == null){
            System.out.println("Empty list");
            return;
        }
        Node slow = head;
        Node fast = head;
        for(int i=1; i<=n; i++){
            if(fast==null){
                System.out.println("Not enough elements in the list");
                return;
            }
            fast = fast.next;
        }
        while(fast!=null){
            fast = fast.next;
            slow = slow.next;
        }
        System.out.println("The nth from end node has value "+slow.data);
    }

    //count the occurence of x in the ll
    public int countOccurrence(int x){
        if(head==null)
            return Integer.MIN_VALUE;
        Node temp = head;
        int count = 0;
        while(temp!=null){
            if(temp.data == x)
                count++;
            temp = temp.next;
        }
        return count;
    }

    //reverse the ll
    public void reverseList(){
        Node prev = null, curr = head, next = null;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    //detect a loop in the linked list
    public boolean detectLoop(){
        Node slow = head, fast = head;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    //find out whether the given ll is palindrome or not
    public boolean isPalindrome(){
        Node slow = head, fast = head;
        Node prev_slow = null;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            prev_slow = slow;
            slow = slow.next;
        }
        Node mid = null;
        if(fast!=null){
            mid = slow;
            slow = slow.next;
        }
        LinkedList x = new LinkedList();
        x.head = slow;
        x.reverseList();
        boolean result = compareList(x);
        x.reverseList();
        if(mid != null){
            prev_slow.next = mid;
            mid.next = slow;
        }
        else{
            prev_slow.next = slow;
        }
        return result;
    }

    //compare whether the two linked list have the same data or not
    public boolean compareList(LinkedList l){
        Node temp1 = head;
        Node temp2 = l.head;
        while(temp1!=null && temp2!=null){
            if(temp1.data == temp2.data){
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
            else{
                return false;
            }
        }
        return true;
    }

    //insert in a linkedlist in sorted manner
    public void insertSorted(int x){
        Node temp = head;
        Node new_node = new Node(x);
        if(head == null){
            head = new_node;
            return;
        }
        if(x <= head.data){
            new_node.next = head;
            head = new_node;
            return;
        }

        while(temp.next!=null && temp.next.data<x){
            temp = temp.next;
        }
        new_node.next = temp.next;
        temp.next = new_node;
    }

    //find the intersection of two linked lists
    public int getIntersection(LinkedList ll){
        Node temp1 = head;
        Node temp2 = ll.head;
        int len1 = length_iterative(temp1);
        int len2 = length_iterative(temp2);
        int count = (int)Math.abs(len1 - len2);
        if(len1 > len2)
            return _getintersection(count, temp1, temp2);
        else
            return _getintersection(count, temp2, temp1);
    }

    //internal function used in above function
    private int _getintersection(int count, Node temp1, Node temp2){
        for(int i=0; i<count; i++){
            temp1 = temp1.next;
        }
        while(temp1!=null && temp2!=null){
            if(temp1 == temp2){
                return temp1.data;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return -1;
    }

    //remove duplicates from a sorted ll
    public void removeDuplicates_sorted(){
        if(head == null)
            return;
        Node temp = head;
        while(temp.next!=null){
            if(temp.data == temp.next.data){
                temp.next = temp.next.next;
            }
            else{
                temp = temp.next;
            }
        }
    }

    //remove duplicates from an unsorted ll
    public void removeDuplicates_unsorted(){
        if(head == null)
            return;
        Node temp1 = head, temp2 = null;
        while(temp1!=null && temp1.next!=null){
            temp2 = temp1;
            while(temp2.next!=null){
                if(temp1.data == temp2.next.data){
                    temp2.next = temp2.next.next;
                }
                else{
                    temp2 = temp2.next;
                }
            }
            temp1 = temp1.next;
        }
    }

    //perform pairwise swap of data
    public void pairwiseSwap(){
        if(head==null && head.next==null)
            return;
        Node temp = head;
        while(temp!=null && temp.next!=null){
            int t = temp.data;
            temp.data = temp.next.data;
            temp.next.data = t;
            temp = temp.next.next;
        }
    }

    //move the last element of given ll to front
    public void moveLast(){
        if(head==null)
            return;
        Node temp = head;
        Node prev = null;
        while(temp.next!=null){
            prev = temp;
            temp = temp.next;
        }
        prev.next = null;
        temp.next = head;
        head = temp;
    }

    //find the intersection of two sorted ll return a new ll wo changing the given lls
    public LinkedList sortedIntersection(LinkedList ll){
        LinkedList intersect = new LinkedList();
        //intersect.append(-1);
        //System.out.println(intersect);
        Node temp1 = head;
        Node temp2 = ll.head;
        if(temp1==null || temp2==null)
            return intersect;
        while(temp1!=null && temp2!=null){
            if(temp1.data == temp2.data){
                intersect.append(temp1.data);
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
            else if(temp1.data > temp2.data){
                temp2 = temp2.next;
            }
            else{
                temp1 = temp1.next;
            }
        }
        return intersect;
    }

    //delete alternate nodes
    public void deleteAlternate(){
        if(head == null)
            return;
        Node temp = head;
        while(temp!=null && temp.next!=null){
            temp.next = temp.next.next;
            if(temp!=null)
                temp = temp.next;
        }
    }

    //alternating split of ll and print both new ll
    public void alternatingSplit(){
        LinkedList a = new LinkedList();
        LinkedList b = new LinkedList();
        Node temp = head;
        while(temp!=null){
            a.append(temp.data);
            temp = temp.next;
            if(temp!=null){
                b.append(temp.data);
                temp = temp.next;
            }
        }
        System.out.println(a);
        System.out.println(b);
    }

    //merge two sorted ll and return a new sorted ll
    public LinkedList mergeSorted(LinkedList ll){
        Node a = head;
        Node b = ll.head;
        LinkedList sorted = new LinkedList();
        while(a!=null && b!=null){
            if(a.data <= b.data){
                sorted.append(a.data);
                a = a.next;
            }
            else{
                sorted.append(b.data);
                b = b.next;
            }
        }
        while(a!=null){
            sorted.append(a.data);
            a = a.next;
        }
        while(b!=null){
            sorted.append(b.data);
            b = b.next;
        }
        return sorted;
    }

    //identical linked lists
    public boolean identicalLists(LinkedList ll){
        Node a = head;
        Node b = ll.head;
        while(true){
            if(a==null && b==null)
                return true;
            else if(a==null && b!=null)
                return false;
            else if(a!=null && b==null)
                return false;
            else if(a.data != b.data)
                return false;
            a = a.next;
            b = b.next;
        }
    }

    //reverse ll in a grp of size k
    public Node reverseSwap(Node h, int k){
        Node curr = h;
        Node prev = null, next = null;
        int count = 0;
        while(curr!=null && count<k){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }
        if(next!=null)
            h.next = reverseSwap(next, k);
        return prev;
    }

    //merge sort for ll
    public void mergeSort(Node head){
        Node temp = head;
        if(temp==null || temp.next==null)
            return;
        LinkedList a = new LinkedList();
        LinkedList b = new LinkedList();
        Split(temp, a, b);
        a.mergeSort(a.head);
        b.mergeSort(b.head);
        LinkedList sorted = a.mergeSorted(b);
        this.head = sorted.head;
    }

    public void Split(Node source, LinkedList a, LinkedList b){
        Node fast, slow;
        if(source == null||source.next==null){
            a.head = source;
            b.head = null;
            return;
        }

        slow = source;
        fast = source.next;
        while(fast!=null){
            fast = fast.next;
            if(fast!=null){
                slow = slow.next;
                fast = fast.next;
            }
        }
        a.head = source;
        b.head = slow.next;
        slow.next = null;
    }

    //reverse alternate k nodes
    public Node reverse_alternate(Node head, int k){
        Node curr = head;
        Node next = null, prev = null;
        int count = 0;
        while(curr!=null && count<k){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }
        if(head!=null)
            head.next = curr;

        count = 0;
        while(count < k-1 && curr !=null){
            curr = curr.next;
            count++;
        }
        if(curr!=null){
            curr.next = this.reverse_alternate(curr.next, k);
        }
        return prev;
    }

    //delete all the nodes with higher values on the right
    public void deleteLesserNodes(){
        this.reverseList();
        this._deleteLEsserNodes();
        this.reverseList();
    }

    public void _deleteLEsserNodes(){
        Node curr = this.head;
        Node maxnode = this.head;
        Node temp;
        while(curr!=null && curr.next!=null){
            if(curr.next.data < maxnode.data){
                curr.next = curr.next.next;
            }
            else{
                curr = curr.next;
                maxnode = curr;
            }
        }
    }

    //segregate odd and even numbers
    public void segregateEvenOdd(){
        Node tail = this.head;
        Node curr = this.head;
        Node temp = this.head;
        while(tail.next!=null){
            tail = tail.next;
        }
        Node end = tail;
        while(curr.data%2 !=0 && curr!=tail){
            end.next = curr;
            curr = curr.next;
            end.next.next = null;
            end = end.next;
        }

        if(curr.data%2==0){
            this.head = curr;
            while(curr !=tail){
                if(curr.data%2==0){
                    temp = curr;
                    curr = curr.next;
                }
                else{
                    temp.next = curr.next;
                    curr.next = null;
                    end.next = curr;
                    end = curr;
                    curr = temp.next;
                }
            }
        }
    }

    //remove loop in ll
    public void removeLoop(){
        Node slow = this.head;
        Node fast = this.head.next;

        while(fast!=null && fast.next !=null){
            if(slow == fast){
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        if(slow == fast){
            System.out.println("Loop detected");
            slow = this.head;
            while(slow != fast.next){
                slow = slow.next;
                fast = fast.next;
            }
            System.out.println(fast.data);
            fast.next = null;
        }
    }

    //add two lists
    public LinkedList addTwoLists(LinkedList blist){
        LinkedList ans = new LinkedList();
        Node a = this.head;
        Node b = blist.head;
        int sum = 0, carry = 0;
        while(a!=null || b!=null){
            sum = (a!=null ? a.data : 0) + (b!= null ? b.data : 0) + carry;
            carry = sum>=10 ? 1 : 0;
            sum = sum % 10;
            ans.append(sum);
            if(a!=null) a = a.next;
            if(b!=null) b = b.next;
        }
        if(carry > 0)
            ans.append(carry);
        return ans;
    }

    //get the union of two linked list
    public LinkedList union(LinkedList ll){
        Node a = this.head;
        Node b = ll.head;
        LinkedList union = new LinkedList();
        while(a !=null){
            union.append(a.data);
            a = a.next;
        }
        while(b!=null){
            Node temp = b;
            a = this.head;
            boolean flag = true;
            while(a!=null){
                if(a.data == temp.data){
                    flag = false;
                    break;
                }
                a = a.next;
            }
            if(flag){
                union.append(temp.data);
            }
            b = b.next;
        }
        return union;
    }

    //get the intersection set of two linked lists
    public LinkedList intersection(LinkedList ll){
        Node a = this.head;
        Node b = ll.head;
        LinkedList intersect = new LinkedList();
        while(a != null){
            Node temp = b;
            while(temp != null){
                if(a.data == temp.data){
                    intersect.append(a.data);
                    break;
                }
                temp = temp.next;
            }
            a = a.next;
        }
        return intersect;
    }

    //find the triplets with given sum given b is in ascending order and c is in descending order
    public void findTriplet(LinkedList ll2, LinkedList ll3, int target_sum){
        Node a = this.head;
        Node b = ll2.head;
        Node c = ll3.head;
        while(a!=null){
            b = ll2.head;
            c = ll3.head;
            while(b!=null && c!=null){
                int sum = a.data + b.data + c.data;
                if (sum == target_sum){
                    System.out.println("Triplet found :"+a.data+" "+b.data+" "+c.data);
                    return;
                }
                else if(sum < target_sum){
                    b = b.next;
                }
                else{
                    c = c.next;
                }
                a = a.next;
            }
        }
    }

    //rotate the linkedlist by k nodes
    public void rotate(int k){
        if(k==0)
            return;
        Node temp = this.head;
        int count = 1;
        while(count < k && temp!=null){
            temp = temp.next;
            count++;
        }
        if(temp == null){
            return;
        }
        Node kth = temp;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = head;
        head = kth.next;
        kth.next = null;
    }
}


