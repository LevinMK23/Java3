import org.junit.Assert;

import java.util.ArrayList;

class Task01<T> {

    T[] arr;

    public Task01(T[] arr) {
        this.arr = arr;
    }

    public void SwapElements(int _Index1, int _Index2) {
        if (arr.length > _Index1 && arr.length > _Index2) {
            T tmp = arr[_Index1];
            arr[_Index1] = arr[_Index2];
            arr[_Index2] = tmp;
        }
    }

    public void ShowElements() {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].toString());
        }
    }

    public ArrayList<T> GetArrayList() {
        ArrayList<T> arrayList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            arrayList.add(arr[i]);
        }
        return arrayList;
    }
}

class Task03{

    Box<Orange> b1,b3;
    Box<Apple> b2;


    public void init() {
        b1 = new Box<>();
        b2 = new Box<>();
        b3 = new Box<>();
        for (int i = 0; i < 10; i++) {
            b1.addFruit(new Orange());
        }
        for (int i = 0; i < 10; i++) {
            b2.addFruit(new Apple());
        }
        for (int i = 0; i < 10; i++) {
            b3.addFruit(new Orange());
        }
    }



    public void testDrop() {
        try{
            b1.dropFruits(b3);
            Assert.assertEquals(30., b3.getWeight(), 0.0001);//вес корзины b1 = 30
            Assert.assertEquals(0., b1.getWeight(), 0.0001);//вес корзины b3 = 0, т.. мы все от туда пересыпали
            Assert.assertEquals(10., b2.getWeight(), 0.0001);//вес корзины b2 =15, т.к. там лежит 10 яблок  с весом 1,5 ед каждое

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
class TestClass {

    private static void TestTask01AndTask02() {
        Task01<String> task01 = new Task01<>(new String[]{"one", "two"});
        task01.ShowElements();
        task01.SwapElements(0, 1);
        task01.ShowElements();
        ArrayList<String> arrayList = task01.GetArrayList();
        System.out.println("содержимое ArrayList:");
        for (String str :
                arrayList) {
            System.out.println(str);
        }
    }


    public static void main(String[] args) {
        TestTask01AndTask02();
        Task03 task03 = new Task03();
        task03.init();
        task03.testCompare();
        task03.testDrop();



    }
}




