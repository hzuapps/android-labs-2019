public class A{
int num ;
public void print(){
System.out.println(num) ;
}
}
public class B{
public static void main(String[] args){
A a = new A() ;
a.num = 10 ; //��������
a.print() ; //���÷���
}
}