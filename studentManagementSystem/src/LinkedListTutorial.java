
public class LinkedListTutorial {
    public static int fun(int n){
        if(n > 100) return n - 5;
        return fun(fun(n+11));
    }

    public static void main(String[] args) {
        System.out.println(fun(45));
    }
}

