import java.util.List;
import java.util.stream.Collectors;

public class ForTests {

    //протестируйте функцию
    public List<Integer> sort(List<Integer> data) {
        return data.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public int binarySearch(int [] data, int key, int l, int r){
        int mid = (l + r) / 2;
        if(data[mid] == key) return mid;
        // чего то не хватает, протестируйте и найдите баг в функции
        if(data[mid] > key) return binarySearch(data, key, l, mid);
        return binarySearch(data, key, mid, r);
    }

    //придумайте 2 функции и протестируйте их
}
